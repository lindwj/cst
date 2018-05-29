
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
public class IndexBanner implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** 主键 */
	private java.lang.Integer indexBannerId;
	/** 参数类型名称 */
	private java.lang.String bannerUrl;
	/** 状态             0 默认             -1 删除 */
	private java.lang.Integer state;
	private java.lang.Integer type;
	/** sort */
	private java.lang.Integer sort;
	/** createByAdm */
	private java.lang.Integer createByAdm;
	/** createDate */
	private java.util.Date createDate;
	/** 商品链接 */
	private java.lang.String productUrl;
	
	private java.lang.String errcode;
	
	private java.lang.String dowhat;
	
	private java.lang.Integer num;
	
	List<Parameter> pList;
	
	List<IndexBanner> indexBannerList;
	
private java.lang.Integer menuId;
	
	public java.lang.Integer getMenuId() {
	return menuId;
}
public void setMenuId(java.lang.Integer menuId) {
	this.menuId = menuId;
}

	public java.lang.Integer getType() {
	return type;
}
public void setType(java.lang.Integer type) {
	this.type = type;
}

	//columns END
	Page page;

	public java.lang.Integer getNum() {
		return num;
	}
	public void setNum(java.lang.Integer num) {
		this.num = num;
	}
	public List<Parameter> getpList() {
		return pList;
	}
	public void setpList(List<Parameter> pList) {
		this.pList = pList;
	}
	/**
	 * 设置 主键
	 * @param indexBannerId
	 */
	public void setIndexBannerId(java.lang.Integer indexBannerId) {
		this.indexBannerId = indexBannerId;
	}
	public List<IndexBanner> getIndexBannerList() {
		return indexBannerList;
	}
	public void setIndexBannerList(List<IndexBanner> indexBannerList) {
		this.indexBannerList = indexBannerList;
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
	 * 主键
	 * @return
	 */
	public java.lang.Integer getIndexBannerId() {
		return this.indexBannerId;
	}
	/**
	 * 设置 参数类型名称
	 * @param bannerUrl
	 */
	public void setBannerUrl(java.lang.String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	/**
	 * 参数类型名称
	 * @return
	 */
	public java.lang.String getBannerUrl() {
		return this.bannerUrl;
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
	 * 设置 商品链接
	 * @param productUrl
	 */
	public void setProductUrl(java.lang.String productUrl) {
		this.productUrl = productUrl;
	}
	/**
	 * 商品链接
	 * @return
	 */
	public java.lang.String getProductUrl() {
		return this.productUrl;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}

