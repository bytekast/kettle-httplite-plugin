package org.pentaho.di.ui.trans.steps.fileexistsweb;

import org.pentaho.di.core.httplite.dwr.FileExistsRemoteModel;
import org.pentaho.di.core.httplite.dwr.FileExistsRemoteProxy;
import org.pentaho.di.core.httplite.dwr.IFileExistsRemoteProxy;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author Rowell Belen
 */
@Configurable(autowire = Autowire.BY_TYPE)
public class FileExistsWebDialogProxy implements IFileExistsRemoteProxy{

  private FileExistsWebDialog fileExistsWebDialog;

  @Autowired
  private FileExistsRemoteProxy fileExistsRemoteProxy;

  public FileExistsWebDialogProxy(FileExistsWebDialog fileExistsWebDialog){
    this.fileExistsWebDialog = fileExistsWebDialog;
  }

  @PostConstruct // called after dependencies are injected
  public void setup(){
    // Need to subscribe this dialog to the proxy to expose remote methods implemented in IMyStepRemoteProxy
    if(fileExistsRemoteProxy != null){
      fileExistsRemoteProxy.subscribe(this.fileExistsWebDialog.getID(), this);
    }
  }

  @Override
  public FileExistsRemoteModel getModel(String id) {
    return fileExistsWebDialog.getModel();
  }

  @Override
  public void applyModel(String id, FileExistsRemoteModel fileExistsRemoteModel) {
    if(fileExistsRemoteModel == null){
      return;
    }

    fileExistsWebDialog.setModel(fileExistsRemoteModel);
  }

  @Override
  public void help(String id) {
    this.fileExistsWebDialog.showHelp();
  }

  @Override
  public void submit(String id, FileExistsRemoteModel fileExistsRemoteModel) {
    this.applyModel(id, fileExistsRemoteModel);
    this.fileExistsWebDialog.close();
  }

  @Override
  public void cancel(String id) {
    // do nothing
    this.fileExistsWebDialog.close();
  }

  @PreDestroy
  public void destroy(){
    fileExistsRemoteProxy.unsubscribe(fileExistsWebDialog.getID());
    this.fileExistsWebDialog = null; // remove reference for garbage collection
  }
}
