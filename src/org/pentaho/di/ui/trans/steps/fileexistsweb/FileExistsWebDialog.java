package org.pentaho.di.ui.trans.steps.fileexistsweb;

import com.thoughtworks.xstream.XStream;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.httplite.dwr.FileExistsRemoteModel;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.trans.steps.fileexistsweb.FileExistsWebMeta;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.di.ui.spoon.dialog.HttpLiteDialog;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

import java.util.UUID;

/**
 * @author Rowell Belen
 */
public class FileExistsWebDialog extends BaseStepDialog implements StepDialogInterface {

  private final String ID = UUID.randomUUID().toString();
  private final XStream xstream = new XStream();

  private FileExistsWebDialogProxy fileExistsWebDialogProxy;
  private HttpLiteDialog httpLiteDialog;

  private FileExistsWebMeta input;
  private FileExistsRemoteModel model;

  public FileExistsWebDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
    super( parent, (BaseStepMeta) in, transMeta, sname );
    input = (FileExistsWebMeta) in;

    // de-serialize
    model = input.getFileExistsRemoteModel();
    logBasic("De-serialize: " + xstream.toXML(this.model));
    if(model == null){
      model = new FileExistsRemoteModel();
    }
    model.setStepName(this.stepname);
  }

  @Override
  public String open() {

    fileExistsWebDialogProxy = new FileExistsWebDialogProxy(this);

    final String url = "http://localhost:3388/static/fileexists/index.html?id=" + getID(); // pass the unique id to the client
    httpLiteDialog =
       new HttpLiteDialog( Spoon.getInstance().getShell(), "File Exists Web Dialog", url){

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

    persistModel();
  }

  public String getID() {
    return ID;
  }

  public void close(){
    logBasic("trying to close...");
    httpLiteDialog.close();
  }

  private void persistModel(){
    // serialize
    input.setFileExistsRemoteModel(model);
    logBasic("De-serialize: " + xstream.toXML(this.model));
  }

  public FileExistsRemoteModel getModel() {
    return model;
  }

  public void setModel(FileExistsRemoteModel model) {
    this.model = model;
    this.stepname = this.model.getStepName();
  }
}
