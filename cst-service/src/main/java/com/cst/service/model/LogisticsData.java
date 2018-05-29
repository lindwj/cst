
package com.cst.service.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cst.service.common.Page;

/**
 * @author lind
 *
 */
public class LogisticsData implements Serializable{	
	
	/* 快递公司编码*/
	private String shipperCode;
	
	/* 物流运单号*/
	private String logisticCode;
	
	/* 物流状态:  0无轨迹  1已揽收  2在途中  
	 * 201到达派件城市  3签收
	 * 4问题件*/
	private String state;
	
	List<Trace> lTraces=new ArrayList<Trace>();;

	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Trace> getlTraces() {
		return lTraces;
	}

	public void setlTraces(List<Trace> lTraces) {
		this.lTraces = lTraces;
	}
	
}

