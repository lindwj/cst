
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
public class Event implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private java.lang.Integer id;
	/** headtxt */
	private java.lang.String headtxt;
	/** banner */
	private java.lang.String banner;
	/** endTime */
	private java.util.Date endTime;
	/** des */
	private java.lang.String des;
	/** admid */
	private java.lang.Integer admid;
	/** status */
	private java.lang.Integer status;
	/** crtTime */
	private java.util.Date crtTime;
	
	private java.lang.String bgcolor;
	//columns END
	Page page;

	public java.lang.String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(java.lang.String bgcolor) {
		this.bgcolor = bgcolor;
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
	 * 设置 headtxt
	 * @param headtxt
	 */
	public void setHeadtxt(java.lang.String headtxt) {
		this.headtxt = headtxt;
	}
	/**
	 * headtxt
	 * @return
	 */
	public java.lang.String getHeadtxt() {
		return this.headtxt;
	}
	/**
	 * 设置 banner
	 * @param banner
	 */
	public void setBanner(java.lang.String banner) {
		this.banner = banner;
	}
	/**
	 * banner
	 * @return
	 */
	public java.lang.String getBanner() {
		return this.banner;
	}
	/**
	 * 设置 endTime
	 * @param endTime
	 */
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * endTime
	 * @return
	 */
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	/**
	 * 设置 des
	 * @param des
	 */
	public void setDes(java.lang.String des) {
		this.des = des;
	}
	/**
	 * des
	 * @return
	 */
	public java.lang.String getDes() {
		return this.des;
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
			.append("Headtxt",getHeadtxt())
			.append("Banner",getBanner())
			.append("EndTime",getEndTime())
			.append("Des",getDes())
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
		if(obj instanceof Event == false) return false;
		if(this == obj) return true;
		Event other = (Event)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

