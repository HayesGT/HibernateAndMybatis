package com.yxkong.common.web.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明：Ajax 处理结果
 * @author ducc
 * @created 2014年7月8日 下午3:45:14
 * @updated
 */
public class Result implements Serializable {
	private static final long serialVersionUID = 474712281925184851L;
	private Status status;
	private Object message;
    private List<Object>  list;
	public Result() {
		super();
	}

	/**
	 * @description 
	 * @param status 状态
	 * @param message 消息
	 */
	public Result(Status status, Object message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * 功能说明：返回结果类型
	 * @author ducc
	 * @created 2014年7月8日 下午3:48:58
	 * @updated
	 */
	public enum Status {
		SUCC, ERROR,NOLOGIN,OTHER
	}

	/**
	 * 功能说明：添加成功信息
	 * @author ducc
	 * @created 2014年7月8日 下午3:48:07
	 * @updated 
	 * @param message 提示信息
	 */
	public void addSucc(Object message) {
		this.message = message;
		this.status = Status.SUCC;
	}

	/**
	 * 添加错误消息
	 * @param message
	 */
	public void addError(Object message) {
		this.message = message;
		this.status = Status.ERROR;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	
}
