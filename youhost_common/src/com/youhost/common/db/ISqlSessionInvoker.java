package com.youhost.common.db;

import java.util.List;

public interface ISqlSessionInvoker {
	public <T> T selectOne(String sqlSessionId, Object param, String query) throws Exception;
	public <E> List<E> selectList(String sqlSessionId, Object param,String query) throws Exception;
	public int batchInsert(String sqlSessionId, List<?> param, String query) throws Exception;
	public int batchUpdate(String sqlSessionId, List<?> param, String query) throws Exception;
	public int batchDelete(String sqlSessionId, List<?> param, String query) throws Exception;
	public int insert(String sqlSessionId, Object param, String query) throws Exception;
	public int update(String sqlSessionId, Object param, String query) throws Exception;
	public int delete(String sqlSessionId, Object param, String query) throws Exception;
}
