package com.youhost.common.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.youhost.common.DefaultSelfLogger;

public class RoutingSqlSessionInvoker extends DefaultSelfLogger implements ISqlSessionInvoker {

	protected RoutingSqlSessionFactory routingSqlSessionFactory;
	public RoutingSqlSessionFactory getRoutingSqlSessionFactory() {
		return routingSqlSessionFactory;
	}
	public void setRoutingSqlSessionFactory(RoutingSqlSessionFactory routingSqlSessionFactory) {
		this.routingSqlSessionFactory = routingSqlSessionFactory;
	}

	@Override
	public <T> T selectOne(String sqlSessionId, Object param, String query) throws Exception {
		return routingSqlSessionFactory.getSqlSession(sqlSessionId).selectOne(query,param);
	}

	@Override
	public <E> List<E> selectList(String sqlSessionId, Object param, String query) throws Exception {
		return routingSqlSessionFactory.getSqlSession(sqlSessionId).selectList(query,param);
	}

	@Override
	public int batchInsert(String sqlSessionId, List<?> param, String query) throws Exception {
		SqlSession ss = null;
		try {
			ss = routingSqlSessionFactory.getBatchSqlSession(sqlSessionId);
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
		}
	}

	@Override
	public int batchUpdate(String sqlSessionId, List<?> param, String query) throws Exception {
		SqlSession ss = null;
		try {
			ss = routingSqlSessionFactory.getBatchSqlSession(sqlSessionId);

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
		}
	}

	@Override
	public int batchDelete(String sqlSessionId, List<?> param, String query) throws Exception {
		SqlSession ss = null;
		try {
			ss = routingSqlSessionFactory.getBatchSqlSession(sqlSessionId);

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
		}
	}

	@Override
	public int insert(String sqlSessionId, Object param, String query) throws Exception {
		int resInt = 0;
		try {
			if (param instanceof List) {
				logInfo("batchInsert : " + query);
				resInt = batchInsert(sqlSessionId, (List<?>) param, query);
			} else {
				logInfo("insert : " + query);
				resInt = routingSqlSessionFactory.getSqlSession(sqlSessionId).insert(query, param);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			logDebug("insert : " + query + " / RESULT : " + resInt);
		}
		return resInt;
	}

	@Override
	public int update(String sqlSessionId, Object param, String query) throws Exception {
		int resInt = 0;
		try {
			if (param instanceof List) {
				logInfo("batchUpdate : " + query);
				resInt = batchUpdate(sqlSessionId, (List<?>) param, query);
			} else {
				logInfo("update : " + query);
				resInt = routingSqlSessionFactory.getSqlSession(sqlSessionId).update(query, param);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			logDebug("update : " + query + " / RESULT : " + resInt);
		}
		return resInt;
	}

	@Override
	public int delete(String sqlSessionId, Object param, String query) throws Exception {
		int resInt = 0;
		try {
			if (param instanceof List) {
				logInfo("batchDelete : " + query);
				resInt = batchDelete(sqlSessionId, (List<?>) param, query);
			} else {
				logInfo("delete : " + query);
				resInt = routingSqlSessionFactory.getSqlSession(sqlSessionId).delete(query, param);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			logDebug("delete : " + query + " / RESULT : " + resInt);
		}
		return resInt;
	}
}
