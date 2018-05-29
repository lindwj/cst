
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
public class LiwuForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private String id;
	/** eventid */
	private String eventid;
	/** name */
	private String name;
	/** txt */
	private String txt;
	/** headurl */
	private String headurl;
	/** price */
	private String price;
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
	 * 设置 price
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * price
	 * @return
	 */
	public String getPrice() {
		return this.price;
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
  
	public void setLiwu(Liwu liwu){
		if(liwu==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.id=liwu.getId()==null?"":String.valueOf(liwu.getId());	
		this.eventid=liwu.getEventid()==null?"":String.valueOf(liwu.getEventid());	
		this.name=liwu.getName()==null?"":liwu.getName();	
		this.txt=liwu.getTxt()==null?"":liwu.getTxt();	
		this.headurl=liwu.getHeadurl()==null?"":liwu.getHeadurl();	
		this.price=liwu.getPrice()==0?"0":String.valueOf(liwu.getPrice());	
		this.admid=liwu.getAdmid()==null?"":String.valueOf(liwu.getAdmid());	
		this.status=liwu.getStatus()==null?"":String.valueOf(liwu.getStatus());	
		this.crtTime=liwu.getCrtTime()==null?"":df.format(liwu.getCrtTime());	
		this.page=liwu.getPage();
	}
  
	public Liwu getLiwu(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Liwu liwu=new Liwu();
		liwu.setId(this.id==null||this.id.length()==0?0:Integer.parseInt(this.id));  
		liwu.setEventid(this.eventid==null||this.eventid.length()==0?0:Integer.parseInt(this.eventid));  
		liwu.setName(this.name==null||this.name.length()==0?"":this.name);  
		liwu.setTxt(this.txt==null||this.txt.length()==0?"":this.txt);  
		liwu.setHeadurl(this.headurl==null||this.headurl.length()==0?"":this.headurl);  
		liwu.setPrice(this.price==null||this.price.length()==0?0:Long.valueOf(this.price));  
		liwu.setAdmid(this.admid==null||this.admid.length()==0?0:Integer.parseInt(this.admid));  
		liwu.setStatus(this.status==null||this.status.length()==0?0:Integer.parseInt(this.status));  
		try {
			liwu.setCrtTime(this.crtTime==null||this.crtTime.length()==0?null:df.parse(this.crtTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		liwu.setPage(this.page);	
		return liwu;
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

