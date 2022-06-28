package com.youhost.common;

import com.youhost.common.util.TraceUtil;

public class DefaultLogableObject extends DefaultSelfLogger {

	@Override
	public String toString() {
		return TraceUtil.getTraceString(this, new DefaultLogableObject(), publishTraceOption());
	}
	
	public TraceOption publishTraceOption(){
		return TraceOptionFactory.getLogOption(TraceOptionFactory.BASE_SINGLELINE);
	}
}
