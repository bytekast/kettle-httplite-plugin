package org.pentaho.di.ui.spoon.dialog;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.ui.xul.impl.AbstractXulEventHandler;

/**
 * @author Rowell Belen
 */
public class HttpLiteController extends AbstractXulEventHandler {

  public void openHttpLite() throws KettleException {
    showHttpLiteDialog();
  }

  public void showHttpLiteDialog() {
    final String url = "http://localhost:3388/static/index.html";
    HttpLiteDialog httpLiteDialog = new HttpLiteDialog( Spoon.getInstance().getShell(), "AngularJS Test", url);
    httpLiteDialog.open();
  }

  public String getName() {
    return "HttpLiteController";
  }
}
