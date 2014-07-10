package org.pentaho.di.core.httplite;

import org.directwebremoting.spring.DwrSpringServlet;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.PluginTypeInterface;
import org.pentaho.di.ui.spoon.SpoonLifecycleListener;
import org.pentaho.di.ui.spoon.SpoonPerspective;
import org.pentaho.di.ui.spoon.SpoonPlugin;
import org.pentaho.di.ui.spoon.SpoonPluginCategories;
import org.pentaho.di.ui.spoon.SpoonPluginInterface;
import org.pentaho.di.ui.spoon.dialog.HttpLiteController;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import java.io.File;
import java.io.IOException;

/**
 * @author Rowell Belen
 */
@SpoonPlugin( id = "httplite", image = "" )
@SpoonPluginCategories( { "spoon" } )
public class HttpLite implements SpoonPluginInterface {

  private ApplicationContext applicationContext;

  @Override
  public void applyToContainer(String category, XulDomContainer xulDomContainer) throws XulException {
    if ( category.equals( "spoon" ) ) {
      xulDomContainer.registerClassLoader( getClass().getClassLoader() );
      xulDomContainer.loadOverlay( "org/pentaho/di/core/httplite/spoon_overlays.xul" );
      xulDomContainer.addEventHandler( new HttpLiteController() );
    }
  }

  @Override
  public SpoonLifecycleListener getLifecycleListener() {
    return new SpoonLifecycleListener() {
      @Override
      public void onEvent(SpoonLifeCycleEvent spoonLifeCycleEvent) {
        if(spoonLifeCycleEvent.equals(SpoonLifeCycleEvent.STARTUP)){
          if(server == null || !server.isStarted()){
            startServer();
          }
        }
        if(spoonLifeCycleEvent.equals(SpoonLifeCycleEvent.SHUTDOWN)){
          stopServer();
        }
      }
    };
  }

  @Override
  public SpoonPerspective getPerspective() {
    return null;
  }

  private static PluginInterface getPluginObject( String pluginId ) {
    for ( Class<? extends PluginTypeInterface> pluginType : PluginRegistry.getInstance().getPluginTypes() ) {
      if ( PluginRegistry.getInstance().findPluginWithId( pluginType, pluginId ) != null ) {
        return PluginRegistry.getInstance().findPluginWithId( pluginType, pluginId );
      }
    }
    return null;
  }

  public static String buildPluginFolderPath() {
    PluginInterface plugin = getPluginObject( "httplite" );
    if ( plugin != null && plugin.getPluginDirectory() != null ) {
      return plugin.getPluginDirectory().getFile();
    }
    return null;
  }

  private HttpServer server;

  private void startServer(){
    // Initialize Grizzly HttpServer
    server = new HttpServer();
    NetworkListener listener = new NetworkListener("grizzly2", "localhost", 3388);
    server.addListener(listener);

    // Initialize web context
    WebappContext ctx = new WebappContext("ctx", "/api");
    ctx.addListener(ContextLoaderListener.class);
    ctx.addContextInitParameter("contextConfigLocation", "classpath*:**/httplite-context.xml");

    final ServletRegistration dwrServlet = ctx.addServlet("dwr", DwrSpringServlet.class);
    dwrServlet.addMapping("/dwr/*");
    dwrServlet.setLoadOnStartup(1);
    dwrServlet.setInitParameter("debug", "true");

    // This is no longer required. Spring manages the DWR objects
    //dwrServlet.setInitParameter("classes", "org.pentaho.di.core.httplite.dwr.RemoteFunctions");

    final ClassLoader origClassLoader = Thread.currentThread().getContextClassLoader();
    ClassLoader pluginClassLoader;
    try {
      PluginInterface plugin = getPluginObject( "httplite" );
      pluginClassLoader = PluginRegistry.getInstance().getClassLoader( plugin );

      // Use the plugin class loader
      Thread.currentThread().setContextClassLoader(pluginClassLoader);

      ctx.deploy(server);

      // Add the StaticHttpHandler to serve static resources from the static folder
      String pluginDirPath = HttpLite.buildPluginFolderPath() + File.separator;
      server.getServerConfiguration().addHttpHandler(
         new StaticHttpHandler(pluginDirPath + "www"), "/static");

      server.start();
    }
    catch (KettlePluginException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      Thread.currentThread().setContextClassLoader(origClassLoader);
    }
  }

  private void stopServer() {
    server.shutdownNow();
  }
}
