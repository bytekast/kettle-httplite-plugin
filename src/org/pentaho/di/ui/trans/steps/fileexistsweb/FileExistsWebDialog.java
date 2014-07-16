package org.pentaho.di.ui.trans.steps.fileexistsweb;

import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.di.ui.spoon.dialog.HttpLiteDialog;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

import java.util.UUID;

/**
 * @author Rowell Belen
 */
public class FileExistsWebDialog extends BaseStepDialog implements StepDialogInterface {

  private final String ID = UUID.randomUUID().toString();

  private FileExistsWebDialogProxy fileExistsWebDialogProxy;

  public FileExistsWebDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
    super( parent, (BaseStepMeta) in, transMeta, sname );
  }

  @Override
  public String open() {

    fileExistsWebDialogProxy = new FileExistsWebDialogProxy(this);

    final String url = "http://localhost:3388/static/fileexists/index.html?id=" + getID(); // pass the unique id to the client
    HttpLiteDialog httpLiteDialog =
       new HttpLiteDialog( Spoon.getInstance().getShell(), "HttpLite", url, "File Exists Web Dialog" ){

        @Override
        public void ok(){
          logBasic("HttpLiteDialog: closing: " + getID());
          super.ok();
          onClose();
        }

         @Override
         public void setSize(final Shell shell){
           BaseStepDialog.setSize( shell, 485, 525, true );
         }

       };
    logBasic("HttpLiteDialog: opening: " + getID());
    httpLiteDialog.open();

    return this.stepname;
  }

  private void onClose(){
    logBasic("FileExistsWebDialog: closing: " + this.getID());
    if(fileExistsWebDialogProxy != null){
      fileExistsWebDialogProxy.destroy(); // unsubscribe remote proxy listener
      fileExistsWebDialogProxy = null; // remove reference for garbage collection
    }
  }

  public String getID() {
    return ID;
  }

  public String getStepname(){
    return this.stepname;
  }

  public void setStepname(String stepname){
    this.stepname = stepname;
  }
}
