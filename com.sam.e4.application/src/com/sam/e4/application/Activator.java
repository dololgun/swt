package com.sam.e4.application;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		
		Dictionary<String, Object> properties = new Hashtable<>();		
		properties.put("service.context.key", "math.random");		
		RandomFuntion implementation = new RandomFuntion();
		bundleContext.registerService("org.eclipse.e4.core.contexts.IContextFunction", implementation, properties);
		
		
		Activator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
