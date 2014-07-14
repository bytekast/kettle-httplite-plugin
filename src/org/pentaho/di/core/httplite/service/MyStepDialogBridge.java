package org.pentaho.di.core.httplite.service;

import org.pentaho.di.ui.trans.steps.mystep.MyStepDialog;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
public class MyStepDialogBridge {

  private MyStepDialog myStepDialog;

  public void registerComponent(MyStepDialog myStepDialog){
    this.myStepDialog = myStepDialog;
  }

  public void updateStepName(String stepName){
    if(this.myStepDialog != null){
      this.myStepDialog.setStepName(stepName);
    }
  }
}
