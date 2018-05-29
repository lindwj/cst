
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
public class Star implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private java.lang.Integer id;
	/** eventid */
	private java.lang.Integer eventid;
	/** name */
	private java.lang.String name;
	
	private java.lang.String code;
	/** headurl */
	private java.lang.String headurl;
	/** txt */
	private java.lang.String txt;
	/** piaoshu */
	private java.lang.Integer piaoshu;
	/** redu */
	private java.lang.Integer redu;
	/** admid */
	private java.lang.Integer admid;
	/** status */
	private java.lang.Integer status;
	/** crtTime */
	private java.util.Date crtTime;
	/** sort */
	private java.lang.Integer sort;
	
	private String picName;
	//columns END
	Page page;
	
	

	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
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
	 * 设置 piaoshu
	 * @param piaoshu
	 */
	public void setPiaoshu(java.lang.Integer piaoshu) {
		this.piaoshu = piaoshu;
	}
	/**
	 * piaoshu
	 * @return
	 */
	public java.lang.Integer getPiaoshu() {
		return this.piaoshu;
	}
	/**
	 * 设置 redu
	 * @param redu
	 */
	public void setRedu(java.lang.Integer redu) {
		this.redu = redu;
	}
	/**
	 * redu
	 * @return
	 */
	public java.lang.Integer getRedu() {
		return this.redu;
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
	/**
	 * 设置 sort
	 * @param sort
	 */
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}
	/**
	 * sort
	 * @return
	 */
	public java.lang.Integer getSort() {
		return this.sort;
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
			.append("Headurl",getHeadurl())
			.append("Txt",getTxt())
			.append("Piaoshu",getPiaoshu())
			.append("Redu",getRedu())
			.append("Admid",getAdmid())
			.append("Status",getStatus())
			.append("CrtTime",getCrtTime())
			.append("Sort",getSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Star == false) return false;
		if(this == obj) return true;
		Star other = (Star)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

