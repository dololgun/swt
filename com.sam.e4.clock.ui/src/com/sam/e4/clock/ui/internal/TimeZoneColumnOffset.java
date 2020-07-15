package com.sam.e4.clock.ui.internal;

import java.util.TimeZone;

public class TimeZoneColumnOffset extends TimeZoneColumn {

	@Override
	public String getText(Object element) {
		// 
		if (element instanceof TimeZone) {
			return String.valueOf(((TimeZone)element).getOffset(System.currentTimeMillis()));
		} else {
			return "";
		}
	}

	@Override
	public String getTitle() {
		// 
		return "Offset";
	}

}
