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

import com.cst.service.model.Parameter;
import com.cst.service.model.ParameterForm;
import com.cst.service.ParameterService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/Parameter")
public class ParameterController{
	
	// 业务逻辑对象
	@Autowired
	private ParameterService parameterService;
	
	// 查询结果
	private List<Parameter> parameterList;
	
	private ParameterForm parameterForm=new ParameterForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String parameterListPage() throws Exception{
		//parameterForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Parameter parameter=null;
//		parameterList = parameterService.getParameterListPage(parameter);
		parameterForm.setParameter(parameter);
		request.setAttribute("page", parameterForm.getPage());
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String parameterAddEditIni() throws Exception{
		Parameter parameter=null;
		if ("edit".equals(doWhat)) {
			parameter = this.parameterService.getParameterById(parameter.getParameterId());
			parameterForm.setParameter(parameter);
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String parameterDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Parameter parameter=null;
		parameter = this.parameterService.getParameterById(parameter.getParameterId());
		parameterForm.setParameter(parameter);
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Parameter parameter=null;
		parameter = this.parameterService.getParameterById(parameter.getParameterId());
		parameterForm.setParameter(parameter);
		return "detail";
	}
	/** 保存新增对象 */
	public String parameterAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //parameterForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Parameter parameter=null;
		this.parameterService.saveParameter(parameter, this.doWhat);
		parameterForm.setParameter(parameter);
		return "detail";
	}
	/**删除对象*/
	public String parameterDelete() throws Exception {	
		Parameter parameter=null;
		this.parameterService.deleteParameter(parameter.getParameterId());
		return "list";
	}	

	public ParameterForm getModel() {
		return parameterForm;
	}
	
	public List<Parameter> getParameterList() {
		return this.parameterList;
	}

	public void setParameterList(List<Parameter> parameterList) {
		this.parameterList = parameterList;
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
