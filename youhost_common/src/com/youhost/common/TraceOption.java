package com.youhost.common;

import com.youhost.common.util.ValidateUtil;

public class TraceOption {
	private String indent;
	private String prefix;
	private String surfix;
	private String lineIndent;
	private String linePrefix;
	private String lineSurfix;
	private boolean lineSwap = true;
	
	public String getIndent() {
		if(ValidateUtil.isEmpty(this.indent)) return "";
		return indent;
	}
	public void setIndent(String indent) {
		this.indent = indent;
	}
	public String getPrefix() {
		if(ValidateUtil.isEmpty(this.prefix)) return "";
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSurfix() {
		if(ValidateUtil.isEmpty(this.surfix)) return "";
		return surfix;
	}
	public void setSurfix(String surfix) {
		this.surfix = surfix;
	}
	public String getLineIndent() {
		if(ValidateUtil.isEmpty(this.lineIndent)) return "";
		return lineIndent;
	}
	public void setLineIndent(String lineIndent) {
		this.lineIndent = lineIndent;
	}
	public String getLinePrefix() {
		if(ValidateUtil.isEmpty(this.linePrefix)) return "";
		return linePrefix;
	}
	public void setLinePrefix(String linePrefix) {
		this.linePrefix = linePrefix;
	}
	public String getLineSurfix() {
		if(ValidateUtil.isEmpty(this.lineSurfix)) return "";
		return lineSurfix;
	}
	public void setLineSurfix(String lineSurfix) {
		this.lineSurfix = lineSurfix;
	}
	public boolean isLineSwap() {
		return lineSwap;
	}
	public void setLineSwap(boolean lineSwap) {
		this.lineSwap = lineSwap;
	}
	
	public static String getLinePrefixString(TraceOption logOption){
		StringBuffer sb = new StringBuffer();
		sb.append(logOption.getIndent());
		sb.append(logOption.getLineIndent());
		sb.append(logOption.getLinePrefix());
		return sb.toString();
	}
	
	public static String getLineSurfixString(TraceOption logOption){
		StringBuffer sb = new StringBuffer();
		sb.append(logOption.getLineSurfix());
		return sb.toString();
	}
	
	public static String getPrefixString(TraceOption logOption){
		StringBuffer sb = new StringBuffer();
		sb.append(logOption.getIndent());
		sb.append(logOption.getPrefix());
		return sb.toString();
	}
	
	public static String getSurfixString(TraceOption logOption){
		StringBuffer sb = new StringBuffer();
		sb.append(logOption.getSurfix());
		return sb.toString();
	}
}
