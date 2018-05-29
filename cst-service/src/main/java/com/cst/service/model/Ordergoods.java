
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class Ordergoods implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** ordergoodsId */
	private java.lang.Integer ordergoodsId;
	/** 订单编号 */
	private java.lang.String code;
	/** 收货地址 */
	private java.lang.String address;
	/** 市场部备注 */
	private java.lang.String marketRemark;
	/** 状态      -1拒绝    0默认 草稿     1已提交    2审核中    3已发货     4订单完成 */
	private java.lang.Integer status;
	/** 提交时间 */
	private java.util.Date subDate;
	/** receiveMan */
	private java.lang.String receiveMan;
	/** 手机号码 */
	private java.lang.String phone;
	/** 产品中心备注 */
	private java.lang.String pcenterRemark;
	/** 库管员备注 */
	private java.lang.String storeRemark;
	/** 产品中心审核人员 */
	private java.lang.Integer pcenterMan;
	/** 市场部审核人员 */
	private java.lang.Integer marketMan;
	/** 财务部审核人员 */
	private java.lang.Integer accountantMan;
	/** 专营店提交人员 */
	private java.lang.Integer shopMan;
	/** 库管员审核人员 */
	private java.lang.Integer storeMan;
	/** 产品中心审核时间 */
	private java.util.Date pcenterTime;
	/** 财务部备注 */
	private java.lang.String accountantRemark;
	/** 库管员审核时间 */
	private java.util.Date storeTime;
	/** 财务部的审核时间 */
	private java.util.Date accountantTime;
	/** 市场部审核时间 */
	private java.util.Date marketTime;
	/** -6财务部已拒绝    -4产品中心已拒绝     -2市场部已拒绝     0草稿       1已提交     2市场部审核通过     4产品中心审核通过     6财务部审核通过 */
	private java.lang.Integer myStatus;
	/** 专营店id*/
	private java.lang.Integer shopId;
	/** 邮政编码*/
	private String zipCode;
	/** 电话号码*/
	private String telPhone;
	
	private String beginTime;
	private String endTime;
	
	private java.lang.Integer menuId;
	
	private List<OrdergoodsProduct> ordergoodsProducts=new ArrayList<OrdergoodsProduct>();
	
	private List<Ordergoods> ordergoodList=new ArrayList<Ordergoods>();
	
	private double totalPrice;
	
	private List<Parameter> pList;
	
	private String subDateStr;
	
	private String marketTimeStr;
	
	private String pcenterTimeStr;
	
	private String accountantTimeStr;
	
	private String storeTimeStr;
	/** 专营店名称*/
	private String shopName;
	/** 专营店编号*/
	private String shopCode;
	
	
	//columns END
	Page page;

	/**
	 * 设置 ordergoodsId
	 * @param ordergoodsId
	 */
	public void setOrdergoodsId(java.lang.Integer ordergoodsId) {
		this.ordergoodsId = ordergoodsId;
	}
	/**
	 * ordergoodsId
	 * @return
	 */
	public java.lang.Integer getOrdergoodsId() {
		return this.ordergoodsId;
	}
	public String getSubDateStr() {
		return subDateStr;
	}
	public void setSubDateStr(String subDateStr) {
		this.subDateStr = subDateStr;
	}
	/**
	 * 设置 订单编号
	 * @param code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * 订单编号
	 * @return
	 */
	public java.lang.String getCode() {
		return this.code;
	}
	/**
	 * 设置 收货地址
	 * @param address
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	/**
	 * 收货地址
	 * @return
	 */
	public java.lang.String getAddress() {
		return this.address;
	}
	/**
	 * 设置 市场部备注
	 * @param marketRemark
	 */
	public void setMarketRemark(java.lang.String marketRemark) {
		this.marketRemark = marketRemark;
	}
	/**
	 * 市场部备注
	 * @return
	 */
	public java.lang.String getMarketRemark() {
		return this.marketRemark;
	}
	/**
	 * 设置 状态       0默认 草稿     1已提交    2审核中    3已发货     4订单完成
	 * @param status
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	/**
	 * 状态       0默认 草稿     1已提交    2审核中    3已发货     4订单完成
	 * @return
	 */
	public java.lang.Integer getStatus() {
		return this.status;
	}
	/**
	 * 设置 提交时间
	 * @param subDate
	 */
	public void setSubDate(java.util.Date subDate) {
		if(subDate!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setSubDateStr(time.format(subDate));
		}
		this.subDate = subDate;
	}
	/**
	 * 提交时间
	 * @return
	 */
	public java.util.Date getSubDate() {
		return this.subDate;
	}
	/**
	 * 设置 receiveMan
	 * @param receiveMan
	 */
	public void setReceiveMan(java.lang.String receiveMan) {
		this.receiveMan = receiveMan;
	}
	/**
	 * receiveMan
	 * @return
	 */
	public java.lang.String getReceiveMan() {
		return this.receiveMan;
	}
	/**
	 * 设置 手机号码
	 * @param phone
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	/**
	 * 手机号码
	 * @return
	 */
	public java.lang.String getPhone() {
		return this.phone;
	}
	/**
	 * 设置 产品中心备注
	 * @param pcenterRemark
	 */
	public void setPcenterRemark(java.lang.String pcenterRemark) {
		this.pcenterRemark = pcenterRemark;
	}
	/**
	 * 产品中心备注
	 * @return
	 */
	public java.lang.String getPcenterRemark() {
		return this.pcenterRemark;
	}
	/**
	 * 设置 库管员备注
	 * @param storeRemark
	 */
	public void setStoreRemark(java.lang.String storeRemark) {
		this.storeRemark = storeRemark;
	}
	/**
	 * 库管员备注
	 * @return
	 */
	public java.lang.String getStoreRemark() {
		return this.storeRemark;
	}
	/**
	 * 设置 产品中心审核人员
	 * @param pcenterMan
	 */
	public void setPcenterMan(java.lang.Integer pcenterMan) {
		this.pcenterMan = pcenterMan;
	}
	/**
	 * 产品中心审核人员
	 * @return
	 */
	public java.lang.Integer getPcenterMan() {
		return this.pcenterMan;
	}
	/**
	 * 设置 市场部审核人员
	 * @param marketMan
	 */
	public void setMarketMan(java.lang.Integer marketMan) {
		this.marketMan = marketMan;
	}
	/**
	 * 市场部审核人员
	 * @return
	 */
	public java.lang.Integer getMarketMan() {
		return this.marketMan;
	}
	/**
	 * 设置 财务部审核人员
	 * @param accountantMan
	 */
	public void setAccountantMan(java.lang.Integer accountantMan) {
		this.accountantMan = accountantMan;
	}
	/**
	 * 财务部审核人员
	 * @return
	 */
	public java.lang.Integer getAccountantMan() {
		return this.accountantMan;
	}
	/**
	 * 设置 专营店提交人员
	 * @param shopMan
	 */
	public void setShopMan(java.lang.Integer shopMan) {
		this.shopMan = shopMan;
	}
	/**
	 * 专营店提交人员
	 * @return
	 */
	public java.lang.Integer getShopMan() {
		return this.shopMan;
	}
	/**
	 * 设置 库管员审核人员
	 * @param storeMan
	 */
	public void setStoreMan(java.lang.Integer storeMan) {
		this.storeMan = storeMan;
	}
	/**
	 * 库管员审核人员
	 * @return
	 */
	public java.lang.Integer getStoreMan() {
		return this.storeMan;
	}
	/**
	 * 设置 产品中心审核时间
	 * @param pcenterTime
	 */
	public void setPcenterTime(java.util.Date pcenterTime) {
		if(pcenterTime!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setPcenterTimeStr(time.format(pcenterTime));
		}
		this.pcenterTime = pcenterTime;
	}
	/**
	 * 产品中心审核时间
	 * @return
	 */
	public java.util.Date getPcenterTime() {
		return this.pcenterTime;
	}
	/**
	 * 设置 财务部备注
	 * @param accountantRemark
	 */
	public void setAccountantRemark(java.lang.String accountantRemark) {
		this.accountantRemark = accountantRemark;
	}
	/**
	 * 财务部备注
	 * @return
	 */
	public java.lang.String getAccountantRemark() {
		return this.accountantRemark;
	}
	/**
	 * 设置 库管员审核时间
	 * @param storeTime
	 */
	public void setStoreTime(java.util.Date storeTime) {
		if(storeTime!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setStoreTimeStr(time.format(storeTime));
		}
		this.storeTime = storeTime;
	}
	/**
	 * 库管员审核时间
	 * @return
	 */
	public java.util.Date getStoreTime() {
		return this.storeTime;
	}
	/**
	 * 设置 财务部的审核时间
	 * @param accountantTime
	 */
	public void setAccountantTime(java.util.Date accountantTime) {
		if(accountantTime!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setAccountantTimeStr(time.format(accountantTime));
		}
		this.accountantTime = accountantTime;
	}
	/**
	 * 财务部的审核时间
	 * @return
	 */
	public java.util.Date getAccountantTime() {
		return this.accountantTime;
	}
	/**
	 * 设置 市场部审核时间
	 * @param marketTime
	 */
	public void setMarketTime(java.util.Date marketTime) {
		if(marketTime!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setMarketTimeStr(time.format(marketTime));
		}
		this.marketTime = marketTime;
	}
	/**
	 * 市场部审核时间
	 * @return
	 */
	public java.util.Date getMarketTime() {
		return this.marketTime;
	}
	/**
	 * 设置 -6财务部已拒绝    -4产品中心已拒绝     -2市场部已拒绝      1市场部审核中     2市场部审核通过      3产品中心审核中    4产品中心审核通过    5财务部审核中     6财务部审核通过
	 * @param myStatus
	 */
	public void setMyStatus(java.lang.Integer myStatus) {
		this.myStatus = myStatus;
	}
	/**
	 * -6财务部已拒绝    -4产品中心已拒绝     -2市场部已拒绝      1市场部审核中     2市场部审核通过      3产品中心审核中    4产品中心审核通过    5财务部审核中     6财务部审核通过
	 * @return
	 */
	public java.lang.Integer getMyStatus() {
		return this.myStatus;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<Parameter> getpList() {
		return pList;
	}
	public void setpList(List<Parameter> pList) {
		this.pList = pList;
	}
	public List<OrdergoodsProduct> getOrdergoodsProducts() {
		return ordergoodsProducts;
	}
	public void setOrdergoodsProducts(List<OrdergoodsProduct> ordergoodsProducts) {
		this.ordergoodsProducts = ordergoodsProducts;
	}
	public List<Ordergoods> getOrdergoodList() {
		return ordergoodList;
	}
	public void setOrdergoodList(List<Ordergoods> ordergoodList) {
		this.ordergoodList = ordergoodList;
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
	public java.lang.Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(java.lang.Integer menuId) {
		this.menuId = menuId;
	}
	public java.lang.Integer getShopId() {
		return shopId;
	}
	public void setShopId(java.lang.Integer shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getMarketTimeStr() {
		return marketTimeStr;
	}
	public void setMarketTimeStr(String marketTimeStr) {
		this.marketTimeStr = marketTimeStr;
	}
	public String getPcenterTimeStr() {
		return pcenterTimeStr;
	}
	public void setPcenterTimeStr(String pcenterTimeStr) {
		this.pcenterTimeStr = pcenterTimeStr;
	}
	public String getAccountantTimeStr() {
		return accountantTimeStr;
	}
	public void setAccountantTimeStr(String accountantTimeStr) {
		this.accountantTimeStr = accountantTimeStr;
	}
	public String getStoreTimeStr() {
		return storeTimeStr;
	}
	public void setStoreTimeStr(String storeTimeStr) {
		this.storeTimeStr = storeTimeStr;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}

