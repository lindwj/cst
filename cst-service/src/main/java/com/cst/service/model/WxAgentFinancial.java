
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
public class WxAgentFinancial implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** wxAgentFinancialId */
	private java.lang.Integer wxAgentFinancialId;
	/** 微信代理人id */
	private java.lang.Integer agentId;
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
	
	private List<WxAgentFinancial> wxAgentFinancials;
	
	private java.lang.String beginTime;
	
	private java.lang.String endTime;

	
	public java.lang.String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(java.lang.String beginTime) {
		this.beginTime = beginTime;
	}
	public java.lang.String getEndTime() {
		return endTime;
	}
	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 设置 wxAgentFinancialId
	 * @param wxAgentFinancialId
	 */
	public void setWxAgentFinancialId(java.lang.Integer wxAgentFinancialId) {
		this.wxAgentFinancialId = wxAgentFinancialId;
	}
	/**
	 * wxAgentFinancialId
	 * @return
	 */
	public java.lang.Integer getWxAgentFinancialId() {
		return this.wxAgentFinancialId;
	}
	/**
	 * 设置 微信代理人id
	 * @param agentId
	 */
	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}
	/**
	 * 微信代理人id
	 * @return
	 */
	public java.lang.Integer getAgentId() {
		return this.agentId;
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
	public List<WxAgentFinancial> getWxAgentFinancials() {
		return wxAgentFinancials;
	}
	public void setWxAgentFinancials(List<WxAgentFinancial> wxAgentFinancials) {
		this.wxAgentFinancials = wxAgentFinancials;
	}
	
}

