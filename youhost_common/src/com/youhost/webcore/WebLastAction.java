package com.youhost.webcore;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.UrlPathHelper;

import com.youhost.common.DefaultLogableObject;

public class WebLastAction extends DefaultLogableObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2505583703319015682L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Object handler;
	
	public WebLastAction(HttpServletRequest request, HttpServletResponse response, Object handler){
		this.request = request;
		this.response = response;
		this.handler = handler;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Object getHandler() {
		return handler;
	}
	
	public String getLastRequestedUrl(){
		UrlPathHelper h = new UrlPathHelper();
		return h.getRequestUri(request);
	}
	
	public String getLastLookupPathForRequest(){
		UrlPathHelper h = new UrlPathHelper();
		return h.getLookupPathForRequest(request);
	}
}
