
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
public class Shuidian implements Serializable{	

	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** shuidianId */
	private java.lang.Integer shuidianId;
	private java.lang.Integer menuId;
	private java.lang.String errcode;
	private java.lang.String dowhat;
	/** fengsid */
	private java.lang.Integer fengsid;
	/** fengsname */
	private java.lang.String fengsname;
	/** xmid */
	private java.lang.Integer xmid;
	/** xmname */
	private java.lang.String xmname;
	/** nian */
	private java.lang.Integer nian;
	/** yue */
	private java.lang.Integer yue;
	private Shuidian shuidian;
	/** shuifei */
	private double shuifei;
	/** shuiliang */
	private double shuiliang;
	/** dianfei */
	private double dianfei;
	/** dianliang */
	private double dianliang;
	/** shuidianfei */
	private double shuidianfei;
	/** note */
	private java.lang.String note;
	/** createByAdm */
	private java.lang.Integer createByAdm;
	/** createTime */
	private java.util.Date createTime;
	//columns END
	Page page;

	public Shuidian getShuidian() {
		return shuidian;
	}
	public void setShuidian(Shuidian shuidian) {
		this.shuidian = shuidian;
	}
	/**
	 * 设置 shuidianId
	 * @param shuidianId
	 */
	public void setShuidianId(java.lang.Integer shuidianId) {
		this.shuidianId = shuidianId;
	}
	/**
	 * shuidianId
	 * @return
	 */
	public java.lang.Integer getShuidianId() {
		return this.shuidianId;
	}
	/**
	 * 设置 fengsid
	 * @param fengsid
	 */
	public void setFengsid(java.lang.Integer fengsid) {
		this.fengsid = fengsid;
	}
	/**
	 * fengsid
	 * @return
	 */
	public java.lang.Integer getFengsid() {
		return this.fengsid;
	}
	/**
	 * 设置 fengsname
	 * @param fengsname
	 */
	public void setFengsname(java.lang.String fengsname) {
		this.fengsname = fengsname;
	}
	/**
	 * fengsname
	 * @return
	 */
	public java.lang.String getFengsname() {
		return this.fengsname;
	}
	/**
	 * 设置 xmid
	 * @param xmid
	 */
	public void setXmid(java.lang.Integer xmid) {
		this.xmid = xmid;
	}
	/**
	 * xmid
	 * @return
	 */
	public java.lang.Integer getXmid() {
		return this.xmid;
	}
	/**
	 * 设置 xmname
	 * @param xmname
	 */
	public void setXmname(java.lang.String xmname) {
		this.xmname = xmname;
	}
	/**
	 * xmname
	 * @return
	 */
	public java.lang.String getXmname() {
		return this.xmname;
	}
	
	
	public java.lang.Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(java.lang.Integer menuId) {
		this.menuId = menuId;
	}
	public java.lang.String getErrcode() {
		return errcode;
	}
	public void setErrcode(java.lang.String errcode) {
		this.errcode = errcode;
	}
	/**
	 * 设置 nian
	 * @param nian
	 */
	public void setNian(java.lang.Integer nian) {
		this.nian = nian;
	}
	/**
	 * nian
	 * @return
	 */
	public java.lang.Integer getNian() {
		return this.nian;
	}
	/**
	 * 设置 yue
	 * @param yue
	 */
	public void setYue(java.lang.Integer yue) {
		this.yue = yue;
	}
	/**
	 * yue
	 * @return
	 */
	public java.lang.Integer getYue() {
		return this.yue;
	}
	/**
	 * 设置 shuifei
	 * @param shuifei
	 */
	public void setShuifei(double shuifei) {
		this.shuifei = shuifei;
	}
	/**
	 * shuifei
	 * @return
	 */
	public double getShuifei() {
		return this.shuifei;
	}
	/**
	 * 设置 shuiliang
	 * @param shuiliang
	 */
	public void setShuiliang(double shuiliang) {
		this.shuiliang = shuiliang;
	}
	/**
	 * shuiliang
	 * @return
	 */
	public double getShuiliang() {
		return this.shuiliang;
	}
	/**
	 * 设置 dianfei
	 * @param dianfei
	 */
	public void setDianfei(double dianfei) {
		this.dianfei = dianfei;
	}
	
	
	
	public java.lang.String getDowhat() {
		return dowhat;
	}
	public void setDowhat(java.lang.String dowhat) {
		this.dowhat = dowhat;
	}
	/**
	 * dianfei
	 * @return
	 */
	public double getDianfei() {
		return this.dianfei;
	}
	/**
	 * 设置 dianliang
	 * @param dianliang
	 */
	public void setDianliang(double dianliang) {
		this.dianliang = dianliang;
	}
	/**
	 * dianliang
	 * @return
	 */
	public double getDianliang() {
		return this.dianliang;
	}
	/**
	 * 设置 shuidianfei
	 * @param shuidianfei
	 */
	public void setShuidianfei(double shuidianfei) {
		this.shuidianfei = shuidianfei;
	}
	/**
	 * shuidianfei
	 * @return
	 */
	public double getShuidianfei() {
		return this.shuidianfei;
	}
	/**
	 * 设置 note
	 * @param note
	 */
	public void setNote(java.lang.String note) {
		this.note = note;
	}
	/**
	 * note
	 * @return
	 */
	public java.lang.String getNote() {
		return this.note;
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
	 * 设置 createTime
	 * @param createTime
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * createTime
	 * @return
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

  public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ShuidianId",getShuidianId())
			.append("Fengsid",getFengsid())
			.append("Fengsname",getFengsname())
			.append("Xmid",getXmid())
			.append("Xmname",getXmname())
			.append("Nian",getNian())
			.append("Yue",getYue())
			.append("Shuifei",getShuifei())
			.append("Shuiliang",getShuiliang())
			.append("Dianfei",getDianfei())
			.append("Dianliang",getDianliang())
			.append("Shuidianfei",getShuidianfei())
			.append("Note",getNote())
			.append("CreateByAdm",getCreateByAdm())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getShuidianId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Shuidian == false) return false;
		if(this == obj) return true;
		Shuidian other = (Shuidian)obj;
		return new EqualsBuilder()
			.append(getShuidianId(),other.getShuidianId())
			.isEquals();
	}
}

