package com.cst.web.controller;

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

import com.cst.service.model.GoodsAddress;
import com.cst.service.model.Manager;
import com.cst.service.model.Nation;
import com.cst.service.model.PreOrders;
import com.cst.service.model.Shop;
import com.cst.service.model.Stock;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cst.service.NationService;
import com.cst.service.PreOrdersService;
import com.cst.service.ShopService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/preOrders")
public class PreOrdersController{
	
	// 业务逻辑对象
	@Autowired
	private PreOrdersService preOrdersService;
	
	@Autowired
	private ShopService shopService;
	
	// 查询结果
	private List<PreOrders> preOrdersList;
	
	@Autowired
	private NationService nationService;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String preOrdersListPage() throws Exception{
		//preOrdersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		PreOrders preOrders=null;
		preOrdersList = preOrdersService.getPreOrdersListPage(preOrders);
		return "list";
	}
	
	
	@RequestMapping("/preOrdersListPageUser.do")
	public String preOrdersListPageUser(PreOrders preOrders, Model model) throws Exception{
		//preOrdersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

//		preOrders.setShopId(Integer.valueOf(mg.getShopIdstr()));
		preOrders.setUserPhone(user.getMobile());
		
		preOrdersList = preOrdersService.getPreOrdersUserListPage(preOrders);
		model.addAttribute("preOrdersList", preOrdersList);
		model.addAttribute("page", preOrders.getPage());
		model.addAttribute("errcode", preOrders.getErrcode());
		
		return "/jsp/orders/ordersListSel";
	}
	
	
	
	
	/** 编辑前初始化对象*/
	public String preOrdersAddEditIni() throws Exception{
		PreOrders preOrders=null;
		if ("edit".equals(doWhat)) {
			preOrders = this.preOrdersService.getPreOrdersById(preOrders.getPreOrdersId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	@RequestMapping("/preOrdersDetail.do")
	public String preOrdersDetail(PreOrders preOrders, Model model) throws Exception {
//		this.setPare_moduleid(14);	
		model.addAttribute("errcode", preOrders.getErrcode());
		
		preOrders = this.preOrdersService.getPreOrdersUserById(preOrders.getPreOrdersId());
		
		model.addAttribute("preOrders", preOrders);
		
		return "/jsp/orders/ordersDetailSel";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		PreOrders preOrders=null;
		preOrders = this.preOrdersService.getPreOrdersById(preOrders.getPreOrdersId());
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/preOrdersAddEdit.do")
	public String preOrdersAddEdit(PreOrders preOrders) throws Exception {		
		//this.setPare_moduleid(14);
        //preOrdersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		this.preOrdersService.savePreOrders(preOrders, this.doWhat);
		return "detail";
	}
	
	
	
	@RequestMapping("/preOrdersAdd.do")
	@ResponseBody
	public String preOrdersAdd(PreOrders preOrders,HttpServletRequest request) throws Exception {		
		//this.setPare_moduleid(14);
        //preOrdersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		String validate = null;
		
		if (preOrders.getApplyPhone() == null || preOrders.getApplyPhone().length() != 11) {
			validate = "101";
		}

		if (preOrders.getApplyName() == null || preOrders.getApplyName().length() == 0) {
			validate = "101";
		}
		
		
		if ("101".equals(validate)) {
			return validate;
		}

		
		preOrders.setStatus(0);
		
		
		

		//获取ip 定位 位置
		String ip = Common.getIpAddr(request);
		
		String json = Common.getJsonString("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
		
		ObjectMapper mapper = new ObjectMapper();  
        JsonNode rootNode = mapper.readTree(json); // 读取Json
        
		if(0==rootNode.path("code").asInt()){
			
			JsonNode data = rootNode.path("data"); 
			
			//匹配店
			String citycode=data.path("city_id").asText();
			Nation nation= nationService.getNationByCode(citycode);
			
			
			if(nation!=null && nation.getId()>0){
			Shop shop =new Shop();
			shop.setCity(nation.getId());
			List<Shop> shopList = this.shopService.getShopByAddress(shop);
			
			if(shopList!=null && shopList.size()>0){
//				model.addAttribute("shopName", shopList.get(0).getName());
				preOrders.setShopName(shopList.get(0).getName());
				preOrders.setShopId(shopList.get(0).getShopId());
				
			}else{
				preOrders.setShopName("无锡市通江大道店");
				preOrders.setShopId(3);
			}
			
			}else{
				preOrders.setShopName("无锡市通江大道店"); 
				preOrders.setShopId(3);
			}
			
		
		}else{
			preOrders.setShopName("无锡市通江大道店");
			preOrders.setShopId(3);
		}
	
		
		this.preOrdersService.savePreOrders(preOrders, "add");
		
		
		return "ok";
	}
	/**删除对象*/
	public String preOrdersDelete() throws Exception {	
		PreOrders preOrders=null;
		this.preOrdersService.deletePreOrders(preOrders.getPreOrdersId());
		return "list";
	}	
	public List<PreOrders> getPreOrdersList() {
		return this.preOrdersList;
	}

	public void setPreOrdersList(List<PreOrders> preOrdersList) {
		this.preOrdersList = preOrdersList;
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
