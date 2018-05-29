package com.cst.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cst.service.model.ContractNo;
import com.cst.service.model.Manager;
import com.cst.service.util.Common;
import com.cst.service.ContractNoService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/contractNo")
public class ContractNoController{
	
	// 业务逻辑对象
	@Autowired
	private ContractNoService contractNoService;
	
	// 查询结果
	private List<ContractNo> contractNoList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/contractNoListPage.do")
	public String contractNoListPage(ContractNo contractNo, Model model) throws Exception{
		//contractNoForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		contractNo.setCreateByAdm(mg.getManagerId());
		
		
		contractNoList = contractNoService.getContractNoListPage(contractNo);
		
		model.addAttribute("contractNoList", contractNoList);
		model.addAttribute("page", contractNo.getPage());
		model.addAttribute("errcode", contractNo.getErrcode());
		
		if(contractNo.getMenuId()!=null&&contractNo.getMenuId()>0){
			subject.getSession().setAttribute("menuId", contractNo.getMenuId());
			}
		
		return "/jsp/contractNo/cnIndex";
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/contractNoAddEditIni.do")
	public String contractNoAddEditIni(ContractNo contractNo,Model model) throws Exception{
		
		
		model.addAttribute("errcode", contractNo.getErrcode());
		contractNo = this.contractNoService.getContractNoById(contractNo.getContractNoId());
		
				
		model.addAttribute("contractNo", contractNo);
		return "/jsp/contractNo/cnEdit";
		
		
	}
	
	
	@RequestMapping("/contractNoAdd.do")
	public String contractNoAdd(ContractNo contractNo,Model model) throws Exception{
		
		
		model.addAttribute("errcode", contractNo.getErrcode());
		
				
		model.addAttribute("contractNo", contractNo);
		return "/jsp/contractNo/cnAdd";
		
		
	}
	
	/** 查看对象*/
	public String contractNoDetail() throws Exception {
//		this.setPare_moduleid(14);	
		ContractNo contractNo=null;
		contractNo = this.contractNoService.getContractNoById(contractNo.getContractNoId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		ContractNo contractNo=null;
		contractNo = this.contractNoService.getContractNoById(contractNo.getContractNoId());
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/contractNoAddEdit.do")
	public ModelAndView contractNoAddEdit(ContractNo contractNo, ModelAndView model) throws Exception {		
		//this.setPare_moduleid(14);
        //contractNoForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		String validate = null;
		// add 新增 edit 修改
		if ("add".equals(contractNo.getDowhat())) {

			// 后端校验 101 省市县 街道 名称 编号 等不能为空

			if (contractNo.getProvince() == null || contractNo.getProvince() <= 0) {
				validate = "101";
			}
			if (contractNo.getCity() == null || contractNo.getCity() <= 0) {
				validate = "101";
			}
			if (contractNo.getDistrict() == null || contractNo.getDistrict() <= 0) {
				validate = "101";
			}
			if (contractNo.getShopId() == null || contractNo.getShopId() <= 0) {
				validate = "101";
			}

			if (contractNo.getContractNoAmt() == null || contractNo.getContractNoAmt()<= 0 || contractNo.getContractNoAmt()>= 999999) {
				validate = "101";
			}
			
			
			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/contractNo/contractNoAdd.do");
				return model;
			}

			// 校验结束

			// 1 表示 激活 ，这里直接赋予 激活状态
			contractNo.setStatus(1);

			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			contractNo.setCreateByAdm(mg.getManagerId());
			

		} 
		
		else if ("edit".equals(contractNo.getDowhat())) {
			
			if (contractNo.getContractNoAmt() == null || contractNo.getContractNoAmt()<= 0  || contractNo.getContractNoAmt()>= 999999) {
				validate = "101";
			}

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.addObject("contractNoId", contractNo.getContractNoId());
				model.setViewName("redirect:/contractNo/contractNoAddEditIni.do");
				return model;
			}
			
			
		}

		try {
			this.contractNoService.saveContractNo(contractNo, contractNo.getDowhat());
			
			
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			

		}
		
		

		model.setViewName("redirect:/contractNo/contractNoListPage.do");
		return model;
		
		
	}
	/**删除对象*/
	@RequestMapping("/contractNoDelete.do")
	public String contractNoDelete(ContractNo contractNo) throws Exception {	
		this.contractNoService.deleteContractNo(contractNo.getContractNoId());
		return "redirect:/contractNo/contractNoListPage.do";
	}	
	public List<ContractNo> getContractNoList() {
		return this.contractNoList;
	}

	public void setContractNoList(List<ContractNo> contractNoList) {
		this.contractNoList = contractNoList;
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
