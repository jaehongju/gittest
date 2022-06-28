package com.youhost.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import com.youhost.common.TraceOption;


public class TraceUtil {
	
	public static String getTraceString(Map<? extends Object, ? extends Object> m, TraceOption logOption){
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<? extends Object, ? extends Object> entry :m.entrySet() ){
			sb.append(TraceOption.getLinePrefixString(logOption));
			sb.append(entry.toString());
			sb.append(TraceOption.getLineSurfixString(logOption));
			sb.append("\r\n");
		}
		return sb.toString();
	}
	
	public static String getTraceString(List<? extends Object> list, TraceOption logOption){
		StringBuilder sb = new StringBuilder();
		for(Object entry : list ){
			sb.append(TraceOption.getLinePrefixString(logOption));
			sb.append(entry.toString());
			sb.append(TraceOption.getLineSurfixString(logOption));
		}
		return sb.toString();
	}
	
	public static String getTraceString(ServletRequest request, TraceOption logOption){
		StringBuilder sb = new StringBuilder();
		Enumeration<?> enumNames =  request.getParameterNames();
		String keyName = "";
		String[] arrValue = null;
		while(enumNames.hasMoreElements()){
			sb.append(TraceOption.getLinePrefixString(logOption));
			keyName = (String) enumNames.nextElement();
			arrValue = request.getParameterValues(keyName);
			sb.append("[").append(keyName).append("=");
			if(arrValue.length==1){
				sb.append(arrValue[0]);
			}else{
				sb.append("array {");
				for(String s : arrValue){
					sb.append(s).append(",");
				}
				sb.replace(sb.lastIndexOf(","), sb.length(), "}");
			}
			sb.append("]");
			sb.append(TraceOption.getLineSurfixString(logOption));
		}
		return sb.toString();
	}
	
	public static String getTraceString(HttpSession session, TraceOption logOption){
		StringBuilder sb = new StringBuilder();
		Enumeration<?> enumNames =  session.getAttributeNames();
		String keyName = "";
		Object value = null;
		while(enumNames.hasMoreElements()){
			sb.append(TraceOption.getLinePrefixString(logOption));
			keyName = (String) enumNames.nextElement();
			value = session.getAttribute(keyName);
			sb.append("[").append(keyName).append("=");
			sb.append(value).append("]");
			sb.append(TraceOption.getLineSurfixString(logOption));
		}
		return sb.toString();
	}
	
	public static String getTraceString(Object obj, Object parent, TraceOption logOption){
		StringBuilder sb = new StringBuilder();
		
		List<Method> ms = getGetterMethodsFromObject(obj);
		List<Method> pms = getGetterMethodsFromObject(parent);
		
		for(Method m : ms){
			boolean isParentsMethod = pms.contains(m);
			
			if(!isParentsMethod){
				String beanName = m.getName().substring(3);
				Object beanValue = null;
				try{beanValue = m.invoke(obj);}catch(Exception ignore){}
				if(logOption.isLineSwap())sb.append("\r\n");
				sb.append(TraceOption.getLinePrefixString(logOption));
				sb.append(beanName).append("=").append(beanValue);
				sb.append(TraceOption.getLineSurfixString(logOption));
			}
		}
		
		return sb.toString();
	}
	
	public static String getJsonString(Object obj, Object parent){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		List<Method> ms = getGetterMethodsFromObject(obj);
		List<Method> pms = getGetterMethodsFromObject(parent);
		
		for(Method m : ms){
			boolean isParentsMethod = pms.contains(m);
			
			if ( !isParentsMethod ) {
				String beanName = m.getName().substring(3);
				Object beanValue = null;
				try {
					beanValue = m.invoke(obj);
				} catch ( Exception ignore ) {
					beanValue=null;
				}
				
				if(beanValue!=null){
					sb.append(beanName).append(":");
					if(beanValue instanceof String) sb.append("'").append(beanValue).append("'");
					else if(beanValue instanceof Number) sb.append(beanValue);
					else sb.append(TraceUtil.getJsonString(beanValue, null));
					sb.append(",");
				}
			}
		}
		if(sb.lastIndexOf(",")!=-1) sb.replace(sb.lastIndexOf(","), sb.length(), "");
		sb.append("}");
		return sb.toString();
	}
	
	private static List<Method> getGetterMethodsFromObject(Object obj){
		Method[] methods = null;
		if(obj==null) methods = Object.class.getMethods();
		else methods = obj.getClass().getMethods();
		
		List<Method> retMethods = new ArrayList<Method>();
		for(Method m : methods){
			if(m.getName().startsWith("get") && (m.getParameterTypes()==null || m.getParameterTypes().length==0)){
				retMethods.add(m);
			}
		}
		
		return retMethods;
	}
}