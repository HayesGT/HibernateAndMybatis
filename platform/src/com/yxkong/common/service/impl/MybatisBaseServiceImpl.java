package com.yxkong.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.dao.SQLAdapter;
import com.yxkong.common.model.IdEntity;
import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.utils.ReflectGenricUtil;
public  abstract class MybatisBaseServiceImpl<T extends IdEntity,ID extends Serializable> implements
		IMybatisBaseService<T,ID> {
	protected abstract IMybatisBaseDao<T,ID> getBaseDao();
	@Override
	public Integer save(T item) {
		return getBaseDao().save(item);
	}
	public Integer batchSave(List<T> items){
		if(items!=null&&items.size()>0){
			for(T t:items){
				this.save(t);
			}
			return items.size();
		}else{
			return 0;
		}
	}
	@Override
	public Integer update(T item) {
		return getBaseDao().update(item);
	}
	public Integer updateStatus(String id,Integer status,Class<? extends IdEntity> clazz)throws Exception{
		if(status==null){
			status=1;
		}
		String tableName=ReflectGenricUtil.getTableName(clazz);
		StringBuffer updateSql=new StringBuffer();
		updateSql.append("update ").append(tableName)
		         .append(" set status=").append(status)
		         .append(" where id=?");
		return this.getBaseDao().updateBySql(updateSql.toString(),id);
	}
	public Integer updateDelStatus(String id,Integer status,Class<? extends IdEntity> clazz) throws Exception{
		if(status==null){
			status=0;
		}
		String tableName=ReflectGenricUtil.getTableName(clazz);
		StringBuffer updateSql=new StringBuffer();
		updateSql.append("update ").append(tableName)
		         .append(" set delStatue=").append(status)
		         .append(" where id=?");
		return this.getBaseDao().updateBySql(updateSql.toString(),id);
	}
	@Override
	public T findById(ID id) {
		return getBaseDao().findById(id);
	}

	@Override
	public Integer deleteById(ID id) {
		return getBaseDao().deleteById(id);
	}

	@Override
	public Integer deleteByIds(ID[] ids) {
		return getBaseDao().deleteByIds(ids);
	}

	@Override
	public Integer findCountBy(T item) {
		return getBaseDao().findCountBy(item);
	}

	@Override
	public List<T> findListBy(T item, String sortColumn, String des) {
		return getBaseDao().findListBy(item, sortColumn, des);
	}
	@Override
    public List<T> findListByMap(String keyId,Map<String,Object> item){
    	return getBaseDao().findListByMap(keyId, item);
    }
	@Override
	public List<T> findListBy(T item) {
		return getBaseDao().findListBy(item);
	}
	@Override
	public List<T> findListBySqlAdapter(String keyId,SQLAdapter sqlAdapter){
		return getBaseDao().selectList(keyId, sqlAdapter);
	}
	@Override
	public List<Map<String,Object>> findListMapBySqlAdapter(String keyId,SQLAdapter sqlAdapter){
		return  getBaseDao().selectListMap(keyId, sqlAdapter);
	}
	@Override
	public List<T> findListByMap(Map<String,Object> map){
		return getBaseDao().findListByMap(map);
	}
	@Override
	public List<T> findList(){
		return getBaseDao().findList();
	}
	@Override
	public Integer save(String keyId, Object parameter) {
		return getBaseDao().save(keyId, parameter);
	}
	@Override
	public Integer update(String keyId, Object parameter) {
		return getBaseDao().update(keyId, parameter);
	}
	@Override
	public T findBy(String keyId, Object parameter) {
		return getBaseDao().findBy(keyId, parameter);
	}
	@Override
	public int deleteBy(String keyId, Object parameter) {
		return getBaseDao().deleteBy(keyId, parameter);
	}
	@Override
	public List<T> selectList(String keyId, Object parameter) {
		return getBaseDao().selectList(keyId, parameter);
	}
	@Override
	public List<Map<String, Object>> selectListMap(String keyId,
			Object parameter) {
		return getBaseDao().selectListMap(keyId, parameter);
	}
	
}
