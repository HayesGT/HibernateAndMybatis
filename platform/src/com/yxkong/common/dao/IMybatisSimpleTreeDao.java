package com.yxkong.common.dao;

import java.io.Serializable;

import com.yxkong.common.model.SimpleTreeBaseEntity;
/**
 * 功能说明：基于Mybatis的基础DAO接口
 * @author ducc
 * @created 2014年6月13日 上午10:26:31
 * @updated 
 * @param <T>  业务实体类型
 * @param <ID> ID类型，如 String，Integer,Long 等实现Serializable的类型
 */
public  interface IMybatisSimpleTreeDao<T extends SimpleTreeBaseEntity,ID extends Serializable > extends IMybatisBaseDao<T, ID> {

}
