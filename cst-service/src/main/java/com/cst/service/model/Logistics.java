
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class Logistics implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** logisticsId */
	private java.lang.Integer logisticsId;
	/** 物流公司 */
	private java.lang.String logisticsBuiness;
	/** 物流单号 */
	private java.lang.String logisticsCode;
	/** 物流电话号码 */
	private java.lang.String logisticsPhone;
	/** 物流状态    -1未签收    0  已发货      1 已签收    2物流完成 */
	private java.lang.Integer logisticsStatus;
	/** 物流订单创建人 */
	private java.lang.Integer logisticsMan;
	/** 物流创建时间 */
	private java.util.Date logisticsTime;
	/** 订单编号*/
	private java.lang.String ordergoodsCode;
	/** 联系人*/
	private String linkMan;
	
	private List<LogisticsProduct> logisticsProducts=new ArrayList<LogisticsProduct>();
	
	private List<Logistics> logistics=new ArrayList<Logistics>();
	
	private String logisticsTimeStr;
	/**物流订单总价*/
	private double totalPrice;
	/**签收页面save人*/
	private Integer shopMan;
	/**签收保存时间*/
	private Date shopTime;
	/**上传图片*/
	private String signPic;
	/**运费*/
	private double sendMoney;
	/**货物状态（受损状况）*/
	private Integer sendStatus;
	/**运输情况描述*/
	private String sendDescription;
	//columns END
	Page page;

	/**
	 * 设置 logisticsId
	 * @param logisticsId
	 */
	public void setLogisticsId(java.lang.Integer logisticsId) {
		this.logisticsId = logisticsId;
	}
	/**
	 * logisticsId
	 * @return
	 */
	public java.lang.Integer getLogisticsId() {
		return this.logisticsId;
	}
	/**
	 * 设置 物流公司
	 * @param logisticsBuiness
	 */
	public void setLogisticsBuiness(java.lang.String logisticsBuiness) {
		this.logisticsBuiness = logisticsBuiness;
	}
	/**
	 * 物流公司
	 * @return
	 */
	public java.lang.String getLogisticsBuiness() {
		return this.logisticsBuiness;
	}
	/**
	 * 设置 物流单号
	 * @param logisticsCode
	 */
	public void setLogisticsCode(java.lang.String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}
	/**
	 * 物流单号
	 * @return
	 */
	public java.lang.String getLogisticsCode() {
		return this.logisticsCode;
	}
	/**
	 * 设置 物流电话号码
	 * @param logisticsPhone
	 */
	public void setLogisticsPhone(java.lang.String logisticsPhone) {
		this.logisticsPhone = logisticsPhone;
	}
	/**
	 * 物流电话号码
	 * @return
	 */
	public java.lang.String getLogisticsPhone() {
		return this.logisticsPhone;
	}
	/**
	 * 设置 物流状态    -1未签收    0  已发货      1 已签收    2物流完成
	 * @param logisticsStatus
	 */
	public void setLogisticsStatus(java.lang.Integer logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}
	/**
	 * 物流状态    -1未签收    0  已发货      1 已签收    2物流完成
	 * @return
	 */
	public java.lang.Integer getLogisticsStatus() {
		return this.logisticsStatus;
	}
	/**
	 * 设置 物流订单创建人
	 * @param logisticsMan
	 */
	public void setLogisticsMan(java.lang.Integer logisticsMan) {
		this.logisticsMan = logisticsMan;
	}
	/**
	 * 物流订单创建人
	 * @return
	 */
	public java.lang.Integer getLogisticsMan() {
		return this.logisticsMan;
	}
	/**
	 * 设置 物流创建时间
	 * @param logisticsTime
	 */
	public void setLogisticsTime(java.util.Date logisticsTime) {
		if(logisticsTime!=null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
			this.setLogisticsTimeStr(time.format(logisticsTime));
		}
		this.logisticsTime = logisticsTime;
	}
	/**
	 * 物流创建时间
	 * @return
	 */
	public java.util.Date getLogisticsTime() {
		return this.logisticsTime;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public java.lang.String getOrdergoodsCode() {
		return ordergoodsCode;
	}
	public void setOrdergoodsCode(java.lang.String ordergoodsCode) {
		this.ordergoodsCode = ordergoodsCode;
	}
	public List<LogisticsProduct> getLogisticsProducts() {
		return logisticsProducts;
	}
	public void setLogisticsProducts(List<LogisticsProduct> logisticsProducts) {
		this.logisticsProducts = logisticsProducts;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public List<Logistics> getLogistics() {
		return logistics;
	}
	public void setLogistics(List<Logistics> logistics) {
		this.logistics = logistics;
	}
	public String getLogisticsTimeStr() {
		return logisticsTimeStr;
	}
	public void setLogisticsTimeStr(String logisticsTimeStr) {
		this.logisticsTimeStr = logisticsTimeStr;
	}
	public Integer getShopMan() {
		return shopMan;
	}
	public void setShopMan(Integer shopMan) {
		this.shopMan = shopMan;
	}
	public Date getShopTime() {
		return shopTime;
	}
	public void setShopTime(Date shopTime) {
		this.shopTime = shopTime;
	}
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getSendDescription() {
		return sendDescription;
	}
	public void setSendDescription(String sendDescription) {
		this.sendDescription = sendDescription;
	}
	public String getSignPic() {
		return signPic;
	}
	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getSendMoney() {
		return sendMoney;
	}
	public void setSendMoney(double sendMoney) {
		this.sendMoney = sendMoney;
	}
}

