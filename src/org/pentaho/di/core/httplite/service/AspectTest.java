package org.pentaho.di.core.httplite.service;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Rowell Belen
 */

@Configurable(autowire = Autowire.BY_TYPE)
public class AspectTest {

  @Autowired
  private HelloWorldService helloWorldService;

  public String aspectTest() {
    return helloWorldService.hello("aspect!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
  }
}
