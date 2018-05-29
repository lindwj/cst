
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
public class LogisticsInfo implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** logisticsInfoId */
	private java.lang.Integer logisticsInfoId;
	/** kdnId */
	private java.lang.Integer kdnId;
	/** 物流单号 */
	private java.lang.String code;
	/** json数据流 */
	private java.lang.String jsonData;
	/** 创建人 */
	private java.lang.Integer createMan;
	/** 创建时间 */
	private java.util.Date createDate;
	/** 推送时间 */
	private java.util.Date pushDate;
	/** 状态 */
	private java.lang.Integer state;
	
	private String eBusinessID;
	
	private String pushTime;
	
	private String count;
	
	private String data;
	
	private String orderCode;
	
	//columns END
	Page page;

	/**
	 * 设置 logisticsInfoId
	 * @param logisticsInfoId
	 */
	public void setLogisticsInfoId(java.lang.Integer logisticsInfoId) {
		this.logisticsInfoId = logisticsInfoId;
	}
	/**
	 * logisticsInfoId
	 * @return
	 */
	public java.lang.Integer getLogisticsInfoId() {
		return this.logisticsInfoId;
	}
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
	 * 设置 物流单号
	 * @param code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * 物流单号
	 * @return
	 */
	public java.lang.String getCode() {
		return this.code;
	}
	/**
	 * 设置 json数据流
	 * @param jsonData
	 */
	public void setJsonData(java.lang.String jsonData) {
		this.jsonData = jsonData;
	}
	/**
	 * json数据流
	 * @return
	 */
	public java.lang.String getJsonData() {
		return this.jsonData;
	}
	/**
	 * 设置 创建人
	 * @param createMan
	 */
	public void setCreateMan(java.lang.Integer createMan) {
		this.createMan = createMan;
	}
	/**
	 * 创建人
	 * @return
	 */
	public java.lang.Integer getCreateMan() {
		return this.createMan;
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
	/**
	 * 设置 推送时间
	 * @param pushDate
	 */
	public void setPushDate(java.util.Date pushDate) {
		this.pushDate = pushDate;
	}
	/**
	 * 推送时间
	 * @return
	 */
	public java.util.Date getPushDate() {
		return this.pushDate;
	}
	/**
	 * 设置 状态
	 * @param state
	 */
	public void setState(java.lang.Integer state) {
		this.state = state;
	}
	/**
	 * 状态
	 * @return
	 */
	public java.lang.Integer getState() {
		return this.state;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String geteBusinessID() {
		return eBusinessID;
	}
	public void seteBusinessID(String eBusinessID) {
		this.eBusinessID = eBusinessID;
	}
	public String getPushTime() {
		return pushTime;
	}
	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}

