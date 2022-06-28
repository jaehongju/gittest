package com.youhost.webcore.beans;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;

import com.youhost.webcore.ApplicationContextHolder;

public class BeanInitializer {
	
	private ApplicationContext applicationContext;
	private BeanDefinitionRegistry beanDefinitionRegistry;
	
	@SuppressWarnings("static-access")
	public BeanInitializer(ApplicationContextHolder ctxHolder){
		this.applicationContext = ctxHolder.getContext();
		this.beanDefinitionRegistry = ctxHolder.getBeanDefinitionRegistry();
	}
	
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	
	protected BeanDefinition getRootBeanDefinition(String className){
		return BeanDefinitionBuilder.rootBeanDefinition(className).getBeanDefinition();
	}
	
	protected void resolveBean(String beanName, BeanDefinition beanDefinition){
		beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
		logger.info("Bean 등록 : [NAME="+beanName+"],[CLASS="+beanDefinition.getBeanClassName()+"]");
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getBean(String beanName){
		return (T) applicationContext.getBean(beanName);
	}
	
	protected <T> T getBean(String beanName, Class<T> requiredType){
		return applicationContext.getBean(beanName,requiredType);
	}
}