package org.pentaho.di.ui.trans.steps.mystep;

import org.pentaho.di.core.httplite.dwr.IMyStepRemoteProxy;
import org.pentaho.di.core.httplite.dwr.MyStepRemoteModel;
import org.pentaho.di.core.httplite.dwr.MyStepRemoteProxy;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author Rowell Belen
 */
@Configurable(autowire = Autowire.BY_TYPE)
public class MyStepDialogProxy implements IMyStepRemoteProxy{

  private MyStepDialog myStepDialog;

  @Autowired
  private MyStepRemoteProxy myStepRemoteProxy;

  public MyStepDialogProxy( MyStepDialog myStepDialog ){
    this.myStepDialog = myStepDialog;
  }

  @PostConstruct // called after dependencies are injected
  public void setup(){
    // Need to register this dialog to the proxy to expose remote methods implemented in IMyStepRemoteProxy
    if(myStepRemoteProxy != null){
      myStepRemoteProxy.register(this.myStepDialog.getID(), this);

      //TODO need to unregister this when the dialog is closed!!!!!!!
    }
  }

  @Override
  public MyStepRemoteModel getModel(String id) {
    MyStepRemoteModel model = new MyStepRemoteModel();
    model.setStepName(myStepDialog.getStepname());

    return model;
  }

  @Override
  public void applyModel(String id, MyStepRemoteModel myStepRemoteModel) {
    if(myStepRemoteModel == null){
      return;
    }

    String stepname =
       (myStepRemoteModel.getStepName() != null) ? myStepRemoteModel.getStepName() : myStepDialog.getStepname();
    myStepDialog.setStepname(stepname);
  }

  @PreDestroy
  public void destroy(){
    this.myStepDialog = null; // remove reference for garbage collection
  }
}
