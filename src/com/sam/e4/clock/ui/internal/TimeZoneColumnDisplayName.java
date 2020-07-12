package com.sam.e4.clock.ui.internal;

import java.util.TimeZone;

public class TimeZoneColumnDisplayName extends TimeZoneColumn {

	@Override
	public String getText(Object element) {
		// 
		if (element instanceof TimeZone) {
			return ((TimeZone)element).getDisplayName();
		} else {
			return "";
		}
	}

	@Override
	public String getTitle() {
		// 
		return "Display Name";
	}

}
