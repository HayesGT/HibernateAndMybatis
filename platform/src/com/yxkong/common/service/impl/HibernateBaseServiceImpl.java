package com.yxkong.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.yxkong.common.dao.IHibernateBaseDao;
import com.yxkong.common.model.BaseEntity;
import com.yxkong.common.page.Pagination;
import com.yxkong.common.service.IHibernateBaseService;

/**
 * Service - 基类
 * 
 * 
 * 
 */
@Transactional
@SuppressWarnings({"unused","rawtypes"})
public abstract class HibernateBaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements IHibernateBaseService<T, ID> {
   
    private static Log log = LogFactory.getLog(HibernateBaseServiceImpl.class);
	
	public abstract IHibernateBaseDao<T, ID> getHibernateDao();

	@Override
	public T lock(ID id) {
		return this.getHibernateDao().lock(id);
	}

	@Override
	public T get(ID id) {
		return this.getHibernateDao().get(id);
	}

	@Override
	public T load(ID id) {
		return this.getHibernateDao().load(id);
	}

	@Override
	public List<T> findAll() {
		return this.getHibernateDao().findAll();
	}

	@Override
	public Object findUniqueByHqlIndex(String hql, Object... values) {
		return this.getHibernateDao().findUniqueByHqlIndex(hql, values);
	}

	@Override
	public List findByHqlIndex(String hql, Object... values) {
		return this.getHibernateDao().findByHqlIndex(hql, values);
	}

	@Override
	public List<T> findByNamedParam(String queryString, String[] paramName,
			Object[] value) {
		return this.getHibernateDao().findByNamedParam(queryString, paramName, value);
	}

	@Override
	public List findByHqlParam(String hql, String[] params, Object[] values) {
		return this.getHibernateDao().findByHqlParam(hql, params, values);
	}

	@Override
	public List findByHqlParamMap(String hql, Map<String, Object> queryMap) {
		return this.getHibernateDao().findByHqlParamMap(hql, queryMap);
	}

	@Override
	public List findByHqlIndexByPage(String hql, int pageNo, int pageSize,
			Object[] values) {
		return this.getHibernateDao().findByHqlIndexByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public Object findUniqueByHqlParam(String hql, String[] params,
			Object[] values) {
		return this.getHibernateDao().findUniqueByHqlParam(hql, params, values);
	}

	@Override
	public Object findUniqueByHqlParamMap(String hql,
			Map<String, Object> queryMap) {
		return this.getHibernateDao().findUniqueByHqlParamMap(hql, queryMap);
	}

	@Override
	public Pagination findAll(int pageNo, int pageSize) {
		return this.getHibernateDao().findAll(pageNo, pageSize);
	}

	@Override
	public Pagination findByHqlIndex(String hql, int pageNo, int pageSize,
			Object... values) {
		return this.getHibernateDao().findByHqlIndex(hql, pageNo, pageSize, values);
	}

	@Override
	public Pagination findByHqlParam(String hql, int pageNo, int pageSize,
			String[] params, Object[] values) {
		return this.getHibernateDao().findByHqlParam(hql, pageNo, pageSize, params, values);
	}

	@Override
	public Pagination findByHqlParamMap(String hql, int pageNo, int pageSize,
			Map<String, Object> queryMap) {
		return this.getHibernateDao().findByHqlParamMap(hql, pageNo, pageSize, queryMap);
	}

	@Override
	public Pagination findBySqlParamMap(String sql, int pageNo, int pageSize,
			Map<String, Object> queryMap) throws Exception {
		return this.getHibernateDao().findBySqlParamMap(sql, pageNo, pageSize, queryMap);
	}

	@Override
	public List<Map<String, Object>> findListBySqlParamMap(String sql,
			Map<String, Object> queryMap) throws Exception {
		return this.getHibernateDao().findListBySqlParamMap(sql, queryMap);
	}

	@Override
	public Pagination findByHqlProperty(int pageNo, int pageSize,
			String property, Object value) {
		return this.getHibernateDao().findByHqlProperty(pageNo, pageSize, property, value);
	}

	@Override
	public List<T> findByProperty(String property, Object value) {
		return this.getHibernateDao().findByProperty(property, value);
	}

	@Override
	public T findUniqueByProperty(String property, Object value) {
		return this.getHibernateDao().findUniqueByProperty(property, value);
	}

	@Override
	public int countByHqlIndex(String hql, Object... values) {
		return this.getHibernateDao().countByHqlIndex(hql, values);
	}

	@Override
	public int countByHqlParam(String hql, String[] params, Object[] values) {
		return this.getHibernateDao().countByHqlParam(hql, params, values);
	}

	@Override
	public int countByHqlParamMap(String hql, Map<String, Object> queryMap) {
		return this.getHibernateDao().countByHqlParamMap(hql, queryMap);
	}

	@Override
	public int count() {
		return this.getHibernateDao().count();
	}

	@Override
	public int countByProperty(String property, Object value) {
		return this.getHibernateDao().countByProperty(property, value);
	}

	@Override
	public T save(T entity) {
		return this.getHibernateDao().save(entity);
	}

	@Override
	public void saveBatch(List<T> list) throws Exception {
		this.getHibernateDao().saveBatch(list);
	}

	@Override
	public void update(T entity) {
		this.getHibernateDao().update(entity);
	}

	@Override
	public void updateBatch(List<T> list) throws Exception {
		this.getHibernateDao().updateBatch(list);
	}

	@Override
	public void saveorUpdate(T entity) {
		this.getHibernateDao().saveorUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateDao().delete(entity);
	}

	@Override
	public T deleteById(ID id) {
		return this.getHibernateDao().deleteById(id);
	}

	@Override
	public void deleteByIds(List<ID> ids) throws Exception {
		this.getHibernateDao().deleteByIds(ids);
	}

	@Override
	public Object findUniqueBySql(String sql) throws Exception {
		return this.getHibernateDao().findUniqueBySql(sql);
	}

	@Override
	public int findCountBySql(String sql) throws Exception {
		return this.getHibernateDao().findCountBySql(sql);
	}

	@Override
	public int findCountBySql(String sql, Map<String, Object> queryMap)
			throws Exception {
		return this.getHibernateDao().findCountBySql(sql, queryMap);
	}

	@Override
	public Object findUniqueBySql(String sql, Map<String, Object> queryMap)
			throws Exception {
		return this.getHibernateDao().findUniqueBySql(sql, queryMap);
	}

	@Override
	public int executeSql(String sql) throws Exception {
		return this.getHibernateDao().executeSql(sql);
	}

	@Override
	public int executeSql(String sql, Map<String, Object> queryMap)
			throws Exception {
		return this.getHibernateDao().executeSql(sql, queryMap);
	}


}