package org.pentaho.di.core.httplite.dwr.model;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 * @author Rowell Belen
 */
@DataTransferObject(javascript = "ThinCreditCardEntry")
public class ThinCreditCardEntry {

  @RemoteProperty
  private String type;

  @RemoteProperty
  private int length;

  @RemoteProperty
  private int count;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
