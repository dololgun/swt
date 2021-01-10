package com.sam.e4.clock.ui.internal;

import java.util.TimeZone;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

public class TimeZoneAdapterFactory implements IAdapterFactory {

	@Override
	public <T> T getAdapter(Object o, Class<T> clazz) {
		// 
		if (IPropertySource.class.isAssignableFrom(clazz) && o instanceof TimeZone) {
			TimeZonePropertySource timeZonePropertySource = new TimeZonePropertySource((TimeZone)o);
			return (T)timeZonePropertySource;
		} else {
			return null;
		}
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] { IPropertySource.class };
	}

}
