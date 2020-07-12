package com.sam.e4.clock.ui.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class ShowTheTime extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// 셀렉션을 얻는다. 
		ISelection sel = HandlerUtil.getActiveWorkbenchWindow(event).getSelectionService().getSelection();
		
		if (!(sel instanceof IStructuredSelection) || sel.isEmpty()) {
			return null;
		}
		
		Object value = ((IStructuredSelection)sel).getFirstElement();
		
		if (!(value instanceof TimeZone)) {
			return null;
		}
		
		TimeZone timeZone = (TimeZone)value;
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(timeZone);
		
		MessageDialog.openInformation(null, "The time is", sdf.format(new Date()));
		
		return null;
	}


}
