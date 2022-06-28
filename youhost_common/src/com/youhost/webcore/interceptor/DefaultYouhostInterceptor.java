package com.youhost.webcore.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.youhost.common.util.PathValidator;

public class DefaultYouhostInterceptor extends HandlerInterceptorAdapter implements IExcludeByPathInterceptor, IHandlerSelectable {
	
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	/* IExcludeByPathInterceptor 구현 */
	private PathValidator excludePathValidator = null;
	private List<String> excludePaths = null;
	public PathValidator getExcludePathValidator() {
		return excludePathValidator;
	}
	public void setExcludePathValidator(PathValidator excludePathValidator) {
		this.excludePathValidator = excludePathValidator;
	}
	public List<String> getExcludePaths() {
		return excludePaths;
	}
	public void setExcludePaths(List<String> excludePaths) {
		this.excludePaths = excludePaths;
	}
	private PathValidator getExcludePathValidatorByInlineparam(){
		PathValidator pv = new PathValidator();
		pv.setAvailPaths(getExcludePaths());
		return pv;
	}
	@Override
	public boolean isExcludeRequest(HttpServletRequest request) {
		/* get request url path */
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String strRequestUrl = urlPathHelper.getLookupPathForRequest(request);
		
		boolean res = false;
		
		/* check from path validator */
		if(excludePathValidator!=null && excludePathValidator.isContain(strRequestUrl)) res = true;
		
		/* check from inline paths */
		if(res || getExcludePathValidatorByInlineparam().isContain(strRequestUrl)) res = true;
		
		return res;
	}
	/* IExcludeByPathInterceptor 구현 END */
	
	/* IHandlerSelectable 구현 */
	private boolean doPostHandle = true;
	private boolean doPreHandle = true;
	private boolean doAfterCompletion = true;
	@Override
	public void setPostHandle(boolean doPostHandle) {
		this.doPostHandle = doPostHandle;
	}
	@Override
	public boolean isPostHandle() {
		return doPostHandle;
	}
	@Override
	public void setPreHandle(boolean doPreHandle) {
		this.doPreHandle = doPreHandle;
	}
	@Override
	public boolean isPreHandle() {
		return doPreHandle;
	}
	@Override
	public void setAfterCompletion(boolean doAfterCompletion) {
		this.doAfterCompletion = doAfterCompletion;
	}
	@Override
	public boolean isAfterCompletion() {
		return doAfterCompletion;
	}
	/* IHandlerSelectable 구현 END */
}
