package com.youhost.common.db;

import java.util.List;

import com.youhost.common.DefaultSelfLogger;
import com.youhost.common.code.SystemCode;

public abstract class AbstractDefaultDao extends DefaultSelfLogger {
	// 2019.04.11 LEE Start
	public String sqlSessionId = SystemCode.SQLSESSION_MASTER;
	// 2019.04.11 LEE End
	public abstract ISqlSessionInvoker getSqlSessionInvoker();
	public abstract void setSqlSessionInvoker(ISqlSessionInvoker sqlSessionInvoker);
	
	// 2019.04.11 LEE Start
	public void setSessionId(String sqlSessionId) {
		this.sqlSessionId = sqlSessionId;
	}
	// 2019.04.11 LEE End
	
	public <E> List<E> selectList(String sqlSessionId, Object param, String query) throws Exception {
		return getSqlSessionInvoker().selectList(sqlSessionId, param, query);
	}
	
	public <T> T  selectOne(String sqlSessionId, Object param, String query) throws Exception {
		return getSqlSessionInvoker().selectOne(sqlSessionId, param, query);
	}
	
	public int insert(String sqlSessionId, Object param, String query) throws Exception {
		return getSqlSessionInvoker().insert(sqlSessionId, param, query);
	}
	
	public int update(String sqlSessionId, Object param, String query) throws Exception {
		return getSqlSessionInvoker().update(sqlSessionId, param, query);
	}
	
	public int delete(String sqlSessionId, Object param, String query) throws Exception {
		return getSqlSessionInvoker().delete(sqlSessionId, param, query);
	}
	public <E> List<E> selectList(Object param, String query) throws Exception {
		return selectList(this.sqlSessionId, param, query);
	}
	
	public <T> T  selectOne(Object param, String query) throws Exception {
		return selectOne(this.sqlSessionId, param, query);
	}
	
	public int insert(Object param, String query) throws Exception {
		return insert(this.sqlSessionId, param, query);
	}
	
	public int update(Object param, String query) throws Exception {
		return update(this.sqlSessionId, param, query);
	}
	
	public int delete(Object param, String query) throws Exception {
		return delete(this.sqlSessionId, param, query);
	}
}