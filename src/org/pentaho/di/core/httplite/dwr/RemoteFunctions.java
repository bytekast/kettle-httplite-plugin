package org.pentaho.di.core.httplite.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.pentaho.di.core.httplite.HttpLite;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
@RemoteProxy
public class RemoteFunctions {

  @RemoteMethod
  public String getPluginFolderPath(){
    return HttpLite.buildPluginFolderPath();
  }
}
