package org.pentaho.di.core.httplite.dwr;

import org.pentaho.di.ui.trans.steps.mystep.MyStepDialog;

/**
 * @author Rowell Belen
 */
public interface IMyStepRemoteProxy {

  String getStepName();
  void setStepName(String stepName);
}
