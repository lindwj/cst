package com.hwl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hwl.service.model.Gaccount;
import com.hwl.service.model.SdElectResAcc;
import com.hwl.service.model.SdElectResAccForm;
import com.hwl.service.RecoveryService;
import com.hwl.service.SdElectResAccService;




/**
 * @author lind
 */

@Controller
@RequestMapping("/recovery")
public class RecoveryController {
	
	// 业务逻辑对象
	@Autowired
	private SdElectResAccService sdElectResAccService;
	
	@Autowired
	private RecoveryService recoveryService;
	
	// 查询结果
	private List<SdElectResAcc> sdElectResAccList;
	
	private SdElectResAccForm sdElectResAccForm=new SdElectResAccForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	@RequestMapping("/recoveryAcc.do")
	@ResponseBody
	public String recoveryAcc() throws Exception {	
		//账套恢复
//		recoveryService.recovery("D:/ziptest/", "D:/ziptarget/", "软件通(2018).zip");
		
		//智能对应
		Gaccount gaccount =new Gaccount();
		gaccount.setSdElectResAccId(26);
		recoveryService.intelligentMatch(gaccount);
		return "list";
	}	
	

	public SdElectResAccForm getModel() {
		return sdElectResAccForm;
	}
	
	public List<SdElectResAcc> getSdElectResAccList() {
		return this.sdElectResAccList;
	}

	public void setSdElectResAccList(List<SdElectResAcc> sdElectResAccList) {
		this.sdElectResAccList = sdElectResAccList;
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
