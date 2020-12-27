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
import org.eclipse.swt.widgets.Composite;

/**
 * @author geonho
 *
 */
public class Ch3Composite extends Composite {

    /**
     * @param parent
     * @param style
     */
    public Ch3Composite(Composite parent) {

        super(parent, SWT.None);

        parent.getShell().setText("Chapter 3 Composite");

        Ch3Group ch3Group = new Ch3Group(this);
        ch3Group.setLocation(0, 0);
        ch3Group.pack();

        Ch3SashForm ch3SashForm = new Ch3SashForm(this);
        ch3SashForm.setLocation(150, 20);
        ch3SashForm.pack();
        
        this.pack();
    }

}
