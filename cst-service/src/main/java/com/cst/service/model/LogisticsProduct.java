
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
public class LogisticsProduct implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** logisticsProductId */
	private java.lang.Integer logisticsProductId;
	/** 物流id */
	private java.lang.Integer logisticsId;
	/** 商品uuid */
	private java.lang.String productUuid;
	/** 发货数量 */
	private java.lang.Integer logisticsNum;
	/** 订单编号   冗余字段 */
	private java.lang.String ordergoodsCode;
	
	private Integer unit;
	
	private String productName;
	
	private Integer productPrice;
	
	private Integer totalNum;
	
	private Integer applyNum;
	
	private List<LogisticsProduct> logisticsProducts;
	/**checkbox判断*/
	private Integer hidden;
	//columns END
	Page page;

	/**
	 * 设置 logisticsProductId
	 * @param logisticsProductId
	 */
	public void setLogisticsProductId(java.lang.Integer logisticsProductId) {
		this.logisticsProductId = logisticsProductId;
	}
	/**
	 * logisticsProductId
	 * @return
	 */
	public java.lang.Integer getLogisticsProductId() {
		return this.logisticsProductId;
	}
	/**
	 * 设置 物流id
	 * @param logisticsId
	 */
	public void setLogisticsId(java.lang.Integer logisticsId) {
		this.logisticsId = logisticsId;
	}
	/**
	 * 物流id
	 * @return
	 */
	public java.lang.Integer getLogisticsId() {
		return this.logisticsId;
	}
	/**
	 * 设置 商品uuid
	 * @param productUuid
	 */
	public void setProductUuid(java.lang.String productUuid) {
		this.productUuid = productUuid;
	}
	/**
	 * 商品uuid
	 * @return
	 */
	public java.lang.String getProductUuid() {
		return this.productUuid;
	}
	/**
	 * 设置 发货数量
	 * @param logisticsNum
	 */
	public void setLogisticsNum(java.lang.Integer logisticsNum) {
		this.logisticsNum = logisticsNum;
	}
	/**
	 * 发货数量
	 * @return
	 */
	public java.lang.Integer getLogisticsNum() {
		return this.logisticsNum;
	}
	/**
	 * 设置 订单编号   冗余字段
	 * @param ordergoodsCode
	 */
	public void setOrdergoodsCode(java.lang.String ordergoodsCode) {
		this.ordergoodsCode = ordergoodsCode;
	}
	/**
	 * 订单编号   冗余字段
	 * @return
	 */
	public java.lang.String getOrdergoodsCode() {
		return this.ordergoodsCode;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public List<LogisticsProduct> getLogisticsProducts() {
		return logisticsProducts;
	}
	public void setLogisticsProducts(List<LogisticsProduct> logisticsProducts) {
		this.logisticsProducts = logisticsProducts;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getHidden() {
		return hidden;
	}
	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}
	public Integer getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}
}

