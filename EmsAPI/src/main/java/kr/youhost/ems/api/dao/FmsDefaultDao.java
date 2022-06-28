package kr.youhost.ems.api.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.youhost.common.db.AbstractDefaultDao;
import com.youhost.common.db.ISqlSessionInvoker;

public class FmsDefaultDao extends AbstractDefaultDao {
	
	@Autowired protected ISqlSessionInvoker sqlSessionInvoker;
	
	FmsDefaultDao(){
		setSessionId("fms");
	}
	
	@Override
	public ISqlSessionInvoker getSqlSessionInvoker() {
		return sqlSessionInvoker;
	}

	public void setSqlSessionInvoker(ISqlSessionInvoker sqlSessionInvoker) {
		this.sqlSessionInvoker = sqlSessionInvoker;
	}
}