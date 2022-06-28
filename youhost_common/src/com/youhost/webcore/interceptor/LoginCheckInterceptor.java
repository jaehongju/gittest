package com.youhost.webcore.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.youhost.common.util.PathValidator;
import com.youhost.webcore.SessionKey;

public class LoginCheckInterceptor extends DefaultYouhostInterceptor {
	
	private String returnViewname = "main.main";
	private boolean saveLastAction = true;
	private PathValidator loginCheckExcludePathValidator = null;
	public String getReturnViewname() {
		return returnViewname;
	}
	public void setReturnViewname(String returnViewname) {
		this.returnViewname = returnViewname;
	}
	public boolean isSaveLastAction() {
		return saveLastAction;
	}
	public void setSaveLastAction(boolean saveLastAction) {
		this.saveLastAction = saveLastAction;
	}
	public PathValidator getLoginCheckExcludePathValidator() {
		return loginCheckExcludePathValidator;
	}
	public void setLoginCheckExcludePathValidator(
			PathValidator loginCheckExcludePathValidator) {
		this.loginCheckExcludePathValidator = loginCheckExcludePathValidator;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(isExcludeRequest(request) || !isPreHandle()) return true;
		
		Object obj = request.getSession().getAttribute(SessionKey.USER_INFO);
		if(obj==null){
			logger.info("비로그인 상태 접근 차단. 이동 => "+getReturnViewname());
			doSaveLastAction(request, response, handler);
			ModelAndView modelAndView = new ModelAndView(getReturnViewname());
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(isExcludeRequest(request) || !isPostHandle()) return;
		
		Object obj = request.getSession().getAttribute(SessionKey.USER_INFO);
		if(obj==null){
			logger.info("비로그인 상태 접근 차단. 이동 => "+getReturnViewname());
			
			doSaveLastAction(request, response, handler);
			
			if(modelAndView == null){
				modelAndView = new ModelAndView(getReturnViewname());
			}
		}
	}
	
	private void doSaveLastAction(HttpServletRequest request,
			HttpServletResponse response, Object handler){
		if(isSaveLastAction()){
//			WebLastAction wla = new WebLastAction(request, response, handler);
//			request.getSession().setAttribute(SessionKey.LAST_ACTION, wla);
			StringBuffer redirectUrl = 	new StringBuffer(request.getServletPath());
			redirectUrl.append("?");
			Map<String, Object> map = request.getParameterMap();
			if (map != null){
				for( String key : map.keySet() ){
					redirectUrl.append(key).append("=").append( ((String[])map.get(key))[0] ).append("&");
		        }
			}
			request.getSession().setAttribute(SessionKey.LAST_ACTION_URL, redirectUrl);
		}
	}
}
