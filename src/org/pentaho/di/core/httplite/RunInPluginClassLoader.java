package org.pentaho.di.core.httplite;

import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;

/**
 * @author Rowell Belen
 */
public class RunInPluginClassLoader {

  public static void run(final Runnable runnable) {

    ClassLoader origClassLoader = null;
    try {
      origClassLoader = Thread.currentThread().getContextClassLoader();
      PluginInterface plugin = HttpLite.getPluginObject("httplite");
      ClassLoader pluginClassLoader = PluginRegistry.getInstance().getClassLoader(plugin);

      // Use the plugin class loader
      Thread.currentThread().setContextClassLoader(pluginClassLoader);

      if (runnable != null) {
        runnable.run();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if(origClassLoader != null){
        Thread.currentThread().setContextClassLoader(origClassLoader);
      }
    }
  }

}
