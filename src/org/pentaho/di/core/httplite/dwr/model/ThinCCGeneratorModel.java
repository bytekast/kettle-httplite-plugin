package org.pentaho.di.core.httplite.dwr.model;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 * @author Rowell Belen
 */
@DataTransferObject(javascript = "ThinCCGeneratorModel")
public class ThinCCGeneratorModel {

  @RemoteProperty
  private String stepName;

  @RemoteProperty
  private String numberFieldName;

  @RemoteProperty
  private String typeFieldName;

  @RemoteProperty
  private String lengthFieldName;

  @RemoteProperty
  private ThinCreditCardEntry[] cardEntries;

  public String getStepName() {
    return stepName;
  }

  public void setStepName(String stepName) {
    this.stepName = stepName;
  }

  public String getNumberFieldName() {
    return numberFieldName;
  }

  public void setNumberFieldName(String numberFieldName) {
    this.numberFieldName = numberFieldName;
  }

  public String getTypeFieldName() {
    return typeFieldName;
  }

  public void setTypeFieldName(String typeFieldName) {
    this.typeFieldName = typeFieldName;
  }

  public String getLengthFieldName() {
    return lengthFieldName;
  }

  public void setLengthFieldName(String lengthFieldName) {
    this.lengthFieldName = lengthFieldName;
  }

  public ThinCreditCardEntry[] getCardEntries() {
    return cardEntries;
  }

  public void setCardEntries(ThinCreditCardEntry[] cardEntries) {
    this.cardEntries = cardEntries;
  }
}
