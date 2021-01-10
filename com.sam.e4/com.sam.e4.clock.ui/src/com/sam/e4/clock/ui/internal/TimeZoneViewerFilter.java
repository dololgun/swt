package com.sam.e4.clock.ui.internal;

import java.util.TimeZone;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class TimeZoneViewerFilter extends ViewerFilter {

	private String pattern;

	public TimeZoneViewerFilter(String pattern) {

		this.pattern = pattern;
	}

	@Override
	public boolean select(Viewer v, Object parent, Object element) {

		//
		if (element instanceof TimeZone) {
			TimeZone timeZone = (TimeZone) element;
			return timeZone.getDisplayName().contains(pattern);
		} else {
			return true;
		}
	}

}
