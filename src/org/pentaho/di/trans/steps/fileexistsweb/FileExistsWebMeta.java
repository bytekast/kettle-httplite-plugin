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
public class FileExistsWebMeta extends BaseStepMeta implements StepMetaInterface {

  private FileExistsRemoteModel fileExistsRemoteModel;

  public FileExistsRemoteModel getFileExistsRemoteModel() {
    return fileExistsRemoteModel;
  }

  public void setFileExistsRemoteModel(FileExistsRemoteModel fileExistsRemoteModel) {
    this.fileExistsRemoteModel = fileExistsRemoteModel;
  }

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private FileExistsWebData myStepData;

  @Override
  public void setDefault() {
    System.out.println("setDefault: " + applicationContext);
  }

  @Override
  public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int i, TransMeta transMeta, Trans trans) {

    FileExistsWeb fileExistsWeb = new FileExistsWeb(stepMeta, stepDataInterface, i, transMeta, trans);

    System.out.println("getStep: " + fileExistsWeb);
    return fileExistsWeb;
  }

  @Override
  public StepDataInterface getStepData() {
    System.out.println("getStepData: " + myStepData);
    return myStepData;
  }
}
