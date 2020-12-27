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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;


/**
 * @author geonho
 *
 */
public class Ch3Group extends Composite {

    /**
     * @param parent
     */
    public Ch3Group(Composite parent) {

        super(parent, SWT.NONE);
        
        Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
        group.setText("그룹 라벨");
        
        Label label = new Label(group, SWT.None);
        label.setText("버튼 2개 : ");
        label.setLocation(20, 20);
        label.pack();
        
        Button button1 = new Button(group, SWT.PUSH);
        button1.setText("Push button");
        button1.setLocation(20, 45);
        button1.pack();
        
        Button button2 = new Button(group, SWT.CHECK);
        button2.setText("check button");
        button2.setBounds(20, 75, 120, 30);

        group.pack();
    }

}
