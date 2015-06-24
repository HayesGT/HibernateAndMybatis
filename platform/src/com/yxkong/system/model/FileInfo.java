package com.yxkong.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yxkong.common.model.BaseEntity;

/**
 * 附件上传信息
 * @author ducc
 */
@Entity
@Table(name = "F_FILEINFO")
public class FileInfo extends BaseEntity {
	private static final long serialVersionUID = -3812969279798615887L;

	/**单据id*/
	@Column(name="dataId")
	private String dataId;
	
	//客户端上传文件名称
	@Column
	private String fileName;
	
	//系统名称格式 单据名称-System。c
	@Column
	private String name;
	
	// 存储的地址按照单据id产生目录 
	@Column
	private String url;
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
	
}
