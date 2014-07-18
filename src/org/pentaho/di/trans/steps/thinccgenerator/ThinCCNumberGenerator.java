package org.pentaho.di.trans.steps.thinccgenerator;

import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.fileexists.FileExists;
import org.pentaho.di.trans.steps.randomccnumber.RandomCCNumberGenerator;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

/**
 * @author Rowell Belen
 */
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class ThinCCNumberGenerator extends RandomCCNumberGenerator implements StepInterface {

  @Autowired
  private ApplicationContext applicationContext;

  public ThinCCNumberGenerator(StepMeta stepMeta, StepDataInterface stepDataInterface, int copyNr, TransMeta transMeta, Trans trans) {
    super(stepMeta, stepDataInterface, copyNr, transMeta, trans);
  }
}
