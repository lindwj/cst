package com.cst.manager.controller;

import java.text.SimpleDateFormat;
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
import com.cst.service.model.ShopAgentFinancial;
import com.cst.service.model.WxAgentFinancial;
import com.cst.service.ShopAgentFinancialService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/ShopAgentFinancial")
public class ShopAgentFinancialController{
	
	// 业务逻辑对象
	@Autowired
	private ShopAgentFinancialService shopAgentFinancialService;
	
	// 查询结果
	private List<ShopAgentFinancial> shopAgentFinancialList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	@RequestMapping("getShopFinancialSvcList.do")
	@ResponseBody
	public ShopAgentFinancial getShopFinancialSvcList(ShopAgentFinancial shopAgentFinancial,Model model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		List<ShopAgentFinancial> shopAgentFinancialList=new ArrayList<>();
		shopAgentFinancial.setShopId(Integer.valueOf(mg.getShopIdstr()));
		shopAgentFinancialList = shopAgentFinancialService.getShopAgentFinancial(shopAgentFinancial);
		shopAgentFinancial.setShopAgentFinancials(shopAgentFinancialList);
		return shopAgentFinancial;
	}
	
	@RequestMapping("getShopFinancialSvcListPageDD.do")
	@ResponseBody
	public ShopAgentFinancial getShopFinancialSvcListPageDD(ShopAgentFinancial shopAgentFinancial,Model model) throws Exception{
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		
		List<ShopAgentFinancial> shopAgentFinancialList=new ArrayList<>();
		shopAgentFinancial.setAgentId(mg.getManagerId());
		
		if(shopAgentFinancial.getStatus() != null&&shopAgentFinancial.getStatus()==0){
			shopAgentFinancial.setStatus(100);
		}
		
		shopAgentFinancialList = shopAgentFinancialService.getShopAgentFinancialListPageDD(shopAgentFinancial);
		
		
		
		shopAgentFinancial.setShopAgentFinancials(shopAgentFinancialList);
		return shopAgentFinancial;
	}
	
	@RequestMapping("updateShop.do")
	@ResponseBody
	public int updateShop(ShopAgentFinancial shopAgentFinancial,Model model) throws Exception{
		if(shopAgentFinancial.getShopAgentFinancials()!=null){
			for(ShopAgentFinancial s:shopAgentFinancial.getShopAgentFinancials()){
				if(s.getShopAgentFinancialId()!=null){
					shopAgentFinancialService.saveShopAgentFinancial(s,"edit");
				}
			}
			return 1;
		}else{
			return 0;
		}
	}
	
	/** 执行搜索 */
	public String shopAgentFinancialListPage() throws Exception{
		//shopAgentFinancialForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		ShopAgentFinancial shopAgentFinancial=null;
		shopAgentFinancialList = shopAgentFinancialService.getShopAgentFinancialListPage(shopAgentFinancial);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String shopAgentFinancialAddEditIni() throws Exception{
		ShopAgentFinancial shopAgentFinancial=null;
		if ("edit".equals(doWhat)) {
			shopAgentFinancial = this.shopAgentFinancialService.getShopAgentFinancialById(shopAgentFinancial.getShopAgentFinancialId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String shopAgentFinancialDetail() throws Exception {
//		this.setPare_moduleid(14);	
		ShopAgentFinancial shopAgentFinancial=null;
		shopAgentFinancial = this.shopAgentFinancialService.getShopAgentFinancialById(shopAgentFinancial.getShopAgentFinancialId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		ShopAgentFinancial shopAgentFinancial=null;
		shopAgentFinancial = this.shopAgentFinancialService.getShopAgentFinancialById(shopAgentFinancial.getShopAgentFinancialId());
		return "detail";
	}
	/** 保存新增对象 */
	public String shopAgentFinancialAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //shopAgentFinancialForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        ShopAgentFinancial shopAgentFinancial=null;
		this.shopAgentFinancialService.saveShopAgentFinancial(shopAgentFinancial, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String shopAgentFinancialDelete() throws Exception {	
		ShopAgentFinancial shopAgentFinancial=null;
		this.shopAgentFinancialService.deleteShopAgentFinancial(shopAgentFinancial.getShopAgentFinancialId());
		return "list";
	}	
	public List<ShopAgentFinancial> getShopAgentFinancialList() {
		return this.shopAgentFinancialList;
	}

	public void setShopAgentFinancialList(List<ShopAgentFinancial> shopAgentFinancialList) {
		this.shopAgentFinancialList = shopAgentFinancialList;
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
