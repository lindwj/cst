
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class Cart implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** 主键 */
	private java.lang.Integer cartId;
	/** 参数类型名称 */
	private java.lang.String productUuid;
	/** 状态 */
	private java.lang.Integer state;
	/** num */
	private java.lang.Integer num;
	/** 消费者 */
	private java.lang.Integer createByUser;
	/** createDate */
	private java.util.Date createDate;
	/** updateDate */
	private java.util.Date updateDate;
	
	private Integer wayType;
	
	private List<OrdersDetail>  ordersDetailList =new ArrayList<OrdersDetail>();
	//columns END
	Page page;

	/**
	 * 设置 主键
	 * @param cartId
	 */
	public void setCartId(java.lang.Integer cartId) {
		this.cartId = cartId;
	}
	public List<OrdersDetail> getOrdersDetailList() {
		return ordersDetailList;
	}
	public void setOrdersDetailList(List<OrdersDetail> ordersDetailList) {
		this.ordersDetailList = ordersDetailList;
	}
	/**
	 * 主键
	 * @return
	 */
	public java.lang.Integer getCartId() {
		return this.cartId;
	}
	/**
	 * 设置 参数类型名称
	 * @param productUuid
	 */
	public void setProductUuid(java.lang.String productUuid) {
		this.productUuid = productUuid;
	}
	/**
	 * 参数类型名称
	 * @return
	 */
	public java.lang.String getProductUuid() {
		return this.productUuid;
	}
	/**
	 * 设置 状态
	 * @param state
	 */
	public void setState(java.lang.Integer state) {
		this.state = state;
	}
	/**
	 * 状态
	 * @return
	 */
	public java.lang.Integer getState() {
		return this.state;
	}
	/**
	 * 设置 num
	 * @param num
	 */
	public void setNum(java.lang.Integer num) {
		this.num = num;
	}
	/**
	 * num
	 * @return
	 */
	public java.lang.Integer getNum() {
		return this.num;
	}
	/**
	 * 设置 消费者
	 * @param createByUser
	 */
	public void setCreateByUser(java.lang.Integer createByUser) {
		this.createByUser = createByUser;
	}
	/**
	 * 消费者
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
	 * 设置 updateDate
	 * @param updateDate
	 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * updateDate
	 * @return
	 */
	public java.util.Date getUpdateDate() {
		return this.updateDate;
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
}

