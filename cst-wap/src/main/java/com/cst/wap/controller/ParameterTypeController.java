package com.cst.wap.controller;

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

import com.cst.service.model.ParameterType;
import com.cst.service.model.ParameterTypeForm;
import com.cst.service.ParameterTypeService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/parameterType")
public class ParameterTypeController{
	
	// 业务逻辑对象
	@Autowired
	private ParameterTypeService parameterTypeService;
	
	// 查询结果
	private List<ParameterType> parameterTypeList;
	
	private ParameterTypeForm parameterTypeForm=new ParameterTypeForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String parameterTypeListPage() throws Exception{
		//parameterTypeForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		ParameterType parameterType=null;
		parameterTypeList = parameterTypeService.getParameterTypeListPage(parameterType);
		parameterTypeForm.setParameterType(parameterType);
		request.setAttribute("page", parameterTypeForm.getPage());
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String parameterTypeAddEditIni() throws Exception{
		ParameterType parameterType=null;
		if ("edit".equals(doWhat)) {
			parameterType = this.parameterTypeService.getParameterTypeById(parameterType.getParameterTypeId());
			parameterTypeForm.setParameterType(parameterType);
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	@RequestMapping("/detail.do")
	public String parameterTypeDetail(Model model,@RequestParam int id) throws Exception {
//		this.setPare_moduleid(14);	
		ParameterType parameterType=null;
		parameterType = this.parameterTypeService.getParameterTypeById(id);
		model.addAttribute("parameterType", parameterType);
		return "/test/test";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		ParameterType parameterType=null;
		parameterType = this.parameterTypeService.getParameterTypeById(parameterType.getParameterTypeId());
		parameterTypeForm.setParameterType(parameterType);
		return "detail";
	}
	/** 保存新增对象 */
	public String parameterTypeAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //parameterTypeForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        ParameterType parameterType=null;
		this.parameterTypeService.saveParameterType(parameterType, this.doWhat);
		parameterTypeForm.setParameterType(parameterType);
		return "detail";
	}
	/**删除对象*/
	public String parameterTypeDelete() throws Exception {	
		ParameterType parameterType=null;
		this.parameterTypeService.deleteParameterType(parameterType.getParameterTypeId());
		return "list";
	}	

	public ParameterTypeForm getModel() {
		return parameterTypeForm;
	}
	
	public List<ParameterType> getParameterTypeList() {
		return this.parameterTypeList;
	}

	public void setParameterTypeList(List<ParameterType> parameterTypeList) {
		this.parameterTypeList = parameterTypeList;
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
