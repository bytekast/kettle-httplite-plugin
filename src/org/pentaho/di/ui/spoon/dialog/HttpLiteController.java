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

  public static void showHttpLiteDialog() {
    HttpLiteDialog httpLiteDialog = new HttpLiteDialog( Spoon.getInstance().getShell() );
    httpLiteDialog.open();
  }

  public String getName() {
    return "HttpLiteController";
  }

}
