package com.youhost.webcore;

import java.io.Serializable;

import com.youhost.common.TraceOptionFactory;
import com.youhost.common.util.TraceUtil;
import com.youhost.common.util.ValidateUtil;

public class DatabaseProperties implements Serializable {
	private static final long serialVersionUID = 593069664473870863L;
	
	private String id;
	private String driver;
	private String url;
	private String username;
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DatabaseProperties){
			DatabaseProperties d = (DatabaseProperties) obj;
			if(!ValidateUtil.isEmpty(d.getId()) && d.getId().equals(getId())) return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return TraceUtil.getTraceString(this, null, TraceOptionFactory.getLogOption(TraceOptionFactory.BASE_SINGLELINE));
	}
}