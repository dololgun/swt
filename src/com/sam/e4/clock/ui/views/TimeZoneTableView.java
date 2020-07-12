package com.sam.e4.clock.ui.views;

import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
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
