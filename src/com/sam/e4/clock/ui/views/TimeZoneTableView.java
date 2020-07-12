package com.sam.e4.clock.ui.views;

import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ViewPart;

import com.sam.e4.clock.ui.internal.TimeZoneColumnDisplayName;
import com.sam.e4.clock.ui.internal.TimeZoneColumnID;
import com.sam.e4.clock.ui.internal.TimeZoneColumnOffset;
import com.sam.e4.clock.ui.internal.TimeZoneColumnSummerTime;
import com.sam.e4.clock.ui.internal.TimeZoneSelectionListener;

public class TimeZoneTableView extends ViewPart {

	// jface 에서 제공하는 트리뷰어를 사용한다.
	private TableViewer tableViewer;
	private TimeZoneSelectionListener selectionListener;

	@Override
	public void createPartControl(Composite parent) {

		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());

		// 테이블 칼럼추가
		new TimeZoneColumnID().addColumnTo(tableViewer);
		new TimeZoneColumnDisplayName().addColumnTo(tableViewer);
		new TimeZoneColumnOffset().addColumnTo(tableViewer);
		new TimeZoneColumnSummerTime().addColumnTo(tableViewer);

		// 테이블 viewer에 데이터 입력
		tableViewer.setInput(Stream.of(TimeZone.getAvailableIDs()).map(TimeZone::getTimeZone).collect(Collectors.toList()).toArray());

		// 셀렉션 프로바이더를 테이블 뷰어로 전달
		getSite().setSelectionProvider(tableViewer);

		// 셀력션 리스너 생성
		selectionListener = new TimeZoneSelectionListener(tableViewer, getSite().getPart());

		// 샐력션 리스너 워크벤치.셀렉션 서비스에 등록
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

		/*
		 * 컨텍스트 메뉴 등록
		 */
		MenuManager manager = new MenuManager("#PopupMenu");
		Menu menu = manager.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);

		/*
		 *  비어있는 메뉴는 등록을 하여도 아무런 효과를 내지 않는다. 이를 위해 액션을 생성하여 등록한다. 하지만 액션은 deprecated 되었다.
		 *  대체하는 것은 command, handler, menu 가 필요하다
		 *  command는 plugin.xml의 extension point에서 추가한다
		 */
		Action deprecated = new Action() {

			@Override
			public void run() {

				MessageDialog.openInformation(null, "Hello", "World");
			}
		};
		deprecated.setText("Hello");
		manager.add(deprecated);
		
		
	}

	@Override
	public void setFocus() {

		tableViewer.getControl().setFocus();
	}

	@Override
	public void dispose() {

		// 셀렉션 리스너 삭제
		if (selectionListener != null) {
			getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			selectionListener = null;
		}
		super.dispose();
	}

}
