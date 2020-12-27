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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author geonho
 *
 */
public class Ch4Composite extends Ch4MouseKey {

    /**
     * @param parent
     */
    public Ch4Composite(Composite parent) {

        super(parent);
        
        Button launch = new Button(this, SWT.PUSH);
        launch.setText("Launch");
        launch.setLocation(40, 120);
        launch.pack();
        
        // SWT의 이벤트 리스너 방식, mouseListener 인터페이스를 사용하는 것보다 adapter를 사용하는 것이 효율적이다.
        launch.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(MouseEvent e) {
                
                // ch4Contriboution 화면 오픈
                Ch4Contribution ch4Contribution = new Ch4Contribution();
                ch4Contribution.open();
            }
            
        });
        
    }

    
}
