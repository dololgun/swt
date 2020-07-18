package com.sam.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class Rainbow {

	public static final Object[] colors = { "Red", "Orange", "Yellow", "Green", "Blue", "Indogo", "Violet" };
	
	@Inject
	private ESelectionService selectionService;
	
	@PostConstruct
	public void create(Composite parent) {
		
		ListViewer lv = new ListViewer(parent, SWT.NONE);
		lv.setContentProvider(new ArrayContentProvider());
		lv.setInput(colors);
		
		lv.addSelectionChangedListener(e -> {
			selectionService.setSelection(e.getSelection());
		});
	}

}
