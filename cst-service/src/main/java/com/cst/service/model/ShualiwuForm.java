
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
public class ShualiwuForm implements Serializable{	
	//private static final long serialVersionUID = -3885313706621697914L;//TODO 删掉重新生成一个 
	//columns START
	/** id */
	private String id;
	/** eventid */
	private String eventid;
	/** openid */
	private String openid;
	/** name */
	private String name;
	/** headurl */
	private String headurl;
	/** starId */
	private String starId;
	/** liwuId */
	private String liwuId;
	/** liwuName */
	private String liwuName;
	/** crtTime */
	private String crtTime;
	/** 1 投票 */
	private String type;
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
	 * 设置 openid
	 * @param openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * openid
	 * @return
	 */
	public String getOpenid() {
		return this.openid;
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
	 * 设置 starId
	 * @param starId
	 */
	public void setStarId(String starId) {
		this.starId = starId;
	}
	/**
	 * starId
	 * @return
	 */
	public String getStarId() {
		return this.starId;
	}
	/**
	 * 设置 liwuId
	 * @param liwuId
	 */
	public void setLiwuId(String liwuId) {
		this.liwuId = liwuId;
	}
	/**
	 * liwuId
	 * @return
	 */
	public String getLiwuId() {
		return this.liwuId;
	}
	/**
	 * 设置 liwuName
	 * @param liwuName
	 */
	public void setLiwuName(String liwuName) {
		this.liwuName = liwuName;
	}
	/**
	 * liwuName
	 * @return
	 */
	public String getLiwuName() {
		return this.liwuName;
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
	 * 设置 1 投票
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 1 投票
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
  
	public void setShualiwu(Shualiwu shualiwu){
		if(shualiwu==null)return;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.id=shualiwu.getId()==null?"":String.valueOf(shualiwu.getId());	
		this.eventid=shualiwu.getEventid()==null?"":String.valueOf(shualiwu.getEventid());	
		this.openid=shualiwu.getOpenid()==null?"":shualiwu.getOpenid();	
		this.name=shualiwu.getName()==null?"":shualiwu.getName();	
		this.headurl=shualiwu.getHeadurl()==null?"":shualiwu.getHeadurl();	
		this.starId=shualiwu.getStarId()==null?"":String.valueOf(shualiwu.getStarId());	
		this.liwuId=shualiwu.getLiwuId()==null?"":String.valueOf(shualiwu.getLiwuId());	
		this.liwuName=shualiwu.getLiwuName()==null?"":shualiwu.getLiwuName();	
		this.crtTime=shualiwu.getCrtTime()==null?"":df.format(shualiwu.getCrtTime());	
		this.type=shualiwu.getType()==null?"":String.valueOf(shualiwu.getType());	
		this.page=shualiwu.getPage();
	}
  
	public Shualiwu getShualiwu(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Shualiwu shualiwu=new Shualiwu();
		shualiwu.setId(this.id==null||this.id.length()==0?0:Integer.parseInt(this.id));  
		shualiwu.setEventid(this.eventid==null||this.eventid.length()==0?0:Integer.parseInt(this.eventid));  
		shualiwu.setOpenid(this.openid==null||this.openid.length()==0?"":this.openid);  
		shualiwu.setName(this.name==null||this.name.length()==0?"":this.name);  
		shualiwu.setHeadurl(this.headurl==null||this.headurl.length()==0?"":this.headurl);  
		shualiwu.setStarId(this.starId==null||this.starId.length()==0?0:Integer.parseInt(this.starId));  
		shualiwu.setLiwuId(this.liwuId==null||this.liwuId.length()==0?0:Integer.parseInt(this.liwuId));  
		shualiwu.setLiwuName(this.liwuName==null||this.liwuName.length()==0?"":this.liwuName);  
		try {
			shualiwu.setCrtTime(this.crtTime==null||this.crtTime.length()==0?null:df.parse(this.crtTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		shualiwu.setType(this.type==null||this.type.length()==0?0:Integer.parseInt(this.type));  
		shualiwu.setPage(this.page);	
		return shualiwu;
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

