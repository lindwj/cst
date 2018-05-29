
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
public class IndexBannerForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** 主键 */
	private String indexBannerId;
	/** 参数类型名称 */
	private String bannerUrl;
	/** 状态             0 默认             -1 删除 */
	private String state;
	/** sort */
	private String sort;
	/** createByAdm */
	private String createByAdm;
	/** createDate */
	private String createDate;
	/** 商品链接 */
	private String productUrl;
	//columns END
	Page page;

	/**
	 * 设置 主键
	 * @param indexBannerId
	 */
	public void setIndexBannerId(String indexBannerId) {
		this.indexBannerId = indexBannerId;
	}
	/**
	 * 主键
	 * @return
	 */
	public String getIndexBannerId() {
		return this.indexBannerId;
	}
	/**
	 * 设置 参数类型名称
	 * @param bannerUrl
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	/**
	 * 参数类型名称
	 * @return
	 */
	public String getBannerUrl() {
		return this.bannerUrl;
	}
	/**
	 * 设置 状态             0 默认             -1 删除
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 状态             0 默认             -1 删除
	 * @return
	 */
	public String getState() {
		return this.state;
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
	 * 设置 商品链接
	 * @param productUrl
	 */
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	/**
	 * 商品链接
	 * @return
	 */
	public String getProductUrl() {
		return this.productUrl;
	}

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setIndexBanner(IndexBanner indexBanner){
		if(indexBanner==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.indexBannerId=indexBanner.getIndexBannerId()==null?"":String.valueOf(indexBanner.getIndexBannerId());	
		this.bannerUrl=indexBanner.getBannerUrl()==null?"":indexBanner.getBannerUrl();	
		this.state=indexBanner.getState()==null?"":String.valueOf(indexBanner.getState());	
		this.sort=indexBanner.getSort()==null?"":String.valueOf(indexBanner.getSort());	
		this.createByAdm=indexBanner.getCreateByAdm()==null?"":String.valueOf(indexBanner.getCreateByAdm());	
		this.createDate=indexBanner.getCreateDate()==null?"":df.format(indexBanner.getCreateDate());	
		this.productUrl=indexBanner.getProductUrl()==null?"":indexBanner.getProductUrl();	
		this.page=indexBanner.getPage();
	}
  
	public IndexBanner getIndexBanner(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		IndexBanner indexBanner=new IndexBanner();
		indexBanner.setIndexBannerId(this.indexBannerId==null||this.indexBannerId.length()==0?0:Integer.parseInt(this.indexBannerId));  
		indexBanner.setBannerUrl(this.bannerUrl==null||this.bannerUrl.length()==0?"":this.bannerUrl);  
		indexBanner.setState(this.state==null||this.state.length()==0?0:Integer.parseInt(this.state));  
		indexBanner.setSort(this.sort==null||this.sort.length()==0?0:Integer.parseInt(this.sort));  
		indexBanner.setCreateByAdm(this.createByAdm==null||this.createByAdm.length()==0?0:Integer.parseInt(this.createByAdm));  
		try {
			indexBanner.setCreateDate(this.createDate==null||this.createDate.length()==0?null:df.parse(this.createDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		indexBanner.setProductUrl(this.productUrl==null||this.productUrl.length()==0?"":this.productUrl);  
		indexBanner.setPage(this.page);	
		return indexBanner;
	}
  
	
}

