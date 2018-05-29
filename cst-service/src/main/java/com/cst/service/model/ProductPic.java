
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
public class ProductPic implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** productPicId */
	private java.lang.Integer productPicId;
	/** productUuid */
	private java.lang.String productUuid;
	/** picUrl */
	private java.lang.String picUrl;
	/** sort */
	private java.lang.Integer sort;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	
	private java.lang.String errcode;
	
	private java.lang.String dowhat;
	
	private List<ProductPic> productPicList;
	//columns END
	Page page;

	
	
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
	public List<ProductPic> getProductPicList() {
		return productPicList;
	}
	public void setProductPicList(List<ProductPic> productPicList) {
		this.productPicList = productPicList;
	}
	/**
	 * 设置 productPicId
	 * @param productPicId
	 */
	public void setProductPicId(java.lang.Integer productPicId) {
		this.productPicId = productPicId;
	}
	/**
	 * productPicId
	 * @return
	 */
	public java.lang.Integer getProductPicId() {
		return this.productPicId;
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
	 * 设置 picUrl
	 * @param picUrl
	 */
	public void setPicUrl(java.lang.String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * picUrl
	 * @return
	 */
	public java.lang.String getPicUrl() {
		return this.picUrl;
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

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}

