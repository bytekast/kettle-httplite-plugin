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
public class FileExistsRemoteProxy implements IFileExistsRemoteProxy {

  private HashMap<String, IFileExistsRemoteProxy> proxyCache = new HashMap<String, IFileExistsRemoteProxy>();

  public synchronized void subscribe(final String id, IFileExistsRemoteProxy iFileExistsRemoteProxy){
    this.proxyCache.put(id, iFileExistsRemoteProxy);
  }

  public synchronized void unsubscribe(final String id){
    this.proxyCache.remove(id);
  }

  @Override
  @RemoteMethod
  public FileExistsRemoteModel getModel(String id) {
    IFileExistsRemoteProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      return proxy.getModel(id);
    }
    return null;
  }

  @Override
  @RemoteMethod
  public void applyModel(String id, FileExistsRemoteModel fileExistsRemoteModel) {
    IFileExistsRemoteProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      proxy.applyModel(id, fileExistsRemoteModel);
    }
  }

  @Scheduled(fixedRate = 10000)
  public void cleanup(){
    System.out.println("FileExistsRemoteProxy: size of proxyCache map: " + this.proxyCache.size());
  }

  @Override
  @RemoteMethod
  public void help(String id) {
    IFileExistsRemoteProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      proxy.help(id);
    }
  }

  @Override
  @RemoteMethod
  public void submit(String id, FileExistsRemoteModel fileExistsRemoteModel) {
    IFileExistsRemoteProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      proxy.submit(id, fileExistsRemoteModel);
    }
  }

  @Override
  @RemoteMethod
  public void cancel(String id) {
    IFileExistsRemoteProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      proxy.cancel(id);
    }
  }
}
