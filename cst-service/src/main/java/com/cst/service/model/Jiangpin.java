
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
public class Jiangpin implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private java.lang.Integer id;
	/** eventid */
	private java.lang.Integer eventid;
	/** headurl */
	private java.lang.String headurl;
	/** name */
	private java.lang.String name;
	/** des */
	private java.lang.String des;
	/** bigurl */
	private java.lang.String bigurl;
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
	 * 设置 bigurl
	 * @param bigurl
	 */
	public void setBigurl(java.lang.String bigurl) {
		this.bigurl = bigurl;
	}
	/**
	 * bigurl
	 * @return
	 */
	public java.lang.String getBigurl() {
		return this.bigurl;
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
			.append("Headurl",getHeadurl())
			.append("Name",getName())
			.append("Des",getDes())
			.append("Bigurl",getBigurl())
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
		if(obj instanceof Jiangpin == false) return false;
		if(this == obj) return true;
		Jiangpin other = (Jiangpin)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

