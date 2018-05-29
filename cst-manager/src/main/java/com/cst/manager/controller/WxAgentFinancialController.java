package com.cst.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Manager;
import com.cst.service.model.ShopFinancial;
import com.cst.service.model.WxAgentFinancial;
import com.cst.service.WxAgentFinancialService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/WxAgentFinancial")
public class WxAgentFinancialController{
	
	// 业务逻辑对象
	@Autowired
	private WxAgentFinancialService wxAgentFinancialService;
	
	// 查询结果
	private List<WxAgentFinancial> wxAgentFinancialList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String wxAgentFinancialListPage() throws Exception{
		//wxAgentFinancialForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		WxAgentFinancial wxAgentFinancial=null;
		wxAgentFinancialList = wxAgentFinancialService.getWxAgentFinancialListPage(wxAgentFinancial);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String wxAgentFinancialAddEditIni() throws Exception{
		WxAgentFinancial wxAgentFinancial=null;
		if ("edit".equals(doWhat)) {
			wxAgentFinancial = this.wxAgentFinancialService.getWxAgentFinancialById(wxAgentFinancial.getWxAgentFinancialId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String wxAgentFinancialDetail() throws Exception {
//		this.setPare_moduleid(14);	
		WxAgentFinancial wxAgentFinancial=null;
		wxAgentFinancial = this.wxAgentFinancialService.getWxAgentFinancialById(wxAgentFinancial.getWxAgentFinancialId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		WxAgentFinancial wxAgentFinancial=null;
		wxAgentFinancial = this.wxAgentFinancialService.getWxAgentFinancialById(wxAgentFinancial.getWxAgentFinancialId());
		return "detail";
	}
	/** 保存新增对象 */
	public String wxAgentFinancialAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //wxAgentFinancialForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        WxAgentFinancial wxAgentFinancial=null;
		this.wxAgentFinancialService.saveWxAgentFinancial(wxAgentFinancial, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String wxAgentFinancialDelete() throws Exception {	
		WxAgentFinancial wxAgentFinancial=null;
		this.wxAgentFinancialService.deleteWxAgentFinancial(wxAgentFinancial.getWxAgentFinancialId());
		return "list";
	}	
	public List<WxAgentFinancial> getWxAgentFinancialList() {
		return this.wxAgentFinancialList;
	}

	public void setWxAgentFinancialList(List<WxAgentFinancial> wxAgentFinancialList) {
		this.wxAgentFinancialList = wxAgentFinancialList;
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
