package com.cst.manager.controller;

import java.util.ArrayList;
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

import com.cst.service.model.Manager;
import com.cst.service.model.ShopFinancial;
import com.cst.service.model.ShopForWxagentFinancial;
import com.cst.service.model.WxAgentFinancial;
import com.cst.service.ShopFinancialService;
import com.cst.service.ShopForWxagentFinancialService;
import com.cst.service.WxAgentFinancialService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/shopFinancial")
public class ShopFinancialController{
	
	// 业务逻辑对象
	@Autowired
	private ShopFinancialService shopFinancialService;
	@Autowired
	private WxAgentFinancialService wxAgentFinancialService;
	@Autowired
	private ShopForWxagentFinancialService shopForWxagentFinancialService;
	
	// 查询结果
	private List<ShopFinancial> shopFinancialList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("shopFinancialListPage.do")
	public String shopFinancialListPage(ShopFinancial shopFinancial,Model model) throws Exception{
		//shopFinancialForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		//临时变量
		Integer status=shopFinancial.getStatus();
		
		//处理批量 转账 和  未转账
		
		if(shopFinancial.getShopFinancialIdList()!=null&&shopFinancial.getShopFinancialIdList().size()>0){
			
			
			//转账操作
			if(shopFinancial.getTransferDateStr()!=null&&shopFinancial.getTransferDateStr().length()>0){
				shopFinancial.setStatus(1);
			}else{
				shopFinancial.setStatus(0);
				shopFinancial.setTransferDateStr("");
				shopFinancial.setTransferUser("");
			}
			
			
			shopFinancial.setShopFinancialIdStr(shopFinancial.getShopFinancialIdList().toString().substring(1, shopFinancial.getShopFinancialIdList().toString().length()-1));
			shopFinancialService.saveShopFinancial(shopFinancial, "edit");
			
			
		}
		
		//赋值  查询 状态字段 恢复原值
		shopFinancial.setStatus(status);
		
		if(shopFinancial.getStatus() != null&&shopFinancial.getStatus()==0){
			shopFinancial.setStatus(100);
		}
		
		shopFinancialList = shopFinancialService.getShopFinancialListPage(shopFinancial);
		
		//赋值  查询 状态字段 恢复原值
		shopFinancial.setStatus(status);
		
		model.addAttribute("shopFinancialList", shopFinancialList);
		
		model.addAttribute("page", shopFinancial.getPage());
		
		if(shopFinancial.getMenuId()!=null&&shopFinancial.getMenuId()>0){
			Subject subject = SecurityUtils.getSubject();
			subject.getSession().setAttribute("menuId", shopFinancial.getMenuId());
		}
		
		
		return "/jsp/shopFinancial/shopFinancial";
	}
	
	@RequestMapping("shopFinancialListPageAll.do")
	@ResponseBody
	public ShopFinancial shopFinancialListPageAll(ShopFinancial shopFinancial,Model model) throws Exception{
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		
		shopFinancial.setShopId(Integer.valueOf(mg.getShopIdstr()));
		List<ShopFinancial> ss=new ArrayList<>();
		ss = shopFinancialService.getShopFinancialSvcAllListPage(shopFinancial);
		ShopForWxagentFinancial w=new ShopForWxagentFinancial();
		 for(ShopFinancial s:ss){
			 w.setShopId(Integer.valueOf(mg.getShopIdstr()));
			 w.setDay(s.getDay());
			 ShopForWxagentFinancial wx=shopForWxagentFinancialService.getShopForWxagentTotal(w);
			 if(wx!=null){
				 s.setMoneyWd(wx.getAmount());
			 }else{
				 s.setMoneyWd(0);
			 }
		 }
		shopFinancial.setShopFinancialList(ss);
		return shopFinancial;
	}
	
	@RequestMapping("shopFinancialSvcListPage.do")
	@ResponseBody
	public ShopFinancial shopFinancialSvcListPage(ShopFinancial shopFinancial,Model model) throws Exception{
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		
		shopFinancial.setShopId(Integer.valueOf(mg.getShopIdstr()));
		if(shopFinancial.getStatus() != null&&shopFinancial.getStatus()==0){
			shopFinancial.setStatus(100);
		}
		
		shopFinancialList = shopFinancialService.getShopFinancialSvcListPage(shopFinancial);
		ShopForWxagentFinancial w=new ShopForWxagentFinancial();
		 for(ShopFinancial s:shopFinancialList){
			 if(s.getPayType()!=null){
				 if(s.getPayType()==1){
					 w.setShopId(Integer.valueOf(mg.getShopIdstr()));
					 w.setDay(s.getDay());
					 ShopForWxagentFinancial wx=shopForWxagentFinancialService.getShopForWxagentTotal(w);
					 if(wx!=null){
						 s.setMoneyWd(wx.getAmount());
					 }else{
						 s.setMoneyWd(0);
					 }
				 }else{
					 s.setMoneyWd(0);
				 }
			 }else{
				 s.setMoneyWd(0);
			 }
		 }
		
		
		shopFinancial.setShopFinancialList(shopFinancialList);
		return shopFinancial;
	}
	
	/** 编辑前初始化对象*/
	public String shopFinancialAddEditIni() throws Exception{
		ShopFinancial shopFinancial=null;
		if ("edit".equals(doWhat)) {
			shopFinancial = this.shopFinancialService.getShopFinancialById(shopFinancial.getShopFinancialId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String shopFinancialDetail() throws Exception {
//		this.setPare_moduleid(14);	
		ShopFinancial shopFinancial=null;
		shopFinancial = this.shopFinancialService.getShopFinancialById(shopFinancial.getShopFinancialId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		ShopFinancial shopFinancial=null;
		shopFinancial = this.shopFinancialService.getShopFinancialById(shopFinancial.getShopFinancialId());
		return "detail";
	}
	/** 保存新增对象 */
	public String shopFinancialAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //shopFinancialForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        ShopFinancial shopFinancial=null;
		this.shopFinancialService.saveShopFinancial(shopFinancial, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String shopFinancialDelete() throws Exception {	
		ShopFinancial shopFinancial=null;
		this.shopFinancialService.deleteShopFinancial(shopFinancial.getShopFinancialId());
		return "list";
	}	
	public List<ShopFinancial> getShopFinancialList() {
		return this.shopFinancialList;
	}

	public void setShopFinancialList(List<ShopFinancial> shopFinancialList) {
		this.shopFinancialList = shopFinancialList;
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
