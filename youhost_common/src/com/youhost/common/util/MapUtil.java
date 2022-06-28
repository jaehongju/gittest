package com.youhost.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

public class MapUtil {
	public static Map<String,Object> storeValueMap(Object source) throws Exception {
		return storeValueMap(source, Object.class);
	}
	
	public static Map<String,Object> storeValueMap(Object source, Class<?> ignoreExtends) throws Exception {
		Map<String,Object> target = new HashMap<String,Object>();
		
		if(ignoreExtends!=null && !ignoreExtends.isInstance(source)){
			throw new Exception(source.getClass()+" is not instance of "+ignoreExtends);
		}

		PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(source.getClass());
		PropertyDescriptor[] ignorePds = (ignoreExtends != null) ? BeanUtils.getPropertyDescriptors(ignoreExtends) : null;
		List<PropertyDescriptor> enablePds = new ArrayList<PropertyDescriptor>();
		for(PropertyDescriptor pd : sourcePds){
			if(ignorePds!=null && ignorePds.length>0){
				boolean isEnablePd = true;
				for(int i=0; i<ignorePds.length && isEnablePd; i++){
					if(ignorePds[i].getName().equals(pd.getName())){
						isEnablePd = false;
					}
				}
				if(isEnablePd) enablePds.add(pd);
			}else{
				enablePds.add(pd);
			}
		}
		
		for(PropertyDescriptor pd : enablePds){
			if (pd != null && pd.getReadMethod() != null) {
				try {
					Method readMethod = pd.getReadMethod();
					if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
						readMethod.setAccessible(true);
					}
					Object value = readMethod.invoke(source);
					target.put(pd.getName(),value);
				} catch (Throwable ex) {
					throw new FatalBeanException("Could not copy properties from source to target", ex);
				}
			}
		}
		return target;
	}
}
