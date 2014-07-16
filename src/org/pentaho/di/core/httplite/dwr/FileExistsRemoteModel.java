package org.pentaho.di.core.httplite.dwr;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 * @author Rowell Belen
 */
@DataTransferObject(javascript = "FileExistsRemoteModel")
public class FileExistsRemoteModel {

  @RemoteProperty
  private String stepName;

  @RemoteProperty
  private String fileNameField;

  @RemoteProperty
  private String resultFieldName;

  @RemoteProperty
  private boolean addFieldName;

  @RemoteProperty
  private boolean includeFileType;

  @RemoteProperty
  private String fileFieldType;

  public String getStepName() {
    return stepName;
  }

  public void setStepName(String stepName) {
    this.stepName = stepName;
  }

  public String getFileNameField() {
    return fileNameField;
  }

  public void setFileNameField(String fileNameField) {
    this.fileNameField = fileNameField;
  }

  public String getResultFieldName() {
    return resultFieldName;
  }

  public void setResultFieldName(String resultFieldName) {
    this.resultFieldName = resultFieldName;
  }

  public boolean isAddFieldName() {
    return addFieldName;
  }

  public void setAddFieldName(boolean addFieldName) {
    this.addFieldName = addFieldName;
  }

  public boolean isIncludeFileType() {
    return includeFileType;
  }

  public void setIncludeFileType(boolean includeFileType) {
    this.includeFileType = includeFileType;
  }

  public String getFileFieldType() {
    return fileFieldType;
  }

  public void setFileFieldType(String fileFieldType) {
    this.fileFieldType = fileFieldType;
  }
}
