package com.youhost.webcore.interceptor;

import javax.servlet.http.HttpServletRequest;

public interface IExcludeByPathInterceptor {
	public boolean isExcludeRequest(HttpServletRequest request);
}
