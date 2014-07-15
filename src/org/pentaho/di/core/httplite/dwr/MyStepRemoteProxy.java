package org.pentaho.di.core.httplite.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.pentaho.di.ui.trans.steps.mystep.MyStepDialog;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Rowell Belen
 */
@Component
@RemoteProxy
public class MyStepRemoteProxy implements IMyStepRemoteProxy {

  private HashMap<String, MyStepDialog> holder = new HashMap<String, MyStepDialog>();

  public void register(final String id, MyStepDialog myStepDialog){
    this.holder.put(id, myStepDialog);
  }

  public void unregister(final String id){
    if(id != null){
      this.holder.remove(id);
    }
  }

  @Override
  @RemoteMethod
  public MyStepRemoteModel getModel(String id) {
    MyStepDialog dialog = this.holder.get(id);
    if(dialog != null){
      return dialog.getModel(id);
    }
    return null;
  }

  @Override
  @RemoteMethod
  public void applyModel(String id, MyStepRemoteModel myStepRemoteModel) {
    MyStepDialog dialog = this.holder.get(id);
    if(dialog != null){
      dialog.applyModel(id, myStepRemoteModel);
    }
  }
}
