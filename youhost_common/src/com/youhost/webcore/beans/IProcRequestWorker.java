package com.youhost.webcore.beans;

public interface IProcRequestWorker {
	public <T> T proc(Object... params) throws Exception ;
}
