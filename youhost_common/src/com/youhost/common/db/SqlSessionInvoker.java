package com.youhost.common.db;

import java.util.List;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.youhost.common.DefaultSelfLogger;
import com.youhost.common.util.ValidateUtil;

public class SqlSessionInvoker extends DefaultSelfLogger implements ISqlSessionInvoker {
	private boolean batch = false;
	private SqlSessionResolver sqlSessionResolver;
	static {
		LogFactory.useLog4JLogging();
	}
	public SqlSessionInvoker() {
		setLogPrefix("[SqlSessionInvoker [");
		setLogSurfix("]]");
	}

	public boolean isBatch() {
		return this.batch;
	}
	public void setBatch(boolean batch) {
		this.batch = batch;
	}

	public SqlSessionResolver getSqlSessionResolver() {
		return this.sqlSessionResolver;
	}
	public void setSqlSessionResolver(SqlSessionResolver sqlSessionResolver) {
		this.sqlSessionResolver = sqlSessionResolver;
	}

	protected SqlSession getSqlSession(String sqlSessionId) {
		String _sqlSessionId = sqlSessionId;
		if (ValidateUtil.isEmpty(sqlSessionId)) {
			_sqlSessionId = "m";
		}
		return this.sqlSessionResolver.getSqlSession(_sqlSessionId);
	}
	protected SqlSession getBatchSqlSession(String sqlSessionId) {
		String _sqlSessionId = sqlSessionId;
		if (ValidateUtil.isEmpty(sqlSessionId)) {
			_sqlSessionId = "m";
		}
		return this.sqlSessionResolver.getSqlSessionBatch(_sqlSessionId);
	}

	protected SqlSession getSqlSession() {
		return getSqlSession("m");
	}

	public <T> T selectOne(String sqlSessionId, Object param, String query) throws Exception {
		return getSqlSession(sqlSessionId).selectOne(query, param);
	}
	public <E> List<E> selectList(String sqlSessionId, Object param,String query) throws Exception {
		return getSqlSession(sqlSessionId).selectList(query, param);
	}

	@SuppressWarnings("rawtypes")
	public int batchInsert(String sqlSessionId, List param, String query) throws Exception {
		SqlSession ss = null;
		try {
			ss = getBatchSqlSession(sqlSessionId);
			for(Object obj : param){
				ss.insert(query, obj);
			}

			ss.flushStatements();
			ss.commit();
			return param.size();
		} catch (Exception ex) {
			try {
				ss.rollback();
			} catch (Exception localException2) {
			}
			throw ex;
		} finally {
			ss.close();
		}
	}
	@SuppressWarnings("rawtypes")
	public int batchUpdate(String sqlSessionId, List param, String query) throws Exception {
		SqlSession ss = null;
		try {
			ss = getBatchSqlSession(sqlSessionId);

			for(Object obj : param){
				ss.update(query, obj);
			}
			
			ss.flushStatements();
			ss.commit();
			return param.size();
		} catch (Exception ex) {
			try {
				ss.rollback();
			} catch (Exception localException2) {
			}
			throw ex;
		} finally {
			ss.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public int batchDelete(String sqlSessionId, List param, String query) throws Exception {
		SqlSession ss = null;
		try {
			ss = getBatchSqlSession(sqlSessionId);

			for(Object obj : param){
				ss.delete(query, obj);
			}

			ss.flushStatements();
			ss.commit();
			return param.size();
		} catch (Exception ex) {
			try {
				ss.rollback();
			} catch (Exception localException2) {
			}
			throw ex;
		} finally {
			ss.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public int insert(String sqlSessionId, Object param, String query) throws Exception {
		int resInt = 0;
		try {
			if (param instanceof List) {
				logInfo("batchInsert : " + query);
				resInt = batchInsert(sqlSessionId, (List) param, query);
			} else {
				logInfo("insert : " + query);
				resInt = getSqlSession(sqlSessionId).insert(query, param);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			logDebug("insert : " + query + " / RESULT : " + resInt);
		}
		return resInt;
	}

	@SuppressWarnings("rawtypes")
	public int update(String sqlSessionId, Object param, String query) throws Exception {
		int resInt = 0;
		try {
			if (param instanceof List) {
				logInfo("batchUpdate : " + query);
				resInt = batchUpdate(sqlSessionId, (List) param, query);
			} else {
				logInfo("update : " + query);
				resInt = getSqlSession(sqlSessionId).update(query, param);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			logDebug("update : " + query + " / RESULT : " + resInt);
		}
		return resInt;
	}

	@SuppressWarnings("rawtypes")
	public int delete(String sqlSessionId, Object param, String query) throws Exception {
		int resInt = 0;
		try {
			if (param instanceof List) {
				logInfo("batchDelete : " + query);
				resInt = batchDelete(sqlSessionId, (List) param, query);
			} else {
				logInfo("delete : " + query);
				resInt = getSqlSession(sqlSessionId).delete(query, param);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			logDebug("delete : " + query + " / RESULT : " + resInt);
		}
		return resInt;
	}
}