package com.yxkong.common.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yxkong.common.model.SimpleTreeBaseEntity;
import com.yxkong.common.service.IMybatisSimpleTreeService;
import com.yxkong.common.utils.ReflectGenricUtil;
import com.yxkong.common.utils.StringUtil;
public  abstract class MybatisSimpleTreeServiceImpl<T extends SimpleTreeBaseEntity,ID extends Serializable> extends MybatisBaseServiceImpl<T, ID> implements
IMybatisSimpleTreeService<T,ID> {
	/**定义编码前缀*/
	private String prefix="0019";
	@Override
	public synchronized void saveTreeNode(T item) throws Exception{
		/**
		 * 计算层级的code
		 *   0001 0000 0000 0000   集团 
		 *   0001 0001 0000 0000   分子公司
		 *   0001 0001 0001 0000   部门
		 *   0001 0001 0001 0001   岗位
		 */
		
		if(item!=null&&item.getpId()!=null){
			String tableName=ReflectGenricUtil.getTableName(item.getClass());
			Integer maxRight=0;
			Integer p_rightValue=0;
			Integer p_treelevel=1;
			/**
			 * 判断节点的层次
			 *    第一层节点：
			 *       获取第一层节点右值的最大值maxRight
			 *       要插入节点的左右值为maxRight+1
			 *                层级为1
			 *    非第一层节点：
			 *       获取要添加节点父节点的左右值和层级P_leftValue  p_rightValue
			 *         根据父节点的左右值去查询子节点右值的最大值maxRight
			 *         如果没有找到，以当前父节点的右值+1作为新节点的左右值
			 *         如果找到新节点的值的左右值为maxRight+1
			 *        新节点的层级为父节点的层级+1
			 */
			//第一层
			if("0".equals(item.getpId())){
				StringBuffer maxRightSql=new StringBuffer();
				maxRightSql.append("select max(rightValue) MAXRIGHT,max(treeDepend) MAXNUM from ").append(tableName).append(" where treeLevel=? ");
				Map<String, Object> mapMax = this.getBaseDao().findMapBySql(maxRightSql.toString(), 1);
				if(mapMax!=null&&mapMax.size()>0){
					maxRight=(Integer) mapMax.get("MAXRIGHT");
					if(maxRight==null){
						maxRight=0;
					}
					Integer maxValue=(Integer) mapMax.get("MAXNUM");
					item.setParent(item.getName());
					if(maxValue==null){
						item.setCode(prefix+"-"+getSufixCode(1));
						item.setTreeDepend(1);
					}else{
						item.setCode(prefix+"-"+getSufixCode(maxValue+1));
						item.setTreeDepend(maxValue+1);
					}
				}
				maxRight++;
				item.setTreeLevel(1);
				item.setLeftValue(maxRight);
				item.setRightValue(maxRight);
			}else{
				StringBuffer parentSql=new StringBuffer();
				parentSql.append("select leftValue P_LEFTVALUE, rightValue P_RIGHTVALUE,treelevel P_TREELEVEL,code P_CODE,parent P_PARENT from ").append(tableName)
				         .append(" f ").append(" where id=?");
				Map<String, Object> parentMap = this.getBaseDao().findMapBySql(parentSql.toString(), item.getpId());
				
				if(parentMap!=null&&parentMap.size()>0){
					p_rightValue=(Integer) parentMap.get("P_RIGHTVALUE");
					Integer P_leftValue=(Integer) parentMap.get("P_LEFTVALUE");
					p_treelevel=(Integer)parentMap.get("P_TREELEVEL");
					String p_code=(String) parentMap.get("P_CODE");
					String p_parent=(String) parentMap.get("P_PARENT");
					item.setParent(p_parent+"->"+item.getName());
					StringBuffer rightMaxSql=new StringBuffer();
					rightMaxSql.append(" select  max(rightValue) MAXRIGHT,max(treeDepend) MAXNUM from ").append(tableName)
					           .append("  where treelevel=").append(p_treelevel+1)
					           .append("  and leftValue>=").append(P_leftValue)
					           .append("  and rightValue<=? ");
					Map<String, Object> mapMax = this.getBaseDao().findMapBySql(rightMaxSql.toString(), p_rightValue);
					
					if(mapMax!=null&&mapMax.size()>0){
						maxRight=(Integer) mapMax.get("MAXRIGHT");
						
						Integer maxValue=(Integer) mapMax.get("MAXNUM");
						if(maxValue!=null){
							item.setCode(p_code+"-"+getSufixCode(maxValue+1));
							item.setTreeDepend(maxValue+1);
						}else{
							item.setCode(p_code+"-"+getSufixCode(1));
							item.setTreeDepend(1);
						}
					}
					if(maxRight==null){
						 maxRight=P_leftValue+1;
					}else{
						maxRight++;
					}
					
					item.setLeftValue(maxRight);
					item.setRightValue(maxRight);
					item.setTreeLevel(p_treelevel+1);
				}
			}
			if(maxRight>p_rightValue){
				//更新父级节点的右值
				StringBuffer p_rightUpdateSql=new StringBuffer();
				p_rightUpdateSql.append("update ").append(tableName)
				              .append(" set rightValue=rightValue+1 ")
				              .append(" where rightValue>=? and treeLevel<=?");
				StringBuffer rightUpdateSql=new StringBuffer();
				rightUpdateSql.append("update ").append(tableName)
				              .append(" set rightValue=rightValue+1 ")
				              .append(" where rightValue>=?  and treeLevel>?");
				StringBuffer leftUpdateSql=new StringBuffer();
				leftUpdateSql.append("update ").append(tableName)
				             .append(" set leftValue=leftValue+1 ")
				             .append(" where leftValue>=?");
				this.getBaseDao().updateBySql(p_rightUpdateSql.toString(), maxRight-1,p_treelevel);
				this.getBaseDao().updateBySql(rightUpdateSql.toString(), maxRight,p_treelevel);
				this.getBaseDao().updateBySql(leftUpdateSql.toString(), maxRight);
			}
			
			this.getBaseDao().save(item);
		}else{
			throw new RuntimeException("保存对象和该对象的pId不能为空");
		}
	}
	private static String getSufixCode(Integer num){
		return StringUtil.getStringNum(num, 4);
	}
	public List<Map<String,Object>> findSiblingsById(String id,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception{
		String tableName=ReflectGenricUtil.getTableName(clazz);
		StringBuffer searchSql=new StringBuffer();
		searchSql.append("select r.id ID,r.name NAME,r.code CODE from ").append(tableName).append(" r ")
		         .append(" where  EXISTS ( SELECT 1 FROM ").append(tableName)
		         .append(" s where s.id = ? and r.treeLevel = s.treeLevel  and r.pId=s.pId )")
		         ;
		return	this.getBaseDao().findListMapBySql(searchSql.toString(), id);
	}
	public List<Map<String,Object>> findParentSiblingsById(String id,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception{
		String tableName=ReflectGenricUtil.getTableName(clazz);
		StringBuffer searchSql=new StringBuffer();
		searchSql.append("select r.id ID,r.name NAME,r.code CODE from ").append(tableName).append(" r ")
		         .append(" where  EXISTS ( SELECT 1 FROM ").append(tableName)
		         .append(" s where s.id = ? and r.treeLevel = s.treeLevel-1)")
		         ;
		return	this.getBaseDao().findListMapBySql(searchSql.toString(), id);
	}
	public List<Map<String,Object>> findChildsBypId(String pId,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception{
		String tableName=ReflectGenricUtil.getTableName(clazz);
		StringBuffer searchSql=new StringBuffer();
		searchSql.append("select r.* from ").append(tableName).append(" r ").append(" where r.pid=?");
		return	this.getBaseDao().findListMapBySql(searchSql.toString(), pId);
	}
	public List<Map<String,Object>> findAllNodeById(String id,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception{
		return findAllNodeById(id,-1,clazz);
	}
	public List<Map<String,Object>> findAllNodeById(String id,Integer num,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception{
		String tableName=ReflectGenricUtil.getTableName(clazz);
		StringBuffer searchSql=new StringBuffer();
		if(num==0){
			searchSql.append("select r.* from ").append(tableName).append(" r ").append(" where r.id=?");
		}else {
			searchSql.append("select r.* from ").append(tableName).append(" r ")
	        .append(" where exists (select 1 from ").append(tableName)
	        .append(" s where s.id=? and r.leftValue>=s.leftValue and r.rightValue<=s.rightValue ");
			if(num>0){
				searchSql.append(" and r.treelevel<=s.treelevel+").append(num);
			}
	        searchSql.append(")");
		}
		return	this.getBaseDao().findListMapBySql(searchSql.toString(), id);
	}
	public Integer deleteRecursiveById(String id,Class<? extends SimpleTreeBaseEntity> clazz)throws Exception{
		String tableName=ReflectGenricUtil.getTableName(clazz);
		
		//由于在mysql的delete语句中不支持别名
		StringBuffer deleteSql=new StringBuffer();
//		deleteSql.append("delete from ").append(tableName).append(" r ")
//        .append(" where r.id exists (select s.id from ").append(tableName)
//        .append(" s where s.id=? and r.leftValue>=s.leftValue and r.leftValue<=s.rightValue )");
		/**   此逻辑在oracle中可以直接，在mysql也不可以执行，
		 * DELETE FROM F_BaseResource WHERE EXISTS 
		 * ( SELECT 1 FROM 
		 *    (SELECT f.leftValue, f.rightValue FROM f_baseresource f
		 *       WHERE f.id = '074fe247-1f8f-45a1-b9fe-24e1cae8a941'
		 *    ) as t
		 *   WHERE
		 *    leftValue >= t.leftValue
		 *    AND rightValue <= t.rightValue
		 * )
		 * */
		StringBuffer parentSql=new StringBuffer();
		parentSql.append("select leftValue P_LEFTVALUE, rightValue P_RIGHTVALUE,treelevel P_TREELEVEL from ").append(tableName)
		         .append(" f ").append(" where id=?");
		Map<String, Object> parentMap = this.getBaseDao().findMapBySql(parentSql.toString(), id);
		Integer p_rightValue=null;
		if(parentMap!=null&&parentMap.size()>0){
			p_rightValue=(Integer) parentMap.get("P_RIGHTVALUE");
			Integer P_leftValue=(Integer) parentMap.get("P_LEFTVALUE");
			deleteSql.append("delete from ").append(tableName)
			         .append(" where leftValue>=").append(P_leftValue)
			         .append(" and leftValue<=?");
		}
		if(deleteSql.length()>0){
			return this.getBaseDao().deleteBySql(deleteSql.toString(), p_rightValue);
		}
		return 0;
	}
	public List<Map<String,Object>> findTreeAll(Class<? extends SimpleTreeBaseEntity> clazz)throws Exception{
		String tableName=ReflectGenricUtil.getTableName(clazz);
		StringBuffer searchSql=new StringBuffer();
		searchSql.append("select r.id as id ,r.name as name,r.pid as pid,r.treelevel treelevel from ").append(tableName).append(" r ")
		         .append(" where 1=?");
		List<Map<String,Object>> findAll= this.getBaseDao().findListMapBySql(searchSql.toString(), 1);
		List<Map<String,Object>>  list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		
		if(findAll!=null&&findAll.size()>0){
			for(Map<String,Object> m:findAll){
				map=new HashMap<String,Object>();
				for(String key:m.keySet()){
					map.put(key.toLowerCase(), m.get(key));
				}
				list.add(map);
			}
		}
		return list;
	}
	
}
