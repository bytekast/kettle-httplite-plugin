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
    FileExistsRemoteModel model = new FileExistsRemoteModel();
    model.setStepName(fileExistsWebDialog.getStepname());

    return model;
  }

  @Override
  public void applyModel(String id, FileExistsRemoteModel fileExistsRemoteModel) {
    if(fileExistsRemoteModel == null){
      return;
    }

    String stepName =
       (fileExistsRemoteModel.getStepName() != null) ? fileExistsRemoteModel.getStepName() : fileExistsWebDialog.getStepname();
    fileExistsWebDialog.setStepname(stepName);
  }

  @PreDestroy
  public void destroy(){
    fileExistsRemoteProxy.unsubscribe(fileExistsWebDialog.getID());
    this.fileExistsWebDialog = null; // remove reference for garbage collection
  }
}
