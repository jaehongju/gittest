package com.youhost.common.db;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.annotation.Transactional;

import com.youhost.common.DefaultSelfLogger;
import com.youhost.common.code.SystemCode;
import com.youhost.common.util.ValidateUtil;
import com.youhost.webcore.annotation.DataSource;

public class DefaultTransactionalServiceAspect extends DefaultSelfLogger {
	
	public void resolveDataSourceName(ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = getMethod(joinPoint);
		String annotationDataSourceName = getAnnotationDataSourceName(method);
		if(!ValidateUtil.isEmpty(annotationDataSourceName)){
			DataSourceNameHolder.setDataSourceName(annotationDataSourceName);
		}else{
			Object[] args = joinPoint.getArgs();
			if(args!=null && args.length>0 && args[0] instanceof String){
				DataSourceNameHolder.setDataSourceName((String) args[0]);
			}else{
				DataSourceNameHolder.setDataSourceName(SystemCode.SQLSESSION_MASTER);
			}
		}
	}
	
	protected Method getMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		/* 현재 method 획득 */
		final String methodName = joinPoint.getSignature().getName();
		final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		if(method.getDeclaringClass().isInterface()){
			method = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
		}
		return method;
	}
	
	protected boolean haveTransactionalAnnotation(Method method) {
		Transactional t = null;
		try{
			t = (Transactional) method.getAnnotation(Transactional.class);
		}catch(Exception ex){
			t = null;
		}
		return t!=null;
	}
	
	protected String getAnnotationDataSourceName(Method method) {
		/* DataSource Annotation 획득 */
		DataSource dataSource = (DataSource) method.getAnnotation(DataSource.class);
		if(dataSource != null){
			return dataSource.value();
		}else{
			return null;
		}
	}
}