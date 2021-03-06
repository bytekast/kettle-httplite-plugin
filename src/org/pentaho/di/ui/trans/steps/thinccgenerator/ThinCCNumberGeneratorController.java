package org.pentaho.di.ui.trans.steps.thinccgenerator;

import org.pentaho.di.core.httplite.dwr.model.ThinCCGeneratorModel;
import org.pentaho.di.core.httplite.dwr.proxy.ThinCCNumberGeneratorService;
import org.pentaho.di.core.httplite.dwr.proxy.ThinCCNumberGeneratorProxy;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author Rowell Belen
 */
@Configurable(autowire = Autowire.BY_TYPE)
public class ThinCCNumberGeneratorController implements ThinCCNumberGeneratorService {

  private ThinCCNumberGeneratorDialog dialog;

  @Autowired
  private ThinCCNumberGeneratorProxy proxy;

  public ThinCCNumberGeneratorController(ThinCCNumberGeneratorDialog dialog){
    this.dialog = dialog;
  }

  @PostConstruct // called after dependencies are injected
  public void setup(){
    // Need to subscribe this dialog to the proxy to expose remote methods implemented in IMyStepRemoteProxy
    if(proxy != null){
      proxy.subscribe(this.dialog.getID(), this);
    }
  }

  @Override
  public ThinCCGeneratorModel getModel(String id) {
    return dialog.getModel();
  }

  @Override
  public void applyModel(String id, ThinCCGeneratorModel remoteModel) {
    if(remoteModel == null){
      return;
    }

    dialog.setModel(remoteModel);
    this.dialog.close();
  }

  @Override
  public void help(String id) {
    this.dialog.showHelp();
  }

  @Override
  public void cancel(String id) {
    // do nothing
    this.dialog.close();
  }

  @PreDestroy
  public void destroy(){
    proxy.unsubscribe(dialog.getID());
    this.dialog = null; // remove reference for garbage collection
  }
}
