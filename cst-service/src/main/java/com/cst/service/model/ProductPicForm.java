
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
public class ProductPicForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** productPicId */
	private String productPicId;
	/** productUuid */
	private String productUuid;
	/** picUrl */
	private String picUrl;
	/** sort */
	private String sort;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private String status;
	//columns END
	Page page;

	/**
	 * 设置 productPicId
	 * @param productPicId
	 */
	public void setProductPicId(String productPicId) {
		this.productPicId = productPicId;
	}
	/**
	 * productPicId
	 * @return
	 */
	public String getProductPicId() {
		return this.productPicId;
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
	 * 设置 picUrl
	 * @param picUrl
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * picUrl
	 * @return
	 */
	public String getPicUrl() {
		return this.picUrl;
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

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setProductPic(ProductPic productPic){
		if(productPic==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.productPicId=productPic.getProductPicId()==null?"":String.valueOf(productPic.getProductPicId());	
		this.productUuid=productPic.getProductUuid()==null?"":productPic.getProductUuid();	
		this.picUrl=productPic.getPicUrl()==null?"":productPic.getPicUrl();	
		this.sort=productPic.getSort()==null?"":String.valueOf(productPic.getSort());	
		this.status=productPic.getStatus()==null?"":String.valueOf(productPic.getStatus());	
		this.page=productPic.getPage();
	}
  
	public ProductPic getProductPic(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ProductPic productPic=new ProductPic();
		productPic.setProductPicId(this.productPicId==null||this.productPicId.length()==0?0:Integer.parseInt(this.productPicId));  
		productPic.setProductUuid(this.productUuid==null||this.productUuid.length()==0?"":this.productUuid);  
		productPic.setPicUrl(this.picUrl==null||this.picUrl.length()==0?"":this.picUrl);  
		productPic.setSort(this.sort==null||this.sort.length()==0?0:Integer.parseInt(this.sort));  
		productPic.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		productPic.setPage(this.page);	
		return productPic;
	}
  
	
}

