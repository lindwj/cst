
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
public class StockForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** stockId */
	private String stockId;
	/** showCapacity */
	private String showCapacity;
	/** shopCapacity */
	private String shopCapacity;
	/** productUuid */
	private String productUuid;
	/** shopId */
	private String shopId;
	/** 状态             -1 删除             0 默认 草稿             1 上架             2 下架 */
	private String status;
	/** updateByAdm */
	private String updateByAdm;
	/** updateDate */
	private String updateDate;
	//columns END
	Page page;

	/**
	 * 设置 stockId
	 * @param stockId
	 */
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	/**
	 * stockId
	 * @return
	 */
	public String getStockId() {
		return this.stockId;
	}
	/**
	 * 设置 showCapacity
	 * @param showCapacity
	 */
	public void setShowCapacity(String showCapacity) {
		this.showCapacity = showCapacity;
	}
	/**
	 * showCapacity
	 * @return
	 */
	public String getShowCapacity() {
		return this.showCapacity;
	}
	/**
	 * 设置 shopCapacity
	 * @param shopCapacity
	 */
	public void setShopCapacity(String shopCapacity) {
		this.shopCapacity = shopCapacity;
	}
	/**
	 * shopCapacity
	 * @return
	 */
	public String getShopCapacity() {
		return this.shopCapacity;
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
  
	public void setStock(Stock stock){
		if(stock==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.stockId=stock.getStockId()==null?"":String.valueOf(stock.getStockId());	
		this.showCapacity=stock.getShowCapacity()==null?"":String.valueOf(stock.getShowCapacity());	
		this.shopCapacity=stock.getShopCapacity()==null?"":String.valueOf(stock.getShopCapacity());	
		this.productUuid=stock.getProductUuid()==null?"":stock.getProductUuid();	
		this.shopId=stock.getShopId()==null?"":String.valueOf(stock.getShopId());	
		this.status=stock.getStatus()==null?"":String.valueOf(stock.getStatus());	
		this.updateByAdm=stock.getUpdateByAdm()==null?"":String.valueOf(stock.getUpdateByAdm());	
		this.updateDate=stock.getUpdateDate()==null?"":df.format(stock.getUpdateDate());	
		this.page=stock.getPage();
	}
  
	public Stock getStock(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Stock stock=new Stock();
		stock.setStockId(this.stockId==null||this.stockId.length()==0?0:Integer.parseInt(this.stockId));  
		stock.setShowCapacity(this.showCapacity==null||this.showCapacity.length()==0?0:Integer.parseInt(this.showCapacity));  
		stock.setShopCapacity(this.shopCapacity==null||this.shopCapacity.length()==0?0:Integer.parseInt(this.shopCapacity));  
		stock.setProductUuid(this.productUuid==null||this.productUuid.length()==0?"":this.productUuid);  
		stock.setShopId(this.shopId==null||this.shopId.length()==0?0:Integer.parseInt(this.shopId));  
		stock.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		stock.setUpdateByAdm(this.updateByAdm==null||this.updateByAdm.length()==0?0:Integer.parseInt(this.updateByAdm));  
		try {
			stock.setUpdateDate(this.updateDate==null||this.updateDate.length()==0?null:df.parse(this.updateDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stock.setPage(this.page);	
		return stock;
	}
  
	
}

