package org.pentaho.di.ui.spoon.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.ui.core.PropsUI;
import org.pentaho.di.ui.core.gui.GUIResource;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

/**
 * @author Rowell Belen
 */
public class HttpLiteDialog extends Dialog {

  private String dialogTitle;
  private String url;

  private Browser wBrowser;
  private FormData fdBrowser;

  private Shell shell;
  private PropsUI props;

  private Display display;

  public HttpLiteDialog(Shell parent, String dialogTitle, String url) {
    super( parent, SWT.NONE );
    props = PropsUI.getInstance();
    this.dialogTitle = dialogTitle;
    this.url = url;
  }

  public void open() {
    Shell parent = getParent();
    display = parent.getDisplay();

    shell = new Shell( parent, SWT.RESIZE | SWT.MAX | SWT.MIN );
    shell.setImage( GUIResource.getInstance().getImageSpoon() );
    props.setLook( shell );

    FormLayout formLayout = new FormLayout();

    shell.setLayout( formLayout );
    shell.setText( dialogTitle );


    // Canvas
    wBrowser = new Browser( shell, SWT.NONE );
    props.setLook( wBrowser );

    fdBrowser = new FormData();
    fdBrowser.left = new FormAttachment( 0, 10 );
    fdBrowser.top = new FormAttachment( 0, 5 );
    fdBrowser.right = new FormAttachment( 100, 0 );
    fdBrowser.bottom = new FormAttachment( 100, 0 );
    wBrowser.setLayoutData( fdBrowser );

    // Detect [X] or ALT-F4 or something that kills this window...
    shell.addShellListener( new ShellAdapter() {
      public void shellClosed( ShellEvent e ) {
        ok();
      }
    } );

    wBrowser.setUrl( url );

    setSize(shell);

    shell.open();
    while ( !shell.isDisposed() ) {
      if ( !display.readAndDispatch() ) {
        display.sleep();
      }
    }
  }

  public void dispose() {
    shell.dispose();
  }

  public void ok() {
    dispose();
  }

  public void setSize(final Shell shell){
    BaseStepDialog.setSize(shell, 800, 600, true);
  }

  public void close(){

    // Run in Display thread
    display.asyncExec(new Runnable() {
      @Override
      public void run() {
        shell.close();
      }
    });
  }
}
