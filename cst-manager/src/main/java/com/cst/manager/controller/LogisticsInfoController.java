package com.cst.manager.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Kdn;
import com.cst.service.model.KdnReturn;
import com.cst.service.model.LogisticsData;
import com.cst.service.model.LogisticsInfo;
import com.cst.service.model.Manager;
import com.cst.service.model.Orders;
import com.cst.service.model.Trace;
import com.cst.service.util.Common;
import com.cst.manager.KdnRealm;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.cst.service.KdnService;
import com.cst.service.LogisticsInfoService;
import com.cst.service.OrdersService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/LogisticsInfo")
public class LogisticsInfoController{
	
	// 业务逻辑对象
	@Autowired
	private LogisticsInfoService logisticsInfoService;
	@Autowired
	private KdnService kdnService;
	@Autowired
	private OrdersService ordersService;
	
	// 查询结果
	private List<LogisticsInfo> logisticsInfoList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	@RequestMapping("/getLogisticsInfo.do")
	@ResponseBody
	public int getLogisticsInfo(LogisticsInfo logisticsInfo) throws Exception{
		Kdn kdn=new Kdn();
		kdn = kdnService.getKdnById(logisticsInfo.getKdnId());
		logisticsInfo.setJsonData(kdn.getCode());
		KdnRealm api = new KdnRealm();
		String result = api.orderTracesSubByJson(logisticsInfo);
		JSONObject jsStr = JSONObject.fromObject(result);
		System.out.println(jsStr);
		if(jsStr.getBoolean("Success")){
			Date now=new Date();
			Orders orders = new Orders();
			orders.setCode(logisticsInfo.getOrderCode());
			logisticsInfo.setCreateDate(now);
			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();
			logisticsInfo.setCreateMan(mg.getManagerId());
			logisticsInfo.setJsonData(null);
			Orders o=ordersService.getOrdersByCode(orders);
			if(o.getLogisticsInfoId()!=null){
				logisticsInfo.setLogisticsInfoId(o.getLogisticsInfoId());
				logisticsInfoService.saveLogisticsInfo(logisticsInfo, "edit");
			}else{
				logisticsInfoService.saveLogisticsInfo(logisticsInfo, "add");
				LogisticsInfo l = logisticsInfoService.getLogisticsInfoBykdnId(logisticsInfo);
				o.setLogisticsInfoId(l.getLogisticsInfoId());
				ordersService.updateOrdersLog(o);
			}
			return 1;
		}else{
			return 0;
		}
	}
	
	@RequestMapping("/getRush.do")
	@ResponseBody
	public com.alibaba.fastjson.JSONObject  getRush(String RequestData,String RequestType,String DataSign) throws Exception{
		System.out.println(RequestData);
		System.out.println(RequestType);
		System.out.println(DataSign);
		JSONObject jsStr = JSONObject.fromObject(RequestData);
		String EBusinessID = jsStr.getString("EBusinessID");
		JSONArray data=new JSONArray();
		data = jsStr.getJSONArray("Data");
		Date now=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=time.format(now);
		KdnReturn kdnReturn=new KdnReturn();
		for(int i=0;i<data.size();i++){
			JSONObject jsonObject = data.getJSONObject(i);
			String ShipperCode = jsonObject.getString("ShipperCode");
			String LogisticCode = jsonObject.getString("LogisticCode");
			Kdn kdn=new Kdn();
			kdn.setCode(ShipperCode);
			Kdn k=kdnService.getKdnByCode(kdn);
			if(k==null){
				kdnReturn.setEBusinessID(EBusinessID);
				kdnReturn.setReason("RequestData内容为空");
				kdnReturn.setSuccess(false);
				kdnReturn.setUpdateTime(nowTime);
				ObjectMapper mapper = new ObjectMapper();
				return JSON.parseObject(mapper.writeValueAsString(kdnReturn));
			}
			LogisticsInfo logisticsInfo=new LogisticsInfo();
			logisticsInfo.setKdnId(k.getKdnId());
			logisticsInfo.setCode(LogisticCode);
			LogisticsInfo l=logisticsInfoService.getLogisticsInfoBykdnId(logisticsInfo);
			if(l==null){
				kdnReturn.setEBusinessID(EBusinessID);
				kdnReturn.setReason("RequestData内容为空");
				kdnReturn.setSuccess(false);
				kdnReturn.setUpdateTime(nowTime);
				ObjectMapper mapper = new ObjectMapper();
				return JSON.parseObject(mapper.writeValueAsString(kdnReturn));
				 
			}
			l.setJsonData(jsonObject.toString());
			logisticsInfoService.saveLogisticsInfo(l, "edit");
		}
		kdnReturn.setEBusinessID(EBusinessID);
		kdnReturn.setReason("");
		kdnReturn.setSuccess(true);
		kdnReturn.setUpdateTime(nowTime);
		ObjectMapper mapper = new ObjectMapper();
		return JSON.parseObject(mapper.writeValueAsString(kdnReturn));
				/*"{'Reason': ''," +
		"'EBusinessID':"+"'"+EBusinessID+"'"+',' +
        "'UpdateTime':"+"'"+nowTime+"'"+',' +
        "'Success':'true'}";*/
	}
	
	@RequestMapping("/getLogistics.do")
	@ResponseBody
	public LogisticsData getLogistics(LogisticsInfo logisticsInfo) throws Exception{
		Orders orders= new Orders();
		orders.setCode(logisticsInfo.getOrderCode());
		Orders o=ordersService.getOrdersByCode(orders);
		LogisticsInfo l=logisticsInfoService.getLogisticsInfoById(o.getLogisticsInfoId());
		String data=l.getJsonData();
		JSONObject j = JSONObject.fromObject(data);
		LogisticsData logisticsData=new LogisticsData();
		logisticsData.setShipperCode(j.getString("ShipperCode"));
		logisticsData.setLogisticCode(j.getString("LogisticCode"));
		logisticsData.setState(j.getString("State"));
		JSONArray jsonArray=j.getJSONArray("Traces");
		List<Trace> list=new ArrayList<Trace>();
		for(int i=0;i<jsonArray.size();i++){
			Trace t=new Trace();
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			t.setAcceptTime(jsonObject.getString("AcceptTime"));
			t.setAcceptStation(jsonObject.getString("AcceptStation"));
			t.setRemark(jsonObject.getString("Remark"));
			list.add(t);
		}
		logisticsData.setlTraces(list);
		return logisticsData;
	}
	
	/** 执行搜索 */
	public String logisticsInfoListPage() throws Exception{
		//logisticsInfoForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		LogisticsInfo logisticsInfo=null;
		logisticsInfoList = logisticsInfoService.getLogisticsInfoListPage(logisticsInfo);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String logisticsInfoAddEditIni() throws Exception{
		LogisticsInfo logisticsInfo=null;
		if ("edit".equals(doWhat)) {
			logisticsInfo = this.logisticsInfoService.getLogisticsInfoById(logisticsInfo.getLogisticsInfoId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String logisticsInfoDetail() throws Exception {
//		this.setPare_moduleid(14);	
		LogisticsInfo logisticsInfo=null;
		logisticsInfo = this.logisticsInfoService.getLogisticsInfoById(logisticsInfo.getLogisticsInfoId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		LogisticsInfo logisticsInfo=null;
		logisticsInfo = this.logisticsInfoService.getLogisticsInfoById(logisticsInfo.getLogisticsInfoId());
		return "detail";
	}
	/** 保存新增对象 */
	public String logisticsInfoAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //logisticsInfoForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        LogisticsInfo logisticsInfo=null;
		this.logisticsInfoService.saveLogisticsInfo(logisticsInfo, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String logisticsInfoDelete() throws Exception {	
		LogisticsInfo logisticsInfo=null;
		this.logisticsInfoService.deleteLogisticsInfo(logisticsInfo.getLogisticsInfoId());
		return "list";
	}	
	public List<LogisticsInfo> getLogisticsInfoList() {
		return this.logisticsInfoList;
	}

	public void setLogisticsInfoList(List<LogisticsInfo> logisticsInfoList) {
		this.logisticsInfoList = logisticsInfoList;
	}
	
	public String getDoWhat() {
		return doWhat;
	}

	public void setDoWhat(String doWhat) {
		this.doWhat = doWhat;
	}
	
	public void setPare_moduleid(int pareModuleid) {
		pare_moduleid = pareModuleid;
	}
	
	
}
