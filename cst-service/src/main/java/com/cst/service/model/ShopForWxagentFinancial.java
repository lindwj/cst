
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.util.List;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class ShopForWxagentFinancial implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** shopForWxagentFinancialId */
	private java.lang.Integer shopForWxagentFinancialId;
	/** shopId */
	private java.lang.Integer shopId;
	/** 每天日期 */
	private java.util.Date day;
	/** createDate */
	private java.util.Date createDate;
	/** 状态   -1 删除   0 未转帐   1 已转账 */
	private java.lang.Integer status;
	/** 金额 */
	private Long amount;
	/** 转账时间 */
	private java.util.Date transferDate;
	/** 转账人 */
	private java.lang.String transferUser;
	/** 支付类型 */
	private java.lang.Integer payType;
	//columns END
	Page page;

	List<ShopForWxagentFinancial> shopForWxagentFinancialList;
	
	private String beginTime;
	
	private String endTime;
	
	private double moneyWd;
	/**
	 * 设置 shopForWxagentFinancialId
	 * @param shopForWxagentFinancialId
	 */
	public void setShopForWxagentFinancialId(java.lang.Integer shopForWxagentFinancialId) {
		this.shopForWxagentFinancialId = shopForWxagentFinancialId;
	}
	/**
	 * shopForWxagentFinancialId
	 * @return
	 */
	public java.lang.Integer getShopForWxagentFinancialId() {
		return this.shopForWxagentFinancialId;
	}
	/**
	 * 设置 shopId
	 * @param shopId
	 */
	public void setShopId(java.lang.Integer shopId) {
		this.shopId = shopId;
	}
	/**
	 * shopId
	 * @return
	 */
	public java.lang.Integer getShopId() {
		return this.shopId;
	}
	/**
	 * 设置 每天日期
	 * @param day
	 */
	public void setDay(java.util.Date day) {
		this.day = day;
	}
	/**
	 * 每天日期
	 * @return
	 */
	public java.util.Date getDay() {
		return this.day;
	}
	/**
	 * 设置 createDate
	 * @param createDate
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * createDate
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * 设置 状态   -1 删除   0 未转帐   1 已转账
	 * @param status
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	/**
	 * 状态   -1 删除   0 未转帐   1 已转账
	 * @return
	 */
	public java.lang.Integer getStatus() {
		return this.status;
	}
	/**
	 * 设置 金额
	 * @param amount
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	/**
	 * 金额
	 * @return
	 */
	public Long getAmount() {
		return this.amount;
	}
	/**
	 * 设置 转账时间
	 * @param transferDate
	 */
	public void setTransferDate(java.util.Date transferDate) {
		this.transferDate = transferDate;
	}
	/**
	 * 转账时间
	 * @return
	 */
	public java.util.Date getTransferDate() {
		return this.transferDate;
	}
	/**
	 * 设置 转账人
	 * @param transferUser
	 */
	public void setTransferUser(java.lang.String transferUser) {
		this.transferUser = transferUser;
	}
	/**
	 * 转账人
	 * @return
	 */
	public java.lang.String getTransferUser() {
		return this.transferUser;
	}
	/**
	 * 设置 支付类型
	 * @param payType
	 */
	public void setPayType(java.lang.Integer payType) {
		this.payType = payType;
	}
	/**
	 * 支付类型
	 * @return
	 */
	public java.lang.Integer getPayType() {
		return this.payType;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<ShopForWxagentFinancial> getShopForWxagentFinancialList() {
		return shopForWxagentFinancialList;
	}
	public void setShopForWxagentFinancialList(List<ShopForWxagentFinancial> shopForWxagentFinancialList) {
		this.shopForWxagentFinancialList = shopForWxagentFinancialList;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getMoneyWd() {
		return moneyWd;
	}
	public void setMoneyWd(double moneyWd) {
		this.moneyWd = moneyWd;
	}
}

