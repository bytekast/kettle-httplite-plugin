package org.pentaho.di.ui.trans.steps.mystep;

import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.httplite.service.MyStepDialogBridge;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.di.ui.spoon.dialog.HttpLiteDialog;
import org.pentaho.di.ui.trans.step.BaseStepDialog;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author Rowell Belen
 */
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class MyStepDialog extends BaseStepDialog implements StepDialogInterface {

  @Autowired
  private MyStepDialogBridge myStepDialogBridge;

  public MyStepDialog( Shell parent, Object in, TransMeta transMeta, String sname ) {
    super( parent, (BaseStepMeta) in, transMeta, sname );

    if(myStepDialogBridge != null){
      myStepDialogBridge.registerComponent(this);
    }
  }

  @Override
  public String open() {
    final String url = "http://localhost:3388/static/index.html";
    HttpLiteDialog httpLiteDialog = new HttpLiteDialog( Spoon.getInstance().getShell(), "HttpLite", url, "AngularJS Test" );
    httpLiteDialog.open();

    return this.stepname;
  }

  public void setStepName(String stepName){
    this.stepname = stepName;
    logBasic(this.stepname);
  }
}
