
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class ShopAgentFinancial implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** shopAgentFinancialId */
	private java.lang.Integer shopAgentFinancialId;
	/** province */
	private java.lang.Integer province;
	/** city */
	private java.lang.Integer city;
	/** district */
	private java.lang.Integer district;
	/** street */
	private java.lang.Integer street;
	/** shopId */
	private java.lang.Integer shopId;
	/** 独家代理id */
	private java.lang.Integer agentId;
	/** 每天日期 */
	private java.util.Date day;
	/** createDate */
	private java.util.Date createDate;
	/** 状态   -1 删除   0 未转帐   1 已转账 */
	private java.lang.Integer status;
	/** 店名 */
	private java.lang.String shopName;
	/** 金额 */
	private double amount;
	
	private double serviceFee;
	
	private double transferFee;
	
	private String dayStr;
	/** 店编号 */
	private java.lang.String code;
	/** 转账时间 */
	private java.util.Date transferDate;
	/** 转账人 */
	private java.lang.String transferUser;
	//columns END
	Page page;

	private List<ShopAgentFinancial> shopAgentFinancials;
	
	private java.lang.String beginTime;
	
	private java.lang.String endTime;
	
	/**
	 * 设置 shopAgentFinancialId
	 * @param shopAgentFinancialId
	 */
	public void setShopAgentFinancialId(java.lang.Integer shopAgentFinancialId) {
		this.shopAgentFinancialId = shopAgentFinancialId;
	}
	/**
	 * shopAgentFinancialId
	 * @return
	 */
	public java.lang.Integer getShopAgentFinancialId() {
		return this.shopAgentFinancialId;
	}
	/**
	 * 设置 province
	 * @param province
	 */
	public void setProvince(java.lang.Integer province) {
		this.province = province;
	}
	/**
	 * province
	 * @return
	 */
	public java.lang.Integer getProvince() {
		return this.province;
	}
	/**
	 * 设置 city
	 * @param city
	 */
	public void setCity(java.lang.Integer city) {
		this.city = city;
	}
	/**
	 * city
	 * @return
	 */
	public java.lang.Integer getCity() {
		return this.city;
	}
	/**
	 * 设置 district
	 * @param district
	 */
	public void setDistrict(java.lang.Integer district) {
		this.district = district;
	}
	/**
	 * district
	 * @return
	 */
	public java.lang.Integer getDistrict() {
		return this.district;
	}
	/**
	 * 设置 street
	 * @param street
	 */
	public void setStreet(java.lang.Integer street) {
		this.street = street;
	}
	/**
	 * street
	 * @return
	 */
	public java.lang.Integer getStreet() {
		return this.street;
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
	 * 设置 独家代理id
	 * @param agentId
	 */
	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}
	/**
	 * 独家代理id
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
		
		if(day!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
			this.setDayStr(time.format(day));
		}
		
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
	 * 设置 店名
	 * @param shopName
	 */
	public void setShopName(java.lang.String shopName) {
		this.shopName = shopName;
	}
	/**
	 * 店名
	 * @return
	 */
	public java.lang.String getShopName() {
		return this.shopName;
	}
	/**
	 * 金额
	 * @return
	 */
	public double getAmount() {
		return this.amount;
	}
	/**
	 * 设置 金额
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
		
		DecimalFormat df = new DecimalFormat ("#.##");
		this.setServiceFee(Double.parseDouble(df.format(amount*6/1000)));
		
		this.setTransferFee(Double.parseDouble(df.format(amount-this.getServiceFee())));
		
	}
	/**
	 * 设置 店编号
	 * @param code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * 店编号
	 * @return
	 */
	public java.lang.String getCode() {
		return this.code;
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

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<ShopAgentFinancial> getShopAgentFinancials() {
		return shopAgentFinancials;
	}
	public void setShopAgentFinancials(List<ShopAgentFinancial> shopAgentFinancials) {
		this.shopAgentFinancials = shopAgentFinancials;
	}
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
	public String getDayStr() {
		return dayStr;
	}
	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public double getTransferFee() {
		return transferFee;
	}
	public void setTransferFee(double transferFee) {
		this.transferFee = transferFee;
	}
	
}

