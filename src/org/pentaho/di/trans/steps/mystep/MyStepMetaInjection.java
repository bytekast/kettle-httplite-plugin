package org.pentaho.di.trans.steps.mystep;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.step.StepInjectionMetaEntry;
import org.pentaho.di.trans.step.StepMetaInjectionInterface;

import java.util.List;

/**
 * @author Rowell Belen
 */
public class MyStepMetaInjection implements StepMetaInjectionInterface {

  @Override
  public List<StepInjectionMetaEntry> getStepInjectionMetadataEntries() throws KettleException {
    return null;
  }

  @Override
  public void injectStepMetadataEntries(List<StepInjectionMetaEntry> stepInjectionMetaEntries) throws KettleException {

  }
}
