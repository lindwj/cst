
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
public class JiangpinForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private String id;
	/** eventid */
	private String eventid;
	/** headurl */
	private String headurl;
	/** name */
	private String name;
	/** des */
	private String des;
	/** bigurl */
	private String bigurl;
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
	 * 设置 bigurl
	 * @param bigurl
	 */
	public void setBigurl(String bigurl) {
		this.bigurl = bigurl;
	}
	/**
	 * bigurl
	 * @return
	 */
	public String getBigurl() {
		return this.bigurl;
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
  
	public void setJiangpin(Jiangpin jiangpin){
		if(jiangpin==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.id=jiangpin.getId()==null?"":String.valueOf(jiangpin.getId());	
		this.eventid=jiangpin.getEventid()==null?"":String.valueOf(jiangpin.getEventid());	
		this.headurl=jiangpin.getHeadurl()==null?"":jiangpin.getHeadurl();	
		this.name=jiangpin.getName()==null?"":jiangpin.getName();	
		this.des=jiangpin.getDes()==null?"":jiangpin.getDes();	
		this.bigurl=jiangpin.getBigurl()==null?"":jiangpin.getBigurl();	
		this.admid=jiangpin.getAdmid()==null?"":String.valueOf(jiangpin.getAdmid());	
		this.status=jiangpin.getStatus()==null?"":String.valueOf(jiangpin.getStatus());	
		this.crtTime=jiangpin.getCrtTime()==null?"":df.format(jiangpin.getCrtTime());	
		this.page=jiangpin.getPage();
	}
  
	public Jiangpin getJiangpin(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Jiangpin jiangpin=new Jiangpin();
		jiangpin.setId(this.id==null||this.id.length()==0?0:Integer.parseInt(this.id));  
		jiangpin.setEventid(this.eventid==null||this.eventid.length()==0?0:Integer.parseInt(this.eventid));  
		jiangpin.setHeadurl(this.headurl==null||this.headurl.length()==0?"":this.headurl);  
		jiangpin.setName(this.name==null||this.name.length()==0?"":this.name);  
		jiangpin.setDes(this.des==null||this.des.length()==0?"":this.des);  
		jiangpin.setBigurl(this.bigurl==null||this.bigurl.length()==0?"":this.bigurl);  
		jiangpin.setAdmid(this.admid==null||this.admid.length()==0?0:Integer.parseInt(this.admid));  
		jiangpin.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		try {
			jiangpin.setCrtTime(this.crtTime==null||this.crtTime.length()==0?null:df.parse(this.crtTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jiangpin.setPage(this.page);	
		return jiangpin;
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

