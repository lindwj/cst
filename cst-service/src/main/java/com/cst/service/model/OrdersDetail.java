
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class OrdersDetail implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** ordersDetailId */
	private java.lang.Integer ordersDetailId;
	/** productUuid */
	private java.lang.String productUuid;
	private java.lang.Integer showCapacity;
	/** shopCapacity */
	private java.lang.Integer sellCapacity;
	/** productUuid */
	/** shopId */
	private java.lang.Integer shopId;
	/** subject */
	private java.lang.String subject;
	/** pic */
	private java.lang.String pic;
	/** productName */
	private java.lang.String productName;
	/** price */
	private double price;
	/** capacity */
	private java.lang.Integer capacity;
	/** code */
	private java.lang.String code;
	//columns END
	Page page;

	public java.lang.Integer getShowCapacity() {
		return showCapacity;
	}
	public void setShowCapacity(java.lang.Integer showCapacity) {
		this.showCapacity = showCapacity;
	}
	public java.lang.Integer getSellCapacity() {
		return sellCapacity;
	}
	public void setSellCapacity(java.lang.Integer sellCapacity) {
		this.sellCapacity = sellCapacity;
	}
	public java.lang.Integer getShopId() {
		return shopId;
	}
	public void setShopId(java.lang.Integer shopId) {
		this.shopId = shopId;
	}
	/**
	 * 设置 ordersDetailId
	 * @param ordersDetailId
	 */
	public void setOrdersDetailId(java.lang.Integer ordersDetailId) {
		this.ordersDetailId = ordersDetailId;
	}
	/**
	 * ordersDetailId
	 * @return
	 */
	public java.lang.Integer getOrdersDetailId() {
		return this.ordersDetailId;
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
	 * 设置 price
	 * @param price
	 */
	
	/**
	 * 设置 capacity
	 * @param capacity
	 */
	public void setCapacity(java.lang.Integer capacity) {
		this.capacity = capacity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * capacity
	 * @return
	 */
	public java.lang.Integer getCapacity() {
		return this.capacity;
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

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}

