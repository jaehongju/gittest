package com.youhost.common.db;

import java.util.HashMap;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SqlSessionResolver {
	private HashMap<String, SqlSession> sqlSessionMap;
	public HashMap<String, SqlSession> getSqlSessionMap() {
		return sqlSessionMap;
	}
	public void setSqlSessionMap(HashMap<String, SqlSession> sqlSessionMap) {
		this.sqlSessionMap = sqlSessionMap;
	}
	
	private HashMap<String, SqlSessionFactory> sqlSessionFactoryMap;
	public HashMap<String, SqlSessionFactory> getSqlSessionFactoryMap() {
		return sqlSessionFactoryMap;
	}
	public void setSqlSessionFactoryMap(HashMap<String, SqlSessionFactory> sqlSessionFactoryMap) {
		this.sqlSessionFactoryMap = sqlSessionFactoryMap;
	}
	
	public SqlSession getSqlSession(String sqlSessionId){
		return this.sqlSessionMap.get(sqlSessionId);
	}
	public SqlSession getSqlSessionBatch(String sqlSessionId){
		return this.sqlSessionFactoryMap.get(sqlSessionId).openSession(ExecutorType.BATCH);
	}
}