package com.cst.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.GoodsAddress;
import com.cst.service.model.TellUs;
import com.cst.service.TellUsService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/TellUs")
public class TellUsController{
	
	// 业务逻辑对象
	@Autowired
	private TellUsService tellUsService;
	
	// 查询结果
	private List<TellUs> tellUsList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	@RequestMapping("/addTellUs.do")
	@ResponseBody
	public int addTellUs(TellUs tellUs) throws Exception{
		if(tellUs.getName()==null){
			return 0;
		}else if(tellUs.getTelephone()==null){
			return 1;
		}else{
			Date d=new Date();
			tellUs.setCreateDate(d);
			tellUsService.saveTellUs(tellUs, "add");
			return 2;
		}
	}
	/** 执行搜索 */
	public String tellUsListPage() throws Exception{
		//tellUsForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		TellUs tellUs=null;
		tellUsList = tellUsService.getTellUsListPage(tellUs);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String tellUsAddEditIni() throws Exception{
		TellUs tellUs=null;
		if ("edit".equals(doWhat)) {
			tellUs = this.tellUsService.getTellUsById(tellUs.getTellUsId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String tellUsDetail() throws Exception {
//		this.setPare_moduleid(14);	
		TellUs tellUs=null;
		tellUs = this.tellUsService.getTellUsById(tellUs.getTellUsId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		TellUs tellUs=null;
		tellUs = this.tellUsService.getTellUsById(tellUs.getTellUsId());
		return "detail";
	}
	/** 保存新增对象 */
	public String tellUsAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //tellUsForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        TellUs tellUs=null;
		this.tellUsService.saveTellUs(tellUs, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String tellUsDelete() throws Exception {	
		TellUs tellUs=null;
		this.tellUsService.deleteTellUs(tellUs.getTellUsId());
		return "list";
	}	
	public List<TellUs> getTellUsList() {
		return this.tellUsList;
	}

	public void setTellUsList(List<TellUs> tellUsList) {
		this.tellUsList = tellUsList;
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
