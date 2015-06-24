package com.yxkong.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yxkong.common.dao.SQLAdapter;
import com.yxkong.common.model.IdEntity;
/**
 * 功能说明：基于Mybatis的基础Service接口
 * @author ducc
 * @created 2014年6月13日 下午8:33:05
 * @updated 
 * @param <T>
 * @param <ID>
 */
public  interface IMybatisBaseService<T extends IdEntity,ID extends Serializable> {
	/**
	 * 功能说明：保存实体
	 * @author ducc
	 * @created 2014年6月13日 上午10:06:13
	 * @updated 
	 * @param item 要保存的实体对象
	 * @return 返回执行成功的记录数
	 */
	public Integer save(T item);
	/***
	 * 功能说明：批量保存，只支持Mysql
	 * 例如：
	<p> <insert id="batchSave" parameterType="java.util.List">
        insert into
			<include refid="tableNameSql"/>
		(
			<include refid="commonSelectColumnsPrefix"/>
		)
		<foreach collection="list" item="item" index="index" separator="," > 
		    (	
				#{item.id, jdbcType=VARCHAR} ,
				#{item.roleId, jdbcType=VARCHAR} ,
				#{item.userId, jdbcType=VARCHAR} ,
				#{item.creator, jdbcType=VARCHAR} ,
				#{item.updator, jdbcType=VARCHAR} ,
				#{item.created, jdbcType=TIMESTAMP} ,
				#{item.updated, jdbcType=TIMESTAMP} ,
				#{item.sort, jdbcType=INTEGER} ,
				#{item.remark, jdbcType=VARCHAR} ,
				#{item.status, jdbcType=INTEGER} ,
				#{item.delStatue, jdbcType=INTEGER} 
		    )
		</foreach>
       </insert></p>
	 * @author ducc
	 * @created 2014年8月15日 下午12:59:57
	 * @updated 
	 * @param items
	 * @return
	 */
	public Integer batchSave(List<T> items);
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用。
	 * @author ducc
	 * @created 2014年6月13日 下午4:48:35
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @param parameter 参数
	 * @return 执行结果——插入成功的记录数
	 */
	public Integer save(String keyId, Object parameter) ;
	
	/**
	 * 功能说明：更新实体
	 * @author ducc
	 * @created 2014年6月13日 上午10:30:01
	 * @updated 
	 * @param item  要保存的实体对象
	 * @return 返回执行成功的记录
	 */
	public Integer update(T item);
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用
	 * @author ducc
	 * @created 2014年6月13日 下午4:51:04
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @param parameter 参数
	 * @return 执行结果——更新成功的记录数
	 */
	public Integer update(String keyId, Object parameter) ;
	/**
	 * 功能说明：根据id更新表的的stauts状态
	 * @author ducc
	 * @created 2014年8月3日 上午11:07:47
	 * @updated 
	 * @param id  更新id
	 * @param status 更新后的状态  1启用，0禁用
	 * @param clazz 对应的实体类
	 * @return
	 * @throws Exception
	 */
	public Integer updateStatus(String id,Integer status,Class<? extends IdEntity> clazz)throws Exception;
	/**
	 * 功能说明：根据id 更新表的delStatus状态
	 * @author ducc
	 * @created 2014年8月3日 上午11:11:17
	 * @updated 
	 * @param id
	 * @param status  1已删除 0未删除
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public Integer updateDelStatus(String id,Integer status,Class<? extends IdEntity> clazz) throws Exception;
	/**
	 * 功能说明：根据id获取业务实体对象
	 * @author ducc
	 * @created 2014年6月13日 上午10:31:19
	 * @updated 
	 * @param id 唯一标示符
	 * @return 指定的唯一标识符对应的持久化对象，如果没有对应的持久化对象，则返回null
	 */
	public T findById(ID id);
	/**
	 * 功能说明：根据自定义sqlId 查询单一对象
	 * @author ducc
	 * @created 2014年6月28日 上午7:37:13
	 * @updated 
	 * @param keyId
	 * @param obj
	 * @return
	 */
	public T findBy(String keyId,Object obj);
	/**
	 * 功能说明：根据业务id删除数据库中的记录
	 * @author ducc
	 * @created 2014年6月13日 上午10:32:44
	 * @updated 
	 * @param id 唯一标示符
	 * @return 返回更新成功的数量
	 */
	public Integer deleteById(ID id);
	/**
	 * 功能说明：批量删除记录
	 * @author ducc
	 * @created 2014年6月13日 上午11:12:51
	 * @updated 
	 * @param ids id数组
	 * @return  返回成功的数量
	 */
	public Integer deleteByIds(ID[] ids);
	/**
	 * 功能说明：将statement包装了命名空间，方便DAO子类调用。
	 * @author ducc
	 * @created 2014年6月13日 下午4:53:46
	 * @updated 
	 * @param keyId 映射的语句ID
	 * @param parameter 参数
	 * @return 执行结果——删除成功的记录数
	 */
	public int deleteBy(String keyId, Object parameter) ;
	/**
	 * 功能说明：获取满足查询条件的记录总数
	 * @author ducc
	 * @created 2014年6月13日 上午10:35:29
	 * @updated 
	 * @param item 查询参数封装的实体
	 * @return 查询的记录数
	 */
	public Integer findCountBy(T item);
	/**
	 * 功能说明：不分页查询 不带排序字段
	 * @author ducc
	 * @created 2014年6月13日 下午4:44:57
	 * @updated 
	 * @param item 查询的实体
	 * @return
	 */
	public List<T> findListBy(T item);
	/**
	 * 功能说明：不分页查询
	 * @author ducc
	 * @created 2014年6月13日 上午10:38:20
	 * @updated 
	 * @param item 实体封装的查询参数
	 * @param sortColumn  排序字段
	 * @param des 排序方式（升序(asc)或降序(desc)
	 * @return
	 */
	public List<T> findListBy(T item,String sortColumn,String des); 
	/**
	 * 功能说明： 根据 映射sql的id和条件map查询数据
	 * @author ducc
	 * @created 2014年8月15日 下午1:10:27
	 * @updated 
	 * @param keyId 映射sql的id
	 * @param item
	 * @return
	 */
	public List<T> findListByMap(String keyId,Map<String,Object> item); 
	/**
	 * 功能说明：根据sql查询list
	 * @author ducc
	 * @created 2014年7月15日 下午8:16:20
	 * @updated 
	 * @param sql
	 * @return
	 */
	public List<T> findListBySqlAdapter(String keyId,SQLAdapter sqlAdapter);
	/**
	 * 功能说明：根据sql查询list
	 * @author ducc
	 * @created 2014年7月15日 下午8:16:20
	 * @updated 
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>> findListMapBySqlAdapter(String keyId,SQLAdapter sqlAdapter);
	/**
	 * 功能说明：根据map查询列表
	 * @author ducc
	 * @created 2014年7月9日 下午5:20:15
	 * @updated 
	 * @param map
	 * @return
	 */
	public List<T> findListByMap(Map<String,Object> map);
	
	/**
	 * 功能说明： 不分页查询，不带条件，不带排序
	 * @author ducc
	 * @created 2014年6月13日 下午9:29:15
	 * @updated 
	 * @return
	 */
	public List<T> findList();
	/**
	 * 功能说明：根据自定义sql Id返回自定义的 list<T>集合
	 * @author ducc
	 * @created 2014年6月28日 上午7:34:37
	 * @updated 
	 * @param keyId
	 * @param parameter
	 * @return
	 */
	public List<T> selectList(String keyId, Object parameter);
	/**
	 * 功能说明：根据自定义sqlId 返回list<Map<String,Object>> 
	 * @author ducc
	 * @created 2014年6月28日 上午7:35:14
	 * @updated 
	 * @param keyId
	 * @param parameter
	 * @return
	 */
	public List<Map<String,Object>> selectListMap(String keyId, Object parameter);
	
}
