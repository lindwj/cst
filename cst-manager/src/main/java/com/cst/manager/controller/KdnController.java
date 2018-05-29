package com.cst.manager.controller;

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

import com.cst.service.model.Kdn;
import com.cst.service.KdnService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/Kdn")
public class KdnController{
	
	// 业务逻辑对象
	@Autowired
	private KdnService kdnService;
	
	// 查询结果
	private List<Kdn> kdnList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	@RequestMapping("/getKdn.do")
	@ResponseBody
	public List<Kdn> getKdn(Kdn kdn) throws Exception{
		return kdnService.getKdnListPage(kdn);
	}
	
	/** 执行搜索 */
	public String kdnListPage() throws Exception{
		//kdnForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Kdn kdn=null;
		kdnList = kdnService.getKdnListPage(kdn);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String kdnAddEditIni() throws Exception{
		Kdn kdn=null;
		if ("edit".equals(doWhat)) {
			kdn = this.kdnService.getKdnById(kdn.getKdnId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String kdnDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Kdn kdn=null;
		kdn = this.kdnService.getKdnById(kdn.getKdnId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Kdn kdn=null;
		kdn = this.kdnService.getKdnById(kdn.getKdnId());
		return "detail";
	}
	/** 保存新增对象 */
	public String kdnAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //kdnForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Kdn kdn=null;
		this.kdnService.saveKdn(kdn, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String kdnDelete() throws Exception {	
		Kdn kdn=null;
		this.kdnService.deleteKdn(kdn.getKdnId());
		return "list";
	}	
	public List<Kdn> getKdnList() {
		return this.kdnList;
	}

	public void setKdnList(List<Kdn> kdnList) {
		this.kdnList = kdnList;
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
