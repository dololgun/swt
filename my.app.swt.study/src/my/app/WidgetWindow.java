package my.app;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

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
        parent.setSize(600, 250);
        
        TabFolder tf = new TabFolder(parent, SWT.NONE);
        
        TabItem tabCh3 = new TabItem(tf, SWT.NONE);
        tabCh3.setText("chapter 3");
        tabCh3.setControl(new Ch3Composite(tf));

        TabItem tabCh4 = new TabItem(tf, SWT.NONE);
        tabCh4.setText("chapter 4");
        tabCh4.setControl(new Ch4Composite(tf));

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
