package com.youhost.common.db;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.dbcp.PoolableConnection;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.youhost.common.util.ValidateUtil;

public class MybatisClobTypeHandler extends BaseTypeHandler<String> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		String inputParam = (String) parameter;
		if(ValidateUtil.isEmpty(inputParam)){
			ps.setNull(i, Types.CLOB);
		}else{
			Connection conn = ps.getConnection();
			if(conn instanceof PoolableConnection){
				conn = ((PoolableConnection) conn).getDelegate();
			}
			Clob newClob = conn.createClob();
			newClob.setString(1, inputParam);
			ps.setClob(i, newClob);
		}
	}
	
	@Override
	public String getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		System.out.println("GET CLOB STRING");
		String value = "";
		Clob clob = rs.getClob(columnName);
		if (clob != null) {
			int size = (int) clob.length();
			value = clob.getSubString(1, size);
		}
		return value;
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		System.out.println("GET CLOB STRING");
		String value = "";
		Clob clob = rs.getClob(columnIndex);
		if (clob != null) {
			int size = (int) clob.length();
			value = clob.getSubString(1, size);
		}
		return value;
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		System.out.println("GET CLOB STRING");
		String value = "";
		Clob clob = cs.getClob(columnIndex);
		if (clob != null) {
			int size = (int) clob.length();
			value = clob.getSubString(1, size);
		}
		return value;
	}
}