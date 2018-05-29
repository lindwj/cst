
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class PreOrders implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** preOrdersId */
	private java.lang.Integer preOrdersId;
	/** code */
	private java.lang.String code;
	/** productUuid */
	private java.lang.String productUuid;
	
	private java.lang.String errcode;
	/** shopId */
	private java.lang.Integer shopId;
	/** applyName */
	private java.lang.String applyName;
	
	private java.lang.String managerName;
	/** applyPhone */
	private java.lang.String applyPhone;
	/** userPhone */
	private java.lang.String userPhone;
	private java.lang.String dowhat;
	
	private java.lang.String beginTime;
	private java.lang.String endTime;
	/** userId */
	private java.lang.Integer userId;
	/** createDate */
	private java.util.Date createDate;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	
	private java.lang.Integer contractNoAmt;
	/** totalFee */
	private Long totalFee;
	/** receiveName */
	private java.lang.String receiveName;
	/** receiveAccount */
	private java.lang.String receiveAccount;
	/** receiveAddress */
	private java.lang.String receiveAddress;
	/** receivePeriod */
	private java.lang.Integer receivePeriod;
	/** receiveMobile */
	private java.lang.String receiveMobile;
	/** updateTime */
	private java.util.Date updateTime;
	
	private java.lang.String updateTimeStr;
	/** shopName */
	private java.lang.String shopName;
	/** pic */
	private java.lang.String pic;
	/** productName */
	private java.lang.String productName;
	/** saleManager */
	private java.lang.Integer saleManager;
	/** contractNo */
	private java.lang.Integer contractNo;
	/** updateManagerId */
	private java.lang.Integer updateManagerId;
	
	List<Product> productList;
	
	List<Manager> managerList;
	
	private List<PreOrders> preOrdersList;
	
private java.lang.Integer menuId;

private java.lang.Integer province;
/** city */
private java.lang.Integer city;
/** district */
private java.lang.Integer district;
	
	public java.lang.Integer getMenuId() {
	return menuId;
}
public void setMenuId(java.lang.Integer menuId) {
	this.menuId = menuId;
}
	
	//columns END
	Page page;

	
	
	public java.lang.Integer getProvince() {
		return province;
	}
	public void setProvince(java.lang.Integer province) {
		this.province = province;
	}
	public java.lang.Integer getCity() {
		return city;
	}
	public void setCity(java.lang.Integer city) {
		this.city = city;
	}
	public java.lang.Integer getDistrict() {
		return district;
	}
	public void setDistrict(java.lang.Integer district) {
		this.district = district;
	}
	public List<PreOrders> getPreOrdersList() {
		return preOrdersList;
	}
	public void setPreOrdersList(List<PreOrders> preOrdersList) {
		this.preOrdersList = preOrdersList;
	}
	public java.lang.Integer getContractNoAmt() {
		return contractNoAmt;
	}
	public void setContractNoAmt(java.lang.Integer contractNoAmt) {
		this.contractNoAmt = contractNoAmt;
	}
	public List<Manager> getManagerList() {
		return managerList;
	}
	public void setManagerList(List<Manager> managerList) {
		this.managerList = managerList;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public java.lang.String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(java.lang.String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
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
	public java.lang.String getManagerName() {
		return managerName;
	}
	public void setManagerName(java.lang.String managerName) {
		this.managerName = managerName;
	}
	public java.lang.String getDowhat() {
		return dowhat;
	}
	public void setDowhat(java.lang.String dowhat) {
		this.dowhat = dowhat;
	}
	public java.lang.String getErrcode() {
		return errcode;
	}
	public void setErrcode(java.lang.String errcode) {
		this.errcode = errcode;
	}
	/**
	 * 设置 preOrdersId
	 * @param preOrdersId
	 */
	public void setPreOrdersId(java.lang.Integer preOrdersId) {
		this.preOrdersId = preOrdersId;
	}
	/**
	 * preOrdersId
	 * @return
	 */
	public java.lang.Integer getPreOrdersId() {
		return this.preOrdersId;
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
	 * 设置 productUuid
	 * @param productUuid
	 */
	public void setProductUuid(java.lang.String productUuid) {
		this.productUuid = productUuid;
	}
	/**
	 * productUuid
	 * @return
	 */
	public java.lang.String getProductUuid() {
		return this.productUuid;
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
	 * 设置 applyName
	 * @param applyName
	 */
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}
	/**
	 * applyName
	 * @return
	 */
	public java.lang.String getApplyName() {
		return this.applyName;
	}
	
	public java.lang.String getApplyPhone() {
		return applyPhone;
	}
	public void setApplyPhone(java.lang.String applyPhone) {
		this.applyPhone = applyPhone;
	}
	/**
	 * 设置 applyPhone
	 * @param applyPhone
	 */
	
	/**
	 * 设置 userId
	 * @param userId
	 */
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	public java.lang.String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(java.lang.String userPhone) {
		this.userPhone = userPhone;
	}
	/**
	 * userId
	 * @return
	 */
	public java.lang.Integer getUserId() {
		return this.userId;
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
	 * 设置 状态             -1 删除             0 默认 草稿             1 上架             2 下架
	 * @param status
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	/**
	 * 状态             -1 删除             0 默认 草稿             1 上架             2 下架
	 * @return
	 */
	public java.lang.Integer getStatus() {
		return this.status;
	}
	/**
	 * 设置 totalFee
	 * @param totalFee
	 */
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * totalFee
	 * @return
	 */
	public Long getTotalFee() {
		return this.totalFee;
	}
	/**
	 * 设置 receiveName
	 * @param receiveName
	 */
	public void setReceiveName(java.lang.String receiveName) {
		this.receiveName = receiveName;
	}
	/**
	 * receiveName
	 * @return
	 */
	public java.lang.String getReceiveName() {
		return this.receiveName;
	}
	/**
	 * 设置 receiveAccount
	 * @param receiveAccount
	 */
	public void setReceiveAccount(java.lang.String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}
	/**
	 * receiveAccount
	 * @return
	 */
	public java.lang.String getReceiveAccount() {
		return this.receiveAccount;
	}
	/**
	 * 设置 receiveAddress
	 * @param receiveAddress
	 */
	public void setReceiveAddress(java.lang.String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	/**
	 * receiveAddress
	 * @return
	 */
	public java.lang.String getReceiveAddress() {
		return this.receiveAddress;
	}
	/**
	 * 设置 receivePeriod
	 * @param receivePeriod
	 */
	public void setReceivePeriod(java.lang.Integer receivePeriod) {
		this.receivePeriod = receivePeriod;
	}
	/**
	 * receivePeriod
	 * @return
	 */
	public java.lang.Integer getReceivePeriod() {
		return this.receivePeriod;
	}
	/**
	 * 设置 receiveMobile
	 * @param receiveMobile
	 */
	public void setReceiveMobile(java.lang.String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}
	/**
	 * receiveMobile
	 * @return
	 */
	public java.lang.String getReceiveMobile() {
		return this.receiveMobile;
	}
	/**
	 * 设置 updateTime
	 * @param updateTime
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		if(updateTime!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setUpdateTimeStr(time.format(updateTime));
		}
		
		this.updateTime = updateTime;
	}
	/**
	 * updateTime
	 * @return
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
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
	/**
	 * 设置 pic
	 * @param pic
	 */
	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}
	/**
	 * pic
	 * @return
	 */
	public java.lang.String getPic() {
		return this.pic;
	}
	/**
	 * 设置 productName
	 * @param productName
	 */
	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}
	/**
	 * productName
	 * @return
	 */
	public java.lang.String getProductName() {
		return this.productName;
	}
	/**
	 * 设置 saleManager
	 * @param saleManager
	 */
	public void setSaleManager(java.lang.Integer saleManager) {
		this.saleManager = saleManager;
	}
	/**
	 * saleManager
	 * @return
	 */
	public java.lang.Integer getSaleManager() {
		return this.saleManager;
	}
	/**
	 * 设置 contractNo
	 * @param contractNo
	 */
	public void setContractNo(java.lang.Integer contractNo) {
		this.contractNo = contractNo;
	}
	/**
	 * contractNo
	 * @return
	 */
	public java.lang.Integer getContractNo() {
		return this.contractNo;
	}
	/**
	 * 设置 updateManagerId
	 * @param updateManagerId
	 */
	public void setUpdateManagerId(java.lang.Integer updateManagerId) {
		this.updateManagerId = updateManagerId;
	}
	/**
	 * updateManagerId
	 * @return
	 */
	public java.lang.Integer getUpdateManagerId() {
		return this.updateManagerId;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}

