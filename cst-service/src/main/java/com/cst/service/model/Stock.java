
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
public class Stock implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** stockId */
	private java.lang.Integer stockId;
	/** showCapacity */
	private java.lang.Integer showCapacity;
	/** shopCapacity */
	private java.lang.Integer shopCapacity;
	
	private java.lang.Integer sellCapacity;
	/** productUuid */
	private java.lang.String productUuid;
	/** shopId */
	private java.lang.Integer shopId;
	
	private java.lang.Integer  typeId;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	/** updateByAdm */
	private java.lang.Integer updateByAdm;
	/** updateDate */
	private java.util.Date updateDate;
	
	private java.lang.String errcode;
	
	private java.lang.String dowhat;
	
	private java.lang.String name;
	
	private java.lang.String pic;
	
	List<Stock> stockList=null;
	//columns END
	Page page;

	
	public java.lang.Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(java.lang.Integer typeId) {
		this.typeId = typeId;
	}
	public java.lang.Integer getSellCapacity() {
		return sellCapacity;
	}
	public void setSellCapacity(java.lang.Integer sellCapacity) {
		this.sellCapacity = sellCapacity;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getPic() {
		return pic;
	}
	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}
	public java.lang.String getErrcode() {
		return errcode;
	}
	public void setErrcode(java.lang.String errcode) {
		this.errcode = errcode;
	}
	public java.lang.String getDowhat() {
		return dowhat;
	}
	public void setDowhat(java.lang.String dowhat) {
		this.dowhat = dowhat;
	}
	public List<Stock> getStockList() {
		return stockList;
	}
	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
	/**
	 * 设置 stockId
	 * @param stockId
	 */
	public void setStockId(java.lang.Integer stockId) {
		this.stockId = stockId;
	}
	/**
	 * stockId
	 * @return
	 */
	public java.lang.Integer getStockId() {
		return this.stockId;
	}
	/**
	 * 设置 showCapacity
	 * @param showCapacity
	 */
	public void setShowCapacity(java.lang.Integer showCapacity) {
		this.showCapacity = showCapacity;
	}
	/**
	 * showCapacity
	 * @return
	 */
	public java.lang.Integer getShowCapacity() {
		return this.showCapacity;
	}
	/**
	 * 设置 shopCapacity
	 * @param shopCapacity
	 */
	public void setShopCapacity(java.lang.Integer shopCapacity) {
		this.shopCapacity = shopCapacity;
	}
	/**
	 * shopCapacity
	 * @return
	 */
	public java.lang.Integer getShopCapacity() {
		return this.shopCapacity;
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
	 * 设置 updateByAdm
	 * @param updateByAdm
	 */
	public void setUpdateByAdm(java.lang.Integer updateByAdm) {
		this.updateByAdm = updateByAdm;
	}
	/**
	 * updateByAdm
	 * @return
	 */
	public java.lang.Integer getUpdateByAdm() {
		return this.updateByAdm;
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

	
}

