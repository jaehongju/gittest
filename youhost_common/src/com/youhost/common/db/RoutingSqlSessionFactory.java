package com.youhost.common.db;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.youhost.common.DefaultSelfLogger;
import com.youhost.common.code.SystemCode;

public class RoutingSqlSessionFactory extends DefaultSelfLogger {
	
	public RoutingSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		this.setSqlSessionFactory(sqlSessionFactory);
		logDebug("RoutingSqlSessionFactory 생성 .. "+sqlSessionFactory);
	}
	
	protected SqlSessionFactory sqlSessionFactory;
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public SqlSession getSqlSession(){
		return getSqlSession(SystemCode.SQLSESSION_MASTER,null);
	}
	
	public SqlSession getBatchSqlSession(){
		return getSqlSession(SystemCode.SQLSESSION_MASTER,ExecutorType.BATCH);
	}
	
	public SqlSession getSqlSession(String dataSourceName){
		return getSqlSession(dataSourceName,null);
	}
	
	public SqlSession getBatchSqlSession(String dataSourceName){
		return getSqlSession(dataSourceName,ExecutorType.BATCH);
	}
	
	public SqlSession getSqlSession(String dataSourceName,ExecutorType executorType){
		SqlSession session = null;
		if(executorType!=null){
			session = sqlSessionFactory.openSession(executorType);
		}else{
			session = sqlSessionFactory.openSession();
		}
		logger.debug("SQL SESSION 활성화 : "+session);
		return session;
	}
}