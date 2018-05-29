
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
public class TellUs implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** tellUsId */
	private java.lang.Integer tellUsId;
	/** 姓名 */
	private java.lang.String name;
	/** 电话 */
	private java.lang.String telephone;
	/**联系时间*/
	private String visit;
	/** 常住地 */
	private java.lang.String address;
	/** 备注 */
	private java.lang.String remark;
	/** 创建时间 */
	private java.util.Date createDate;
	//columns END
	Page page;

	/**
	 * 设置 tellUsId
	 * @param tellUsId
	 */
	public void setTellUsId(java.lang.Integer tellUsId) {
		this.tellUsId = tellUsId;
	}
	/**
	 * tellUsId
	 * @return
	 */
	public java.lang.Integer getTellUsId() {
		return this.tellUsId;
	}
	/**
	 * 设置 姓名
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * 姓名
	 * @return
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * 设置 电话
	 * @param telephone
	 */
	public void setTelephone(java.lang.String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 电话
	 * @return
	 */
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	/**
	 * 设置 随时 1      ,       8:30-17:00   2，     17:00-20:00      3
	 * @param visit
	 */
	/**
	 * 设置 常住地
	 * @param address
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	/**
	 * 常住地
	 * @return
	 */
	public java.lang.String getAddress() {
		return this.address;
	}
	/**
	 * 设置 备注
	 * @param remark
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	/**
	 * 备注
	 * @return
	 */
	public java.lang.String getRemark() {
		return this.remark;
	}
	/**
	 * 设置 创建时间
	 * @param createDate
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 创建时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getVisit() {
		return visit;
	}
	public void setVisit(String visit) {
		this.visit = visit;
	}
}

