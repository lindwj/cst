
package com.cst.service.model;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class Liwu implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private java.lang.Integer id;
	/** eventid */
	private java.lang.Integer eventid;
	/** name */
	private java.lang.String name;
	/** txt */
	private java.lang.String txt;
	/** headurl */
	private java.lang.String headurl;
	/** price */
	private Long price;
	/** admid */
	private java.lang.Integer admid;
	/** status */
	private java.lang.Integer status;
	/** crtTime */
	private java.util.Date crtTime;
	//columns END
	Page page;

	/**
	 * 设置 id
	 * @param id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	/**
	 * id
	 * @return
	 */
	public java.lang.Integer getId() {
		return this.id;
	}
	/**
	 * 设置 eventid
	 * @param eventid
	 */
	public void setEventid(java.lang.Integer eventid) {
		this.eventid = eventid;
	}
	/**
	 * eventid
	 * @return
	 */
	public java.lang.Integer getEventid() {
		return this.eventid;
	}
	/**
	 * 设置 name
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * name
	 * @return
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * 设置 txt
	 * @param txt
	 */
	public void setTxt(java.lang.String txt) {
		this.txt = txt;
	}
	/**
	 * txt
	 * @return
	 */
	public java.lang.String getTxt() {
		return this.txt;
	}
	/**
	 * 设置 headurl
	 * @param headurl
	 */
	public void setHeadurl(java.lang.String headurl) {
		this.headurl = headurl;
	}
	/**
	 * headurl
	 * @return
	 */
	public java.lang.String getHeadurl() {
		return this.headurl;
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
	/**
	 * 设置 admid
	 * @param admid
	 */
	public void setAdmid(java.lang.Integer admid) {
		this.admid = admid;
	}
	/**
	 * admid
	 * @return
	 */
	public java.lang.Integer getAdmid() {
		return this.admid;
	}
	/**
	 * 设置 status
	 * @param status
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	/**
	 * status
	 * @return
	 */
	public java.lang.Integer getStatus() {
		return this.status;
	}
	/**
	 * 设置 crtTime
	 * @param crtTime
	 */
	public void setCrtTime(java.util.Date crtTime) {
		this.crtTime = crtTime;
	}
	/**
	 * crtTime
	 * @return
	 */
	public java.util.Date getCrtTime() {
		return this.crtTime;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Eventid",getEventid())
			.append("Name",getName())
			.append("Txt",getTxt())
			.append("Headurl",getHeadurl())
			.append("Price",getPrice())
			.append("Admid",getAdmid())
			.append("Status",getStatus())
			.append("CrtTime",getCrtTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Liwu == false) return false;
		if(this == obj) return true;
		Liwu other = (Liwu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

