package org.pentaho.di.ui.trans.steps.mystep;

import org.pentaho.di.core.httplite.dwr.IMyStepRemoteProxy;
import org.pentaho.di.core.httplite.dwr.MyStepRemoteModel;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PreDestroy;


/**
 * @author Rowell Belen
 */
@Configurable
public class MyStepDialogProxy implements IMyStepRemoteProxy{

  private MyStepDialog myStepDialog;

  public MyStepDialogProxy( MyStepDialog myStepDialog ){
    this.myStepDialog = myStepDialog;
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
