package com.sam.e4.clock.ui.internal;

import java.util.TimeZone;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;


public class TimeZoneSelectionListener implements ISelectionListener {
	
	private Viewer viewer;
	private IWorkbenchPart part;
	
	public TimeZoneSelectionListener(Viewer viewer, IWorkbenchPart part) {

		this.viewer = viewer;
		this.part = part;
	}



	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection sel) {
		
		// 자신의 part에서 발생한 이벤트라면 무시해야 한다.
		if (part == this.part) {
			return;
		}
		
		// 변환가능한지 확인
		if (!(sel instanceof IStructuredSelection) || sel.isEmpty()) {
			return;
		}
		
		// 이벤트로 전달된 셀렉션
		Object selected = ((IStructuredSelection)sel).getFirstElement();
		
		// 현재 뷰의 셀렉션
		Object current = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
		
		if (!(selected instanceof TimeZone)) {
			return;
		}
		
		if (selected == current) {
			return;
		}
		
		viewer.setSelection(sel);
		if (viewer instanceof StructuredViewer) {
			((StructuredViewer)viewer).reveal(selected);
		}

	}

}
