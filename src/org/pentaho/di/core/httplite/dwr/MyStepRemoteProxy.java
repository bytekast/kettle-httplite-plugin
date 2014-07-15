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

  public void registerComponent(MyStepDialog myStepDialog){
    this.myStepDialog = myStepDialog;
  }

  @RemoteMethod
  public String getStepName(){
    if(this.myStepDialog != null){
      return this.myStepDialog.getStepName();
    }

    return null;
  }

  @RemoteMethod
  public void setStepName(String stepName){
    if(this.myStepDialog != null){
      this.myStepDialog.setStepName(stepName);
    }
  }

}
