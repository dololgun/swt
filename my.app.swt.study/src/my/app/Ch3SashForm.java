/*
 * You are strictly prohibited to copy, disclose, distribute, modify, or use this program in part
 * or as a whole without the prior written consent of Samsung Asset Management & Samsung SDS.
 * Samsung Asset Management & Samsung SDS owns the intellectual property rights in and to this program.
 * ~
 * (Copyright ⓒ 2019 Samsung Asset Management & Samsung SDS. All Rights Reserved| Confidential)
 * ~
 */
package my.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author geonho
 *
 */
public class Ch3SashForm extends Composite {

    /**
     * @param parent
     * @param style
     */
    public Ch3SashForm(Composite parent) {

        super(parent, SWT.NONE);
        
        SashForm sf = new SashForm(this, SWT.VERTICAL);
        sf.setSize(120, 80);
        
        Button button1 = new Button(sf, SWT.ARROW | SWT.UP);
        button1.setSize(120, 40);
        
        Button button2 = new Button(sf, SWT.ARROW | SWT.DOWN);
        button2.setBounds(0, 40, 120, 40);
    }

}
