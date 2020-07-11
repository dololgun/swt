package com.sam.e4.clock.ui.views;

import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.sam.e4.clock.ui.internal.TimeZoneIDColumn;

public class TimeZoneTableView extends ViewPart {

	// jface 에서 제공하는 트리뷰어를 사용한다.
	private TableViewer tableViewer;

	@Override
	public void createPartControl(Composite parent) {
		
		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		new TimeZoneIDColumn().addColumnTo(tableViewer);
		tableViewer.setInput(Stream.of(TimeZone.getAvailableIDs()).map(TimeZone::getTimeZone).collect(Collectors.toList()).toArray());
		getSite().setSelectionProvider(tableViewer);

	}

	@Override
	public void setFocus() {

		tableViewer.getControl().setFocus();
	}

}
