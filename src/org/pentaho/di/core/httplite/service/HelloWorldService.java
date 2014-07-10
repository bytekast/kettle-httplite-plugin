package org.pentaho.di.core.httplite.service;

import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
public class HelloWorldService {

  public String hello(String s){
    return "Hello " + s;
  }
}
