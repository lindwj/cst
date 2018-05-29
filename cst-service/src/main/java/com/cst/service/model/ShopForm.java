
package com.cst.service.model;

import java.io.Serializable;
//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class ShopForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** shopId */
	private String shopId;
	/** name */
	private String name;
	/** province */
	private String province;
	/** city */
	private String city;
	/** district */
	private String district;
	/** street */
	private String street;
	/** address */
	private String address;
	/** code */
	private String code;
	/** createByAdm */
	private String createByAdm;
	/** createDate */
	private String createDate;
	/** updateByAdm */
	private String updateByAdm;
	/** updateDate */
	private String updateDate;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private String status;
	//columns END
	Page page;

	/**
	 * 设置 shopId
	 * @param shopId
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * shopId
	 * @return
	 */
	public String getShopId() {
		return this.shopId;
	}
	/**
	 * 设置 name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 设置 province
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * province
	 * @return
	 */
	public String getProvince() {
		return this.province;
	}
	/**
	 * 设置 city
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * city
	 * @return
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * 设置 district
	 * @param district
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * district
	 * @return
	 */
	public String getDistrict() {
		return this.district;
	}
	/**
	 * 设置 street
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * street
	 * @return
	 */
	public String getStreet() {
		return this.street;
	}
	/**
	 * 设置 address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * address
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * 设置 code
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * code
	 * @return
	 */
	public String getCode() {
		return this.code;
	}
	/**
	 * 设置 createByAdm
	 * @param createByAdm
	 */
	public void setCreateByAdm(String createByAdm) {
		this.createByAdm = createByAdm;
	}
	/**
	 * createByAdm
	 * @return
	 */
	public String getCreateByAdm() {
		return this.createByAdm;
	}
	/**
	 * 设置 createDate
	 * @param createDate
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * createDate
	 * @return
	 */
	public String getCreateDate() {
		return this.createDate;
	}
	/**
	 * 设置 updateByAdm
	 * @param updateByAdm
	 */
	public void setUpdateByAdm(String updateByAdm) {
		this.updateByAdm = updateByAdm;
	}
	/**
	 * updateByAdm
	 * @return
	 */
	public String getUpdateByAdm() {
		return this.updateByAdm;
	}
	/**
	 * 设置 updateDate
	 * @param updateDate
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * updateDate
	 * @return
	 */
	public String getUpdateDate() {
		return this.updateDate;
	}
	/**
	 * 设置 状态             -1 删除             0 默认 草稿             1 上架             2 下架
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 状态             -1 删除             0 默认 草稿             1 上架             2 下架
	 * @return
	 */
	public String getStatus() {
		return this.status;
	}

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setShop(Shop shop){
		if(shop==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.shopId=shop.getShopId()==null?"":String.valueOf(shop.getShopId());	
		this.name=shop.getName()==null?"":shop.getName();	
		this.province=shop.getProvince()==null?"":String.valueOf(shop.getProvince());	
		this.city=shop.getCity()==null?"":String.valueOf(shop.getCity());	
		this.district=shop.getDistrict()==null?"":String.valueOf(shop.getDistrict());	
		this.street=shop.getStreet()==null?"":String.valueOf(shop.getStreet());	
		this.address=shop.getAddress()==null?"":shop.getAddress();	
		this.code=shop.getCode()==null?"":shop.getCode();	
		this.createByAdm=shop.getCreateByAdm()==null?"":String.valueOf(shop.getCreateByAdm());	
		this.createDate=shop.getCreateDate()==null?"":df.format(shop.getCreateDate());	
		this.updateByAdm=shop.getUpdateByAdm()==null?"":String.valueOf(shop.getUpdateByAdm());	
		this.updateDate=shop.getUpdateDate()==null?"":df.format(shop.getUpdateDate());	
		this.status=shop.getStatus()==null?"":String.valueOf(shop.getStatus());	
		this.page=shop.getPage();
	}
  
	public Shop getShop(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Shop shop=new Shop();
		shop.setShopId(this.shopId==null||this.shopId.length()==0?0:Integer.parseInt(this.shopId));  
		shop.setName(this.name==null||this.name.length()==0?"":this.name);  
		shop.setProvince(this.province==null||this.province.length()==0?0:Integer.parseInt(this.province));  
		shop.setCity(this.city==null||this.city.length()==0?0:Integer.parseInt(this.city));  
		shop.setDistrict(this.district==null||this.district.length()==0?0:Integer.parseInt(this.district));  
		shop.setStreet(this.street==null||this.street.length()==0?0:Integer.parseInt(this.street));  
		shop.setAddress(this.address==null||this.address.length()==0?"":this.address);  
		shop.setCode(this.code==null||this.code.length()==0?"":this.code);  
		shop.setCreateByAdm(this.createByAdm==null||this.createByAdm.length()==0?0:Integer.parseInt(this.createByAdm));  
		try {
			shop.setCreateDate(this.createDate==null||this.createDate.length()==0?null:df.parse(this.createDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		shop.setUpdateByAdm(this.updateByAdm==null||this.updateByAdm.length()==0?0:Integer.parseInt(this.updateByAdm));  
		try {
			shop.setUpdateDate(this.updateDate==null||this.updateDate.length()==0?null:df.parse(this.updateDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		shop.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		shop.setPage(this.page);	
		return shop;
	}
  
	
}

