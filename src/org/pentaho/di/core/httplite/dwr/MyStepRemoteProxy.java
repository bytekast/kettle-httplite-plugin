package org.pentaho.di.core.httplite.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Rowell Belen
 */
@Component
@RemoteProxy
public class MyStepRemoteProxy implements IMyStepRemoteProxy {

  private HashMap<String, IMyStepRemoteProxy> holder = new HashMap<String, IMyStepRemoteProxy>();

  public void register(final String id, IMyStepRemoteProxy myStepRemoteProxy){
    this.holder.put(id, myStepRemoteProxy);

    // need to find a way to clean this up vestigial instances at some point
  }

  @Override
  @RemoteMethod
  public MyStepRemoteModel getModel(String id) {
    IMyStepRemoteProxy dialog = this.holder.get(id);
    if(dialog != null){
      return dialog.getModel(id);
    }
    return null;
  }

  @Override
  @RemoteMethod
  public void applyModel(String id, MyStepRemoteModel myStepRemoteModel) {
    IMyStepRemoteProxy dialog = this.holder.get(id);
    if(dialog != null){
      dialog.applyModel(id, myStepRemoteModel);
    }
  }

  @Scheduled(fixedRate = 10000)
  public void cleanup(){
    System.out.println("size of holder map: " + this.holder.size());
  }
}
