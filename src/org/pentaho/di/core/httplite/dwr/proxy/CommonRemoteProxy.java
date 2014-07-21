package org.pentaho.di.core.httplite.dwr.proxy;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.pentaho.di.core.httplite.HttpLite;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
@RemoteProxy
public class CommonRemoteProxy {

  @RemoteMethod
  public String getPluginFolderPath(){
    return HttpLite.buildPluginFolderPath();
  }
}
