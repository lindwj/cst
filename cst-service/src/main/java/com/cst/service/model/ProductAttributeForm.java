
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
public class ProductAttributeForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** productAttributeId */
	private String productAttributeId;
	/** productUuid */
	private String productUuid;
	/** name */
	private String name;
	/** value */
	private String value;
	/** unit */
	private String unit;
	/** sort */
	private String sort;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private String status;
	/** updateByAdm */
	private String updateByAdm;
	/** updateDate */
	private String updateDate;
	//columns END
	Page page;

	/**
	 * 设置 productAttributeId
	 * @param productAttributeId
	 */
	public void setProductAttributeId(String productAttributeId) {
		this.productAttributeId = productAttributeId;
	}
	/**
	 * productAttributeId
	 * @return
	 */
	public String getProductAttributeId() {
		return this.productAttributeId;
	}
	/**
	 * 设置 productUuid
	 * @param productUuid
	 */
	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
	/**
	 * productUuid
	 * @return
	 */
	public String getProductUuid() {
		return this.productUuid;
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
	 * 设置 value
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * value
	 * @return
	 */
	public String getValue() {
		return this.value;
	}
	/**
	 * 设置 unit
	 * @param unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * unit
	 * @return
	 */
	public String getUnit() {
		return this.unit;
	}
	/**
	 * 设置 sort
	 * @param sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * sort
	 * @return
	 */
	public String getSort() {
		return this.sort;
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

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setProductAttribute(ProductAttribute productAttribute){
		if(productAttribute==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.productAttributeId=productAttribute.getProductAttributeId()==null?"":String.valueOf(productAttribute.getProductAttributeId());	
		this.productUuid=productAttribute.getProductUuid()==null?"":productAttribute.getProductUuid();	
		this.name=productAttribute.getName()==null?"":productAttribute.getName();	
		this.value=productAttribute.getValue()==null?"":productAttribute.getValue();	
		this.unit=productAttribute.getUnit()==null?"":productAttribute.getUnit();	
		this.sort=productAttribute.getSort()==null?"":String.valueOf(productAttribute.getSort());	
		this.status=productAttribute.getStatus()==null?"":String.valueOf(productAttribute.getStatus());	
		this.updateByAdm=productAttribute.getUpdateByAdm()==null?"":String.valueOf(productAttribute.getUpdateByAdm());	
		this.updateDate=productAttribute.getUpdateDate()==null?"":df.format(productAttribute.getUpdateDate());	
		this.page=productAttribute.getPage();
	}
  
	public ProductAttribute getProductAttribute(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ProductAttribute productAttribute=new ProductAttribute();
		productAttribute.setProductAttributeId(this.productAttributeId==null||this.productAttributeId.length()==0?0:Integer.parseInt(this.productAttributeId));  
		productAttribute.setProductUuid(this.productUuid==null||this.productUuid.length()==0?"":this.productUuid);  
		productAttribute.setName(this.name==null||this.name.length()==0?"":this.name);  
		productAttribute.setValue(this.value==null||this.value.length()==0?"":this.value);  
		productAttribute.setUnit(this.unit==null||this.unit.length()==0?"":this.unit);  
		productAttribute.setSort(this.sort==null||this.sort.length()==0?0:Integer.parseInt(this.sort));  
		productAttribute.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		productAttribute.setUpdateByAdm(this.updateByAdm==null||this.updateByAdm.length()==0?0:Integer.parseInt(this.updateByAdm));  
		try {
			productAttribute.setUpdateDate(this.updateDate==null||this.updateDate.length()==0?null:df.parse(this.updateDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		productAttribute.setPage(this.page);	
		return productAttribute;
	}
  
	
}

