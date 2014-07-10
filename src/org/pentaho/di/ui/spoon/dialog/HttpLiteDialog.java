package org.pentaho.di.ui.spoon.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.ui.core.PropsUI;
import org.pentaho.di.ui.core.dialog.ShowBrowserDialog;
import org.pentaho.di.ui.core.dialog.ShowHelpDialog;
import org.pentaho.di.ui.core.gui.GUIResource;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

/**
 * @author Rowell Belen
 */
public class HttpLiteDialog extends ShowHelpDialog {

  public HttpLiteDialog(Shell parent, String dialogTitle, String url, String header) {
    super(parent, dialogTitle, url, header);
  }


  /*
  private Shell shell;
  private PropsUI props;

  public HttpLiteDialog(Shell parent) {
    super( parent, SWT.NONE );
    props = PropsUI.getInstance();
  }

  public void open() {
    Shell parent = getParent();
    Display display = parent.getDisplay();

    shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX | SWT.MIN);
    props.setLook(shell);
    shell.setImage(GUIResource.getInstance().getImageSpoon());

    BaseStepDialog.setSize(shell);

    shell.open();

    shell.addShellListener(new ShellAdapter() {
      public void shellActivated(ShellEvent e) {

      }
    });

    // Detect X or ALT-F4 or something that kills this window...
    shell.addShellListener(new ShellAdapter() {
      public void shellClosed(ShellEvent e) {

      }
    });

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }
  */
}
