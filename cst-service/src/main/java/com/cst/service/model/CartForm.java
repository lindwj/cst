
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
public class CartForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** 主键 */
	private String cartId;
	/** 参数类型名称 */
	private String productUuid;
	/** 状态 */
	private String state;
	/** num */
	private String num;
	/** 消费者 */
	private String createByUser;
	/** createDate */
	private String createDate;
	/** updateDate */
	private String updateDate;
	//columns END
	Page page;

	/**
	 * 设置 主键
	 * @param cartId
	 */
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	/**
	 * 主键
	 * @return
	 */
	public String getCartId() {
		return this.cartId;
	}
	/**
	 * 设置 参数类型名称
	 * @param productUuid
	 */
	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
	/**
	 * 参数类型名称
	 * @return
	 */
	public String getProductUuid() {
		return this.productUuid;
	}
	/**
	 * 设置 状态
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 状态
	 * @return
	 */
	public String getState() {
		return this.state;
	}
	/**
	 * 设置 num
	 * @param num
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * num
	 * @return
	 */
	public String getNum() {
		return this.num;
	}
	/**
	 * 设置 消费者
	 * @param createByUser
	 */
	public void setCreateByUser(String createByUser) {
		this.createByUser = createByUser;
	}
	/**
	 * 消费者
	 * @return
	 */
	public String getCreateByUser() {
		return this.createByUser;
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
  
	public void setCart(Cart cart){
		if(cart==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.cartId=cart.getCartId()==null?"":String.valueOf(cart.getCartId());	
		this.productUuid=cart.getProductUuid()==null?"":cart.getProductUuid();	
		this.state=cart.getState()==null?"":String.valueOf(cart.getState());	
		this.num=cart.getNum()==null?"":String.valueOf(cart.getNum());	
		this.createByUser=cart.getCreateByUser()==null?"":String.valueOf(cart.getCreateByUser());	
		this.createDate=cart.getCreateDate()==null?"":df.format(cart.getCreateDate());	
		this.updateDate=cart.getUpdateDate()==null?"":df.format(cart.getUpdateDate());	
		this.page=cart.getPage();
	}
  
	public Cart getCart(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Cart cart=new Cart();
		cart.setCartId(this.cartId==null||this.cartId.length()==0?0:Integer.parseInt(this.cartId));  
		cart.setProductUuid(this.productUuid==null||this.productUuid.length()==0?"":this.productUuid);  
		cart.setState(this.state==null||this.state.length()==0?0:Integer.parseInt(this.state));  
		cart.setNum(this.num==null||this.num.length()==0?0:Integer.parseInt(this.num));  
		cart.setCreateByUser(this.createByUser==null||this.createByUser.length()==0?0:Integer.parseInt(this.createByUser));  
		try {
			cart.setCreateDate(this.createDate==null||this.createDate.length()==0?null:df.parse(this.createDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			cart.setUpdateDate(this.updateDate==null||this.updateDate.length()==0?null:df.parse(this.updateDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cart.setPage(this.page);	
		return cart;
	}
  
	
}

