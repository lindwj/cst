
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
public class EventForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private String id;
	/** headtxt */
	private String headtxt;
	/** banner */
	private String banner;
	/** endTime */
	private String endTime;
	/** des */
	private String des;
	/** admid */
	private String admid;
	/** status */
	private String status;
	/** crtTime */
	private String crtTime;
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
	 * 设置 headtxt
	 * @param headtxt
	 */
	public void setHeadtxt(String headtxt) {
		this.headtxt = headtxt;
	}
	/**
	 * headtxt
	 * @return
	 */
	public String getHeadtxt() {
		return this.headtxt;
	}
	/**
	 * 设置 banner
	 * @param banner
	 */
	public void setBanner(String banner) {
		this.banner = banner;
	}
	/**
	 * banner
	 * @return
	 */
	public String getBanner() {
		return this.banner;
	}
	/**
	 * 设置 endTime
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * endTime
	 * @return
	 */
	public String getEndTime() {
		return this.endTime;
	}
	/**
	 * 设置 des
	 * @param des
	 */
	public void setDes(String des) {
		this.des = des;
	}
	/**
	 * des
	 * @return
	 */
	public String getDes() {
		return this.des;
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

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setEvent(Event event){
		if(event==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.id=event.getId()==null?"":String.valueOf(event.getId());	
		this.headtxt=event.getHeadtxt()==null?"":event.getHeadtxt();	
		this.banner=event.getBanner()==null?"":event.getBanner();	
		this.endTime=event.getEndTime()==null?"":df.format(event.getEndTime());	
		this.des=event.getDes()==null?"":event.getDes();	
		this.admid=event.getAdmid()==null?"":String.valueOf(event.getAdmid());	
		this.status=event.getStatus()==null?"":String.valueOf(event.getStatus());	
		this.crtTime=event.getCrtTime()==null?"":df.format(event.getCrtTime());	
		this.page=event.getPage();
	}
  
	public Event getEvent(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Event event=new Event();
		event.setId(this.id==null||this.id.length()==0?0:Integer.parseInt(this.id));  
		event.setHeadtxt(this.headtxt==null||this.headtxt.length()==0?"":this.headtxt);  
		event.setBanner(this.banner==null||this.banner.length()==0?"":this.banner);  
		try {
			event.setEndTime(this.endTime==null||this.endTime.length()==0?null:df.parse(this.endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		event.setDes(this.des==null||this.des.length()==0?"":this.des);  
		event.setAdmid(this.admid==null||this.admid.length()==0?0:Integer.parseInt(this.admid));  
		event.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		try {
			event.setCrtTime(this.crtTime==null||this.crtTime.length()==0?null:df.parse(this.crtTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		event.setPage(this.page);	
		return event;
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

