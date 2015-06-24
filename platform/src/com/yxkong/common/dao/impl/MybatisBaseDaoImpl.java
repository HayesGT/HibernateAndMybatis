package com.yxkong.common.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.dao.SQLAdapter;
import com.yxkong.common.model.IdEntity;
import com.yxkong.common.utils.ReflectGenricUtil;
import com.yxkong.common.utils.StringUtil;
import com.yxkong.common.utils.UUIDUtil;
import com.yxkong.common.utils.bean.BeanMapUtil;
@SuppressWarnings("unchecked")
public abstract  class MybatisBaseDaoImpl<T extends IdEntity,ID extends Serializable >  extends SqlSessionDaoSupport implements IMybatisBaseDao<T,ID> {
	public static final String SQLNAME_SEPARATOR = ".";

	public static final String SQL_SAVE = "save";   
	public static final String SQL_UPDATE = "update";   
	public static final String SQL_FINDBYID = "findById";
	public static final String SQL_DELETEBYID = "deleteById";
	public static final String SQL_DELETEBYIDS = "deleteByIds";
	public static final String SQL_FINDLISTBY = "findListBy";
	public static final String SQL_FINDLISTBYMAP = "findListByMap";
	public static final String SQL_FINDCOUNTBY = "findCountBy";

	private static final String SORT_NAME = "SORT";
	
	private static final String DIR_NAME = "DIR";
	/** 不能用于SQL中的非法字符（主要用于排序字段名） */
	public static final String[] ILLEGAL_CHARS_FOR_SQL = {",", ";", " ", "\"", "%"};
	private Class<T> clazz ;
	/**
	 * spring-mabatis1.2.2已经将sqlSessionFactory的自动注入移除掉了
	 */
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	/**
	 * 功能说明：获取默认SqlMapping命名空间
	 *       <br/>使用泛型参数中业务实体类型的全限定名作为默认的命名空间。
	 *       <br/>如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则。
	 * @author ducc
	 * @updated 
	 * @return 返回命名空间字符串
	 */
	protected String getDefaultSqlNamespace() {
		clazz= ReflectGenricUtil.getClassGenricType(this.getClass());
		return clazz.getName();
	}
	/**
	 * 获取 SqlMapping 的命名空间
	 */
	private String sqlNamespace = getDefaultSqlNamespace();
	/**
	 * 功能说明：将SqlMapping命名空间与给定的SqlMapping名组合在一起。
	 * @author ducc
	 * @updated 
	 * @param sqlName  SqlMapping名
	 * @return 组合了SqlMapping命名空间后的完整SqlMapping名
	 */
	protected String getSqlName(String sqlName) {
		return sqlNamespace+ SQLNAME_SEPARATOR + sqlName;
	}
	/**
	 * 功能说明：获取 SqlMapping 的命名空间
	 * @author ducc
	 * @updated 
	 * @return  SqlMapping命名空间
	 */
	public String getSqlNamespace() {
		return sqlNamespace;
	}
	/**
	 * 功能说明：设置SqlMapping命名空间。
	 *       此方法只用于注入SqlMapping命名空间，以改变默认的SqlMapping命名空间，
	 *       不能滥用此方法随意改变SqlMapping命名空间。
	 * @author ducc
	 * @updated 
	 * @param sqlNamespace 要设置的sqlMapping命名空间的名称
	 */
	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}
    /**
     * 功能说明：生成主键值。
     *     默认情况下什么也不做；
     *     如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值。
     * @author ducc
     * @updated 
     * @param item 要持久化的对象
     */
    protected void generateId(T item) {
    	item.setId(UUIDUtil.uuid());
	}
	public Integer save(T item) {
		generateId(item);
		return this.getSqlSession().insert(getSqlName(SQL_SAVE),item);
	}
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用。
	 * @author ducc
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @param parameter 参数
	 * @return 执行结果——插入成功的记录数
	 */
	public Integer save(String keyId, Object parameter) {
		return this.getSqlSession().insert(getSqlName(keyId), parameter);
	}
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用。
	 * @author ducc
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @return 执行结果——插入成功的记录数
	 */
	protected Integer save(String keyId) {
		return this.getSqlSession().insert(getSqlName(keyId));
	}
	/**
	 * 功能说明：使用update方法更新
	 * @author ducc
	 * @updated 
	 * @return 执行结果——插入成功的记录数
	 */
	public Integer update(T item) {
		return this.getSqlSession().update(getSqlName(SQL_UPDATE), item);
	}
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用
	 * @author ducc
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @param parameter 参数
	 * @return 执行结果——更新成功的记录数
	 */
	public Integer update(String keyId, Object parameter) {
		return this.getSqlSession().update(getSqlName(keyId), parameter);
	}
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用。
	 * @author ducc
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @return 执行结果——更新成功的记录数
	 */
	protected int update(String keyId) {
		return this.getSqlSession().update(getSqlName(keyId));
	}
	public T findById(ID id) {
		return (T)this.getSqlSession().selectOne(getSqlName(SQL_FINDBYID), id);
	}
	public T findBy(String keyId,Object parameter) {
		return (T)this.getSqlSession().selectOne(getSqlName(keyId), parameter);
	}
	public Map<String,Object> findMapBy(String namespace,String keyId,Map<String,Object> map){
		return this.getSqlSession().selectOne(namespace+"."+keyId, map);
	}
	public Integer deleteById(ID id) {
		return this.getSqlSession().delete(getSqlName(SQL_DELETEBYID), id);
	}
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用。
	 * @author ducc
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @param parameter 参数
	 * @return 执行结果——删除成功的记录数
	 */
	public int deleteBy(String keyId, Object parameter) {
		return this.getSqlSession().delete(getSqlName(keyId), parameter);
	}
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用。
	 * @author ducc
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @return
	 */
	protected int delete(String statement) {
		return this.getSqlSession().delete(getSqlName(statement));
	}
	public Integer deleteByIds(ID[] ids){
		return this.getSqlSession().delete(getSqlName(SQL_DELETEBYIDS), ids);
	}
	/**
	 * 功能说明：从给定字符串中将指定的非法字符串数组中各字符串过滤掉。
	 * @author ducc
	 * @updated 
	 * @param str 待过滤的字符串
	 * @param filterChars 指定的非法字符串数组
	 * @return 过滤后的字符串
	 */
	protected String filterIllegalChars(String str, String[] filterChars) {
		String rs = str;
		if (rs != null && filterChars != null) {
			for (String fc : filterChars) {
				if (fc != null && fc.length() > 0) {
					str = str.replaceAll(fc, ""); 
				}
			}
		}
		return rs;
	}
	public Integer findCountBy(T item) {
		return this.getSqlSession().selectOne(getSqlName(SQL_FINDCOUNTBY), item);
	}
	protected List<T> selectList(String keyId, Object parameter, RowBounds rowBounds) {
		return this.getSqlSession().selectList(getSqlName(keyId), parameter, rowBounds);
	}
	
	public List<T> findListBy(T item, String sortColumn, String des) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		try{
			if(item!=null){
				paramMap = BeanMapUtil.bean2Map(item);
			}
		}catch(Exception e){
			throw new RuntimeException("获取参数失败", e);
		}
		if (sortColumn != null) {
			// 排序字段不为空，过滤其中可能存在的非法字符
			sortColumn = filterIllegalChars(sortColumn, ILLEGAL_CHARS_FOR_SQL);
		}
		if (StringUtil.isNotEmpty(sortColumn) && StringUtil.isNotEmpty(des)) {
			paramMap.put(SORT_NAME, sortColumn);
			paramMap.put(DIR_NAME, des);
		} 
		return this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBY), paramMap);
	}
	public List<T> findListBySql(String keyId,String sql){
		return this.getSqlSession().selectList(getSqlName(keyId) , new SQLAdapter(sql));
	}
	public List<T> selectList(String keyId, Object parameter) {
		return this.getSqlSession().selectList(getSqlName(keyId), parameter);
	}
	public List<Map<String,Object>> selectListMap(String keyId, Object parameter) {
		return this.getSqlSession().selectList(getSqlName(keyId), parameter);
	}
	protected List<T> selectList(String keyId) {
		return this.getSqlSession().selectList(getSqlName(keyId));
	}
	protected Object selectOne(String keyId, Object parameter) {
		return this.getSqlSession().selectOne(getSqlName(keyId), parameter);
	}
	protected Object selectOne(String keyId) {
		return this.getSqlSession().selectOne(getSqlName(keyId));
	}
	protected Map<String,Object> selectMap(String keyId, Object parameter, String mapKey,RowBounds rowBounds) {
		return this.getSqlSession().selectMap(getSqlName(keyId),parameter, mapKey, rowBounds);
	}
	/**
	 * 功能说明：
	 * @author ducc
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @param parameter 参数
	 * @param mapKey 数据mapKey
	 * @return
	 */
	protected Map<String,Object> selectMap(String keyId, Object parameter, String mapKey) {
		return this.getSqlSession().selectMap(getSqlName(keyId), parameter, mapKey);
	}
	protected void select(String keyId, Object parameter, RowBounds rowBounds,
			ResultHandler handler) {
		this.getSqlSession().select(getSqlName(keyId),parameter, rowBounds, handler);
	}
	protected void select(String keyId, Object parameter, ResultHandler handler) {
		this.getSqlSession().select(getSqlName(keyId), parameter, handler);
	}
	protected void select(String keyId, ResultHandler handler) {
		this.getSqlSession().select(getSqlName(keyId), handler);
	}
	public List<T> findListBy(T item){
		return findListBy(item, null, null);
	}
	public List<T> findListByMap(Map<String,Object> map){
		return this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBYMAP), map);
	}
	public List<T> findListByMap(String keyId,Map<String,Object> map){
		return this.getSqlSession().selectList(getSqlName(keyId), map);
	}
	public List<T> findListBy(String namespace,String keyId,T item){
		return findListBy(namespace,keyId,item,null,null);
	}
	public List<T> findListBy(String namespace,String keyId,T item,String sortColumn,String des){
		Map<String,Object> paramMap=new HashMap<String,Object>();
		try{
			if(item!=null){
				paramMap = BeanMapUtil.bean2Map(item);
			}
		}catch(Exception e){
			throw new RuntimeException("获取参数失败", e);
		}
		if (sortColumn != null) {
			// 排序字段不为空，过滤其中可能存在的非法字符
			sortColumn = filterIllegalChars(sortColumn, ILLEGAL_CHARS_FOR_SQL);
		}
		if (StringUtil.isNotEmpty(sortColumn) && StringUtil.isNotEmpty(des)) {
			paramMap.put(SORT_NAME, sortColumn);
			paramMap.put(DIR_NAME, des);
		} 
		return this.getSqlSession().selectList(namespace+"."+keyId, paramMap);
	}
	public List<Map<String,Object>> findListMapBy(String namespace,String keyId,Map<String,Object> map){
		return findListMapBy(namespace,keyId,map,null,null);
	}
	public List<Map<String,Object>> findListMapBy(String namespace,String keyId,Map<String,Object> map,String sortColumn,String des){
		
		Map<String,Object> paramMap=new HashMap<String,Object>();
		if(map!=null&&map.size()>0){
			paramMap.putAll(map);
		}
		if (sortColumn != null) {
			// 排序字段不为空，过滤其中可能存在的非法字符
			sortColumn = filterIllegalChars(sortColumn, ILLEGAL_CHARS_FOR_SQL);
		}
		if (StringUtil.isNotEmpty(sortColumn) && StringUtil.isNotEmpty(des)) {
			paramMap.put(SORT_NAME, sortColumn);
			paramMap.put(DIR_NAME, des);
		} 
		return this.getSqlSession().selectList(namespace+"."+keyId, paramMap);
	}
	@Override
	public List<T> findList() {
		return findListBy(null);
	}
	public List<Map<String,Object>> findListMapBySql(String sql,Object... obj) throws SQLException{
		SqlRunner sr=new SqlRunner(getSqlSession().getConnection());
		return sr.selectAll(sql, obj);
	}
	public Map<String,Object> findMapBySql(String sql,Object... obj )throws SQLException{
		SqlRunner sr=new SqlRunner(getSqlSession().getConnection());
		List<Map<String,Object>> selectAll=sr.selectAll(sql, obj);
		if(selectAll!=null&&selectAll.size()>0){
			return selectAll.get(0);
		}
		return null;
	}
	public Integer updateBySql(String sql,Object... obj)throws SQLException{
		SqlRunner sr=new SqlRunner(getSqlSession().getConnection());
		return sr.update(sql, obj);
	}
	public Integer deleteBySql(String sql,Object... obj)throws SQLException{
		SqlRunner sr=new SqlRunner(getSqlSession().getConnection());
		return sr.delete(sql, obj);
	}



}
