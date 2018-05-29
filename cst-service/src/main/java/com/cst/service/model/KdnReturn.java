
package com.cst.service.model;


//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class KdnReturn implements Serializable{	

	@JsonProperty(value = "EBusinessID")
	private String EBusinessID;
	@JsonProperty(value = "UpdateTime")
	private String UpdateTime;
	@JsonProperty(value = "Reason")
	private String Reason;
	@JsonProperty(value = "Success")
	private Boolean Success;
	
	@JsonIgnore
	public String getEBusinessID() {
		return EBusinessID;
	}
	@JsonIgnore
	public void setEBusinessID(String EBusinessID) {
		this.EBusinessID = EBusinessID;
	}
	@JsonIgnore
	public String getUpdateTime() {
		return UpdateTime;
	}
	@JsonIgnore
	public void setUpdateTime(String UpdateTime) {
		this.UpdateTime = UpdateTime;
	}
	@JsonIgnore
	public String getReason() {
		return Reason;
	}
	@JsonIgnore
	public void setReason(String Reason) {
		this.Reason = Reason;
	}
	@JsonIgnore
	public Boolean getSuccess() {
		return Success;
	}
	@JsonIgnore
	public void setSuccess(Boolean Success) {
		this.Success = Success;
	}
}

