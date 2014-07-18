package org.pentaho.di.core.httplite.dwr.proxy;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.pentaho.di.core.httplite.dwr.model.ThinCCGeneratorModel;
import org.pentaho.di.core.httplite.dwr.model.ThinCreditCardType;
import org.pentaho.di.trans.steps.randomccnumber.RandomCreditCardNumberGenerator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rowell Belen
 */
@Component
@RemoteProxy
public class ThinCCNumberGeneratorProxy implements IThinCCNumberGeneratorProxy {

  private HashMap<String, IThinCCNumberGeneratorProxy> proxyCache = new HashMap<String, IThinCCNumberGeneratorProxy>();

  public synchronized void subscribe(final String id, IThinCCNumberGeneratorProxy iCreditCardGeneratorProxy){
    this.proxyCache.put(id, iCreditCardGeneratorProxy);
  }

  public synchronized void unsubscribe(final String id){
    this.proxyCache.remove(id);
  }

  @RemoteMethod
  public ThinCreditCardType[] getCardTypes(){

    List<ThinCreditCardType> list = new ArrayList<ThinCreditCardType>();

    String[] types = RandomCreditCardNumberGenerator.cardTypes;
    if(types != null){
      for(int i=0; i < types.length; i++){
        ThinCreditCardType type = new ThinCreditCardType();
        type.setValue(i+"");
        type.setName(types[i]);
        list.add(type);
      }
    }

    return list.toArray(new ThinCreditCardType[list.size()]);
  }

  @Override
  @RemoteMethod
  public ThinCCGeneratorModel getModel(String id) {
    IThinCCNumberGeneratorProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      return proxy.getModel(id);
    }
    return null;
  }

  @Override
  @RemoteMethod
  public void applyModel(String id, ThinCCGeneratorModel thinCCGeneratorModel) {
    IThinCCNumberGeneratorProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      proxy.applyModel(id, thinCCGeneratorModel);
    }
  }

  @Override
  @RemoteMethod
  public void help(String id) {
    IThinCCNumberGeneratorProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      proxy.help(id);
    }
  }

  @Override
  @RemoteMethod
  public void cancel(String id) {
    IThinCCNumberGeneratorProxy proxy = this.proxyCache.get(id);
    if(proxy != null){
      proxy.cancel(id);
    }
  }

  @Scheduled(fixedRate = 10000)
  public void cleanup(){
    System.out.println("CreditCardGeneratorProxy: size of proxyCache map: " + this.proxyCache.size());
  }
}
