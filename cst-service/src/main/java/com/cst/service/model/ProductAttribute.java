
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
public class ProductAttribute implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** productAttributeId */
	private java.lang.Integer productAttributeId;
	/** productUuid */
	private java.lang.String productUuid;
	/** name */
	private java.lang.String name;
	/** value */
	private java.lang.String value;
	/** unit */
	private java.lang.String unit;
	/** sort */
	private java.lang.Integer sort;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	/** updateByAdm */
	private java.lang.Integer updateByAdm;
	/** updateDate */
	private java.util.Date updateDate;
	
	private java.lang.String errcode;
	private java.lang.String dowhat;
	
	private List<ProductAttribute> productAttributeList;
	//columns END
	Page page;

	public List<ProductAttribute> getProductAttributeList() {
		return productAttributeList;
	}
	public void setProductAttributeList(List<ProductAttribute> productAttributeList) {
		this.productAttributeList = productAttributeList;
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
	/**
	 * 设置 productAttributeId
	 * @param productAttributeId
	 */
	public void setProductAttributeId(java.lang.Integer productAttributeId) {
		this.productAttributeId = productAttributeId;
	}
	/**
	 * productAttributeId
	 * @return
	 */
	public java.lang.Integer getProductAttributeId() {
		return this.productAttributeId;
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
	 * 设置 name
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * name
	 * @return
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * 设置 value
	 * @param value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	/**
	 * value
	 * @return
	 */
	public java.lang.String getValue() {
		return this.value;
	}
	/**
	 * 设置 unit
	 * @param unit
	 */
	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}
	/**
	 * unit
	 * @return
	 */
	public java.lang.String getUnit() {
		return this.unit;
	}
	/**
	 * 设置 sort
	 * @param sort
	 */
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}
	/**
	 * sort
	 * @return
	 */
	public java.lang.Integer getSort() {
		return this.sort;
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

