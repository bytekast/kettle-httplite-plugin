package org.pentaho.di.ui.trans.steps.mystep;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.di.ui.spoon.dialog.HttpLiteDialog;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

import java.util.UUID;

/**
 * @author Rowell Belen
 */
public class MyStepDialog extends BaseStepDialog implements StepDialogInterface {

  private final String ID = UUID.randomUUID().toString();

  public MyStepDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
    super( parent, (BaseStepMeta) in, transMeta, sname );
  }

  @Override
  public String open() {
    new MyStepDialogProxy(this); // register remote proxy listener for web requests
    final String url = "http://localhost:3388/static/index.html?id=" + getID(); // pass the unique id to the client
    HttpLiteDialog httpLiteDialog = new HttpLiteDialog( Spoon.getInstance().getShell(), "HttpLite", url, "AngularJS Test" );
    httpLiteDialog.open();

    return this.stepname;
  }

  public String getID() {
    // return ID;
    return DigestUtils.md5Hex(getStepname());
  }

  public String getStepname(){
    return this.stepname;
  }

  public void setStepname(String stepname){
    this.stepname = stepname;
  }
}
