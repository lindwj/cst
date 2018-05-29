
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
public class AgentProductCfg implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** agentProductCfgId */
	private java.lang.Integer agentProductCfgId;
	/** 外键 */
	private java.lang.Integer agentCfgId;
	/** 商品uuid */
	private java.lang.String productUuid;
	/** price */
	private Long price;
	
	private Integer managerId;
	//columns END
	Page page;

	List<Product> products=new ArrayList<Product>();
	/**
	 * 设置 agentProductCfgId
	 * @param agentProductCfgId
	 */
	public void setAgentProductCfgId(java.lang.Integer agentProductCfgId) {
		this.agentProductCfgId = agentProductCfgId;
	}
	/**
	 * agentProductCfgId
	 * @return
	 */
	public java.lang.Integer getAgentProductCfgId() {
		return this.agentProductCfgId;
	}
	/**
	 * 设置 外键
	 * @param agentCfgId
	 */
	public void setAgentCfgId(java.lang.Integer agentCfgId) {
		this.agentCfgId = agentCfgId;
	}
	/**
	 * 外键
	 * @return
	 */
	public java.lang.Integer getAgentCfgId() {
		return this.agentCfgId;
	}
	/**
	 * 设置 商品uuid
	 * @param productUuid
	 */
	public void setProductUuid(java.lang.String productUuid) {
		this.productUuid = productUuid;
	}
	/**
	 * 商品uuid
	 * @return
	 */
	public java.lang.String getProductUuid() {
		return this.productUuid;
	}
	/**
	 * 设置 price
	 * @param price
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	/**
	 * price
	 * @return
	 */
	public Long getPrice() {
		return this.price;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}

