package org.pentaho.di.core.httplite.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.pentaho.di.core.httplite.HttpLite;
import org.pentaho.di.core.httplite.service.AspectTest;
import org.pentaho.di.core.httplite.service.HelloWorldService;
import org.pentaho.di.core.httplite.service.MyStepDialogBridge;
import org.pentaho.di.ui.spoon.Spoon;
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

  @Autowired
  private Spoon spoonInstance;

  @Autowired
  private MyStepDialogBridge myStepDialogBridge;

  @RemoteMethod
  public String hello(String s){
    return helloWorldService.hello(s);
  }

  @RemoteMethod
  public String getPluginFolderPath(){
    return HttpLite.buildPluginFolderPath();
  }

  @RemoteMethod
  public String getSpoonInfo(){
    return spoonInstance.getName();
  }

  @RemoteMethod
  public String configurableTest(){
    return new AspectTest().aspectTest();
  }

  @RemoteMethod
  public void updateStepName(String stepName){
    myStepDialogBridge.updateStepName(stepName);
  }
}
