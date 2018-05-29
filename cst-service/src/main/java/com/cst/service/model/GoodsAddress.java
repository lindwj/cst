
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
public class GoodsAddress implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** goodsAddressId */
	private java.lang.Integer goodsAddressId;
	/** name */
	private java.lang.String name;
	/** province */
	private java.lang.Integer province;
	/** city */
	private java.lang.Integer city;
	/** district */
	private java.lang.Integer district;
	/** street */
	private java.lang.Integer street;
	
	
	private java.lang.String provincestr;
	/** city */
	private java.lang.String citystr;
	/** district */
	private java.lang.String districtstr;
	/** street */
	private java.lang.String streetstr;
	
	/** address */
	private java.lang.String address;
	/** mobile */
	private java.lang.String mobile;
	
	private java.lang.String telephone;
	
	/** createByUser */
	private java.lang.Integer createByUser;
	/** createDate */
	private java.util.Date createDate;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	
	private java.lang.Integer isDefault;
	
	private java.lang.String dowhat;
	
	private java.lang.String goodsAddressUuid;
	
	private Integer provinceVal;
	
	private Integer cityVal;
	
	private Integer districtVal;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String districtCode;
	
	private java.lang.String errcode;
	
	private String openid;
	
	private List<GoodsAddress> goodsAddressList;
	//columns END
	Page page;
	
	

	public List<GoodsAddress> getGoodsAddressList() {
		return goodsAddressList;
	}
	public void setGoodsAddressList(List<GoodsAddress> goodsAddressList) {
		this.goodsAddressList = goodsAddressList;
	}
	public java.lang.String getTelephone() {
		return telephone;
	}
	public void setTelephone(java.lang.String telephone) {
		this.telephone = telephone;
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
	public java.lang.Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(java.lang.Integer isDefault) {
		this.isDefault = isDefault;
	}
	public java.lang.String getGoodsAddressUuid() {
		return goodsAddressUuid;
	}
	public void setGoodsAddressUuid(java.lang.String goodsAddressUuid) {
		this.goodsAddressUuid = goodsAddressUuid;
	}
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
	/**
	 * 设置 goodsAddressId
	 * @param goodsAddressId
	 */
	public void setGoodsAddressId(java.lang.Integer goodsAddressId) {
		this.goodsAddressId = goodsAddressId;
	}
	/**
	 * goodsAddressId
	 * @return
	 */
	public java.lang.Integer getGoodsAddressId() {
		return this.goodsAddressId;
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
	 * 设置 mobile
	 * @param mobile
	 */
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	/**
	 * mobile
	 * @return
	 */
	public java.lang.String getMobile() {
		return this.mobile;
	}
	/**
	 * 设置 createByUser
	 * @param createByUser
	 */
	public void setCreateByUser(java.lang.Integer createByUser) {
		this.createByUser = createByUser;
	}
	/**
	 * createByUser
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
	public Integer getProvinceVal() {
		return provinceVal;
	}
	public void setProvinceVal(Integer provinceVal) {
		this.provinceVal = provinceVal;
	}
	public Integer getCityVal() {
		return cityVal;
	}
	public void setCityVal(Integer cityVal) {
		this.cityVal = cityVal;
	}
	public Integer getDistrictVal() {
		return districtVal;
	}
	public void setDistrictVal(Integer districtVal) {
		this.districtVal = districtVal;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}

