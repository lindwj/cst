package com.cst.manager.controller;

import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.cst.service.model.AgentCfg;
import com.cst.service.model.AgentProductCfg;
import com.cst.service.model.Manager;
import com.cst.service.model.Product;
import com.cst.service.AgentCfgService;
import com.cst.service.AgentProductCfgService;
import com.cst.service.ManagerService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/AgentCfg")
public class AgentCfgController{
	
	// 业务逻辑对象
	@Autowired
	private AgentCfgService agentCfgService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private AgentProductCfgService agentProductCfgService;
	
	// 查询结果
	private List<AgentCfg> agentCfgList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	@RequestMapping("/saveAgent.do")
	public ModelAndView saveAgent(AgentCfg agentCfg, ModelAndView model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now=new Date();
		Manager m=managerService.getManagerById(agentCfg.getManagerId());
		AgentCfg ag=agentCfgService.getAgentCfgById(m.getManagerId());
		if(ag==null){
			AgentCfg a=new AgentCfg();
			a.setAgentId(m.getManagerId());
			a.setAgentName(m.getName());
			a.setType(2);
			a.setState("1");
			a.setCreateByAdm(mg.getManagerId());
			a.setCreateDate(now);
			agentCfgService.saveAgentCfg(a, "add");
			for(Product p:agentCfg.getProducts()){
				if(p!=null){
					if(p.getProductUuid()!=null&&p.getProductUuid()!=""){
						AgentProductCfg agentProductCfg=new AgentProductCfg(); 
						agentProductCfg.setProductUuid(p.getProductUuid());
						agentProductCfg.setAgentCfgId(a.getAgentCfgId());
						agentProductCfgService.saveAgentProductCfg(agentProductCfg, "add");
					}
				}
			}
		}else{
			for(Product p:agentCfg.getProducts()){
				if(p!=null){
					if(p.getProductUuid()!=null&&p.getProductUuid()!=""){
						AgentProductCfg agentProductCfg=new AgentProductCfg(); 
						agentProductCfg.setProductUuid(p.getProductUuid());
						agentProductCfg.setAgentCfgId(ag.getAgentCfgId());
						agentProductCfgService.saveAgentProductCfg(agentProductCfg, "add");
					}
				}
			}
		}
		model.setViewName("redirect:/product/productListPageAll.do?managerId="+agentCfg.getManagerId());
		return model;
	}
	
	/** 执行搜索 */
	public String agentCfgListPage() throws Exception{
		//agentCfgForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		AgentCfg agentCfg=null;
		agentCfgList = agentCfgService.getAgentCfgListPage(agentCfg);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String agentCfgAddEditIni() throws Exception{
		AgentCfg agentCfg=null;
		if ("edit".equals(doWhat)) {
			agentCfg = this.agentCfgService.getAgentCfgById(agentCfg.getAgentCfgId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String agentCfgDetail() throws Exception {
//		this.setPare_moduleid(14);	
		AgentCfg agentCfg=null;
		agentCfg = this.agentCfgService.getAgentCfgById(agentCfg.getAgentCfgId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		AgentCfg agentCfg=null;
		agentCfg = this.agentCfgService.getAgentCfgById(agentCfg.getAgentCfgId());
		return "detail";
	}
	/** 保存新增对象 */
	public String agentCfgAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //agentCfgForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        AgentCfg agentCfg=null;
		this.agentCfgService.saveAgentCfg(agentCfg, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String agentCfgDelete() throws Exception {	
		AgentCfg agentCfg=null;
		this.agentCfgService.deleteAgentCfg(agentCfg.getAgentCfgId());
		return "list";
	}	
	public List<AgentCfg> getAgentCfgList() {
		return this.agentCfgList;
	}

	public void setAgentCfgList(List<AgentCfg> agentCfgList) {
		this.agentCfgList = agentCfgList;
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
