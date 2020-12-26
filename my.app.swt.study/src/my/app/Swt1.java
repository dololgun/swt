package my.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author geonho
 *
 */
public class Swt1 {

    public static void main(String[] args) {
        
        /*
         * display 객체는 무조건 생성해야 한다. 이것을 통해 os의 위젯과 컨테이너를 사용할 수 있다.
         * 대부분의 어플리케이션이 다소의 display객체를 생성하고 몇 몇 매소드만을 호출하지만 매우 중요한 역할을 수행한다.
         * display의 가장 중요한 역할은 swt의 언어를 os의 언어로 통역하는 것이다.
         * display 객체가 os를 위한 티켓 객체를 생성한다. 
         */
        Display display = new Display();
        Shell shell = new Shell(display, SWT.TITLE | SWT.MIN | SWT.MAX | SWT.RESIZE | SWT.CLOSE);

        Text helloText = new Text(shell, SWT.CENTER);
        helloText.setText("이건호");
        helloText.pack();

        shell.pack();
        // main window 생성되고 화면에 컨트롤이 렌더링된다.
        shell.open();

        while (!shell.isDisposed()) {
            // 운영체제를 통해 들어오는 사용자 엑션을 display객체를 통해 받는다?
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        // 윈도우를 닫으면 shell이 dispose된다. 
        display.dispose();
    }
}
