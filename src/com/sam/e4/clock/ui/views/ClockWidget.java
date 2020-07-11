/*
 * You are strictly prohibited to copy, disclose, distribute, modify, or use this program in part
 * or as a whole without the prior written consent of Samsung Asset Management & Samsung SDS.
 * Samsung Asset Management & Samsung SDS owns the intellectual property rights in and to this program.
 * ~
 * (Copyright ⓒ 2019 Samsung Asset Management & Samsung SDS. All Rights Reserved| Confidential)
 * ~
 */
package com.sam.e4.clock.ui.views;

import java.time.LocalDateTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/**
 * <p>
 * <b>이름 : </b>클래스(패키지) 한글명
 * 
 * <p>
 * 클래스(패키지)의 역할에 대해 서술 하시오
 * 
 * <p>
 * History
 * 
 * <pre>
 * 수 정 일  수 정 자           수정내용
 * --------  -----------------  -------------------------------
 * 20.07.03             최초생성
 * </pre>
 */
public class ClockWidget extends Canvas {

    private final Color color;
    /**
     * 
     */
    public ClockWidget(Composite parent, int style, RGB rgb) {

        super(parent, style);
        
        // 누수가 되고 있어요
        color = new Color(parent.getDisplay(), rgb);

        // 캔버스에 페인트 이벤트리스너를 등록한다.
        this.addPaintListener(e -> {
        	// 화면을 다시 그릴 때 paint이벤트가 발생한다.
            paintControl(e);
        });
        
        // 위젯이 사라질 때 발생하는 이벤트
        // color 객체를 직접 dispose하여 메모리 누수를 방지한다.
        this.addDisposeListener(e -> {
    		if (this.color != null && !color.isDisposed()) {
    			color.dispose();
    		}
        });

        // 일반적으로 여기에 스레드를 적용하지 않는다고 한다.
        new Thread(() -> {
            while (!this.isDisposed()) {

                // UI스레드를 사용하기위해 다음과 같이 처리해야 한다.
                // 직접 redraw를 호출하면 접근 오류가 발생한다.
                // clock.redraw();
                this.getDisplay().asyncExec(() -> {
                    if (this != null && !this.isDisposed()) {
                        this.redraw();
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    return;
                }
            }
        }, "TickTock").start();
    }

    /**
     * 도형을 그린다. 
     * @param e
     */
    private void paintControl(PaintEvent e) {

        e.gc.drawArc(e.x, e.y, e.width - 1, e.height - 1, 0, 360);
        
        int sec = LocalDateTime.now().getSecond();
        int arc = (15 - sec) * 6 % 360;
//        Color blue = e.display.getSystemColor(SWT.COLOR_BLUE);
        e.gc.setBackground(this.color);
        e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 1, 2);
    }

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {

        int size;
        if (wHint == SWT.DEFAULT) {
            size = hHint;
        } else if (hHint == SWT.DEFAULT) {
            size = wHint;
        } else {
            size = Math.min(wHint, hHint);
        }

        if (size == SWT.DEFAULT) {
            size = 50;
        }

        return new Point(size, size);
    }

    /**
     * 여기다 리소를 해제해도 아무 의미 없다. 동작하지 않는다.
     * 이 매소드는 가장 최상위 View에서만 발생하고 해당 View에서는 disposeListener를 호출한다. 
     */
	@Override
	public void dispose() {
		
		if (this.color != null && !color.isDisposed()) {
			color.dispose();
		}
		super.dispose();
	}
    
    

}
