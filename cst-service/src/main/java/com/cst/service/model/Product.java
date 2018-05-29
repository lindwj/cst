
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
public class Product implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** productId */
	private java.lang.Integer productId;
	/** 名称 */
	private java.lang.String name;
	
	private java.lang.String describes;
	
	private java.lang.String picForAll;
	/** 价格 */
	private double price;
	/** 原价 */
	private double costPrice;
	/** 图片 */
	private java.lang.String pic;
	/** 描述 */
	private java.lang.String description;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private java.lang.Integer status;
	/** 编码 */
	private java.lang.String code;
	/** createByAdm */
	private java.lang.Integer createByAdm;
	/** createDate */
	private java.util.Date createDate;
	/** updateByAdm */
	private java.lang.Integer updateByAdm;
	/** updateDate */
	private java.util.Date updateDate;
	/** uuid 开发  唯一标识，避免暴露 主键 */
	private java.lang.String productUuid;
	/** 级联parameter             商品所属类型 */
	private java.lang.Integer typeId;
	
	private java.lang.String typeIdStr;
	
	private java.lang.String errcode;
	
	private java.lang.String dowhat;
	
	//svc
	private java.lang.String shopName;
	
	private java.lang.Integer showCapacity;
	
	private GoodsAddress goodsAddress;
	
	private java.lang.Integer num;
	
	
	private int capacity;
	
	private int sort;
	
	private Integer managerId;
	/** 许可证号 */
	private java.lang.String license;
	/** 保质期 */
	private java.lang.String releaseDate;
	/** 标准号 */
	private java.lang.String standardNo;
	/** 原产地 */
	private java.lang.String provenance;
	/** 品牌 */
	private java.lang.String brand;
	/** 净含量 */
	private java.lang.String netContent;
	
	/** 条形码 */
	private java.lang.String barcode;
	/** 系列 */
	private java.lang.String series;
	/** 存储方式 */
	private java.lang.String storage;
	
	private java.lang.String keywords;
	
	private java.lang.String descriptionSeo;
	
	List<ProductCfg> productCfgList;
	
	List<Product> products=new ArrayList<Product>();
	
	private String paName;
	
	private String paValue;
	
	private String paUnit;
	
private java.lang.Integer menuId;

	private Integer cartAll;
	
	private String seoName;
	
	private Integer seoTypeId;
	
	private Integer seoStatus;

	public java.lang.Integer getMenuId() {
	return menuId;
}
public void setMenuId(java.lang.Integer menuId) {
	this.menuId = menuId;
}


public java.lang.String getKeywords() {
	return keywords;
}
public void setKeywords(java.lang.String keywords) {
	this.keywords = keywords;
}



public java.lang.String getDescriptionSeo() {
	return descriptionSeo;
}
public void setDescriptionSeo(java.lang.String descriptionSeo) {
	this.descriptionSeo = descriptionSeo;
}
public java.lang.String getPicForAll() {
	return picForAll;
}
public void setPicForAll(java.lang.String picForAll) {
	this.picForAll = picForAll;
}
public java.lang.String getDescribes() {
	return describes;
}
public void setDescribes(java.lang.String describes) {
	this.describes = describes;
}
public List<ProductCfg> getProductCfgList() {
	return productCfgList;
}
public void setProductCfgList(List<ProductCfg> productCfgList) {
	this.productCfgList = productCfgList;
}
public java.lang.String getShopName() {
	return shopName;
}
public void setShopName(java.lang.String shopName) {
	this.shopName = shopName;
}
public java.lang.Integer getShowCapacity() {
	return showCapacity;
}
public void setShowCapacity(java.lang.Integer showCapacity) {
	this.showCapacity = showCapacity;
}
public GoodsAddress getGoodsAddress() {
	return goodsAddress;
}
public void setGoodsAddress(GoodsAddress goodsAddress) {
	this.goodsAddress = goodsAddress;
}
public java.lang.Integer getNum() {
	return num;
}
public void setNum(java.lang.Integer num) {
	this.num = num;
}
public int getSort() {
	return sort;
}
public void setSort(int sort) {
	this.sort = sort;
}
public java.lang.String getBarcode() {
	return barcode;
}
public void setBarcode(java.lang.String barcode) {
	this.barcode = barcode;
}
public java.lang.String getSeries() {
	return series;
}
public void setSeries(java.lang.String series) {
	this.series = series;
}
public java.lang.String getStorage() {
	return storage;
}
public void setStorage(java.lang.String storage) {
	this.storage = storage;
}



private List<ProductAttribute> productAttributeList;
//columns END

public List<ProductAttribute> getProductAttributeList() {
	return productAttributeList;
}
public void setProductAttributeList(List<ProductAttribute> productAttributeList) {
	this.productAttributeList = productAttributeList;
}

private List<ProductPic> productPicList;


	
	public java.lang.String getLicense() {
	return license;
}
public void setLicense(java.lang.String license) {
	this.license = license;
}
public java.lang.String getReleaseDate() {
	return releaseDate;
}
public void setReleaseDate(java.lang.String releaseDate) {
	this.releaseDate = releaseDate;
}
public java.lang.String getStandardNo() {
	return standardNo;
}
public void setStandardNo(java.lang.String standardNo) {
	this.standardNo = standardNo;
}
public java.lang.String getProvenance() {
	return provenance;
}
public void setProvenance(java.lang.String provenance) {
	this.provenance = provenance;
}
public java.lang.String getBrand() {
	return brand;
}
public void setBrand(java.lang.String brand) {
	this.brand = brand;
}
public java.lang.String getNetContent() {
	return netContent;
}
public void setNetContent(java.lang.String netContent) {
	this.netContent = netContent;
}
	public List<ProductPic> getProductPicList() {
	return productPicList;
}
public void setProductPicList(List<ProductPic> productPicList) {
	this.productPicList = productPicList;
}

	//columns END
	Page page;

	
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public java.lang.String getTypeIdStr() {
		return typeIdStr;
	}
	public void setTypeIdStr(java.lang.String typeIdStr) {
		this.typeIdStr = typeIdStr;
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
	 * 设置 productId
	 * @param productId
	 */
	public void setProductId(java.lang.Integer productId) {
		this.productId = productId;
	}
	/**
	 * productId
	 * @return
	 */
	public java.lang.Integer getProductId() {
		return this.productId;
	}
	/**
	 * 设置 名称
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * 名称
	 * @return
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * 设置 价格
	 * @param price
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	/**
	 * 设置 图片
	 * @param pic
	 */
	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}
	/**
	 * 图片
	 * @return
	 */
	public java.lang.String getPic() {
		return this.pic;
	}
	/**
	 * 设置 描述
	 * @param description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	/**
	 * 描述
	 * @return
	 */
	public java.lang.String getDescription() {
		return this.description;
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
	 * 设置 编码
	 * @param code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * 编码
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
	 * 设置 uuid 开发  唯一标识，避免暴露 主键
	 * @param productUuid
	 */
	public void setProductUuid(java.lang.String productUuid) {
		this.productUuid = productUuid;
	}
	/**
	 * uuid 开发  唯一标识，避免暴露 主键
	 * @return
	 */
	public java.lang.String getProductUuid() {
		return this.productUuid;
	}
	/**
	 * 设置 级联parameter             商品所属类型
	 * @param typeId
	 */
	public void setTypeId(java.lang.Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * 级联parameter             商品所属类型
	 * @return
	 */
	public java.lang.Integer getTypeId() {
		return this.typeId;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getPaName() {
		return paName;
	}
	public void setPaName(String paName) {
		this.paName = paName;
	}
	public String getPaValue() {
		return paValue;
	}
	public void setPaValue(String paValue) {
		this.paValue = paValue;
	}
	public String getPaUnit() {
		return paUnit;
	}
	public void setPaUnit(String paUnit) {
		this.paUnit = paUnit;
	}
	public Integer getCartAll() {
		return cartAll;
	}
	public void setCartAll(Integer cartAll) {
		this.cartAll = cartAll;
	}
	public String getSeoName() {
		return seoName;
	}
	public void setSeoName(String seoName) {
		this.seoName = seoName;
	}
	public Integer getSeoTypeId() {
		return seoTypeId;
	}
	public void setSeoTypeId(Integer seoTypeId) {
		this.seoTypeId = seoTypeId;
	}
	public Integer getSeoStatus() {
		return seoStatus;
	}
	public void setSeoStatus(Integer seoStatus) {
		this.seoStatus = seoStatus;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
}

