package com.youhost.webcore.interceptor;

public interface IHandlerSelectable {
	public void setPostHandle(boolean doPostHandle);
	public boolean isPostHandle();
	public void setPreHandle(boolean doPreHandle);
	public boolean isPreHandle();
	public void setAfterCompletion(boolean doAfterCompletion);
	public boolean isAfterCompletion();
}
