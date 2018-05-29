
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
public class ShopFinancial implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** shopFinancialId */
	private java.lang.Integer shopFinancialId;
	
	private String shopFinancialIdStr;
	
	private List<String> shopFinancialIdList;
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
	/** day */
	private java.util.Date day;
	
	private java.lang.Integer payType;
	
	private String dayStr;
	/** createDate */
	private java.util.Date createDate;
	/** 状态             -1 删除             0 默认 未转帐             1 已转账              */
	private java.lang.Integer status;
	/** shopName */
	private java.lang.String shopName;
	/** amount */
	private double amount;
	
	private double serviceFee;
	
	private double transferFee;
	/** code */
	private java.lang.String code;
	
	private java.lang.String beginTime;
	
	private java.lang.String endTime;
	/** transferDate */
	private java.util.Date transferDate;
	
	private String transferDateStr;
	/** transferUser */
	private java.lang.String transferUser;
	
	private List<ShopFinancial> shopFinancialList;
	
	private double moneyWd;
	
private java.lang.Integer menuId;
	
	public java.lang.Integer getMenuId() {
	return menuId;
}
public void setMenuId(java.lang.Integer menuId) {
	this.menuId = menuId;
}

	public java.lang.Integer getPayType() {
	return payType;
}
public void setPayType(java.lang.Integer payType) {
	this.payType = payType;
}

	//columns END
	Page page;

	public double getTransferFee() {
		return transferFee;
	}
	public void setTransferFee(double transferFee) {
		this.transferFee = transferFee;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getShopFinancialIdStr() {
		return shopFinancialIdStr;
	}
	public void setShopFinancialIdStr(String shopFinancialIdStr) {
		this.shopFinancialIdStr = shopFinancialIdStr;
	}
	public List<String> getShopFinancialIdList() {
		return shopFinancialIdList;
	}
	public void setShopFinancialIdList(List<String> shopFinancialIdList) {
		this.shopFinancialIdList = shopFinancialIdList;
	}
	public String getDayStr() {
		return dayStr;
	}
	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}
	public String getTransferDateStr() {
		return transferDateStr;
	}
	public void setTransferDateStr(String transferDateStr) {
		this.transferDateStr = transferDateStr;
	}
	public List<ShopFinancial> getShopFinancialList() {
		return shopFinancialList;
	}
	public void setShopFinancialList(List<ShopFinancial> shopFinancialList) {
		this.shopFinancialList = shopFinancialList;
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
	/**
	 * 设置 shopFinancialId
	 * @param shopFinancialId
	 */
	public void setShopFinancialId(java.lang.Integer shopFinancialId) {
		this.shopFinancialId = shopFinancialId;
	}
	/**
	 * shopFinancialId
	 * @return
	 */
	public java.lang.Integer getShopFinancialId() {
		return this.shopFinancialId;
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
	 * 设置 day
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
	 * day
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
	 * 设置 状态             -1 删除             0 默认 未转帐             1 已转账             
	 * @param status
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	/**
	 * 状态             -1 删除             0 默认 未转帐             1 已转账             
	 * @return
	 */
	public java.lang.Integer getStatus() {
		return this.status;
	}
	/**
	 * 设置 shopName
	 * @param shopName
	 */
	public void setShopName(java.lang.String shopName) {
		this.shopName = shopName;
	}
	/**
	 * shopName
	 * @return
	 */
	public java.lang.String getShopName() {
		return this.shopName;
	}
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
		
		DecimalFormat df = new DecimalFormat ("#.##");
		this.setServiceFee(Double.parseDouble(df.format(amount*6/1000)));
		
		this.setTransferFee(Double.parseDouble(df.format(amount-this.getServiceFee())));
		
	}
	/**
	 * 设置 code
	 * @param code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * code
	 * @return
	 */
	public java.lang.String getCode() {
		return this.code;
	}
	/**
	 * 设置 transferDate
	 * @param transferDate
	 */
	public void setTransferDate(java.util.Date transferDate) {
		
		if(transferDate!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
			this.setTransferDateStr(time.format(transferDate));
		}
		
		
		this.transferDate = transferDate;
	}
	/**
	 * transferDate
	 * @return
	 */
	public java.util.Date getTransferDate() {
		return this.transferDate;
	}
	/**
	 * 设置 transferUser
	 * @param transferUser
	 */
	public void setTransferUser(java.lang.String transferUser) {
		this.transferUser = transferUser;
	}
	/**
	 * transferUser
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
	public double getMoneyWd() {
		return moneyWd;
	}
	public void setMoneyWd(double moneyWd) {
		this.moneyWd = moneyWd;
	}
}

