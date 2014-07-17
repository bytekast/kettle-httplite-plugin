package org.pentaho.di.ui.trans.steps.mystep;

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
public class MyStepDialog extends BaseStepDialog implements StepDialogInterface {

  private final String ID = UUID.randomUUID().toString();

  private MyStepDialogProxy myStepDialogProxy;

  public MyStepDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
    super( parent, (BaseStepMeta) in, transMeta, sname );
  }

  @Override
  public String open() {
    myStepDialogProxy = new MyStepDialogProxy(this); // subscribe remote proxy listener for web requests

    final String url = "http://localhost:3388/static/mystep/index.html?id=" + getID(); // pass the unique id to the client
    HttpLiteDialog httpLiteDialog =
       new HttpLiteDialog( Spoon.getInstance().getShell(), "AngularJS Test", url){

        @Override
        public void ok(){
          logBasic("HttpLiteDialog: closing: " + getID());
          super.ok();
          onClose();
        }

       };
    logBasic("HttpLiteDialog: opening: " + getID());
    httpLiteDialog.open();

    return this.stepname;
  }

  private void onClose(){
    logBasic("MyStepDialog: closing: " + this.getID());
    if(myStepDialogProxy != null){
      myStepDialogProxy.destroy(); // unsubscribe remote proxy listener
      myStepDialogProxy = null; // remove reference for garbage collection
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
