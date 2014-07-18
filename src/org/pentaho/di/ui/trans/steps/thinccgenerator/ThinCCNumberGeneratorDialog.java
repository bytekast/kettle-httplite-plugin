package org.pentaho.di.ui.trans.steps.thinccgenerator;

import com.thoughtworks.xstream.XStream;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.httplite.dwr.model.ThinCCGeneratorModel;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.trans.steps.thinccgenerator.ThinCCNumberGeneratorMeta;
import org.pentaho.di.ui.core.dialog.ShowHelpDialog;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.di.ui.spoon.dialog.HttpLiteDialog;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

import java.util.UUID;

/**
 * @author Rowell Belen
 */
public class ThinCCNumberGeneratorDialog extends BaseStepDialog implements StepDialogInterface {

  private final String ID = UUID.randomUUID().toString();
  private final XStream xstream = new XStream();

  private ThinCCNumberGeneratorDialogProxy dialogProxy;
  private HttpLiteDialog httpLiteDialog;

  private ThinCCNumberGeneratorMeta input;
  private ThinCCGeneratorModel model;

  public ThinCCNumberGeneratorDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
    super( parent, (BaseStepMeta) in, transMeta, sname );
    input = (ThinCCNumberGeneratorMeta) in;

    // de-serialize
    model = input.getThinCCGeneratorModel();
    logBasic("De-serialize: " + xstream.toXML(this.model));
    if(model == null){
      model = new ThinCCGeneratorModel();
    }
    model.setStepName(this.stepname);
  }

  @Override
  public String open() {

    dialogProxy = new ThinCCNumberGeneratorDialogProxy(this);

    final String url = "http://localhost:3388/static/thinccgenerator/index.html?id=" + getID(); // pass the unique id to the client
    httpLiteDialog =
       new HttpLiteDialog( Spoon.getInstance().getShell(), "Thin Credit Card Number Generator", url){

        @Override
        public void ok(){
          logBasic("HttpLiteDialog: closing: " + getID());
          super.ok();
          onClose();
        }

         @Override
         public void setSize(final Shell shell){
           BaseStepDialog.setSize( shell, 900, 600, true );
         }

       };
    logBasic("HttpLiteDialog: opening: " + getID());
    httpLiteDialog.open();

    return this.stepname;
  }

  private void onClose(){

    persistModel();

    logBasic("FileExistsWebDialog: closing: " + this.getID());
    if(dialogProxy != null){
      dialogProxy.destroy(); // unsubscribe remote proxy listener
      dialogProxy = null; // remove reference for garbage collection
    }
  }

  public String getID() {
    return ID;
  }

  public void close(){
    logBasic("trying to close...");
    httpLiteDialog.close();
  }

  public void showHelp(){
    Spoon.getInstance().getShell().getDisplay().asyncExec(new Runnable() {
      @Override
      public void run() {
        ShowHelpDialog dialog = new ShowHelpDialog(Spoon.getInstance().getShell(), "Help", "http://lmgtfy.com/?q=spoon+random+credit+card+number+generator+step", "" );
        dialog.open();
      }
    });
  }

  private void persistModel(){
    // serialize
    input.setThinCCGeneratorModel(model);
    logBasic("Serialize: " + xstream.toXML(this.model));
  }

  public ThinCCGeneratorModel getModel() {
    return model;
  }

  public void setModel(ThinCCGeneratorModel model) {
    this.model = model;
    this.stepname = this.model.getStepName();
  }
}
