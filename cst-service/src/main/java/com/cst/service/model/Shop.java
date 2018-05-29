
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
public class Shop implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** shopId */
	private java.lang.Integer shopId;
	/** name */
	private java.lang.String name;
	
	private java.lang.String nickName;
	/** province */
	private java.lang.Integer province;
	/** city */
	private java.lang.Integer city;
	/** district */
	private java.lang.Integer district;
	/** street */
	private java.lang.Integer street;
	
	/** province */
	private java.lang.String provincestr;
	/** city */
	private java.lang.String citystr;
	/** district */
	private java.lang.String districtstr;
	/** street */
	private java.lang.String streetstr;
	
	/** address */
	private java.lang.String address;
	/** code */
	private java.lang.String code;
	
	private java.lang.String dowhat;
	
	private java.lang.String errcode;
	
	/** createByAdm */
	private java.lang.Integer createByAdm;
	/** createDate */
	private java.util.Date createDate;
	/** updateByAdm */
	private java.lang.Integer updateByAdm;
	/** updateDate */
	private java.util.Date updateDate;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	
	
private java.lang.Integer menuId;
	
	public java.lang.Integer getMenuId() {
	return menuId;
}
public void setMenuId(java.lang.Integer menuId) {
	this.menuId = menuId;
}
	//columns END
	Page page;

	
	
	public java.lang.String getNickName() {
		return nickName;
	}
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	public java.lang.String getProvincestr() {
		return provincestr;
	}
	public void setProvincestr(java.lang.String provincestr) {
		this.provincestr = provincestr;
	}
	public java.lang.String getCitystr() {
		return citystr;
	}
	public void setCitystr(java.lang.String citystr) {
		this.citystr = citystr;
	}
	public java.lang.String getDistrictstr() {
		return districtstr;
	}
	public void setDistrictstr(java.lang.String districtstr) {
		this.districtstr = districtstr;
	}
	public java.lang.String getStreetstr() {
		return streetstr;
	}
	public void setStreetstr(java.lang.String streetstr) {
		this.streetstr = streetstr;
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
	 * 设置 address
	 * @param address
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	/**
	 * address
	 * @return
	 */
	public java.lang.String getAddress() {
		return this.address;
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

