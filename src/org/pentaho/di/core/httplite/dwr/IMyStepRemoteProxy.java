package org.pentaho.di.core.httplite.dwr;


/**
 * @author Rowell Belen
 */
public interface IMyStepRemoteProxy {

  MyStepRemoteModel getModel();
  void applyModel(MyStepRemoteModel myStepRemoteModel);
}
