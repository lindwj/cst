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

import com.cst.service.model.AgentCfg;
import com.cst.service.model.AgentProductCfg;
import com.cst.service.model.Product;
import com.cst.service.AgentCfgService;
import com.cst.service.AgentProductCfgService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/AgentProductCfg")
public class AgentProductCfgController{
	
	// 业务逻辑对象
	@Autowired
	private AgentProductCfgService agentProductCfgService;
	@Autowired
	private AgentCfgService agentCfgService;
	
	// 查询结果
	private List<AgentProductCfg> agentProductCfgList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	@RequestMapping("/delAgent.do")
	@ResponseBody
	public int delAgent(AgentProductCfg agentProductCfg, Model model) throws Exception {
		AgentCfg a=agentCfgService.getAgentCfgById(agentProductCfg.getManagerId());
		if(a!=null){
			agentProductCfg.setAgentCfgId(a.getAgentCfgId());
			agentProductCfgService.deleteAgentProductCfg(agentProductCfg);
			return 1;
		}else{
			return 0;
		}
	}
	
	/** 执行搜索 */
	public String agentProductCfgListPage() throws Exception{
		//agentProductCfgForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		AgentProductCfg agentProductCfg=null;
		agentProductCfgList = agentProductCfgService.getAgentProductCfgListPage(agentProductCfg);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String agentProductCfgAddEditIni() throws Exception{
		AgentProductCfg agentProductCfg=null;
		if ("edit".equals(doWhat)) {
			agentProductCfg = this.agentProductCfgService.getAgentProductCfgById(agentProductCfg.getAgentProductCfgId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String agentProductCfgDetail() throws Exception {
//		this.setPare_moduleid(14);	
		AgentProductCfg agentProductCfg=null;
		agentProductCfg = this.agentProductCfgService.getAgentProductCfgById(agentProductCfg.getAgentProductCfgId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		AgentProductCfg agentProductCfg=null;
		agentProductCfg = this.agentProductCfgService.getAgentProductCfgById(agentProductCfg.getAgentProductCfgId());
		return "detail";
	}
	/** 保存新增对象 */
	public String agentProductCfgAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //agentProductCfgForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        AgentProductCfg agentProductCfg=null;
		this.agentProductCfgService.saveAgentProductCfg(agentProductCfg, this.doWhat);
		return "detail";
	}
	public List<AgentProductCfg> getAgentProductCfgList() {
		return this.agentProductCfgList;
	}

	public void setAgentProductCfgList(List<AgentProductCfg> agentProductCfgList) {
		this.agentProductCfgList = agentProductCfgList;
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
