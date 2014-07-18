package org.pentaho.di.trans.steps.thinccgenerator;

import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.core.httplite.dwr.model.ThinCCGeneratorModel;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.randomccnumber.RandomCCNumberGeneratorMeta;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author Rowell Belen
 */
@Step( id = "ThinCCNumberGenerator", image = "PaloCellInput.png",
   i18nPackageName = "org.pentaho.di.trans.steps.thinccgenerator", name = "ThinCCNumberGenerator.TransName",
   description = "ThinCCNumberGenerator.TransDescription",
   categoryDescription = "i18n:org.pentaho.di.trans.step:BaseStep.Category.Experimental" )
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class ThinCCNumberGeneratorMeta extends RandomCCNumberGeneratorMeta implements StepMetaInterface {

  private ThinCCGeneratorModel thinCCGeneratorModel;

  public ThinCCGeneratorModel getThinCCGeneratorModel() {

    // map models
    //ThinCCGeneratorModel thinCCGeneratorModel = new ThinCCGeneratorModel();

    return thinCCGeneratorModel;
  }

  public void setThinCCGeneratorModel(ThinCCGeneratorModel thinCCGeneratorModel) {

    this.thinCCGeneratorModel = thinCCGeneratorModel;

    // map models

  }
}
