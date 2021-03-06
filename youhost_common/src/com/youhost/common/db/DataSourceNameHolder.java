package com.youhost.common.db;

public class DataSourceNameHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static void setDataSourceName(String dataSourceName){
		contextHolder.set(dataSourceName);
	}
	public static String getDataSourceName(){
		return contextHolder.get();
	}
	public static void clearDataSourceName(){
		contextHolder.remove();
	}
}
