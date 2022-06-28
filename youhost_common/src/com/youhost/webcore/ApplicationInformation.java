package com.youhost.webcore;

import java.io.Serializable;

import com.youhost.common.TraceOption;
import com.youhost.common.util.TraceUtil;

public class ApplicationInformation implements Serializable {
	private static final long serialVersionUID = 2107664545205840331L;
	
	private String name;
	private String descript;
	private DatabaseProperties databaseProperties;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public DatabaseProperties getDatabaseProperties() {
		return databaseProperties;
	}
	public void setDatabaseProperties(DatabaseProperties databaseProperties) {
		this.databaseProperties = databaseProperties;
	}
	@Override
	public String toString() {
		return TraceUtil.getTraceString(this, null, new TraceOption());
	}
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if(obj!=null && obj instanceof ApplicationInformation){
			ApplicationInformation compare = (ApplicationInformation) obj;
			try{if(compare.getName().equals(this.getName())) res = true;}catch(Exception ignore){}
		}
		return res;
	}
	public String toJsonString() {
		return TraceUtil.getJsonString(this, null);
	}
}