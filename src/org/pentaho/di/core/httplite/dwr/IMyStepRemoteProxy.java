package org.pentaho.di.core.httplite.dwr;


/**
 * @author Rowell Belen
 */
public interface IMyStepRemoteProxy {

  MyStepRemoteModel getModel(String id);
  void applyModel(String id, MyStepRemoteModel myStepRemoteModel);
}
