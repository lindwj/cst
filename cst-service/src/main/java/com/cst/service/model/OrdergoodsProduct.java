
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
public class OrdergoodsProduct implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** ordergoodsProductId */
	private java.lang.Integer ordergoodsProductId;
	/** 订单编号 */
	private java.lang.String ordergoodsCode;
	/** 商品uuid */
	private java.lang.String productUuid;
	/** 商品单位 */
	private java.lang.Integer unit;
	/** 申请数量 */
	private java.lang.Integer applyNum;
	/** 调整后数量 */
	private java.lang.Integer updateNum;
	/** 商品名称 */
	private java.lang.String productName;
	/** 商品价格 */
	private Long productPrice;
	
	private Integer num;
	
	private List<OrdergoodsProduct> ordergoodsProducts;
	//columns END
	Page page;

	/**
	 * 设置 ordergoodsProductId
	 * @param ordergoodsProductId
	 */
	public void setOrdergoodsProductId(java.lang.Integer ordergoodsProductId) {
		this.ordergoodsProductId = ordergoodsProductId;
	}
	/**
	 * ordergoodsProductId
	 * @return
	 */
	public java.lang.Integer getOrdergoodsProductId() {
		return this.ordergoodsProductId;
	}
	/**
	 * 设置 订单编号
	 * @param ordergoodsCode
	 */
	public void setOrdergoodsCode(java.lang.String ordergoodsCode) {
		this.ordergoodsCode = ordergoodsCode;
	}
	/**
	 * 订单编号
	 * @return
	 */
	public java.lang.String getOrdergoodsCode() {
		return this.ordergoodsCode;
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
	 * 设置 商品单位
	 * @param unit
	 */
	public void setUnit(java.lang.Integer unit) {
		this.unit = unit;
	}
	/**
	 * 商品单位
	 * @return
	 */
	public java.lang.Integer getUnit() {
		return this.unit;
	}
	/**
	 * 设置 申请数量
	 * @param applyNum
	 */
	public void setApplyNum(java.lang.Integer applyNum) {
		this.applyNum = applyNum;
	}
	/**
	 * 申请数量
	 * @return
	 */
	public java.lang.Integer getApplyNum() {
		return this.applyNum;
	}
	/**
	 * 设置 调整后数量
	 * @param updateNum
	 */
	public void setUpdateNum(java.lang.Integer updateNum) {
		this.updateNum = updateNum;
	}
	/**
	 * 调整后数量
	 * @return
	 */
	public java.lang.Integer getUpdateNum() {
		return this.updateNum;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public java.lang.String getProductName() {
		return productName;
	}
	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}
	public Long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}
	public List<OrdergoodsProduct> getOrdergoodsProducts() {
		return ordergoodsProducts;
	}
	public void setOrdergoodsProducts(List<OrdergoodsProduct> ordergoodsProducts) {
		this.ordergoodsProducts = ordergoodsProducts;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
}

