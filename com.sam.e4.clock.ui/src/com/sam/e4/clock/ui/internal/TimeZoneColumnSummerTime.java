package com.sam.e4.clock.ui.internal;

import java.util.TimeZone;

public class TimeZoneColumnSummerTime extends TimeZoneColumn {

	@Override
	public String getText(Object element) {
		// 
		if (element instanceof TimeZone) {
			return String.valueOf(((TimeZone)element).useDaylightTime());
		} else {
			return "";
		}
	}

	@Override
	public String getTitle() {
		// 
		return "Summer Time";
	}

}
