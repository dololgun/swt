package com.sam.e4.clock.ui.internal;

import java.util.TimeZone;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;


public class TimeZoneViewerComparator extends ViewerComparator {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		
		int compare;
		if (e1 instanceof TimeZone && e2 instanceof TimeZone) {
			TimeZone t1 = (TimeZone)e1;
			TimeZone t2 = (TimeZone)e2;
			compare = t2.getOffset(System.currentTimeMillis()) - t1.getOffset(System.currentTimeMillis());
			
		} else {
			compare = e1.toString().compareTo(e2.toString());
		}
		
		// reverse 옵션 추가
		boolean reverse = Boolean.parseBoolean(String.valueOf(viewer.getData("REVERSE")));
		return reverse ? -compare : compare;
	}
	
}
