package com.youhost.common.db;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.EnumSet;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.youhost.common.code.ICodeInteger;

public abstract class IntEnumTypeHandler<E extends Enum<E>> implements TypeHandler<E> {
	protected EnumSet<E> enumSet;
	public IntEnumTypeHandler(Class<E> enumClass){
		if(!ICodeInteger.class.isAssignableFrom(enumClass)){
			throw new IllegalArgumentException();
		}
		this.enumSet = EnumSet.allOf(enumClass);
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		//System.out.println("SET PARAMETER:"+ps+"/"+i+"/"+parameter+"/"+jdbcType);
		if(parameter == null) ps.setNull(i, Types.INTEGER);
		else ps.setInt(i, ((ICodeInteger)parameter).getCodeInt());
	}

	@Override
	public E getResult(ResultSet rs, String columnName) throws SQLException {
		//System.out.println("getResult:"+rs+"/"+columnName);
		//if(rs.wasNull()) return null;
		return valueOf(rs.getInt(columnName));
	}

	@Override
	public E getResult(ResultSet rs, int columnIndex) throws SQLException {
		//System.out.println("getResult:"+rs+"/"+columnIndex);
		//if(rs.wasNull()) return null;
		return valueOf(rs.getInt(columnIndex));
	}

	@Override
	public E getResult(CallableStatement cs, int columnIndex) throws SQLException {
		//System.out.println("getResult:"+cs+"/"+columnIndex);
		//if(cs.wasNull()) return null;
		return valueOf(cs.getInt(columnIndex));
	}
	
	public E valueOf(int value){
		for(E e : this.enumSet){
			if(((ICodeInteger)e).getCodeInt()==value) {
				//System.out.println("VALUE_OF : " +value + " = " + e);
				return e;
			}
		}
		//System.out.println("VALUE_OF : " +value + " = null ");
		return null;
	}
}
