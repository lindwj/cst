package com.cst.manager.controller;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.cst.service.model.GoodsAddress;
import com.cst.service.model.Manager;
import com.cst.service.model.Nation;
import com.cst.service.model.PreOrders;
import com.cst.service.model.Product;
import com.cst.service.model.Shop;
import com.cst.service.model.Stock;
import com.cst.service.util.Common;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cst.service.ContractNoService;
import com.cst.service.ManagerService;
import com.cst.service.NationService;
import com.cst.service.PreOrdersService;
import com.cst.service.ProductService;
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
	private ManagerService managerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShopService shopService;
	
	// 查询结果
	private List<PreOrders> preOrdersList;
	
	@Autowired
	private ContractNoService contractNoService;
	
	@Autowired
	private NationService nationService;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/preOrdersListPage.do")
	public String preOrdersListPage(PreOrders preOrders, Model model) throws Exception{
		//preOrdersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		preOrders.setShopId(Integer.valueOf(mg.getShopIdstr()));
		
		
		List<Product> productList =  this.productService.getProductByType("10");
		

		List<Manager> managerList =this.managerService.getManagerByShop(Integer.valueOf(mg.getShopIdstr()));
		
		
		ContractNo contractNo =this.contractNoService.getContractNoByShop(Integer.valueOf(mg.getShopIdstr()));
		if(contractNo!=null){
			model.addAttribute("contractNoAmt", contractNo.getContractNoAmt());
		}
		
		model.addAttribute("managerList", managerList);	
		model.addAttribute("productList", productList);	
		
		
		preOrdersList = preOrdersService.getPreOrdersListPage(preOrders);
		model.addAttribute("preOrdersList", preOrdersList);
		model.addAttribute("page", preOrders.getPage());
		model.addAttribute("errcode", preOrders.getErrcode());
		
		return "/jsp/preOrders/preOrdersIndex";
	}
	
	
	
	
	
	@RequestMapping("/preOrdersSvcListPage.do")
	@ResponseBody
	public PreOrders preOrdersSvcListPage(PreOrders preOrders) throws Exception{
		//preOrdersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		preOrders.setShopId(Integer.valueOf(mg.getShopIdstr()));
		
		
		List<Product> productList =  this.productService.getProductByType("10");
		

		List<Manager> managerList =this.managerService.getManagerByShop(Integer.valueOf(mg.getShopIdstr()));
		
		
		ContractNo contractNo =this.contractNoService.getContractNoByShop(Integer.valueOf(mg.getShopIdstr()));
		if(contractNo!=null){
			preOrders.setContractNoAmt(contractNo.getContractNoAmt());
		}
		
		preOrdersList=null;
		
		preOrdersList = preOrdersService.getPreOrdersListPage(preOrders);
		
		
		preOrders.setProductList(productList);
		preOrders.setManagerList(managerList);
		preOrders.setPreOrdersList(preOrdersList);
		preOrders.setPage(preOrders.getPage());
		
		
		return preOrders;
	}
	
	
	
	
	@RequestMapping("/preOrdersListPageSel.do")
	public String preOrdersListPageSel(PreOrders preOrders, Model model) throws Exception{
		//preOrdersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

//		preOrders.setShopId(Integer.valueOf(mg.getShopIdstr()));
		
		
		preOrdersList = preOrdersService.getPreOrdersSelListPage(preOrders);
		model.addAttribute("preOrdersList", preOrdersList);
		model.addAttribute("page", preOrders.getPage());
		model.addAttribute("errcode", preOrders.getErrcode());
		
		if(preOrders.getMenuId()!=null&&preOrders.getMenuId()>0){
			subject.getSession().setAttribute("menuId", preOrders.getMenuId());
			}
		
		return "/jsp/preOrders/preOrdersIndexSel";
	}
	
	
	
	
	/** 编辑前初始化对象*/
	@RequestMapping("/preOrdersAddEditIni.do")
	public String preOrdersAddEditIni(PreOrders preOrders,Model model) throws Exception{
		
		model.addAttribute("errcode", preOrders.getErrcode());
		preOrders = this.preOrdersService.getPreOrdersById(preOrders.getPreOrdersId());
		
		
		List<Product> productList =  this.productService.getProductByType("10");
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		List<Manager> managerList =this.managerService.getManagerByShop(Integer.valueOf(mg.getShopIdstr()));
		
		
		ContractNo contractNo =this.contractNoService.getContractNoByShop(Integer.valueOf(mg.getShopIdstr()));
		if(contractNo!=null){
			model.addAttribute("contractNoAmt", contractNo.getContractNoAmt());
		}
		
		model.addAttribute("managerList", managerList);	
		model.addAttribute("productList", productList);		
		model.addAttribute("preOrders", preOrders);
		return "/jsp/preOrders/preOrdersEdit";
		
	}
	
	
	
	@RequestMapping("/preOrdersAddEditIniSel.do")
	public String preOrdersAddEditIniSel(PreOrders preOrders,Model model) throws Exception{
		
		model.addAttribute("errcode", preOrders.getErrcode());
		preOrders = this.preOrdersService.getPreOrdersById(preOrders.getPreOrdersId());
		
		
		List<Product> productList =  this.productService.getProductByType("10");
		
		
		if(preOrders.getShopId()!=null){

		List<Manager> managerList =this.managerService.getManagerByShop(preOrders.getShopId());
		
		
		ContractNo contractNo =this.contractNoService.getContractNoByShop(preOrders.getShopId());
		if(contractNo!=null){
			model.addAttribute("contractNoAmt", contractNo.getContractNoAmt());
		}
		
		model.addAttribute("managerList", managerList);	
		
		}
		
		
		model.addAttribute("productList", productList);		
		model.addAttribute("preOrders", preOrders);
		return "/jsp/preOrders/preOrdersEditSel";
		
	}
	
	
	
	
	/** 查看对象*/
	public String preOrdersDetail() throws Exception {
//		this.setPare_moduleid(14);	
		PreOrders preOrders=null;
		preOrders = this.preOrdersService.getPreOrdersById(preOrders.getPreOrdersId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		PreOrders preOrders=null;
		preOrders = this.preOrdersService.getPreOrdersById(preOrders.getPreOrdersId());
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/preOrdersAddEdit.do")
	public ModelAndView preOrdersAddEdit(PreOrders preOrders, ModelAndView model) throws Exception {		
		
		String validate = null;
		// add 新增 edit 修改
		if ("add".equals(preOrders.getDowhat())) {


			if (preOrders.getProductUuid() == null || preOrders.getProductUuid().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getUserPhone() == null || preOrders.getUserPhone().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveName() == null || preOrders.getReceiveName().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveAccount() == null || preOrders.getReceiveAccount().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveAddress() == null || preOrders.getReceiveAddress().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveMobile() == null || preOrders.getReceiveMobile().length() <= 0) {
				validate = "101";
			}
			
			
			if (preOrders.getReceiveMobile().length() != 11 || preOrders.getUserPhone().length() != 11) {
				validate = "101";
			}
			
			if (preOrders.getReceivePeriod() == null || preOrders.getReceivePeriod() <= 0 || preOrders.getReceivePeriod() >= 12) {
				validate = "101";
			}
			if (preOrders.getSaleManager() == null || preOrders.getSaleManager() <= 0) {
				validate = "101";
			}
			if (preOrders.getContractNo() == null || preOrders.getContractNo() <= 0) {
				validate = "101";
			}
			
			
			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			preOrders.setUpdateManagerId(mg.getManagerId());
			
			
			preOrders.setShopId(Integer.valueOf(mg.getShopIdstr()));
			
			
			ContractNo contractNo =this.contractNoService.getContractNoByShop(preOrders.getShopId());
			if(contractNo==null){
				validate = "101";	
			}
			
			if(contractNo!=null){
				if(preOrders.getContractNo()>contractNo.getContractNoAmt()){
					validate = "101";	
				}
			}
			
			
			
			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/preOrders/preOrdersListPage.do");
				return model;
			}

			// 校验结束

			// 1 表示 激活 ，这里直接赋予 激活状态
			preOrders.setStatus(1);

			
			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 10000;
			preOrders.setCode(mg.getManagerId() + time.format(now) + i);
			
			
			
			
			
			

		} 
		
		else if ("edit".equals(preOrders.getDowhat())) {
			
			if (preOrders.getProductUuid() == null || preOrders.getProductUuid().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getUserPhone() == null || preOrders.getUserPhone().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveName() == null || preOrders.getReceiveName().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveAccount() == null || preOrders.getReceiveAccount().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveAddress() == null || preOrders.getReceiveAddress().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveMobile() == null || preOrders.getReceiveMobile().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveMobile().length() != 11 || preOrders.getUserPhone().length() != 11) {
				validate = "101";
			}
			if (preOrders.getReceivePeriod() == null || preOrders.getReceivePeriod() <= 0 || preOrders.getReceivePeriod() >= 12) {
				validate = "101";
			}
			if (preOrders.getSaleManager() == null || preOrders.getSaleManager() <= 0) {
				validate = "101";
			}
			if (preOrders.getContractNo() == null || preOrders.getContractNo() <= 0) {
				validate = "101";
			}
			
			
			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			preOrders.setUpdateManagerId(mg.getManagerId());
			
			
			ContractNo contractNo =this.contractNoService.getContractNoByShop(Integer.valueOf(mg.getShopIdstr()));
			if(contractNo==null){
				validate = "101";	
			}
			
			if(contractNo!=null){
				if(preOrders.getContractNo()>contractNo.getContractNoAmt()){
					validate = "101";	
				}
			}
			

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.addObject("preOrdersId", preOrders.getPreOrdersId());
				model.setViewName("redirect:/preOrders/preOrdersAddEditIni.do");
				return model;
			}
			
			
			// 1 表示 激活 ，这里直接赋予 激活状态
				preOrders.setStatus(1);

						
						// 订单编号
				Date now = new Date();
				SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
				SecureRandom random = new SecureRandom();
				int i = Math.abs(random.nextInt()) % 10000;
				preOrders.setCode(mg.getManagerId() + time.format(now) + i);
			
			
		}

		try {
			
			this.preOrdersService.savePreOrders(preOrders,preOrders.getDowhat());
			
			
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			

			if ("edit".equals(preOrders.getDowhat())) {
			model.addObject("preOrdersId", preOrders.getPreOrdersId());
			model.setViewName("redirect:/preOrders/preOrdersAddEditIni.do");
			return model;
			}
			
			
			
			
		}
		
		

		model.setViewName("redirect:/preOrders/preOrdersListPage.do");
		return model;
		
		
		
	}
	
	
	
	@RequestMapping("/preOrdersAddEditSvc.do")
	@ResponseBody
	public int preOrdersAddEditSvc(PreOrders preOrders) throws Exception {		
		
		String validate = null;
		// add 新增 edit 修改
		if ("add".equals(preOrders.getDowhat())) {


			if (preOrders.getProductUuid() == null || preOrders.getProductUuid().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getUserPhone() == null || preOrders.getUserPhone().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveName() == null || preOrders.getReceiveName().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveAccount() == null || preOrders.getReceiveAccount().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveAddress() == null || preOrders.getReceiveAddress().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveMobile() == null || preOrders.getReceiveMobile().length() <= 0) {
				validate = "101";
			}
			
			
			if (preOrders.getReceiveMobile().length() != 11 || preOrders.getUserPhone().length() != 11) {
				validate = "101";
			}
			
			if (preOrders.getReceivePeriod() == null || preOrders.getReceivePeriod() <= 0 || preOrders.getReceivePeriod() >= 12) {
				validate = "101";
			}
			if (preOrders.getSaleManager() == null || preOrders.getSaleManager() <= 0) {
				validate = "101";
			}
			if (preOrders.getContractNo() == null || preOrders.getContractNo() <= 0) {
				validate = "101";
			}
			
			
			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			preOrders.setUpdateManagerId(mg.getManagerId());
			
			
			preOrders.setShopId(Integer.valueOf(mg.getShopIdstr()));
			
			
			ContractNo contractNo =this.contractNoService.getContractNoByShop(preOrders.getShopId());
			if(contractNo==null){
				validate = "101";	
			}
			
			if(contractNo!=null){
				if(preOrders.getContractNo()>contractNo.getContractNoAmt()){
					validate = "101";	
				}
			}
			
			
			
			if ("101".equals(validate)) {
				
				return 101;
			}

			// 校验结束

			// 1 表示 激活 ，这里直接赋予 激活状态
			preOrders.setStatus(1);

			
			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 10000;
			preOrders.setCode(mg.getManagerId() + time.format(now) + i);
			
			
			
			
			
			

		} 
		
		else if ("edit".equals(preOrders.getDowhat())) {
			
			if (preOrders.getProductUuid() == null || preOrders.getProductUuid().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getUserPhone() == null || preOrders.getUserPhone().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveName() == null || preOrders.getReceiveName().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveAccount() == null || preOrders.getReceiveAccount().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveAddress() == null || preOrders.getReceiveAddress().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveMobile() == null || preOrders.getReceiveMobile().length() <= 0) {
				validate = "101";
			}
			if (preOrders.getReceiveMobile().length() != 11 || preOrders.getUserPhone().length() != 11) {
				validate = "101";
			}
			if (preOrders.getReceivePeriod() == null || preOrders.getReceivePeriod() <= 0 || preOrders.getReceivePeriod() >= 12) {
				validate = "101";
			}
			if (preOrders.getSaleManager() == null || preOrders.getSaleManager() <= 0) {
				validate = "101";
			}
			if (preOrders.getContractNo() == null || preOrders.getContractNo() <= 0) {
				validate = "101";
			}
			
			
			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			preOrders.setUpdateManagerId(mg.getManagerId());
			
			
			ContractNo contractNo =this.contractNoService.getContractNoByShop(Integer.valueOf(mg.getShopIdstr()));
			if(contractNo==null){
				validate = "101";	
			}
			
			if(contractNo!=null){
				if(preOrders.getContractNo()>contractNo.getContractNoAmt()){
					validate = "101";	
				}
			}
			

			if ("101".equals(validate)) {
				
				return 101;
			}
			
			
			// 1 表示 激活 ，这里直接赋予 激活状态
				preOrders.setStatus(1);

						
						// 订单编号
				Date now = new Date();
				SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
				SecureRandom random = new SecureRandom();
				int i = Math.abs(random.nextInt()) % 10000;
				preOrders.setCode(mg.getManagerId() + time.format(now) + i);
			
			
		}

		try {
			
			this.preOrdersService.savePreOrders(preOrders,preOrders.getDowhat());
			
			
		} catch (DuplicateKeyException be) {
			// 重复值
			
			return 102;
			
		}
		
		

		return 100;
		
		
		
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
