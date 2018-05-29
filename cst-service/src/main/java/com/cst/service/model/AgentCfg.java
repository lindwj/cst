
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
public class AgentCfg implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** agentCfgId */
	private java.lang.Integer agentCfgId;
	/** 代理划分名称 */
	private java.lang.String agentName;
	/** createDate */
	private java.util.Date createDate;
	/** createByAdm */
	private java.lang.Integer createByAdm;
	/** 状态 */
	private java.lang.String state;
	/** 1 微信代理 2 独家代理 */
	private java.lang.Integer type;
	/** 独家代理人id */
	private java.lang.Integer agentId;
	
	private Integer managerId;
	//columns END
	Page page;

	List<Product> products=new ArrayList<Product>();
	/**
	 * 设置 agentCfgId
	 * @param agentCfgId
	 */
	public void setAgentCfgId(java.lang.Integer agentCfgId) {
		this.agentCfgId = agentCfgId;
	}
	/**
	 * agentCfgId
	 * @return
	 */
	public java.lang.Integer getAgentCfgId() {
		return this.agentCfgId;
	}
	/**
	 * 设置 代理划分名称
	 * @param agentName
	 */
	public void setAgentName(java.lang.String agentName) {
		this.agentName = agentName;
	}
	/**
	 * 代理划分名称
	 * @return
	 */
	public java.lang.String getAgentName() {
		return this.agentName;
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
	 * 设置 状态
	 * @param state
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}
	/**
	 * 状态
	 * @return
	 */
	public java.lang.String getState() {
		return this.state;
	}
	/**
	 * 设置 1 微信代理 2 独家代理
	 * @param type
	 */
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	/**
	 * 1 微信代理 2 独家代理
	 * @return
	 */
	public java.lang.Integer getType() {
		return this.type;
	}
	/**
	 * 设置 独家代理人id
	 * @param agentId
	 */
	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}
	/**
	 * 独家代理人id
	 * @return
	 */
	public java.lang.Integer getAgentId() {
		return this.agentId;
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

