
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cst.service.common.Page;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * @author lind
 *
 */
public class Orders implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** ordersId */
	private java.lang.Integer ordersId;
	/** code */
	private java.lang.String code;
	/** productUuid */
	private java.lang.String productUuid;
	/** shopId */
	private java.lang.Integer shopId;
	/** bdhOrderStatus */
	private java.lang.String bdhOrderStatus;
	/** alipayOrderNotify */
	private java.lang.String alipayOrderNotify;
	/** alipayOrderReturn */
	private java.lang.String alipayOrderReturn;
	/** createByUser */
	private java.lang.Integer createByUser;
	/** createDate */
	private java.util.Date createDate;
	
	private java.lang.String orderCode;
	private java.lang.String state;
	
	private String createDatestr;
	
	
	
	
	private List<Orders> ordersList=new ArrayList<Orders>();
	
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	
	private String statusstr;
	/** tradeNo */
	private java.lang.String tradeNo;
	/** totalFee */
	private double totalFee;
	/** receiveName */
	private java.lang.String receiveName;
	/** buyerEmail */
	private java.lang.String buyerEmail;
	/** receiveAddress */
	private java.lang.String receiveAddress;
	/** receiveZip */
	private java.lang.String receiveZip;
	/** receivePhone */
	private java.lang.String receivePhone;
	/** receiveMobile */
	private java.lang.String receiveMobile;
	/** totalFromBdh */
	private double totalFromBdh;
	/** sellerEmail */
	private java.lang.String sellerEmail;
	/** subject */
	private java.lang.String subject;
	/** alibody */
	private java.lang.String alibody;
	/** payTime */
	private java.util.Date payTime;
	/** payType */
	private java.lang.Integer payType;
	/** goodsAddressUuid */
	private java.lang.String goodsAddressUuid;
	/** shopName */
	private java.lang.String shopName;
	/** pic */
	private java.lang.String pic;
	/** productName */
	private java.lang.String productName;
	/** price */
	private double price;
	/** capacity */
	private java.lang.Integer capacity;
	
	private java.lang.String errcode;
	
	private java.lang.Integer province;
	/** city */
	private java.lang.Integer city;
	/** district */
	private java.lang.Integer district;
	
	

	private String resultCode;
	
	private String returnCode;
	
	private String openid;
	
	private String transactionId;
	
	private String appId;
	
	private String timeStamp;
	
	private String nonceStr;
	
	private String paypackage;
	
	private String signType;
	
	private String paySign;
	
	private Integer wayType;
	
	private Integer showCapacity;
	
	private double serviceFee;
	
	private String note;	
	
	private GoodsAddress goodsAddress;
	
	private Product product;

	List<GoodsAddress> goodsAddressListadd;
	
	List<GoodsAddress> goodsAddressList;
	
	private Integer logisticsInfoId;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<GoodsAddress> getGoodsAddressListadd() {
		return goodsAddressListadd;
	}
	public void setGoodsAddressListadd(List<GoodsAddress> goodsAddressListadd) {
		this.goodsAddressListadd = goodsAddressListadd;
	}
	public List<GoodsAddress> getGoodsAddressList() {
		return goodsAddressList;
	}
	public void setGoodsAddressList(List<GoodsAddress> goodsAddressList) {
		this.goodsAddressList = goodsAddressList;
	}
	public GoodsAddress getGoodsAddress() {
		return goodsAddress;
	}
	public void setGoodsAddress(GoodsAddress goodsAddress) {
		this.goodsAddress = goodsAddress;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public java.lang.String getState() {
		return state;
	}
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.lang.String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(java.lang.String orderCode) {
		this.orderCode = orderCode;
	}

	private List<OrdersDetail>  ordersDetailList =new ArrayList<OrdersDetail>();
	
private java.lang.Integer menuId;
	
	public java.lang.Integer getMenuId() {
	return menuId;
}
public void setMenuId(java.lang.Integer menuId) {
	this.menuId = menuId;
}
	
	//columns END
	Page page;

	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPaypackage() {
		return paypackage;
	}
	public void setPaypackage(String paypackage) {
		this.paypackage = paypackage;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
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
	public List<Orders> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}
	/**
	 * 设置 ordersId
	 * @param ordersId
	 */
	
	public void setOrdersId(java.lang.Integer ordersId) {
		this.ordersId = ordersId;
	}
	public List<OrdersDetail> getOrdersDetailList() {
		return ordersDetailList;
	}
	public void setOrdersDetailList(List<OrdersDetail> ordersDetailList) {
		this.ordersDetailList = ordersDetailList;
	}
	public String getStatusstr() {
		return statusstr;
	}
	public void setStatusstr(String statusstr) {
		this.statusstr = statusstr;
	}
	public String getCreateDatestr() {
		return createDatestr;
	}
	public void setCreateDatestr(String createDatestr) {
		this.createDatestr = createDatestr;
	}
	public java.lang.String getErrcode() {
		return errcode;
	}
	public void setErrcode(java.lang.String errcode) {
		this.errcode = errcode;
	}
	/**
	 * ordersId
	 * @return
	 */
	public java.lang.Integer getOrdersId() {
		return this.ordersId;
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
	 * 设置 bdhOrderStatus
	 * @param bdhOrderStatus
	 */
	public void setBdhOrderStatus(java.lang.String bdhOrderStatus) {
		this.bdhOrderStatus = bdhOrderStatus;
	}
	/**
	 * bdhOrderStatus
	 * @return
	 */
	public java.lang.String getBdhOrderStatus() {
		return this.bdhOrderStatus;
	}
	/**
	 * 设置 alipayOrderNotify
	 * @param alipayOrderNotify
	 */
	public void setAlipayOrderNotify(java.lang.String alipayOrderNotify) {
		this.alipayOrderNotify = alipayOrderNotify;
	}
	/**
	 * alipayOrderNotify
	 * @return
	 */
	public java.lang.String getAlipayOrderNotify() {
		return this.alipayOrderNotify;
	}
	/**
	 * 设置 alipayOrderReturn
	 * @param alipayOrderReturn
	 */
	public void setAlipayOrderReturn(java.lang.String alipayOrderReturn) {
		this.alipayOrderReturn = alipayOrderReturn;
	}
	/**
	 * alipayOrderReturn
	 * @return
	 */
	public java.lang.String getAlipayOrderReturn() {
		return this.alipayOrderReturn;
	}
	/**
	 * 设置 createByUser
	 * @param createByUser
	 */
	public void setCreateByUser(java.lang.Integer createByUser) {
		this.createByUser = createByUser;
	}
	/**
	 * createByUser
	 * @return
	 */
	public java.lang.Integer getCreateByUser() {
		return this.createByUser;
	}
	/**
	 * 设置 createDate
	 * @param createDate
	 */
	public void setCreateDate(java.util.Date createDate) {
		
		if(createDate!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setCreateDatestr(time.format(createDate));
		}
		
		
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
	 * 设置 tradeNo
	 * @param tradeNo
	 */
	public void setTradeNo(java.lang.String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * tradeNo
	 * @return
	 */
	public java.lang.String getTradeNo() {
		return this.tradeNo;
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
	 * 设置 buyerEmail
	 * @param buyerEmail
	 */
	public void setBuyerEmail(java.lang.String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	/**
	 * buyerEmail
	 * @return
	 */
	public java.lang.String getBuyerEmail() {
		return this.buyerEmail;
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
	 * 设置 receiveZip
	 * @param receiveZip
	 */
	public void setReceiveZip(java.lang.String receiveZip) {
		this.receiveZip = receiveZip;
	}
	/**
	 * receiveZip
	 * @return
	 */
	public java.lang.String getReceiveZip() {
		return this.receiveZip;
	}
	/**
	 * 设置 receivePhone
	 * @param receivePhone
	 */
	public void setReceivePhone(java.lang.String receivePhone) {
		this.receivePhone = receivePhone;
	}
	/**
	 * receivePhone
	 * @return
	 */
	public java.lang.String getReceivePhone() {
		return this.receivePhone;
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
	 * 设置 sellerEmail
	 * @param sellerEmail
	 */
	public void setSellerEmail(java.lang.String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	/**
	 * sellerEmail
	 * @return
	 */
	public java.lang.String getSellerEmail() {
		return this.sellerEmail;
	}
	/**
	 * 设置 subject
	 * @param subject
	 */
	public void setSubject(java.lang.String subject) {
		this.subject = subject;
	}
	/**
	 * subject
	 * @return
	 */
	public java.lang.String getSubject() {
		return this.subject;
	}
	/**
	 * 设置 alibody
	 * @param alibody
	 */
	public void setAlibody(java.lang.String alibody) {
		this.alibody = alibody;
	}
	/**
	 * alibody
	 * @return
	 */
	public java.lang.String getAlibody() {
		return this.alibody;
	}
	/**
	 * 设置 payTime
	 * @param payTime
	 */
	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * payTime
	 * @return
	 */
	public java.util.Date getPayTime() {
		return this.payTime;
	}
	/**
	 * 设置 payType
	 * @param payType
	 */
	public void setPayType(java.lang.Integer payType) {
		this.payType = payType;
	}
	/**
	 * payType
	 * @return
	 */
	public java.lang.Integer getPayType() {
		return this.payType;
	}
	/**
	 * 设置 goodsAddressUuid
	 * @param goodsAddressUuid
	 */
	public void setGoodsAddressUuid(java.lang.String goodsAddressUuid) {
		this.goodsAddressUuid = goodsAddressUuid;
	}
	/**
	 * goodsAddressUuid
	 * @return
	 */
	public java.lang.String getGoodsAddressUuid() {
		return this.goodsAddressUuid;
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
	
	public double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	public double getTotalFromBdh() {
		return totalFromBdh;
	}
	public void setTotalFromBdh(double totalFromBdh) {
		this.totalFromBdh = totalFromBdh;
		
		DecimalFormat df = new DecimalFormat ("#.##");
		this.setServiceFee(Double.parseDouble(df.format(totalFromBdh*6/1000)));
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * 设置 capacity
	 * @param capacity
	 */
	public void setCapacity(java.lang.Integer capacity) {
		this.capacity = capacity;
	}
	/**
	 * capacity
	 * @return
	 */
	public java.lang.Integer getCapacity() {
		return this.capacity;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Integer getWayType() {
		return wayType;
	}
	public void setWayType(Integer wayType) {
		this.wayType = wayType;
	}
	public Integer getShowCapacity() {
		return showCapacity;
	}
	public void setShowCapacity(Integer showCapacity) {
		this.showCapacity = showCapacity;
	}
	public Integer getLogisticsInfoId() {
		return logisticsInfoId;
	}
	public void setLogisticsInfoId(Integer logisticsInfoId) {
		this.logisticsInfoId = logisticsInfoId;
	}
}

