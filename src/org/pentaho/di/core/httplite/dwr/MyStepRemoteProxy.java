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

  private HashMap<String, IMyStepRemoteProxy> proxyCache = new HashMap<String, IMyStepRemoteProxy>();

  public synchronized void subscribe(final String id, IMyStepRemoteProxy myStepRemoteProxy){
    this.proxyCache.put(id, myStepRemoteProxy);
  }

  public synchronized void unsubscribe(final String id){
    this.proxyCache.remove(id);
  }

  @Override
  @RemoteMethod
  public MyStepRemoteModel getModel(String id) {
    IMyStepRemoteProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      return proxy.getModel(id);
    }
    return null;
  }

  @Override
  @RemoteMethod
  public void applyModel(String id, MyStepRemoteModel myStepRemoteModel) {
    IMyStepRemoteProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      proxy.applyModel(id, myStepRemoteModel);
    }
  }

  @Scheduled(fixedRate = 10000)
  public void cleanup(){
    System.out.println("MyStepRemoteProxy: size of proxyCache map: " + this.proxyCache.size());
  }
}
