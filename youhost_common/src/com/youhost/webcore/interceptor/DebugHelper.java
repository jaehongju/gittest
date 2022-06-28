package com.youhost.webcore.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UrlPathHelper;

import com.youhost.common.TraceOption;
import com.youhost.common.TraceOptionFactory;
import com.youhost.common.util.TraceUtil;

public class DebugHelper extends DefaultYouhostInterceptor {
	public static long requestId = 1L;
	private boolean preLogRequestParams = true;
	private boolean preLogRequestSessions = true;
	private boolean preLogHandler = true;
	private boolean preLogHandlerMappingInfo = false;
	String linePrefix = "\n ==== ";
	
	public void setPreLogRequestParams(boolean preLogRequestParams) {
		this.preLogRequestParams = preLogRequestParams;
	}
	public void setPreLogRequestSessions(boolean preLogRequestSessions) {
		this.preLogRequestSessions = preLogRequestSessions;
	}
	public void setPreLogHandler(boolean preLogHandler) {
		this.preLogHandler = preLogHandler;
	}
	public void setPreLogHandlerMappingInfo(boolean preLogHandlerMappingInfo) {
		this.preLogHandlerMappingInfo = preLogHandlerMappingInfo;
	}

	protected List<String> handlerMappingNames = new ArrayList<String>();
	{
		handlerMappingNames.add(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		handlerMappingNames.add(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		handlerMappingNames.add(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		handlerMappingNames.add(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING);
		handlerMappingNames.add(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		
		if(isExcludeRequest(request) || !isPreHandle()) return true;
		
		StringBuilder sb = new StringBuilder();
		sb.append("PRE TRACE");
		
		sb.append(linePrefix).append(" Request Url [ ").append(urlPathHelper.getRequestUri(request)).append(" ]");
		sb.append(linePrefix).append(" LOOK UP FOR [ ").append(urlPathHelper.getLookupPathForRequest(request)).append(" ]");
		sb.append(linePrefix).append(" Query [ ").append(urlPathHelper.getOriginatingQueryString(request)).append(" ]");
		
		if(this.preLogHandler){
			sb.append(linePrefix).append(" Handler [ ");
			if(handler instanceof ResourceHttpRequestHandler){
				ResourceHttpRequestHandler rh = (ResourceHttpRequestHandler) handler;
				sb.append(rh.getApplicationContext().getDisplayName());
			}else if(handler instanceof HandlerMethod){
				HandlerMethod hm = (HandlerMethod) handler;			
				sb.append(hm.getMethod().getDeclaringClass());
				sb.append(" <= ");
				sb.append(hm.getMethod().getName());		
			}
			sb.append(" ] ");
			
			if(this.preLogHandlerMappingInfo){
				sb.append(linePrefix).append(" Handler Mapping Info");
				for(String s : handlerMappingNames){
					sb.append("\n \t ").append(s).append(" = ");
				}
			}
		}
		if(this.preLogRequestParams){
			sb.append(linePrefix).append(" Request Parameters");
			String str = TraceUtil.getTraceString(request, TraceOptionFactory.getLogOption(TraceOptionFactory.BASE_SINGLELINE));
			if(str.trim().length()>0) sb.append("\n").append(str);
		}
		if(this.preLogRequestSessions){
			sb.append(linePrefix).append(" Request Sessions");
			String str = TraceUtil.getTraceString(request.getSession(), TraceOptionFactory.getLogOption(TraceOptionFactory.BASE_SINGLELINE));
			if(str.trim().length()>0) sb.append("\n").append(str);
		}
		
		LoggerFactory.getLogger(this.getClass()).info(sb.toString());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String strRequestUrl = urlPathHelper.getRequestUri(request);
		
		if(isExcludeRequest(request) || !isPostHandle()) return;
		
		StringBuilder sb = new StringBuilder();
		sb.append("POST TRACE : ").append(strRequestUrl);
		
		if(modelAndView!=null){
			sb.append(linePrefix).append(" ModelAndView - View ");
			sb.append("\n \t View : ").append(modelAndView.getView());
			sb.append("\n \t ViewName : ").append(modelAndView.getViewName());
			sb.append(linePrefix).append(" ModelAndView - Model \n");
			sb.append(TraceUtil.getTraceString(modelAndView.getModel(), new TraceOption()));
		}else{
			sb.append("\n:::: ModelAndView is empty ");
		}
		
		LoggerFactory.getLogger(this.getClass()).info(sb.toString());
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		if(isExcludeRequest(request) || !isAfterCompletion()) return;
		
		StringBuilder sb = new StringBuilder();
		sb.append("AFTER COMPLETE");
		LoggerFactory.getLogger(this.getClass()).info(sb.toString());
	}
	
}
