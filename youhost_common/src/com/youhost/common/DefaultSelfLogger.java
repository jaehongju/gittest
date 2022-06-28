package com.youhost.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 자기자신 클래스에 대한 고유로깅 기본구현 클래스
 * 자기자신 클래스에 대한 Log4j로거 제공<br>
 * 로그스트링 고유 Prefix/Surfix 에 대한 설정<br>
 * 
 * @author 김경중(kkj99@youhost.co.kr)
 *
 */
public class DefaultSelfLogger implements ISelfLogger {
	
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());
	private String logPrefix = "";
	private String logSurfix = "";
	
	public Logger selfLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String selfLogPrefix() {
		return this.logPrefix;
	}

	public void setLogPrefix(String logPrefix) {
		this.logPrefix = logPrefix;
	}

	public String selfLogSurfix() {
		return this.logSurfix;
	}

	public void setLogSurfix(String logSurfix) {
		this.logSurfix = logSurfix;
	}
	
	protected String convirtLogMessage(String message) {
		return selfLogPrefix() + message + selfLogSurfix();
	}

	protected void logDebug(String message) {
		selfLogger().debug(convirtLogMessage(message));
	}

	protected void logInfo(String message) {
		selfLogger().info(convirtLogMessage(message));
	}
}