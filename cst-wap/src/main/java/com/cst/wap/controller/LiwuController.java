package com.cst.wap.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Liwu;
import com.cst.service.model.LiwuForm;
import com.cst.service.LiwuService;




/**
 * @author lind
 */

@Controller
@RequestMapping("/liwu")
public class LiwuController {
	
	// 业务逻辑对象
	@Autowired
	private LiwuService liwuService;
	
	// 查询结果
	private List<Liwu> liwuList;
	
	private LiwuForm liwuForm=new LiwuForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/liwuListPage.do")
	@ResponseBody
	public List<Liwu> liwuListPage(Liwu liwu) throws Exception{
		//liwuForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//		Liwu liwu=liwuForm.getLiwu();
		liwuList = liwuService.getLiwuListPage(liwu);
		if(liwuList==null) {
			return null;
		}
//		liwuForm.setLiwu(liwu);
//		request.setAttribute("page", liwuForm.getPage());
		return liwuList;
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/liwuAddEditIni.do")
	@ResponseBody
	public String liwuAddEditIni() throws Exception{
		Liwu liwu=liwuForm.getLiwu();
		if ("edit".equals(doWhat)) {
			liwu = this.liwuService.getLiwuById(liwu.getId());
			liwuForm.setLiwu(liwu);
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	@RequestMapping("/liwuDetail.do")
	@ResponseBody
	public String liwuDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Liwu liwu=liwuForm.getLiwu();
		liwu = this.liwuService.getLiwuById(liwu.getId());
		liwuForm.setLiwu(liwu);
		return "detail";
	}

	@RequestMapping("/editCancel.do")
	@ResponseBody
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Liwu liwu=liwuForm.getLiwu();
		liwu = this.liwuService.getLiwuById(liwu.getId());
		liwuForm.setLiwu(liwu);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/liwuAddEdit.do")
	@ResponseBody
	public String liwuAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //liwuForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Liwu liwu=liwuForm.getLiwu();
		this.liwuService.saveLiwu(liwu, this.doWhat);
		liwuForm.setLiwu(liwu);
		return "detail";
	}
	/**删除对象*/
	@RequestMapping("/liwuDelete.do")
	@ResponseBody
	public String liwuDelete() throws Exception {	
		Liwu liwu=liwuForm.getLiwu();
		this.liwuService.deleteLiwu(liwu.getId());
		return "list";
	}	

	public LiwuForm getModel() {
		return liwuForm;
	}
	
	public List<Liwu> getLiwuList() {
		return this.liwuList;
	}

	public void setLiwuList(List<Liwu> liwuList) {
		this.liwuList = liwuList;
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
