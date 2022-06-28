package com.youhost.webcore.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.youhost.common.db.SqlSessionInvoker;
import com.youhost.common.db.SqlSessionResolver;
import com.youhost.common.util.ValidateUtil;
import com.youhost.webcore.ApplicationContextHolder;
import com.youhost.webcore.ApplicationInformation;
import com.youhost.webcore.DatabaseProperties;

public class MybatisBeanInitializer extends BeanInitializer {
	
	public MybatisBeanInitializer(ApplicationContextHolder ctxHolder) {
		super(ctxHolder);
	}

	public static final String namePrefix_pooledDataSource = "pooledDataSource#";
	public static final String namePrefix_sqlSession = "sqlSession#";
	public static final String namePrefix_sqlSessionFactory = "sqlSessionFactory#";
	public static final String namePrefix_transactionManager = "transactionManager#";
	public static final String namePrefix_transactionInterceptor = "transactionInterceptor#";
	public static final String namePrefix_sqlSessionResolver = "sqlSessionResolver";
	public static final String namePrefix_baseDao = "baseDao";
	
	/**
	 * 트랜잭션 옵션 : true / false
	 */
	private boolean transactionResolveOption = true;
	public void setTransactionResolveOption(boolean transactionResolveOption){
		this.transactionResolveOption = transactionResolveOption;
	}
	
	protected Resource[] sqlMapperLocations = null;
	public void setSqlMapperLocations(Resource[] mapperLocations) {
		this.sqlMapperLocations = mapperLocations;
	}
		
	private List<DatabaseProperties> databaseProperties;
	public void setDatabaseProperties(List<DatabaseProperties> databaseProperties) {
		this.databaseProperties = databaseProperties;
	}
	public void setDatabaseProperties(DatabaseProperties databaseProperties) {
		initProps();
		this.databaseProperties.add(databaseProperties);
	}
	
	private List<ApplicationInformation> applicationInformations;
	public void setApplicationInformations(List<ApplicationInformation> applicationInformations) {
		this.applicationInformations = applicationInformations;
	}
	public void setApplicationInformation(ApplicationInformation applicationInformation) {
		initProps();
		this.applicationInformations.add(applicationInformation);
	}
	
	private void initProps(){
		if(this.databaseProperties!=null) this.databaseProperties.clear();
		else this.databaseProperties = new ArrayList<DatabaseProperties>();
		if(this.applicationInformations!=null) this.applicationInformations.clear();
		else this.applicationInformations = new ArrayList<ApplicationInformation>();
	}
	
	public void init(){
		logger.info("Mybatis Bean 초기화 시작");
		Map<String, SqlSessionTemplate> sqlSessionMap = new HashMap<String, SqlSessionTemplate>();
		Map<String, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<String, SqlSessionFactory>();
		
		if(databaseProperties!=null && databaseProperties.size()>0){
			for(DatabaseProperties dbProp : databaseProperties){
				logger.info("DB 빈을 등록합니다.. : "+dbProp);
				resolvePooledDataSourceBean(dbProp);
				resolveSqlSessionFactory(namePrefix_sqlSessionFactory+dbProp.getId(), getPooledDataSourceBean(namePrefix_pooledDataSource+dbProp.getId()));
				resolveSqlSession(namePrefix_sqlSession+dbProp.getId(), getSqlSessionFactoryBean(namePrefix_sqlSessionFactory+dbProp.getId()),null,null);
				sqlSessionFactoryMap.put(dbProp.getId(), getSqlSessionFactoryBean(namePrefix_sqlSessionFactory+dbProp.getId()));
				sqlSessionMap.put(dbProp.getId(), getSqlSessionBean(namePrefix_sqlSession+dbProp.getId()));
			}
		}
		
		if(applicationInformations!=null && applicationInformations.size()>0){
			for(ApplicationInformation appInfo : applicationInformations){
				logger.info("DB 빈을 등록합니다.. : "+appInfo.getName());
				DatabaseProperties dbProp = appInfo.getDatabaseProperties();
				resolvePooledDataSourceBean(dbProp);
				resolveSqlSessionFactory(namePrefix_sqlSessionFactory+dbProp.getId(), getPooledDataSourceBean(namePrefix_pooledDataSource+dbProp.getId()));
				resolveSqlSession(namePrefix_sqlSession+dbProp.getId(), getSqlSessionFactoryBean(namePrefix_sqlSessionFactory+dbProp.getId()),null,null);
				sqlSessionFactoryMap.put(dbProp.getId(), getSqlSessionFactoryBean(namePrefix_sqlSessionFactory+dbProp.getId()));
				sqlSessionMap.put(dbProp.getId(), getSqlSessionBean(namePrefix_sqlSession+dbProp.getId()));
			}
		}
		
		logger.info("전역 DAO Bean을 등록합니다..");
		resolveSqlSessionResolver(namePrefix_sqlSessionResolver,sqlSessionMap,sqlSessionFactoryMap);
		SqlSessionResolver sqlSessionResolver = getBean(namePrefix_sqlSessionResolver);
		resolveBaseDao(namePrefix_baseDao, sqlSessionResolver);
		logger.info("Mybatis Bean 초기화 완료");
	}
	
	private TransactionAttributeSource transactionAttributeSource = null;
	public void setTransactionAttributeSource(TransactionAttributeSource transactionAttributeSource){
		this.transactionAttributeSource = transactionAttributeSource;
	}
	
	protected Resource mybatisConfigLocation = null;
	public void setMybatisConfigLocation(Resource configLocation) {
		this.mybatisConfigLocation = configLocation;
	}
	
	public PooledDataSource getPooledDataSourceBean(String beanName){
		return getBean(beanName, PooledDataSource.class);
	}
	
	public DefaultSqlSessionFactory getSqlSessionFactoryBean(String beanName){
		return getBean(beanName, DefaultSqlSessionFactory.class);
	}
	
	public SqlSessionTemplate getSqlSessionBean(String beanName){
		return getBean(beanName, SqlSessionTemplate.class);
	}
	
	public PlatformTransactionManager getPlatformTransactionManager(String beanName){
		return getBean(beanName, PlatformTransactionManager.class);
	}
	
	public TransactionInterceptor getTransactionInterceptor(String beanName){
		return getBean(beanName, TransactionInterceptor.class);
	}
	
	public void resolvePooledDataSourceBean(DatabaseProperties dbProp){
		if(dbProp==null || ValidateUtil.isEmpty(dbProp.getId())) return;
		
		BeanDefinition bd = getRootBeanDefinition(PooledDataSource.class.getName());
		bd.getPropertyValues().addPropertyValue("driver", dbProp.getDriver());
		bd.getPropertyValues().addPropertyValue("url", dbProp.getUrl());
		bd.getPropertyValues().addPropertyValue("username", dbProp.getUsername());
		bd.getPropertyValues().addPropertyValue("password", dbProp.getPassword());
		bd.getPropertyValues().addPropertyValue("poolMaximumCheckoutTime", 30000);
		
		resolveBean(namePrefix_pooledDataSource+dbProp.getId(),bd);
		
		if(transactionResolveOption){
			resolveTransactionManager(namePrefix_transactionManager+dbProp.getId(), getPooledDataSourceBean(namePrefix_pooledDataSource+dbProp.getId()));
			resolveTransactionInterceptor(namePrefix_transactionInterceptor+dbProp.getId(), getPlatformTransactionManager(namePrefix_transactionManager+dbProp.getId()));
		}
	}
	
	public void resolveTransactionManager(String beanName, DataSource dataSource){
		BeanDefinition bd = getRootBeanDefinition(DataSourceTransactionManager.class.getName());
		bd.getPropertyValues().addPropertyValue("dataSource", dataSource);
		
		resolveBean(beanName,bd);
	}
	
	public void resolveTransactionInterceptor(String beanName, PlatformTransactionManager platformTransactionManager){
		BeanDefinition bd = getRootBeanDefinition(TransactionInterceptor.class.getName());
		bd.getPropertyValues().addPropertyValue("transactionManager", platformTransactionManager);
		if(this.transactionAttributeSource!=null){
			bd.getPropertyValues().addPropertyValue("transactionAttributeSource", transactionAttributeSource);
		}
		
		resolveBean(beanName,bd);
	}
	
	public void resolveSqlSessionFactory(String beanName, DataSource dataSource){
		BeanDefinition bd = getRootBeanDefinition(SqlSessionFactoryBean.class.getName());
		bd.getPropertyValues().addPropertyValue("configLocation", this.mybatisConfigLocation);
		bd.getPropertyValues().addPropertyValue("mapperLocations", this.sqlMapperLocations);
		bd.getPropertyValues().addPropertyValue("dataSource", dataSource);
		
		resolveBean(beanName,bd);
	}
	
	public void resolveSqlSession(String beanName, DefaultSqlSessionFactory sqlSessionFactoryBean, ExecutorType executorType, PersistenceExceptionTranslator exceptionTranslator){
		BeanDefinition bd = getRootBeanDefinition(SqlSessionTemplate.class.getName());
		bd.getConstructorArgumentValues().addIndexedArgumentValue(0, sqlSessionFactoryBean);
		if(executorType!=null){
			bd.getConstructorArgumentValues().addIndexedArgumentValue(1, executorType);
			if(exceptionTranslator!=null) bd.getConstructorArgumentValues().addIndexedArgumentValue(2, exceptionTranslator);
		}
		
		resolveBean(beanName,bd);
	}
	

	public void resolveSqlSessionResolver(String beanName, Map<String, SqlSessionTemplate> sqlSessionMap, Map<String, SqlSessionFactory> sqlSessionFactoryMap){
		BeanDefinition bd = getRootBeanDefinition(SqlSessionResolver.class.getName());
		bd.getPropertyValues().addPropertyValue("sqlSessionMap", sqlSessionMap);
		bd.getPropertyValues().addPropertyValue("sqlSessionFactoryMap", sqlSessionFactoryMap);
		
		resolveBean(beanName,bd);
	}
	
	public void resolveBaseDao(String beanName, SqlSessionResolver sqlSessionResolver){
		BeanDefinition bd = getRootBeanDefinition(SqlSessionInvoker.class.getName());
		bd.getPropertyValues().addPropertyValue("sqlSessionResolver", sqlSessionResolver);
		
		resolveBean(beanName,bd);
	}

	public void customInit(){
		doCustomInit();
	}
	
	protected void doCustomInit(){
		init();
	}
}