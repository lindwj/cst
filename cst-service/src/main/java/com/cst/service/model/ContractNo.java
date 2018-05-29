
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
public class ContractNo implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** contractNoId */
	private java.lang.Integer contractNoId;
	/** province */
	private java.lang.Integer province;
	/** city */
	private java.lang.Integer city;
	/** district */
	private java.lang.Integer district;
	
	private String provincestr;
	/** city */
	private String citystr;
	/** district */
	private String districtstr;
	/** street */
	private java.lang.Integer street;
	/** shopId */
	private java.lang.Integer shopId;
	
	private String shopIdstr;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	/** createByAdm */
	private java.lang.Integer createByAdm;
	/** createDate */
	private java.util.Date createDate;
	/** updateByAdm */
	private java.lang.Integer updateByAdm;
	/** updateDate */
	private java.util.Date updateDate;
	/** contractNoAmt */
	private java.lang.Integer contractNoAmt;
	
	private String errcode;
	private String dowhat;
	
private java.lang.Integer menuId;
	
	public java.lang.Integer getMenuId() {
	return menuId;
}
public void setMenuId(java.lang.Integer menuId) {
	this.menuId = menuId;
}
	
	
	public String getProvincestr() {
		return provincestr;
	}
	public void setProvincestr(String provincestr) {
		this.provincestr = provincestr;
	}
	public String getCitystr() {
		return citystr;
	}
	public void setCitystr(String citystr) {
		this.citystr = citystr;
	}
	public String getDistrictstr() {
		return districtstr;
	}
	public void setDistrictstr(String districtstr) {
		this.districtstr = districtstr;
	}
	public String getDowhat() {
		return dowhat;
	}
	public void setDowhat(String dowhat) {
		this.dowhat = dowhat;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	//columns END
	Page page;

	public String getShopIdstr() {
		return shopIdstr;
	}
	public void setShopIdstr(String shopIdstr) {
		this.shopIdstr = shopIdstr;
	}
	/**
	 * 设置 contractNoId
	 * @param contractNoId
	 */
	public void setContractNoId(java.lang.Integer contractNoId) {
		this.contractNoId = contractNoId;
	}
	/**
	 * contractNoId
	 * @return
	 */
	public java.lang.Integer getContractNoId() {
		return this.contractNoId;
	}
	/**
	 * 设置 province
	 * @param province
	 */
	public void setProvince(java.lang.Integer province) {
		this.province = province;
	}
	/**
	 * province
	 * @return
	 */
	public java.lang.Integer getProvince() {
		return this.province;
	}
	/**
	 * 设置 city
	 * @param city
	 */
	public void setCity(java.lang.Integer city) {
		this.city = city;
	}
	/**
	 * city
	 * @return
	 */
	public java.lang.Integer getCity() {
		return this.city;
	}
	/**
	 * 设置 district
	 * @param district
	 */
	public void setDistrict(java.lang.Integer district) {
		this.district = district;
	}
	/**
	 * district
	 * @return
	 */
	public java.lang.Integer getDistrict() {
		return this.district;
	}
	/**
	 * 设置 street
	 * @param street
	 */
	public void setStreet(java.lang.Integer street) {
		this.street = street;
	}
	/**
	 * street
	 * @return
	 */
	public java.lang.Integer getStreet() {
		return this.street;
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
	/**
	 * 设置 contractNoAmt
	 * @param contractNoAmt
	 */
	public void setContractNoAmt(java.lang.Integer contractNoAmt) {
		this.contractNoAmt = contractNoAmt;
	}
	/**
	 * contractNoAmt
	 * @return
	 */
	public java.lang.Integer getContractNoAmt() {
		return this.contractNoAmt;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}

