package com.youhost.common;

import org.slf4j.Logger;

//import org.apache.log4j.Logger;

/**
 * Log4j 로거 참조, 로그스트링 고유 Prefix/Surfix 제공 구조
 * @author 김경중(kkj99@youhost.co.kr)
 */
public interface ISelfLogger {
	public Logger selfLogger();
	public String selfLogPrefix();
	public void setLogPrefix(String logPrefix);
	public String selfLogSurfix();
	public void setLogSurfix(String logSurfix);
}
