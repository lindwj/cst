
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class Kdn implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** kdnId */
	private java.lang.Integer kdnId;
	/** 快递公司 */
	private java.lang.String business;
	/** 编码 */
	private java.lang.String code;
	//columns END
	Page page;

	/**
	 * 设置 kdnId
	 * @param kdnId
	 */
	public void setKdnId(java.lang.Integer kdnId) {
		this.kdnId = kdnId;
	}
	/**
	 * kdnId
	 * @return
	 */
	public java.lang.Integer getKdnId() {
		return this.kdnId;
	}
	/**
	 * 设置 快递公司
	 * @param business
	 */
	public void setBusiness(java.lang.String business) {
		this.business = business;
	}
	/**
	 * 快递公司
	 * @return
	 */
	public java.lang.String getBusiness() {
		return this.business;
	}
	/**
	 * 设置 编码
	 * @param code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * 编码
	 * @return
	 */
	public java.lang.String getCode() {
		return this.code;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}

