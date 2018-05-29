
package com.cst.service.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class ShuidianForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** shuidianId */
	private String shuidianId;
	/** fengsid */
	private String fengsid;
	/** fengsname */
	private String fengsname;
	/** xmid */
	private String xmid;
	/** xmname */
	private String xmname;
	/** nian */
	private String nian;
	/** yue */
	private String yue;
	/** shuifei */
	private String shuifei;
	/** shuiliang */
	private String shuiliang;
	/** dianfei */
	private String dianfei;
	/** dianliang */
	private String dianliang;
	/** shuidianfei */
	private String shuidianfei;
	/** note */
	private String note;
	//columns END
	Page page;

	/**
	 * 设置 shuidianId
	 * @param shuidianId
	 */
	public void setShuidianId(String shuidianId) {
		this.shuidianId = shuidianId;
	}
	/**
	 * shuidianId
	 * @return
	 */
	public String getShuidianId() {
		return this.shuidianId;
	}
	/**
	 * 设置 fengsid
	 * @param fengsid
	 */
	public void setFengsid(String fengsid) {
		this.fengsid = fengsid;
	}
	/**
	 * fengsid
	 * @return
	 */
	public String getFengsid() {
		return this.fengsid;
	}
	/**
	 * 设置 fengsname
	 * @param fengsname
	 */
	public void setFengsname(String fengsname) {
		this.fengsname = fengsname;
	}
	/**
	 * fengsname
	 * @return
	 */
	public String getFengsname() {
		return this.fengsname;
	}
	/**
	 * 设置 xmid
	 * @param xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * xmid
	 * @return
	 */
	public String getXmid() {
		return this.xmid;
	}
	/**
	 * 设置 xmname
	 * @param xmname
	 */
	public void setXmname(String xmname) {
		this.xmname = xmname;
	}
	/**
	 * xmname
	 * @return
	 */
	public String getXmname() {
		return this.xmname;
	}
	/**
	 * 设置 nian
	 * @param nian
	 */
	public void setNian(String nian) {
		this.nian = nian;
	}
	/**
	 * nian
	 * @return
	 */
	public String getNian() {
		return this.nian;
	}
	/**
	 * 设置 yue
	 * @param yue
	 */
	public void setYue(String yue) {
		this.yue = yue;
	}
	/**
	 * yue
	 * @return
	 */
	public String getYue() {
		return this.yue;
	}
	/**
	 * 设置 shuifei
	 * @param shuifei
	 */
	public void setShuifei(String shuifei) {
		this.shuifei = shuifei;
	}
	/**
	 * shuifei
	 * @return
	 */
	public String getShuifei() {
		return this.shuifei;
	}
	/**
	 * 设置 shuiliang
	 * @param shuiliang
	 */
	public void setShuiliang(String shuiliang) {
		this.shuiliang = shuiliang;
	}
	/**
	 * shuiliang
	 * @return
	 */
	public String getShuiliang() {
		return this.shuiliang;
	}
	/**
	 * 设置 dianfei
	 * @param dianfei
	 */
	public void setDianfei(String dianfei) {
		this.dianfei = dianfei;
	}
	/**
	 * dianfei
	 * @return
	 */
	public String getDianfei() {
		return this.dianfei;
	}
	/**
	 * 设置 dianliang
	 * @param dianliang
	 */
	public void setDianliang(String dianliang) {
		this.dianliang = dianliang;
	}
	/**
	 * dianliang
	 * @return
	 */
	public String getDianliang() {
		return this.dianliang;
	}
	/**
	 * 设置 shuidianfei
	 * @param shuidianfei
	 */
	public void setShuidianfei(String shuidianfei) {
		this.shuidianfei = shuidianfei;
	}
	/**
	 * shuidianfei
	 * @return
	 */
	public String getShuidianfei() {
		return this.shuidianfei;
	}
	/**
	 * 设置 note
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * note
	 * @return
	 */
	public String getNote() {
		return this.note;
	}

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setShuidian(Shuidian shuidian){
		if(shuidian==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.shuidianId=shuidian.getShuidianId()==null?"":String.valueOf(shuidian.getShuidianId());	
		this.fengsid=shuidian.getFengsid()==null?"":String.valueOf(shuidian.getFengsid());	
		this.fengsname=shuidian.getFengsname()==null?"":shuidian.getFengsname();	
		this.xmid=shuidian.getXmid()==null?"":String.valueOf(shuidian.getXmid());	
		this.xmname=shuidian.getXmname()==null?"":shuidian.getXmname();	
		this.nian=shuidian.getNian()==null?"":String.valueOf(shuidian.getNian());	
		this.yue=shuidian.getYue()==null?"":String.valueOf(shuidian.getYue());	
//		this.shuifei=shuidian.getShuifei()==null?"":shuidian.getShuifei();	
//		this.shuiliang=shuidian.getShuiliang()==null?"":shuidian.getShuiliang();	
//		this.dianfei=shuidian.getDianfei()==null?"":shuidian.getDianfei();	
//		this.dianliang=shuidian.getDianliang()==null?"":shuidian.getDianliang();	
//		this.shuidianfei=shuidian.getShuidianfei()==null?"":shuidian.getShuidianfei();	
		this.note=shuidian.getNote()==null?"":shuidian.getNote();	
		this.page=shuidian.getPage();
	}
  
	public Shuidian getShuidian(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Shuidian shuidian=new Shuidian();
		shuidian.setShuidianId(this.shuidianId==null||this.shuidianId.length()==0?0:Integer.parseInt(this.shuidianId));  
		shuidian.setFengsid(this.fengsid==null||this.fengsid.length()==0?0:Integer.parseInt(this.fengsid));  
		shuidian.setFengsname(this.fengsname==null||this.fengsname.length()==0?"":this.fengsname);  
		shuidian.setXmid(this.xmid==null||this.xmid.length()==0?0:Integer.parseInt(this.xmid));  
		shuidian.setXmname(this.xmname==null||this.xmname.length()==0?"":this.xmname);  
		shuidian.setNian(this.nian==null||this.nian.length()==0?0:Integer.parseInt(this.nian));  
		shuidian.setYue(this.yue==null||this.yue.length()==0?0:Integer.parseInt(this.yue));  
//		shuidian.setShuifei(this.shuifei==null||this.shuifei.length()==0?"":this.shuifei);  
//		shuidian.setShuiliang(this.shuiliang==null||this.shuiliang.length()==0?"":this.shuiliang);  
//		shuidian.setDianfei(this.dianfei==null||this.dianfei.length()==0?"":this.dianfei);  
//		shuidian.setDianliang(this.dianliang==null||this.dianliang.length()==0?"":this.dianliang);  
//		shuidian.setShuidianfei(this.shuidianfei==null||this.shuidianfei.length()==0?"":this.shuidianfei);  
		shuidian.setNote(this.note==null||this.note.length()==0?"":this.note);  
		shuidian.setPage(this.page);	
		return shuidian;
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

