
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
public class StarForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private String id;
	/** eventid */
	private String eventid;
	/** name */
	private String name;
	/** headurl */
	private String headurl;
	/** txt */
	private String txt;
	/** piaoshu */
	private String piaoshu;
	/** redu */
	private String redu;
	/** admid */
	private String admid;
	/** status */
	private String status;
	/** crtTime */
	private String crtTime;
	/** sort */
	private String sort;
	//columns END
	Page page;

	/**
	 * 设置 id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * 设置 eventid
	 * @param eventid
	 */
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	/**
	 * eventid
	 * @return
	 */
	public String getEventid() {
		return this.eventid;
	}
	/**
	 * 设置 name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 设置 headurl
	 * @param headurl
	 */
	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}
	/**
	 * headurl
	 * @return
	 */
	public String getHeadurl() {
		return this.headurl;
	}
	/**
	 * 设置 txt
	 * @param txt
	 */
	public void setTxt(String txt) {
		this.txt = txt;
	}
	/**
	 * txt
	 * @return
	 */
	public String getTxt() {
		return this.txt;
	}
	/**
	 * 设置 piaoshu
	 * @param piaoshu
	 */
	public void setPiaoshu(String piaoshu) {
		this.piaoshu = piaoshu;
	}
	/**
	 * piaoshu
	 * @return
	 */
	public String getPiaoshu() {
		return this.piaoshu;
	}
	/**
	 * 设置 redu
	 * @param redu
	 */
	public void setRedu(String redu) {
		this.redu = redu;
	}
	/**
	 * redu
	 * @return
	 */
	public String getRedu() {
		return this.redu;
	}
	/**
	 * 设置 admid
	 * @param admid
	 */
	public void setAdmid(String admid) {
		this.admid = admid;
	}
	/**
	 * admid
	 * @return
	 */
	public String getAdmid() {
		return this.admid;
	}
	/**
	 * 设置 status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * status
	 * @return
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * 设置 crtTime
	 * @param crtTime
	 */
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}
	/**
	 * crtTime
	 * @return
	 */
	public String getCrtTime() {
		return this.crtTime;
	}
	/**
	 * 设置 sort
	 * @param sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * sort
	 * @return
	 */
	public String getSort() {
		return this.sort;
	}

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setStar(Star star){
		if(star==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.id=star.getId()==null?"":String.valueOf(star.getId());	
		this.eventid=star.getEventid()==null?"":String.valueOf(star.getEventid());	
		this.name=star.getName()==null?"":star.getName();	
		this.headurl=star.getHeadurl()==null?"":star.getHeadurl();	
		this.txt=star.getTxt()==null?"":star.getTxt();	
		this.piaoshu=star.getPiaoshu()==null?"":String.valueOf(star.getPiaoshu());	
		this.redu=star.getRedu()==null?"":String.valueOf(star.getRedu());	
		this.admid=star.getAdmid()==null?"":String.valueOf(star.getAdmid());	
		this.status=star.getStatus()==null?"":String.valueOf(star.getStatus());	
		this.crtTime=star.getCrtTime()==null?"":df.format(star.getCrtTime());	
		this.sort=star.getSort()==null?"":String.valueOf(star.getSort());	
		this.page=star.getPage();
	}
  
	public Star getStar(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Star star=new Star();
		star.setId(this.id==null||this.id.length()==0?0:Integer.parseInt(this.id));  
		star.setEventid(this.eventid==null||this.eventid.length()==0?0:Integer.parseInt(this.eventid));  
		star.setName(this.name==null||this.name.length()==0?"":this.name);  
		star.setHeadurl(this.headurl==null||this.headurl.length()==0?"":this.headurl);  
		star.setTxt(this.txt==null||this.txt.length()==0?"":this.txt);  
		star.setPiaoshu(this.piaoshu==null||this.piaoshu.length()==0?0:Integer.parseInt(this.piaoshu));  
		star.setRedu(this.redu==null||this.redu.length()==0?0:Integer.parseInt(this.redu));  
		star.setAdmid(this.admid==null||this.admid.length()==0?0:Integer.parseInt(this.admid));  
		star.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		try {
			star.setCrtTime(this.crtTime==null||this.crtTime.length()==0?null:df.parse(this.crtTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		star.setSort(this.sort==null||this.sort.length()==0?0:Integer.parseInt(this.sort));  
		star.setPage(this.page);	
		return star;
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

