package org.pentaho.di.core.httplite.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.pentaho.di.core.httplite.HttpLite;
import org.pentaho.di.core.httplite.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
@RemoteProxy
public class RemoteFunctions {

  @Autowired
  private HelloWorldService helloWorldService;

  @RemoteMethod
  public String hello(String s){
    return helloWorldService.hello(s);
  }

  @RemoteMethod
  public String getPluginFolderPath(){
    return HttpLite.buildPluginFolderPath();
  }
}
