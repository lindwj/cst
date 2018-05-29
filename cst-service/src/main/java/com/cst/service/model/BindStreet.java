
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
public class BindStreet implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** bindStreetId */
	private java.lang.Integer bindStreetId;
	/** street */
	private java.lang.Integer street;
	
	private java.lang.Integer province;
	/** city */
	private java.lang.Integer city;
	/** district */
	private java.lang.Integer district;
	/** shopId */
	private java.lang.Integer shopId;
	
	
	
	List<BindStreet> streetList=new ArrayList<BindStreet>();
	//columns END
	Page page;

	
	
	public List<BindStreet> getStreetList() {
		return streetList;
	}
	public void setStreetList(List<BindStreet> streetList) {
		this.streetList = streetList;
	}
	public java.lang.Integer getProvince() {
		return province;
	}
	public void setProvince(java.lang.Integer province) {
		this.province = province;
	}
	public java.lang.Integer getCity() {
		return city;
	}
	public void setCity(java.lang.Integer city) {
		this.city = city;
	}
	public java.lang.Integer getDistrict() {
		return district;
	}
	public void setDistrict(java.lang.Integer district) {
		this.district = district;
	}
	/**
	 * 设置 bindStreetId
	 * @param bindStreetId
	 */
	public void setBindStreetId(java.lang.Integer bindStreetId) {
		this.bindStreetId = bindStreetId;
	}
	/**
	 * bindStreetId
	 * @return
	 */
	public java.lang.Integer getBindStreetId() {
		return this.bindStreetId;
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

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}

