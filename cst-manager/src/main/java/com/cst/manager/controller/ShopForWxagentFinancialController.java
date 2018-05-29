package com.cst.manager.controller;

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
import com.cst.service.model.ShopForWxagentFinancial;
import com.cst.service.model.WxAgentFinancial;
import com.cst.service.ShopForWxagentFinancialService;
import com.cst.service.WxAgentFinancialService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/ShopForWxagentFinancial")
public class ShopForWxagentFinancialController{
	
	// 业务逻辑对象
	@Autowired
	private ShopForWxagentFinancialService shopForWxagentFinancialService;
	@Autowired
	private WxAgentFinancialService wxAgentFinancialService;
	
	// 查询结果
	private List<ShopForWxagentFinancial> shopForWxagentFinancialList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/*@RequestMapping("ShopForWxagentFinancialList.do")
	@ResponseBody
	public ShopForWxagentFinancial shopFinancialSvcListPage(ShopForWxagentFinancial shopForWxagentFinancial,Model model) throws Exception{
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		
		shopForWxagentFinancial.setShopId(Integer.valueOf(mg.getShopIdstr()));
		if(shopForWxagentFinancial.getStatus() != null&&shopForWxagentFinancial.getStatus()==0){
			shopForWxagentFinancial.setStatus(100);
		}
		
		shopForWxagentFinancialList = shopForWxagentFinancialService.getShopForWxagentFinancialList(shopForWxagentFinancial);
		WxAgentFinancial w=new WxAgentFinancial();
		 for(ShopForWxagentFinancial s:shopForWxagentFinancialList){
			 if(s.getPayType()!=null){
				 w.setAgentId(mg.getManagerId());
				 w.setDay(s.getDay());
				 WxAgentFinancial wx=wxAgentFinancialService.getWxAgentTotal(w);
				 if(wx!=null){
					 s.setMoneyWd(wx.getAmount());
				 }else{
					 s.setMoneyWd(0);
				 }
			 }else{
				 s.setMoneyWd(0);
			 }
		 }
		 shopForWxagentFinancial.setShopForWxagentFinancialList(shopForWxagentFinancialList);
		 return shopForWxagentFinancial;
	}*/
	
	/** 执行搜索 */
	public String shopForWxagentFinancialListPage() throws Exception{
		//shopForWxagentFinancialForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		ShopForWxagentFinancial shopForWxagentFinancial=null;
		shopForWxagentFinancialList = shopForWxagentFinancialService.getShopForWxagentFinancialListPage(shopForWxagentFinancial);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String shopForWxagentFinancialAddEditIni() throws Exception{
		ShopForWxagentFinancial shopForWxagentFinancial=null;
		if ("edit".equals(doWhat)) {
			shopForWxagentFinancial = this.shopForWxagentFinancialService.getShopForWxagentFinancialById(shopForWxagentFinancial.getShopForWxagentFinancialId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String shopForWxagentFinancialDetail() throws Exception {
//		this.setPare_moduleid(14);	
		ShopForWxagentFinancial shopForWxagentFinancial=null;
		shopForWxagentFinancial = this.shopForWxagentFinancialService.getShopForWxagentFinancialById(shopForWxagentFinancial.getShopForWxagentFinancialId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		ShopForWxagentFinancial shopForWxagentFinancial=null;
		shopForWxagentFinancial = this.shopForWxagentFinancialService.getShopForWxagentFinancialById(shopForWxagentFinancial.getShopForWxagentFinancialId());
		return "detail";
	}
	/** 保存新增对象 */
	public String shopForWxagentFinancialAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //shopForWxagentFinancialForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        ShopForWxagentFinancial shopForWxagentFinancial=null;
		this.shopForWxagentFinancialService.saveShopForWxagentFinancial(shopForWxagentFinancial, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String shopForWxagentFinancialDelete() throws Exception {	
		ShopForWxagentFinancial shopForWxagentFinancial=null;
		this.shopForWxagentFinancialService.deleteShopForWxagentFinancial(shopForWxagentFinancial.getShopForWxagentFinancialId());
		return "list";
	}	
	public List<ShopForWxagentFinancial> getShopForWxagentFinancialList() {
		return this.shopForWxagentFinancialList;
	}

	public void setShopForWxagentFinancialList(List<ShopForWxagentFinancial> shopForWxagentFinancialList) {
		this.shopForWxagentFinancialList = shopForWxagentFinancialList;
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
