package com.youhost.common;

public class TraceOptionFactory {
	public static final String BASE_MULTILINE = "LogOptionBaseMultiLine";
	public static final String BASE_SINGLELINE = "LogOptionBaseSingleLine";
	public static final String BASE_MAP = "LogOptionBaseMap";
	public static final String BASE_ARRAY = "LogOptionBaseArray";
	public static final String JSON = "LogOptionJson";
	
	public static TraceOption getLogOption(){
		return getLogOption(TraceOptionFactory.BASE_MULTILINE, 0);
	}
	
	public static TraceOption getLogOption(String templateString){
		return getLogOption(templateString, 0);
	}
	
	public static TraceOption getLogOption(String templateString, int tabIndentNum){
		String tabIndent = "";
		if(tabIndentNum > 0){
			for(int i=0; i<=tabIndentNum; i++){
				tabIndent += "\t";
			}
		}
		TraceOption retLogOption = null;
		if(templateString.equals(BASE_MULTILINE)){
			retLogOption = getBaseMultiline();
		}else if(templateString.equals(BASE_SINGLELINE)){
			retLogOption = getBaseSingleline();
		}else if(templateString.equals(JSON)){
			retLogOption = getJson();
		}
		retLogOption.setIndent(tabIndent);
		return retLogOption;
	}
	
	protected static TraceOption getBaseMultiline(){
		TraceOption retLogOption = new TraceOption();
		retLogOption.setLinePrefix("[");
		retLogOption.setLineSurfix("]");
		retLogOption.setLineSwap(true);
		return retLogOption;
	}
	
	protected static TraceOption getBaseSingleline(){
		TraceOption retLogOption = new TraceOption();
		retLogOption.setLinePrefix("[");
		retLogOption.setLineSurfix("]");
		retLogOption.setLineSwap(false);
		return retLogOption;
	}
	
	protected static TraceOption getJson(){
		TraceOption retLogOption = getBaseSingleline();
		retLogOption.setLinePrefix("{");
		retLogOption.setLineSurfix("}");
		return retLogOption;
	}
}