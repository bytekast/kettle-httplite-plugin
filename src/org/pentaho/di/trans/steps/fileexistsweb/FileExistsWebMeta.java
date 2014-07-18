package org.pentaho.di.trans.steps.fileexistsweb;

import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.core.httplite.dwr.FileExistsRemoteModel;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.fileexists.FileExistsMeta;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

/**
 * @author Rowell Belen
 */
@Step( id = "FileExistsWeb", image = "PaloCellInput.png",
   i18nPackageName = "org.pentaho.di.trans.steps.fileexistsweb", name = "FileExistsWeb.TransName",
   description = "FileExistsWeb.TransDescription",
   categoryDescription = "i18n:org.pentaho.di.trans.step:BaseStep.Category.Experimental" )
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class FileExistsWebMeta extends FileExistsMeta implements StepMetaInterface {

  private FileExistsRemoteModel fileExistsRemoteModel;

  public FileExistsRemoteModel getFileExistsRemoteModel() {

    // map models
    FileExistsRemoteModel fileExistsRemoteModel = new FileExistsRemoteModel();
    fileExistsRemoteModel.setAddFieldName(addResultFilenames());
    fileExistsRemoteModel.setFileFieldType(getFileTypeFieldName());
    fileExistsRemoteModel.setFileNameField(getDynamicFilenameField());
    fileExistsRemoteModel.setIncludeFileType(includeFileType());
    fileExistsRemoteModel.setResultFieldName(getResultFieldName());

    return fileExistsRemoteModel;
  }

  public void setFileExistsRemoteModel(FileExistsRemoteModel fileExistsRemoteModel) {

    this.fileExistsRemoteModel = fileExistsRemoteModel;

    // map models
    setaddResultFilenames(fileExistsRemoteModel.isAddFieldName());
    setFileTypeFieldName(fileExistsRemoteModel.getFileFieldType());
    setDynamicFilenameField(fileExistsRemoteModel.getFileNameField());
    setincludeFileType(fileExistsRemoteModel.isIncludeFileType());
    setResultFieldName(fileExistsRemoteModel.getResultFieldName());
  }

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private FileExistsWebData myStepData;

}
