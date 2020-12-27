/*
 * You are strictly prohibited to copy, disclose, distribute, modify, or use this program in part 
 * or as a whole without the prior written consent of Samsung Asset Management & Samsung SDS.
 * Samsung Asset Management & Samsung SDS owns the intellectual property rights in and to this program.
 * ~
 * (Copyright ⓒ 2019 Samsung Asset Management & Samsung SDS. All Rights Reserved| Confidential)
 * ~
 */
package my.app;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;

/**
 * @author geonho
 *
 */
public class Ch4StatusAction extends Action {
    
    private StatusLineManager sm;
    private int triggerCount = 0;

    public Ch4StatusAction(StatusLineManager sm) {
        
        super("&Trigger@Ctrl+T", AS_PUSH_BUTTON);
        this.sm = sm;
        this.setToolTipText("Trigger the Action");
//        this.setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(), "eclipse.gif"));
    }

    @Override
    public void run() {

        this.triggerCount++;
        sm.setMessage("the status action has fired. count: " + triggerCount);
        super.run();
    }
    
    
    
}
