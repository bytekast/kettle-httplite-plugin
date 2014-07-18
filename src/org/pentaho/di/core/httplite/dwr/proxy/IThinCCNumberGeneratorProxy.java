package org.pentaho.di.core.httplite.dwr.proxy;

import org.pentaho.di.core.httplite.dwr.model.ThinCCGeneratorModel;

/**
 * @author Rowell Belen
 */
public interface IThinCCNumberGeneratorProxy {

  ThinCCGeneratorModel getModel(String id);
  void applyModel(String id, ThinCCGeneratorModel thinCCGeneratorModel);
  void help(String id);
  void cancel(String id);
}
