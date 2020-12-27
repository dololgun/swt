/*
 * You are strictly prohibited to copy, disclose, distribute, modify, or use this program in part
 * or as a whole without the prior written consent of Samsung Asset Management & Samsung SDS.
 * Samsung Asset Management & Samsung SDS owns the intellectual property rights in and to this program.
 * ~
 * (Copyright ⓒ 2019 Samsung Asset Management & Samsung SDS. All Rights Reserved| Confidential)
 * ~
 */
package my.app;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * jface의 contribution과 action을 테스트할 수 있는 화면이다.
 * contribution은 contirbution manager와 연결되어 동작한다.
 * 
 * <p>
 * contributionManager는 action을 추가할 수 있는 add 매소드를 가지고 있고 이 매소드를 사용하여 action을 추가할 수있다.
 * 또 다른 방법으로, contributionItem의 fill 매소드를 이용하여 대상 manager에 action을 할당할 수 있다.  
 * 
 * @author geonho
 *
 */
public class Ch4Contribution extends ApplicationWindow {

    StatusLineManager slm = new StatusLineManager();
    Ch4StatusAction statusAction = new Ch4StatusAction(slm);

    // 액션컨트리부션아이템을 생성
    // 액션컨트리뷰선 아이템은 액션을 가지고 있다.
    ActionContributionItem aci = new ActionContributionItem(statusAction);

    /**
     * @param parentShell
     */
    public Ch4Contribution() {

        super(null);
        addStatusLine();
        addMenuBar();
        addToolBar(SWT.FLAT | SWT.WRAP);
    }

    @Override
    protected Control createContents(Composite parent) {

        getShell().setText("Action/Contirbution Example");
        parent.setSize(290, 150);

        // 화면에 액션을 채운다. fill의 인자가 composite이면 fill의 형태는 button이다.
        aci.fill(parent);

        return parent;
    }

    public static void main(String[] args) {

        Ch4Contribution swin = new Ch4Contribution();
        swin.setBlockOnOpen(true);
        swin.open();
        Display.getCurrent().dispose();
    }

    @Override
    protected MenuManager createMenuManager() {

        MenuManager mainMenu = new MenuManager();
        MenuManager actionMenu = new MenuManager("Menu");
        // 아래와 같이 manager(contributionManager)에 action이 직접 할당되면 암묵적으로 ActionContributionItem이 생성된다.
        // action은 계속 재 활용 할 수 있지만 actionContributionItem을 사용하려면 add할때마다 new해야 한다.
        mainMenu.add(actionMenu);
        actionMenu.add(this.statusAction);
        return mainMenu;
    }

    @Override
    protected ToolBarManager createToolBarManager(int style) {

        ToolBarManager toolBarManger = new ToolBarManager(style);
        toolBarManger.add(new ActionContributionItem(this.statusAction));
        return toolBarManger;
    }

    @Override
    protected StatusLineManager createStatusLineManager() {

        return slm;
    }
}
