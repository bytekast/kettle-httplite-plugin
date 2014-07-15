package org.pentaho.di.core.httplite.dwr;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 * @author Rowell Belen
 */
@DataTransferObject(javascript = "MyStepRemoteModel")
public class MyStepRemoteModel {

  @RemoteProperty
  private String stepName;

  public String getStepName() {
    return stepName;
  }

  public void setStepName(String stepName) {
    this.stepName = stepName;
  }
}
