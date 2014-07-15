package org.pentaho.di.ui.trans.steps.mystep;

import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.httplite.dwr.IMyStepRemoteProxy;
import org.pentaho.di.core.httplite.dwr.MyStepRemoteModel;
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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * @author Rowell Belen
 */
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class MyStepDialog extends BaseStepDialog implements StepDialogInterface, IMyStepRemoteProxy {

  private final String ID = UUID.randomUUID().toString();

  @Autowired
  private MyStepRemoteProxy myStepRemoteProxy;

  public MyStepDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
    super( parent, (BaseStepMeta) in, transMeta, sname );

    // Need to register this dialog to the proxy to expose remote methods implemented in IMyStepRemoteProxy
    if(myStepRemoteProxy != null){
      myStepRemoteProxy.register(this.ID, this);
      logBasic("Registered: " + this.ID);
    }
  }

  @Override
  public String open() {
    final String url = "http://localhost:3388/static/index.html?id=" + this.ID; // pass the unique id to the client
    HttpLiteDialog httpLiteDialog = new HttpLiteDialog( Spoon.getInstance().getShell(), "HttpLite", url, "AngularJS Test" );
    httpLiteDialog.open();

    return this.stepname;
  }

  @PostConstruct
  public void register(){

  }

  @PreDestroy
  public void unregister(){
    if(myStepRemoteProxy != null){
      myStepRemoteProxy.unregister(this.ID);
      logBasic("Unregistered: " + this);
    }
  }

  @Override
  public MyStepRemoteModel getModel(String id) {

    MyStepRemoteModel model = new MyStepRemoteModel();
    model.setStepName(this.stepname);

    return model;
  }

  @Override
  public void applyModel(String id, MyStepRemoteModel myStepRemoteModel) {

    if(myStepRemoteModel == null){
      return;
    }

    this.stepname =
       (myStepRemoteModel.getStepName() != null) ? myStepRemoteModel.getStepName() : this.stepname;
    logBasic(this.stepname);
  }
}
