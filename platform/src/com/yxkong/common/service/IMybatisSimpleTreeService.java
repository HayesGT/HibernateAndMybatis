package com.yxkong.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yxkong.common.model.SimpleTreeBaseEntity;
/**
 * 功能说明：基于mybatis的简单树的操作
 * @author ducc
 * @created 2014年8月1日 下午2:22:27
 * @updated 
 * @param <T>
 * @param <ID>
 */
public  interface IMybatisSimpleTreeService<T extends SimpleTreeBaseEntity,ID extends Serializable> extends IMybatisBaseService<T, ID> {
	/**
	 * 功能说明：保存带左右值的节点树
	 * @author ducc
	 * @created 2014年8月1日 下午2:34:14
	 * @updated 
	 * @param item
	 * @return
	 */
	public void saveTreeNode(T item) throws Exception;
	/**
	 * 功能说明：根据id查询兄弟节点的数据
	 * @author ducc
	 * @created 2014年8月2日 下午6:23:43
	 * @updated 
	 * @param id
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findSiblingsById(String id,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception;
	/**
	 * 功能说明：根据id查询父节点的兄弟节点的数据
	 * @author ducc
	 * @created 2014年8月2日 下午6:23:43
	 * @updated 
	 * @param id
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findParentSiblingsById(String id,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception;
	/**
	 * 功能说明： 查询当前节点和它所有层级的子节点
	 * @author ducc
	 * @created 2014年8月2日 上午8:58:50
	 * @updated 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findAllNodeById(String id,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception;
	/**
	 * 功能说明：根据父节点查询对应的子节点
	 * @author ducc
	 * @created 2014年8月12日 下午4:43:21
	 * @updated 
	 * @param pId
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findChildsBypId(String pId,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception;
	/**
	 * 功能说明：查询当前节点和包含几层的数据
	 * @author ducc
	 * @created 2014年8月2日 上午9:01:38
	 * @updated 
	 * @param id
	 * @param num 查询几层  -1表示不限制层数，  0表示只查询当前节点，1表示一共查询两层， 2表示一共查询三层 ...
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findAllNodeById(String id,Integer num,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception;
	/**
	 * 功能说明：根据id，递归删除节点
	 * @author ducc
	 * @created 2014年8月2日 上午10:44:14
	 * @updated 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer deleteRecursiveById(String id,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception;
	/**
	 * 功能说明：获取所有的节点
	 * @author ducc
	 * @created 2014年8月12日 下午4:41:58
	 * @updated 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findTreeAll(Class<? extends SimpleTreeBaseEntity> clazz)throws Exception;
}
