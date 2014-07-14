package org.pentaho.di.core.httplite;

import org.directwebremoting.spring.DwrSpringServlet;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
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
import org.springframework.web.context.ContextLoaderListener;

import java.io.File;
import java.io.IOException;

/**
 * @author Rowell Belen
 */
@SpoonPlugin( id = "httplite", image = "" )
@SpoonPluginCategories( { "spoon" } )
public class HttpLite implements SpoonPluginInterface {

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

  public static PluginInterface getPluginObject( String pluginId ) {
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

  final private HttpServer server = new HttpServer();

  private void startServer(){
    // Initialize Grizzly HttpServer
    NetworkListener listener = new NetworkListener("grizzly2", "localhost", 3388);
    server.addListener(listener);

    // Initialize web context
    final WebappContext ctx = new WebappContext("ctx", "/api");
    ctx.addListener(ContextLoaderListener.class);
    ctx.addContextInitParameter("contextConfigLocation", "classpath*:**/httplite-context.xml");

    final ServletRegistration dwrServlet = ctx.addServlet("dwr", DwrSpringServlet.class);
    dwrServlet.addMapping("/dwr/*");
    dwrServlet.setLoadOnStartup(1);
    dwrServlet.setInitParameter("debug", "true");

    // This is no longer required. Spring manages the DWR objects
    //dwrServlet.setInitParameter("classes", "org.pentaho.di.core.httplite.dwr.RemoteFunctions");

    RunInPluginClassLoader.run(new Runnable() {
      @Override
      public void run(){

        ctx.deploy(server);

        // Add the StaticHttpHandler to serve static resources from the static folder
        String pluginDirPath = HttpLite.buildPluginFolderPath() + File.separator;
        StaticHttpHandler staticHandler = new StaticHttpHandler(pluginDirPath + "www");
        staticHandler.setFileCacheEnabled(false); // disable cache for dev purposes
        server.getServerConfiguration().addHttpHandler( staticHandler, "/static");

        try {
          server.start();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void stopServer() {
    server.shutdownNow();
  }
}
