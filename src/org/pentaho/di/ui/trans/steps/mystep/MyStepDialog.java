package org.pentaho.di.ui.trans.steps.mystep;

import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.httplite.dwr.MyStepRemoteProxy;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.di.ui.spoon.dialog.HttpLiteDialog;
import org.pentaho.di.ui.trans.step.BaseStepDialog;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.UUID;

/**
 * @author Rowell Belen
 */
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class MyStepDialog extends BaseStepDialog implements StepDialogInterface {

  private final String ID = UUID.randomUUID().toString();

  @Autowired
  private MyStepRemoteProxy myStepRemoteProxy;

  public MyStepDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
    super( parent, (BaseStepMeta) in, transMeta, sname );
  }

  @Override
  public String open() {

    // Need to register this dialog to the proxy to expose remote methods implemented in IMyStepRemoteProxy
    if(myStepRemoteProxy != null){
      myStepRemoteProxy.register(this.ID, new MyStepDialogProxy(this));
      logBasic("Registered: " + this.ID);
    }

    final String url = "http://localhost:3388/static/index.html?id=" + this.ID; // pass the unique id to the client
    HttpLiteDialog httpLiteDialog = new HttpLiteDialog( Spoon.getInstance().getShell(), "HttpLite", url, "AngularJS Test" );
    httpLiteDialog.open();

    return this.stepname;
  }

  public String getStepname(){
    return this.stepname;
  }

  public void setStepname(String stepname){
    this.stepname = stepname;
  }
}
