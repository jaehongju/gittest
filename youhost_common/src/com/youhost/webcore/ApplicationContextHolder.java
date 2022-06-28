package com.youhost.webcore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class ApplicationContextHolder implements ApplicationContextAware, BeanFactoryPostProcessor {

	private static ApplicationContext ctx;
	private static ConfigurableListableBeanFactory factory;
		
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}
	public static BeanDefinitionRegistry getBeanDefinitionRegistry(){
		return (BeanDefinitionRegistry) factory;
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		factory = beanFactory;
	}
	
	public static ApplicationContext getContext(){
		return ctx;
	}

	public ApplicationContext getApplicationContext(){
		return ctx;
	}
	
	public static List<String> getMappedRequestUrls(){
		List<String> listMappedRequests = new ArrayList<String>();
		
		for(String requestMappingHandlerBeanName : getContext().getBeanNamesForType(RequestMappingHandlerMapping.class)){
			RequestMappingHandlerMapping mp = getContext().getBean(requestMappingHandlerBeanName, RequestMappingHandlerMapping.class);
			if(mp!=null){
				for(Map.Entry<RequestMappingInfo,HandlerMethod> entry : mp.getHandlerMethods().entrySet()){
					for(String s : entry.getKey().getPatternsCondition().getPatterns()){
						listMappedRequests.add(s);
					}
				}
			}
		}
		
		return listMappedRequests;
	}
}