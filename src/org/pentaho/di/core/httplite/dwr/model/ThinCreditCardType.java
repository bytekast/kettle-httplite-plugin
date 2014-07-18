package org.pentaho.di.core.httplite.dwr.model;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 * @author Rowell Belen
 */
@DataTransferObject(javascript = "ThinCreditCardTypes")
public class ThinCreditCardType {

  @RemoteProperty
  private String name;

  @RemoteProperty
  private String value;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
