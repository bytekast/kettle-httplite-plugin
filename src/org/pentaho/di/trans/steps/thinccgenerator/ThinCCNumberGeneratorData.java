package org.pentaho.di.trans.steps.thinccgenerator;

import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.steps.fileexists.FileExistsData;
import org.pentaho.di.trans.steps.randomccnumber.RandomCCNumberGeneratorData;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
public class ThinCCNumberGeneratorData extends RandomCCNumberGeneratorData implements StepDataInterface {
}
