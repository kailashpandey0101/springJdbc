package com.imcs.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.imcs.spring.factory.AppConfig;

public final class AppContext {

	private static ApplicationContext context=null;
	
	public static ApplicationContext getInstance() {
		if(context==null) {
			context=getApplicationContext();
		}
		return context;
	}

	private static ApplicationContext getApplicationContext() {
		ApplicationContext context= new AnnotationConfigApplicationContext(AppConfig.class);
		return context;
	}
}
