package org.pentaho.di.core.httplite.dwr;

/**
 * @author Rowell Belen
 */
public interface IFileExistsRemoteProxy {

  FileExistsRemoteModel getModel(String id);
  void applyModel(String id, FileExistsRemoteModel fileExistsRemoteModel);

  void help(String id);
  void submit(String id, FileExistsRemoteModel fileExistsRemoteModel);
  void cancel(String id);
}
