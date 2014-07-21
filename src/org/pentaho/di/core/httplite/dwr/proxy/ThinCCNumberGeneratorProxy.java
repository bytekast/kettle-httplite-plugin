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
public class ThinCCNumberGeneratorProxy implements ThinCCNumberGeneratorService {

  private HashMap<String, ThinCCNumberGeneratorService> serviceCache = new HashMap<String, ThinCCNumberGeneratorService>();

  public synchronized void subscribe(final String id, ThinCCNumberGeneratorService service){
    this.serviceCache.put(id, service);
  }

  public synchronized void unsubscribe(final String id){
    this.serviceCache.remove(id);
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
    ThinCCNumberGeneratorService service = this.serviceCache.get(id);
    if(service != null){
      return service.getModel(id);
    }
    return null;
  }

  @Override
  @RemoteMethod
  public void applyModel(String id, ThinCCGeneratorModel thinCCGeneratorModel) {
    ThinCCNumberGeneratorService service = this.serviceCache.get(id);
    if(service != null){
      service.applyModel(id, thinCCGeneratorModel);
    }
  }

  @Override
  @RemoteMethod
  public void help(String id) {
    ThinCCNumberGeneratorService service = this.serviceCache.get(id);
    if(service != null){
      service.help(id);
    }
  }

  @Override
  @RemoteMethod
  public void cancel(String id) {
    ThinCCNumberGeneratorService service = this.serviceCache.get(id);
    if(service != null){
      service.cancel(id);
    }
  }

  @Scheduled(fixedRate = 10000)
  public void cleanup(){
    System.out.println("ThinCCNumberGeneratorProxy: size of serviceCache map: " + this.serviceCache.size());
  }
}
