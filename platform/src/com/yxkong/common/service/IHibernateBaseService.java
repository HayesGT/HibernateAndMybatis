package com.yxkong.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yxkong.common.model.BaseEntity;
import com.yxkong.common.page.Pagination;



/**
 * Service - 基类
 */
@SuppressWarnings({"rawtypes"})
public interface IHibernateBaseService<T extends BaseEntity, ID extends Serializable> {
	/**
	 * 通过ID查找并锁定对象
	 * @param id 记录的ID
	 * @return 实体对象
	 */
	public T lock(ID id);

	/**
	 * 通过ID查找对象,不锁定对象
	 * @param id
	 * @return 实体对象
	 */
	public T get(ID id);

	/**
	 * 通过ID查找对象,不锁定对象
	 * @param id 记录的ID
	 * @return 实体对象
	 */
	public T load(ID id);

	/**
	 * 查找所有对象
	 * @return 对象列表
	 */
	public List<T> findAll();
	
	/**
	 * 按HQL查询唯一对象.,HQL格式 (*** where p1=? and p2=?)
	 * @param hql
	 * @param values
	 * @return
	 */
	public Object findUniqueByHqlIndex(String hql, Object... values);
	
	/**
	 * 按HQL查询对象列表,HQL格式 (*** where p1=? and p2=?)
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 */
	public List findByHqlIndex(String hql, Object... values);
	
	/**根据属性名查询
	 * HibernateTemplate查找对象
	 * @return 对象列表
	 */
	public List<T> findByNamedParam(String queryString , String[] paramName , Object[] value);
	
	/**
	 * 根据HQL,参数名称和参数值获取对象列表,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param params
	 * @param values
	 * @return
	 */
	public List findByHqlParam(String hql, String[] params, Object[] values);
	/**
	 * 根据HQL,参数名称和参数值获取对象列表,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param queryMap   {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	public List findByHqlParamMap(String hql,Map<String, Object> queryMap);
	/**
	 * 根据分页查询数据
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public List findByHqlIndexByPage(String hql, int pageNo, int pageSize,Object[] values);
	/**
	 * 根据HQL,参数名称和参数值获取唯一对象,,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param params
	 * @param values
	 * @return
	 */
	public Object findUniqueByHqlParam(String hql, String[] params, Object[] values);
	/**
	 * 根据HQL,参数名称和参数值获取唯一对象,,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param queryMap  {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	public Object findUniqueByHqlParamMap(String hql, Map<String,Object> queryMap);
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination findAll(int pageNo, int pageSize);
	
	/**
	 * 根据HQL语句分页查询,HQL格式 (*** where p1=? and p2=?)
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public Pagination findByHqlIndex(String hql,int pageNo, int pageSize,Object... values);
	
	/**
	 * 根据HQL语句分页查询,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public Pagination findByHqlParam(String hql,int pageNo, int pageSize,String[] params,Object[] values);
	/**
	 *  根据HQL语句分页查询,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param pageNo  页码
	 * @param pageSize  每页数量
	 * @param queryMap 条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	public Pagination findByHqlParamMap(String hql,int pageNo, int pageSize,Map<String,Object> queryMap);
	/**
	 *  根据SQL语句分页查询,SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param sql
	 * @param pageNo  页码
	 * @param pageSize  每页数量
	 * @param queryMap 条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	public Pagination findBySqlParamMap(String sql,int pageNo, int pageSize,Map<String,Object> queryMap) throws Exception;
	/**
	 *  根据SQL语句分页查询,SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param sql
	 * @param queryMap 条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	public List<Map<String,Object>> findListBySqlParamMap(String sql,Map<String,Object> queryMap) throws Exception;
	
	/**
	 * 根据属性值分页查询
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public Pagination findByHqlProperty(int pageNo, int pageSize,String property,Object value);
	

	/**
	 * 按属性查找对象列表.
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> findByProperty(String property, Object value);

	/**
	 *  按属性查找唯一对象.
	 * @param property
	 * @param value
	 * @return
	 */
	public T findUniqueByProperty(String property, Object value);

	/**
	 * 按属性值索引查找对象的数量,HQL格式 (*** where p1=? and p2=?)
	 * @param hql
	 * @param values
	 * @return
	 */
	public int countByHqlIndex(String hql, Object... values);
	
	/**
	 * 按属性名称查找对象的数量,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param params
	 * @param values
	 * @return
	 */
	public int countByHqlParam(String hql,String[] params,Object[] values);
	/**
	 * 按属性名称查找对象的数量,HQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param hql
	 * @param queryMap 条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 */
	public int countByHqlParamMap(String hql, Map<String,Object> queryMap);
	
	/**
	 * 获取总数量
	 * @return
	 */
	public int count();
	
	/**
	 * 按属性查找对象的数量
	 * @param property
	 * @param value
	 * @return
	 */
	public int countByProperty(String property, Object value);
	
	/**
	 * 保存对象
	 * @param entity 实体对象
	 * @return 主键
	 */
	public T save(T entity);
	/**
	 * 批量保存对象,每50条提交一次事务
	 * @param list
	 */
	public void saveBatch(List<T> list)throws Exception;
	/**
	 * 更新对象
	 * @param entity 实体对象
	 */
	public void update(T entity);
	/**
	 * 批量更新对象,每50条提交一次事务
	 * @param list
	 * @throws Exception
	 */
	public void updateBatch(List<T> list)throws Exception;

	/**
	 * 保存或更新对象
	 * @param entity 实体对象
	 */
	public void saveorUpdate(T entity);

	
	/**
	 * 删除对象
	 * @param entity 实体对象
	 */
	public void delete(T entity);

	/**
	 * 根据ID删除记录
	 * @param id 记录ID
	 */
	public T deleteById(ID id);
	
	/**
	 * 更具 ID 循环删除记录，如果对象不存在在后台打出日志，但不抛出异常
	 * @param ids
	 */
	public void deleteByIds(List<ID> ids)throws Exception;
	  /**
     * 根据sql查询单一值
     * @param sql
     * @return
     */
	public Object findUniqueBySql(String sql)throws Exception;
	/**
	 * 根据sql查询数量
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int findCountBySql(String sql)throws Exception;
	/**
	 * 根据sql查询数量  SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param sql 
	 * @param queryMap queryMap  条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 * @throws Exception
	 */
	public int findCountBySql(String sql, Map<String,Object> queryMap)throws Exception;
	/**
	 * 根据sql单值查询
	 * @param sql   SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param queryMap  条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 * @throws Exception
	 */
	public Object findUniqueBySql(String sql, Map<String,Object> queryMap)throws Exception;
	/**
	 * 根据sql更新
	 * @param string
	 */
	public int executeSql(String sql)throws Exception;
	/**
	 * 根据sql 更新
	 * @param sql   SQL格式 (*** where p1=:p1 and p2=:p2)
	 * @param queryMap  条件值封装  {"p1":"xxx","p2":"zzz"}
	 * @return
	 * @throws Exception
	 */
	public int executeSql(String sql, Map<String,Object> queryMap)throws Exception;


}