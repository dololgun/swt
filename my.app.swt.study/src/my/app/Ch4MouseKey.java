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
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * SWT의 Listener 이벤트를 학습한다.
 * 리스너는 크게 2가지 종류가 있다.
 * 1. 형식 리스너
 *   - 특정 이벤트에 맞춰 사용하는 이벤트(예: 마우스 리스너, 키 리스너)로 adapter로 사용하는 것이 더욱 편하다.
 * 2. 비형식 리스터
 *   - 어떤 이벤트에도 할당할 수 있는 리스너 인터페이스 그 자체
 * 
 * @author geonho
 *
 */
public class Ch4MouseKey extends Composite {

    private Label output;

    private Listener untypedListener = e -> {
        switch (e.type) {
        case SWT.MouseEnter:
            output.setText("mouse enter event");
            break;
        case SWT.MouseExit:
            output.setText("mouse exit event");
            break;
        }
    };

    public Ch4MouseKey(Composite parent) {

        super(parent, SWT.NULL);

        Button typed = new Button(this, SWT.PUSH);
        typed.setText("Typed");
        typed.setLocation(20, 20);
        typed.pack();

        typed.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                keyHandler();
            }
        });

        Button untyped = new Button(this, SWT.PUSH);
        untyped.setText("untyped");
        untyped.setLocation(20, 40);
        untyped.pack();

        untyped.addListener(SWT.MouseEnter, untypedListener);
        untyped.addListener(SWT.MouseExit, untypedListener);

        output = new Label(this, SWT.SHADOW_OUT);
        output.setBounds(40, 70, 200, 40);
        output.setText("no event");
        output.pack();

    }

    private void keyHandler() {

        output.setText("key event");
    }
}
