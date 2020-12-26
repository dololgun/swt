package my.app;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * @author geonho
 *
 */
public class WidgetWindow extends ApplicationWindow {

    /**
     * null을 넣으면 새로운 shell을 생성한다.
     */
    public WidgetWindow() {

        super(null);
    }

    @Override
    protected Control createContents(Composite parent) {

        getShell().setText("나의 위젯 윈도우");
        parent.setSize(400, 250);

        return parent;
    }

    public static void main(String[] args) {

        WidgetWindow wwin = new WidgetWindow();
        // open된 동안 닫히는 것을 막기위함.
        wwin.setBlockOnOpen(true);

        // 닫힐때까지 여기서 멈춰있는다.
        wwin.open();

        // wwin이 disallocated 되면 자원을 해제한다.
        Display.getCurrent().dispose();

    }

}
