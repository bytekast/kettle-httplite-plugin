package org.pentaho.di.trans.steps.mystep;

import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;

/**
 * @author Rowell Belen
 */
@Step( id = "MyStep", image = "PaloCellInput.png",
   i18nPackageName = "org.pentaho.di.trans.steps.mystep", name = "MyStep.TransName",
   description = "MyStep.TransDescription",
   categoryDescription = "i18n:org.pentaho.di.trans.step:BaseStep.Category.Experimental" )
public class MyStepMeta extends BaseStepMeta implements StepMetaInterface {
  @Override
  public void setDefault() {

  }

  @Override
  public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int i, TransMeta transMeta, Trans trans) {
    return null;
  }

  @Override
  public StepDataInterface getStepData() {
    return null;
  }
}
