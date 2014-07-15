package org.pentaho.di.core.httplite.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.pentaho.di.ui.trans.steps.mystep.MyStepDialog;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
@RemoteProxy
public class MyStepRemoteProxy implements IMyStepRemoteProxy {

  private MyStepDialog myStepDialog;

  public void register(MyStepDialog myStepDialog){
    this.myStepDialog = myStepDialog;
  }

  @Override
  @RemoteMethod
  public MyStepRemoteModel getModel() {
    if(this.myStepDialog != null){
      return this.myStepDialog.getModel();
    }
    return null;
  }

  @Override
  @RemoteMethod
  public void applyModel(MyStepRemoteModel myStepRemoteModel) {
    if(this.myStepDialog != null){
      this.myStepDialog.applyModel(myStepRemoteModel);
    }
  }
}
