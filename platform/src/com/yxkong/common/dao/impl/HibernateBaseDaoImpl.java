package com.yxkong.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.yxkong.common.dao.IHibernateBaseDao;
import com.yxkong.common.model.BaseEntity;
import com.yxkong.common.page.Pagination;
import com.yxkong.system.model.ThemeSetting;

/**
 * hibernate dao 基类
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes","unused" })
public abstract class HibernateBaseDaoImpl<T extends BaseEntity, ID extends Serializable> extends
HibernateDaoSupport implements IHibernateBaseDao<T, ID> {
	private static Log log = LogFactory.getLog(HibernateBaseDaoImpl.class);
    /** *  为父类HibernateDaoSupport注入hibernateTemplate
     * @param hibernateTemplate
     */
	@Resource(name="hibernateTemplate")
    public void setSuperHibernateTemplate(HibernateTemplate hibernateTemplate){
    	super.setHibernateTemplate(hibernateTemplate);
    }

	private Class<T> persistentClass;

	/**
	 * 初始化时，自动获取范型指定的具体类
	 */
	public HibernateBaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
		
	/**
	 * 按HQL查询对象列表
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 */
	@Override
	public Pagination findByHqlIndex(String hql, int pageNo, int pageSize,Object... values) {
		int count = this.countByHqlIndex(hql, values);
		Pagination pagination = new Pagination(pageNo, pageSize, count);
		Query query = this.currentSession().createQuery(hql);
		if (values != null && values.length > 0)
			for (int i = 0; i < values.length; i++) {
				Object parameter = values[i];
				query.setParameter(i, parameter);
			}
		query.setFirstResult(pagination.getFirstResult());
		query.setMaxResults(pagination.getPageSize());
		pagination.setList(query.list());
		return pagination;
	}

	/**
	 * 根据HQL语句分页查询,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	@Override
	public Pagination findByHqlParam(String hql, int pageNo, int pageSize,String[] params, Object[] values) {
		int count = this.countByHqlParam(hql, params, values);
		Pagination pagination = new Pagination(pageNo, pageSize, count);
		Query query = this.currentSession().createQuery(hql);
		if (params != null && values != null && values.length > 0
				&& params.length > 0 && params.length == values.length)
			for (int i = 0; i < params.length; i++) {
				query.setParameter(params[i].toString(), values[i]);
			}
		query.setFirstResult(pagination.getFirstResult());
		query.setMaxResults(pagination.getPageSize());
		pagination.setList(query.list());
		return pagination;
	}
	/**
	 *  根据HQL语句分页查询,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param pageNo  页码
	 * @param pageSize  每页数量
	 * @param queryMap 条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	public Pagination findByHqlParamMap(String hql,int pageNo, int pageSize,Map<String,Object> queryMap){
		int count = this.countByHqlParamMap(hql,queryMap);
		Pagination pagination = new Pagination(pageNo, pageSize, count);
		Query query = this.currentSession().createQuery(hql);
		if(queryMap!=null&&!queryMap.isEmpty()){
			for(String key:queryMap.keySet()){
				query.setParameter(key,queryMap.get(key));
			}
		}
		query.setFirstResult(pagination.getFirstResult());
		query.setMaxResults(pagination.getPageSize());
		pagination.setList(query.list());
		return pagination;
	}

	/**
	 * 根据属性值分页查询
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	@Override
	public Pagination findByHqlProperty(int pageNo, int pageSize, String property,Object value) {
		Assert.hasText(property);
		StringBuffer hqlfrom=new StringBuffer();
		hqlfrom.append("from  ").append(getPersistentClass().getName()).append(" t ").append(" where t.").append(property).append("=? ");
		StringBuffer hqlCount=new StringBuffer();
		hqlCount.append("select count(*) ").append(hqlfrom);
		int totalCount =( (Integer) currentSession().createQuery(hqlCount.toString()).setParameter(0, value).iterate().next() ).intValue();
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			pagination.setList(new ArrayList());
			return pagination;
		}
		Query query = currentSession().createQuery(hqlfrom.toString()).setParameter(0, value)
				                      .setFirstResult(pagination.getFirstResult())
		                              .setMaxResults(pagination.getPageSize());
		pagination.setList(query.list());
		return pagination;
	}

	/**
	 * 按属性查找对象的数量
	 * @param property
	 * @param value
	 * @return
	 */
	@Override
	public int countByProperty(String property, Object value) {
		if(property==null){
			return 0;
		}
		StringBuffer sqlBuf=new StringBuffer();
		sqlBuf.append("select count(*) from ").append(getPersistentClass().getName()).append(" t ").append(" where t.").append(property).append("=? ");
		return ( (Integer) currentSession().createQuery(sqlBuf.toString()).setParameter(0, value).iterate().next() ).intValue();
	}
   
	/**
	 * 保存对象
	 * @param entity 实体对象
	 * @return 主键
	 */
	public T save(T obj) {
		return (T) getHibernateTemplate().save(obj);
	}
	/**
	 * 批量保存对象,每50条提交一次事务
	 * @param list
	 */
	@Override
	public void saveBatch(final List<T> list)throws Exception{
		Assert.notEmpty(list);
		getHibernateTemplate().execute(new HibernateCallback() {
			 public Object doInHibernate(Session session) throws HibernateException {
	                for (int i = 0; i < list.size(); i++) {
	                    session.save(list.get(i));
	                    if (i % 50 == 0) {// 每50条数据提交一次session内容
	                        session.flush();
	                        session.clear();
	                    }
	                }
	                session.flush();
	                session.clear();
	                return null;
	            }
		});
	}
	/**
	 * 保存或更新对象
	 * @param entity 实体对象
	 */
	public void saveorUpdate(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}
	/**
	 * 批量更新对象,每50条提交一次事务
	 * @param list
	 * @throws Exception
	 */
	public void updateBatch(final List<T> list)throws Exception{
		Assert.notEmpty(list);
		getHibernateTemplate().execute(new HibernateCallback() {
			 public Object doInHibernate(Session session) throws HibernateException {
	                for (int i = 0; i < list.size(); i++) {
	                    session.update(list.get(i));
	                    if (i % 50 == 0) {// 每50条数据提交一次session内容
	                        session.flush();
	                        session.clear();
	                    }
	                }
	                session.flush();
	                session.clear();
	                return null;
	            }
		});
	}
	/**
	 * 更新对象
	 * @param entity 实体对象
	 */
	public void update(T obj) {
		getHibernateTemplate().update(obj);
	}
	public Object get(ID id, Class cls){
		return getHibernateTemplate().get(cls, id);
	}
	/**
	 * 删除对象
	 * @param entity 实体对象
	 */
	public void delete(T obj) {
		getHibernateTemplate().delete(obj);
	}

	/**
	 * 通过ID查找并锁定对象
	 * @param id 记录的ID
	 * @return 实体对象
	 */
	public T lock(ID id) {
		return (T) currentSession().load(getPersistentClass(), id, LockOptions.UPGRADE);
	}

	/**
	 * 通过ID查找对象,不锁定对象
	 * @param id
	 * @return 实体对象
	 */
	@Override
	public T get(ID id) {
		return getHibernateTemplate().get(getPersistentClass(), id);
	}

	/**
	 * 通过ID查找对象,不锁定对象
	 * @param id 记录的ID
	 * @return 实体对象
	 */
	@Override
	public T load(ID id) {
		return getHibernateTemplate().load(getPersistentClass(), id);
	}

	/**
	 * 查找所有对象
	 * @return 对象列表
	 */
	@Override
	public List<T> findAll() {
		return getHibernateTemplate().loadAll(getPersistentClass());
	}

	/**
	 * HibernateTemplate查找对象
	 * @return 对象列表
	 */
	public List<T> findByNamedParam(String queryString , String[] paramName , Object[] value)  {
		return (List<T>) getHibernateTemplate().findByNamedParam(queryString,paramName,value);
	}
	/**
	 * 按HQL查询唯一对象.,HQL格式 (*** where p1=? and p2=?)
	 * @param hql
	 * @param values
	 * @return
	 */
	@Override
	public Object findUniqueByHqlIndex(final String hql, final Object... values) {
		Query query = this.currentSession().createQuery(hql);
		if (values != null && values.length > 0)
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		return query.uniqueResult();
	}

	/**
	 * 按HQL查询对象列表,HQL格式 (*** where p1=? and p2=?)
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 */
	@Override
	public List<T> findByHqlIndex(final String hql, final Object... values) {
		Query query = this.currentSession().createQuery(hql);
		if (values != null && values.length > 0)
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		return query.list();
	}
	/**
	 * 按HQL分页查询对象列表,HQL格式 (*** where p1=? and p2=?)
	 * @param pageNo 第几页
	 * @param pageSize 每页多少条
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 */
	@Override
	public List<T> findByHqlIndexByPage(String hql, int pageNo, int pageSize,Object[] values){
		Query query = this.currentSession().createQuery(hql);
		if (values != null && values.length > 0)
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	/**
	 * 根据HQL,参数名称和参数值获取对象列表,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param params
	 * @param values
	 * @return
	 */
	@Override
	public List findByHqlParam(final String hql, final String[] params,final Object[] values) {
		Query query = this.currentSession().createQuery(hql);
		if (params != null && values != null && values.length > 0&& params.length > 0 && params.length == values.length)
			for (int i = 0; i < params.length; i++) {
				query.setParameter(params[i].toString(), values[i]);
			}
		return query.list();
	}
	/**
	 * 根据HQL,参数名称和参数值获取对象列表,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param queryMap   {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	@Override
	public List findByHqlParamMap(final String hql,Map<String,Object> queryMap){
		Query query = this.currentSession().createQuery(hql);
		if(queryMap!=null&&!queryMap.isEmpty()){
			for(String key:queryMap.keySet()){
				query.setParameter(key,queryMap.get(key));
			}
		}
		return query.list();
	}
	/**
	 *  根据SQL语句分页查询,SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param sql
	 * @param pageNo  页码
	 * @param pageSize  每页数量
	 * @param queryMap 条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Pagination findBySqlParamMap(String sql,int pageNo, int pageSize,Map<String,Object> queryMap) throws Exception{
		//coutSql
		int totalCount =this.findCountBySql(sql, queryMap);
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			pagination.setList(new ArrayList());
			return pagination;
		}
		SQLQuery query = this.currentSession().createSQLQuery(sql);
		if(queryMap!=null&&!queryMap.isEmpty()){
			for(String key:queryMap.keySet()){
				query.setParameter(key,queryMap.get(key));
			}
		}
		query.setFirstResult(pagination.getFirstResult());
		query.setMaxResults(pagination.getPageSize());
		query.setResultTransformer(Transformers.TO_LIST);
		pagination.setList(query.list());
	    return pagination;
	}
	public List<Map<String,Object>> findListBySqlParamMap(String sql,Map<String,Object> queryMap) throws Exception{
		SQLQuery query = this.currentSession().createSQLQuery(sql);
		if(queryMap!=null&&!queryMap.isEmpty()){
			for(String key:queryMap.keySet()){
				query.setParameter(key,queryMap.get(key));
			}
		}
		//转成list集合
		query.setResultTransformer(Transformers.TO_LIST);
		return query.list();
	}
	/**
	 * 根据HQL,参数名称和参数值获取唯一对象,,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param params
	 * @param values
	 * @return
	 */
	@Override
	public Object findUniqueByHqlParam(String hql, String[] params, Object[] values) {
		Query query = this.currentSession().createQuery(hql);
		if (params != null && values != null && values.length > 0&& params.length > 0 && params.length == values.length)
			for (int i = 0; i < params.length; i++) {
				query.setParameter(params[i].toString(), values[i]);
			}
		return query.uniqueResult();
	}
	/**
	 * 根据HQL,参数名称和参数值获取唯一对象,,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param queryMap  {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	@Override
	public Object findUniqueByHqlParamMap(String hql, Map<String,Object> queryMap){
		Query query = this.currentSession().createQuery(hql);
		if(queryMap!=null&&!queryMap.isEmpty()){
			for(String key:queryMap.keySet()){
				query.setParameter(key,queryMap.get(key));
			}
		}
		return query.uniqueResult();
	}
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Pagination findAll(int pageNo, int pageSize) {
		StringBuffer hqlfrom=new StringBuffer();
		hqlfrom.append("from  ").append(getPersistentClass().getName()).append(" t ");
		StringBuffer hqlCount=new StringBuffer();
		hqlCount.append("select count(*) ").append(hqlfrom);
		int totalCount =( (Integer) currentSession().createQuery(hqlCount.toString()).iterate().next() ).intValue();
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			pagination.setList(new ArrayList());
			return pagination;
		}
		Query query = currentSession().createQuery(hqlfrom.toString())
				                      .setFirstResult(pagination.getFirstResult())
		                              .setMaxResults(pagination.getPageSize());
		pagination.setList(query.list());
		return pagination;
	}

	/**
	 * 按属性查找对象列表.
	 * @param property
	 * @param value
	 * @return
	 */
	@Override
	public List<T> findByProperty(String property, Object value) {
		Assert.hasText(property);
		StringBuffer hqlfrom=new StringBuffer();
		hqlfrom.append("from  ").append(getPersistentClass().getName()).append(" t ").append(" where t.").append(property).append("=? ");
		return currentSession().createQuery(hqlfrom.toString()).setParameter(0, value).list();
	}

	/**
	 *  按属性查找唯一对象.
	 * @param property
	 * @param value
	 * @return
	 */
	@Override
	public T findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		StringBuffer hqlfrom=new StringBuffer();
		hqlfrom.append("from  ").append(getPersistentClass().getName()).append(" t ").append(" where t.").append(property).append("=? ");
		return (T) currentSession().createQuery(hqlfrom.toString()).setParameter(0, value).uniqueResult();
	}

	/**
	 * 按属性值索引查找对象的数量,HQL格式 (*** where p1=? and p2=?)
	 * @param hql
	 * @param values
	 * @return
	 */
	@Override
	public int countByHqlIndex(String hql, Object... values) {
		String countHql = HqlUtil.getRowCountHql(hql);
		Query query = this.currentSession().createQuery(countHql);
		if (values != null && values.length > 0)
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		return  ((Integer) query.iterate().next()).intValue();
	}

	/**
	 * 按属性名称查找对象的数量,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param params
	 * @param values
	 * @return
	 */
	@Override
	public int countByHqlParam(String hql, String[] params, Object[] values) {
		String countHql = HqlUtil.getRowCountHql(hql);
		Query query = this.currentSession().createQuery(countHql);
		if (params != null && values != null && values.length > 0
				&& params.length > 0 && params.length == values.length)
			for (int i = 0; i < params.length; i++) {
				query.setParameter(params[i].toString(), values[i]);
			}
		return ((Integer) query.iterate().next()).intValue();
	}
	/**
	 * 按属性名称查找对象的数量,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param params
	 * @param values
	 * @return
	 */
	@Override
	public int countByHqlParamMap(String hql, Map<String,Object> queryMap) {
		String countHql = HqlUtil.getRowCountHql(hql);
		Query query = this.currentSession().createQuery(countHql);
		if (queryMap != null && !queryMap.isEmpty())
			for (String key:queryMap.keySet()) {
				query.setParameter(key,queryMap.get(key));
			}
		return ((Integer) query.iterate().next()).intValue();
	}

	/**
	 * 获取总数量
	 * @return
	 */
	@Override
	public int count() {
		Query query = this.currentSession().createQuery("from "+getPersistentClass().getName());
		return ((Integer) query.iterate().next()).intValue();
	}

	/**
	 * 根据ID删除记录
	 * @param id 记录ID
	 */
	@Override
	public T deleteById(ID id) {
		T t = this.load(id);
		this.getHibernateTemplate().delete(t);
		return t ;
	}

	@Override
	public void deleteByIds(List<ID> ids) {
		if(ids == null || ids.size() == 0){
			return;
		}
		StringBuffer sqlWhere=new StringBuffer();
		int i=0;
		for(ID id : ids){
			i++;
			if(i!=ids.size()){
				sqlWhere.append("'").append(id).append("',");
			}else{
				sqlWhere.append("'").append(id).append("'");
			}
		}
		this.currentSession().createQuery("delete from "+getPersistentClass() +" t where t.in("+sqlWhere+")").executeUpdate();
	}
	/**
	 * 根据sql查询数量
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int findCountBySql(String sql)throws Exception{
		return this.findCountBySql(sql, null);
	}
	/**
	 * 根据sql查询数量  SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param sql 
	 * @param queryMap queryMap  条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 * @throws Exception
	 */
	public int findCountBySql(String sql, Map<String,Object> queryMap)throws Exception{
		String countSql = HqlUtil.getRowCountHql(sql);
		SQLQuery countQuery = this.currentSession().createSQLQuery(countSql);
		
		if(queryMap!=null&&queryMap.size()>0){
			for(String key:queryMap.keySet()){
				countQuery.setParameter(key,queryMap.get(key));
			}
		}
		return ((Integer)countQuery.iterate().next()).intValue();
	}
	  /**
     * 根据sql查询单一值
     * @param sql
     * @return
     */
	@Override
	public Object findUniqueBySql(String sql) throws Exception {
		return  this.findUniqueBySql(sql, null);
	}
	/**
	 * 
	 * @param sql   SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param queryMap  条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object findUniqueBySql(String sql, Map<String,Object> queryMap)throws Exception{
		SQLQuery query = this.currentSession().createSQLQuery(sql);
		if(queryMap!=null&&queryMap.size()>0){
			for(String key:queryMap.keySet()){
				query.setParameter(key,queryMap.get(key));
			}
		}
		return query.uniqueResult();
	}
	/**
	 * 根据sql更新
	 * @param string
	 */
	@Override
	public int executeSql(String sql)throws Exception {
		return executeSql(sql, null);
	}
	/**
	 * 根据sql 更新
	 * @param sql   SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param queryMap  条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 * @throws Exception
	 */
	@Override
	public int executeSql(String sql, Map<String,Object> queryMap)throws Exception {
		SQLQuery query = this.currentSession().createSQLQuery(sql);
		if(queryMap!=null&&queryMap.size()>0){
			for(String key:queryMap.keySet()){
				query.setParameter(key,queryMap.get(key));
			}
		}
		return query.executeUpdate();
	}
	/**
	 * 内部类，从原始HQL拼装出求和的HQL
	 */
	static class HqlUtil {
		public static final String ROW_COUNT = "select count(*) ";
		public static final String FROM = "from";
		public static final String DISTINCT = "distinct";
		public static final String HQL_FETCH = "fetch";
		public static final String ORDER_BY = "order ";

		public static String getRowCountHql(String hql) {
			int fromIndex = hql.toLowerCase().indexOf(FROM);
			String projectionHql = hql.substring(0, fromIndex);

			hql = hql.substring(fromIndex);
			String rowCountHql = hql.replace(HQL_FETCH, "");

			int index = rowCountHql.indexOf(ORDER_BY);
			if (index > 0) {
				rowCountHql = rowCountHql.substring(0, index);
			}
			return wrapProjection(projectionHql) + rowCountHql;
		}

		private static String wrapProjection(String projection) {
			if (projection.indexOf("select") == -1) {
				return ROW_COUNT;
			} else {
				return projection.replace("select", "select count(") + ") ";
			}
		}
	}

}