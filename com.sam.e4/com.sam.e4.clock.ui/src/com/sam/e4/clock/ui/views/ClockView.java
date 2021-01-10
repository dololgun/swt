package com.sam.e4.clock.ui.views;

import java.util.TimeZone;
import java.util.stream.Stream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ClockView extends ViewPart {
	
	private Combo timezones;

    @Override
    public void createPartControl(Composite parent) {
        
        Object[] objects = parent.getDisplay().getDeviceData().objects;
        System.out.println("objects's count = " + Stream.of(objects).count()); 
        
        RowLayout layout = new RowLayout(SWT.HORIZONTAL); 
        parent.setLayout(layout);

        // 캔버스를 가져온다.
        final Canvas clock1 = new ClockWidget(parent, SWT.NONE, new RGB(255, 0, 0));
        clock1.setLayoutData(new RowData(20, 20));
        final Canvas clock2 = new ClockWidget(parent, SWT.NONE, new RGB(0, 255, 0));
        final Canvas clock3 = new ClockWidget(parent, SWT.NONE, new RGB(0, 0, 255));
        clock3.setLayoutData(new RowData(100, 100));
        
        // timezone 콤보박스 생성
        this.timezones = new Combo(parent, SWT.DROP_DOWN);
        this.timezones.setVisibleItemCount(5);
        String[] ids = TimeZone.getAvailableIDs();
        Stream.of(ids).forEach(s -> timezones.add(s));
    }

    @Override
    public void setFocus() {
    	this.timezones.setFocus();
    }
}
