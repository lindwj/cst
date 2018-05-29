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

import com.cst.service.model.BindStreet;
import com.cst.service.BindStreetService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/bindStreet")
public class BindStreetController{
	
	// 业务逻辑对象
	@Autowired
	private BindStreetService bindStreetService;
	
	// 查询结果
	private List<BindStreet> bindStreetList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String bindStreetListPage() throws Exception{
		//bindStreetForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		BindStreet bindStreet=null;
		bindStreetList = bindStreetService.getBindStreetListPage(bindStreet);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String bindStreetAddEditIni() throws Exception{
		BindStreet bindStreet=null;
		if ("edit".equals(doWhat)) {
			bindStreet = this.bindStreetService.getBindStreetById(bindStreet.getBindStreetId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String bindStreetDetail(BindStreet bindStreet,Model model) throws Exception {
//		this.setPare_moduleid(14);	
		bindStreet = this.bindStreetService.getBindStreetById(bindStreet.getBindStreetId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		BindStreet bindStreet=null;
		bindStreet = this.bindStreetService.getBindStreetById(bindStreet.getBindStreetId());
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("bindStreetAddEdit.do")
	public String bindStreetAddEdit(BindStreet bindStreet,Model model) throws Exception {		
		
		//没有勾选 任何街道
		if(bindStreet.getStreetList()==null || bindStreet.getStreetList().size()==0){
			return "redirect:/nation/getNationList.do?code=101&shopId="+bindStreet.getShopId();
		}
		
		
		//初始化 新增数据
		for(BindStreet bs : bindStreet.getStreetList()){
			bs.setShopId(bindStreet.getShopId());
			bs.setDistrict(bindStreet.getDistrict());
		}
		
		try{
			//按县区 清除 旧数据
		this.bindStreetService.deleteBindStreet(bindStreet);
		
		this.bindStreetService.saveBindStreet(bindStreet.getStreetList(),"add");
		}catch(Exception e){
			//街道重复
			return "redirect:/nation/getNationList.do?code=100&shopId="+bindStreet.getShopId();
		}
		
		
		return "redirect:/nation/getNationList.do?shopId="+bindStreet.getShopId();
	}
	/**删除对象*/
	public String bindStreetDelete() throws Exception {	
		BindStreet bindStreet=null;
//		this.bindStreetService.deleteBindStreet(bindStreet.getBindStreetId());
		return "list";
	}	
	public List<BindStreet> getBindStreetList() {
		return this.bindStreetList;
	}

	public void setBindStreetList(List<BindStreet> bindStreetList) {
		this.bindStreetList = bindStreetList;
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
