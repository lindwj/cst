
package com.cst.service.model;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import com.cst.service.common.Page;
import java.text.SimpleDateFormat;

/**
 * @author lind
 *
 */
public class Shualiwu implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private java.lang.Integer id;
	/** eventid */
	private java.lang.Integer eventid;
	/** openid */
	private java.lang.String openid;
	/** name */
	private java.lang.String name;
	/** headurl */
	private java.lang.String headurl;
	/** starId */
	private java.lang.Integer starId;
	/** liwuId */
	private java.lang.Integer liwuId;
	/** liwuName */
	private java.lang.String liwuName;
	/** crtTime */
	private java.util.Date crtTime;
	private String crtTimestr;
	/** 1 投票 */
	private java.lang.Integer type;
	//columns END
	Page page;

	
	
	public String getCrtTimestr() {
		return crtTimestr;
	}
	public void setCrtTimestr(String crtTimestr) {
		this.crtTimestr = crtTimestr;
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
	 * 设置 openid
	 * @param openid
	 */
	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}
	/**
	 * openid
	 * @return
	 */
	public java.lang.String getOpenid() {
		return this.openid;
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
	 * 设置 starId
	 * @param starId
	 */
	public void setStarId(java.lang.Integer starId) {
		this.starId = starId;
	}
	/**
	 * starId
	 * @return
	 */
	public java.lang.Integer getStarId() {
		return this.starId;
	}
	/**
	 * 设置 liwuId
	 * @param liwuId
	 */
	public void setLiwuId(java.lang.Integer liwuId) {
		this.liwuId = liwuId;
	}
	/**
	 * liwuId
	 * @return
	 */
	public java.lang.Integer getLiwuId() {
		return this.liwuId;
	}
	/**
	 * 设置 liwuName
	 * @param liwuName
	 */
	public void setLiwuName(java.lang.String liwuName) {
		this.liwuName = liwuName;
	}
	/**
	 * liwuName
	 * @return
	 */
	public java.lang.String getLiwuName() {
		return this.liwuName;
	}
	/**
	 * 设置 crtTime
	 * @param crtTime
	 */
	public void setCrtTime(java.util.Date crtTime) {
		this.crtTime = crtTime;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setCrtTimestr(crtTime==null?"":df.format(crtTime));
	}
	/**
	 * crtTime
	 * @return
	 */
	public java.util.Date getCrtTime() {
		return this.crtTime;
	}
	/**
	 * 设置 1 投票
	 * @param type
	 */
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	/**
	 * 1 投票
	 * @return
	 */
	public java.lang.Integer getType() {
		return this.type;
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
			.append("Openid",getOpenid())
			.append("Name",getName())
			.append("Headurl",getHeadurl())
			.append("StarId",getStarId())
			.append("LiwuId",getLiwuId())
			.append("LiwuName",getLiwuName())
			.append("CrtTime",getCrtTime())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Shualiwu == false) return false;
		if(this == obj) return true;
		Shualiwu other = (Shualiwu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

