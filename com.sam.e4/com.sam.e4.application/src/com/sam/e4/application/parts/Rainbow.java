package com.sam.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class Rainbow {

	public static final Object[] colors = { "Red", "Orange", "Yellow", "Green", "Blue", "Indogo", "Violet" };
	
	@Inject
	private ESelectionService selectionService;
	
	@Inject
	private EventBroker eventBroker; 
	
	@PostConstruct
	public void create(Composite parent) {
		
		ListViewer lv = new ListViewer(parent, SWT.NONE);
		lv.setContentProvider(new ArrayContentProvider());
		lv.setInput(colors);
		

		lv.addSelectionChangedListener(e -> {
			// 셀렉션 서비스에 이벤트를 전달한다. (1번방법)
			// selectionService.setSelection(e.getSelection());
			
			// 이벤트브로커에 셀렉션을 전달한다. (2번방법)
			IStructuredSelection sel = (IStructuredSelection) e.getSelection();
			Object color = sel.getFirstElement();
			// async방식
			eventBroker.post("rainbow/color",  color);
			
			// sync방식
			eventBroker.send("d", "");
			
		});
	}

}
