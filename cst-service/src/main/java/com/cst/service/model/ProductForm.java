
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
public class ProductForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** productId */
	private String productId;
	/** 名称 */
	private String name;
	/** 价格 */
	private String price;
	/** 原价 */
	private String costPrice;
	/** 图片 */
	private String pic;
	/** 描述 */
	private String description;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private String status;
	/** 编码 */
	private String code;
	/** createByAdm */
	private String createByAdm;
	/** createDate */
	private String createDate;
	/** updateByAdm */
	private String updateByAdm;
	/** updateDate */
	private String updateDate;
	/** uuid 开发  唯一标识，避免暴露 主键 */
	private String productUuid;
	/** 级联parameter             商品所属类型 */
	private String typeId;
	//columns END
	Page page;

	/**
	 * 设置 productId
	 * @param productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * productId
	 * @return
	 */
	public String getProductId() {
		return this.productId;
	}
	/**
	 * 设置 名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 设置 价格
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * 价格
	 * @return
	 */
	public String getPrice() {
		return this.price;
	}
	/**
	 * 设置 原价
	 * @param costPrice
	 */
	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	/**
	 * 原价
	 * @return
	 */
	public String getCostPrice() {
		return this.costPrice;
	}
	/**
	 * 设置 图片
	 * @param pic
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * 图片
	 * @return
	 */
	public String getPic() {
		return this.pic;
	}
	/**
	 * 设置 描述
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 描述
	 * @return
	 */
	public String getDescription() {
		return this.description;
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
	 * 设置 编码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 编码
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
	 * 设置 uuid 开发  唯一标识，避免暴露 主键
	 * @param productUuid
	 */
	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
	/**
	 * uuid 开发  唯一标识，避免暴露 主键
	 * @return
	 */
	public String getProductUuid() {
		return this.productUuid;
	}
	/**
	 * 设置 级联parameter             商品所属类型
	 * @param typeId
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * 级联parameter             商品所属类型
	 * @return
	 */
	public String getTypeId() {
		return this.typeId;
	}

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setProduct(Product product){
		if(product==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.productId=product.getProductId()==null?"":String.valueOf(product.getProductId());	
		this.name=product.getName()==null?"":product.getName();	
		this.price=product.getPrice()==0?"0":String.valueOf(product.getPrice());	
		this.costPrice=product.getCostPrice()==0?"0":String.valueOf(product.getCostPrice());	
		this.pic=product.getPic()==null?"":product.getPic();	
		this.description=product.getDescription()==null?"":product.getDescription();	
		this.status=product.getStatus()==null?"":String.valueOf(product.getStatus());	
		this.code=product.getCode()==null?"":product.getCode();	
		this.createByAdm=product.getCreateByAdm()==null?"":String.valueOf(product.getCreateByAdm());	
		this.createDate=product.getCreateDate()==null?"":df.format(product.getCreateDate());	
		this.updateByAdm=product.getUpdateByAdm()==null?"":String.valueOf(product.getUpdateByAdm());	
		this.updateDate=product.getUpdateDate()==null?"":df.format(product.getUpdateDate());	
		this.productUuid=product.getProductUuid()==null?"":product.getProductUuid();	
		this.typeId=product.getTypeId()==null?"":String.valueOf(product.getTypeId());	
		this.page=product.getPage();
	}
  
	public Product getProduct(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Product product=new Product();
		product.setProductId(this.productId==null||this.productId.length()==0?0:Integer.parseInt(this.productId));  
		product.setName(this.name==null||this.name.length()==0?"":this.name);  
		product.setPrice(this.price==null||this.price.length()==0?0:Double.valueOf(this.price));  
		product.setCostPrice(this.costPrice==null||this.costPrice.length()==0?0:Double.valueOf(this.costPrice));  
		product.setPic(this.pic==null||this.pic.length()==0?"":this.pic);  
		product.setDescription(this.description==null||this.description.length()==0?"":this.description);  
		product.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		product.setCode(this.code==null||this.code.length()==0?"":this.code);  
		product.setCreateByAdm(this.createByAdm==null||this.createByAdm.length()==0?0:Integer.parseInt(this.createByAdm));  
		try {
			product.setCreateDate(this.createDate==null||this.createDate.length()==0?null:df.parse(this.createDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setUpdateByAdm(this.updateByAdm==null||this.updateByAdm.length()==0?0:Integer.parseInt(this.updateByAdm));  
		try {
			product.setUpdateDate(this.updateDate==null||this.updateDate.length()==0?null:df.parse(this.updateDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setProductUuid(this.productUuid==null||this.productUuid.length()==0?"":this.productUuid);  
		product.setTypeId(this.typeId==null||this.typeId.length()==0?0:Integer.parseInt(this.typeId));  
		product.setPage(this.page);	
		return product;
	}
  
	
}

