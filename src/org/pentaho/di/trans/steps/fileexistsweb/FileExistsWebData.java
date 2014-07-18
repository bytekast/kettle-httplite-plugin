package org.pentaho.di.trans.steps.fileexistsweb;

import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.steps.fileexists.FileExistsData;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
public class FileExistsWebData extends FileExistsData implements StepDataInterface {
}
