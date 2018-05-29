
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
public class ProductCfg implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** 主键 */
	private java.lang.Integer productCfgId;
	/** 商品uuid */
	private java.lang.String productUuid;
	/** 状态             0 默认             -1 删除 */
	private java.lang.Integer state;
	/** sort */
	private java.lang.Integer sort;
	/** createByAdm */
	private java.lang.Integer createByAdm;
	/** createDate */
	private java.util.Date createDate;
	/** 推荐类型 1 预订 2 热门 3 推荐 */
	private java.lang.Integer type;
	
	private double price;
	
private java.lang.String errcode;
	
	private java.lang.String dowhat;
	
	private java.lang.String name ;
	private java.lang.String pic;
	private java.lang.String typeStr;
	
	private java.lang.String picUrl;
	
private java.lang.Integer menuId;
	
	public java.lang.Integer getMenuId() {
	return menuId;
}
public void setMenuId(java.lang.Integer menuId) {
	this.menuId = menuId;
}
	//columns END
	Page page;

	public java.lang.String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(java.lang.String picUrl) {
		this.picUrl = picUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public java.lang.String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(java.lang.String typeStr) {
		this.typeStr = typeStr;
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
	 * 设置 主键
	 * @param productCfgId
	 */
	public void setProductCfgId(java.lang.Integer productCfgId) {
		this.productCfgId = productCfgId;
	}
	/**
	 * 主键
	 * @return
	 */
	public java.lang.Integer getProductCfgId() {
		return this.productCfgId;
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
	 * 设置 状态             0 默认             -1 删除
	 * @param state
	 */
	public void setState(java.lang.Integer state) {
		this.state = state;
	}
	/**
	 * 状态             0 默认             -1 删除
	 * @return
	 */
	public java.lang.Integer getState() {
		return this.state;
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
	 * 设置 createByAdm
	 * @param createByAdm
	 */
	public void setCreateByAdm(java.lang.Integer createByAdm) {
		this.createByAdm = createByAdm;
	}
	/**
	 * createByAdm
	 * @return
	 */
	public java.lang.Integer getCreateByAdm() {
		return this.createByAdm;
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
	 * 设置 推荐类型 1 预订 2 热门 3 推荐
	 * @param type
	 */
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	/**
	 * 推荐类型 1 预订 2 热门 3 推荐
	 * @return
	 */
	public java.lang.Integer getType() {
		return this.type;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}

