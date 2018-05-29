package com.cst.manager.controller;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.cst.service.model.IndexBanner;
import com.cst.service.model.Logistics;
import com.cst.service.model.LogisticsProduct;
import com.cst.service.model.Manager;
import com.cst.service.model.Ordergoods;
import com.cst.service.model.OrdergoodsProduct;
import com.cst.service.model.Orders;
import com.cst.service.model.Parameter;
import com.cst.service.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.mysql.jdbc.log.Log;
import com.cst.service.LogisticsProductService;
import com.cst.service.LogisticsService;
import com.cst.service.OrdergoodsProductService;
import com.cst.service.OrdergoodsService;
import com.cst.service.ParameterService;
import com.cst.service.ProductService;
import com.cst.service.dao.OrdergoodsMapper;
import com.cst.service.dao.OrdergoodsProductMapper;



/**
 * @author lind
 */

@Controller
@RequestMapping("/Ordergoods")
public class OrdergoodsController{
	
	// 业务逻辑对象
	@Autowired
	private OrdergoodsService ordergoodsService;
	@Autowired
	private OrdergoodsMapper ordergoodsMapper;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private LogisticsProductService logisticsProductService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private OrdergoodsProductMapper ordergoodsProductMapper;
	@Autowired
	private LogisticsService logisticsService;
	
	// 查询结果
	private List<Ordergoods> ordergoodsList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String ordergoodsListPage() throws Exception{
		Ordergoods ordergoods=null;
		ordergoodsList = ordergoodsService.getOrdergoodsListPage(ordergoods);
		return "list";
	}
	
	/** 查询订单详情信息*/
	@RequestMapping("/getorder.do")
	public String getorder(Ordergoods ordergoods,Model model) throws Exception{
		Ordergoods o=ordergoodsService.getOrdergoodsByCode(ordergoods.getCode());
		Logistics logisticss=new Logistics();
		logisticss.setOrdergoodsCode(ordergoods.getCode());
		List<Logistics> logisticslist=logisticsService.getLogisticsList(logisticss);
		model.addAttribute("logistics",logisticslist);
		model.addAttribute("ordergoods", o);
		return "/jsp/ordergoods/orderDetail";
	}
	
	/** 保存/修改订单基本信息 */
	@RequestMapping("/updateOrder.do")
	public String updateOrder(Ordergoods ordergoods,Model model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now=new Date();
		ordergoods.setStoreTime(now);
		ordergoods.setStoreMan(mg.getManagerId());
		if(ordergoods.getCode()!=null){
			ordergoodsMapper.updateOrdergoodsCode(ordergoods);
		}
		model.addAttribute("code", ordergoods.getCode());
		return "redirect:/Ordergoods/getOrderDetail.do";
	}
	
	/** 获取订单商品*/
	@RequestMapping("/getLogistics.do")
	public String getLogistics(Ordergoods ordergoods,Model model)throws Exception{
		Ordergoods ordergoods2=ordergoodsService.getOrderByCode(ordergoods.getCode());
		LogisticsProduct logist=new LogisticsProduct();
		List<LogisticsProduct> l=logisticsProductService.getLogisticsProductListPage(logist);
		List<LogisticsProduct> logisticsProducts=new ArrayList<LogisticsProduct>();
		if(l==null||l.size()==0){
			for(OrdergoodsProduct ordergoodsProduct:ordergoods2.getOrdergoodsProducts()){
				ordergoodsProduct.setNum(0);
			}
		}else{
			for(LogisticsProduct ll:l){
				if(ll.getOrdergoodsCode().equals(ordergoods.getCode())){
					if(logisticsProducts!=null){
						logisticsProducts=logisticsProductService.getNum(ordergoods.getCode());
					}
				}
			}
		}
		if(logisticsProducts==null||logisticsProducts.size()==0){
			for(OrdergoodsProduct ordergoodsProduct:ordergoods2.getOrdergoodsProducts()){
				ordergoodsProduct.setNum(0);
			}
		}else{
			Iterator<OrdergoodsProduct> it=ordergoods2.getOrdergoodsProducts().iterator();
			while(it.hasNext()){
				OrdergoodsProduct ordergoodsProduct=it.next();
				for(LogisticsProduct logistics:logisticsProducts){
					if(ordergoodsProduct.getProductUuid().equals(logistics.getProductUuid())){
						if(logistics.getTotalNum()==ordergoodsProduct.getApplyNum()){
							it.remove();
						}else{
							ordergoodsProduct.setNum(logistics.getTotalNum());
						}
					}
				}
			}
		}
		model.addAttribute("ordergoods",ordergoods2);
		return "/jsp/ordergoods/logisticsDetail";
	}
	
	/** 得到订单详情信息（包括物流） */
	@RequestMapping("/getOrderDetail.do")
	public String getOrderDetail(Ordergoods ordergoods,Model model) throws Exception{
		Ordergoods ordergoods2=ordergoodsService.getOrderByCode(ordergoods.getCode());
		LogisticsProduct logist=new LogisticsProduct();
		List<LogisticsProduct> l=logisticsProductService.getLogisticsProductListPage(logist);
		List<LogisticsProduct> logisticsProducts=new ArrayList<LogisticsProduct>();
		if(l==null||l.size()==0){
			for(OrdergoodsProduct ordergoodsProduct:ordergoods2.getOrdergoodsProducts()){
				ordergoodsProduct.setNum(0);
			}
		}else{
			for(LogisticsProduct ll:l){
				if(ll.getOrdergoodsCode().equals(ordergoods.getCode())){
					if(logisticsProducts!=null){
						logisticsProducts=logisticsProductService.getNum(ordergoods.getCode());
					}
				}
			}
		}
		if(logisticsProducts==null||logisticsProducts.size()==0){
			for(OrdergoodsProduct ordergoodsProduct:ordergoods2.getOrdergoodsProducts()){
				ordergoodsProduct.setNum(0);
			}
		}else{
			for(OrdergoodsProduct ordergoodsProduct:ordergoods2.getOrdergoodsProducts()){
				for(LogisticsProduct logistics:logisticsProducts){
					if(ordergoodsProduct.getProductUuid().equals(logistics.getProductUuid())){
						ordergoodsProduct.setNum(logistics.getTotalNum());
					}
				}
			}
		}
		Logistics logisticss=new Logistics();
		logisticss.setOrdergoodsCode(ordergoods.getCode());
		List<Logistics> logisticslist=logisticsService.getLogisticsList(logisticss);
		model.addAttribute("logistics",logisticslist);
		model.addAttribute("ordergoods",ordergoods2);
		return "/jsp/ordergoods/storeDetail";
	}
	
	/** 执行搜索（市场部） */
	@RequestMapping("/searchMarketList.do")
	public String searchMarketList(Ordergoods ordergoods, Model model) throws Exception {
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		ordergoodsList = ordergoodsService.searchListPage(ordergoods);
		model.addAttribute("ordergoodsList", ordergoodsList);
		model.addAttribute("page", ordergoods.getPage());
		
		if(ordergoods.getMenuId()!=null&&ordergoods.getMenuId()>0){
			subject.getSession().setAttribute("menuId", ordergoods.getMenuId());
		}
		return "/jsp/ordergoods/market";
	}
	/** 执行搜索（产品中心） */
	@RequestMapping("/searchpCenterList.do")
	public String searchpCenterList(Ordergoods ordergoods, Model model) throws Exception {
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		ordergoodsList = ordergoodsService.searchListPage(ordergoods);
		model.addAttribute("ordergoodsList", ordergoodsList);
		model.addAttribute("page", ordergoods.getPage());
		
		if(ordergoods.getMenuId()!=null&&ordergoods.getMenuId()>0){
			subject.getSession().setAttribute("menuId", ordergoods.getMenuId());
			}

		return "/jsp/ordergoods/pCenter";
	}
	/** 执行搜索（财务部） */
	@RequestMapping("/searchMoneyList.do")
	public String searchMoneyList(Ordergoods ordergoods, Model model) throws Exception {
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		ordergoodsList = ordergoodsService.searchListPage(ordergoods);
		model.addAttribute("ordergoodsList", ordergoodsList);
		model.addAttribute("page", ordergoods.getPage());
		
		if(ordergoods.getMenuId()!=null&&ordergoods.getMenuId()>0){
			subject.getSession().setAttribute("menuId", ordergoods.getMenuId());
			}

		return "/jsp/ordergoods/money";
	}
	/** 通过审批（市场部） */
	@RequestMapping("/passMarketList.do")
	public String passMarketList(Ordergoods ordergoods, Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now =new Date();
		if(ordergoods.getMarketRemark()!=null){
			ordergoods.setMarketMan(mg.getManagerId());
			ordergoods.setMyStatus(4);
			ordergoods.setStatus(2);
			ordergoods.setMarketTime(now);
			ordergoodsMapper.updateOrdergoods(ordergoods);
		}
		return "redirect:/Ordergoods/marketListPage.do";
	}
	
	/** 订单完成 */
	@RequestMapping("/success.do")
	public String success(Ordergoods ordergoods, Model model) throws Exception {
		ordergoods.setMyStatus(10);
		ordergoods.setStatus(4);
		ordergoodsMapper.updateOrdergoods(ordergoods);
		return "redirect:/Ordergoods/marketListPage.do";
	}
	
	/** 拒绝审批（市场部） */
	@RequestMapping("/refuseMarketList.do")
	public String refuseMarketList(Ordergoods ordergoods, Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now =new Date();
		if(ordergoods.getMarketRemark()!=null){
			ordergoods.setMarketMan(mg.getManagerId());
			ordergoods.setMyStatus(-2);
			ordergoods.setStatus(-1);
			ordergoods.setMarketTime(now);
			ordergoodsMapper.updateOrdergoods(ordergoods);
		}
		return "redirect:/Ordergoods/marketListPage.do";
	}
	/** 通过审批（产品中心） */
	@RequestMapping("/passpCenterList.do")
	public String passpCenterList(Ordergoods ordergoods, Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now =new Date();
		Ordergoods o=ordergoodsService.getOrderByCode(ordergoods.getCode());
		for(OrdergoodsProduct op:o.getOrdergoodsProducts()){
			if(op.getUpdateNum()==null){
				op.setUpdateNum(op.getApplyNum());
				ordergoodsProductMapper.updateOrdergoodsProduct(op);
			}
		}
		if(ordergoods.getPcenterRemark()!=null){
			ordergoods.setPcenterMan(mg.getManagerId());
			ordergoods.setMyStatus(4);
			ordergoods.setPcenterTime(now);
			ordergoodsMapper.updateOrdergoods(ordergoods);
		}
		return "redirect:/Ordergoods/pCenterListPage.do";
	}
	/** 拒绝审批（产品中心） */
	@RequestMapping("/refusepCenterList.do")
	public String refusepCenterList(Ordergoods ordergoods, Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now =new Date();
		if(ordergoods.getPcenterRemark()!=null){
			ordergoods.setPcenterMan(mg.getManagerId());
			ordergoods.setMyStatus(-4);
			ordergoods.setStatus(-1);
			ordergoods.setPcenterTime(now);
			ordergoodsMapper.updateOrdergoods(ordergoods);
		}
		return "redirect:/Ordergoods/pCenterListPage.do";
	}
	/** 通过审批（财务部） */
	@RequestMapping("/passMoneyList.do")
	public String passMoneyList(Ordergoods ordergoods, Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now =new Date();
		if(ordergoods.getAccountantRemark()!=null){
			ordergoods.setAccountantMan(mg.getManagerId());
			ordergoods.setMyStatus(6);
			ordergoods.setAccountantTime(now);
			ordergoodsMapper.updateOrdergoods(ordergoods);
		}
		return "redirect:/Ordergoods/moneyListPage.do";
	}
	/** 拒绝审批（财务部） */
	@RequestMapping("/refuseMoneyList.do")
	public String refuseMoneyList(Ordergoods ordergoods, Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now =new Date();
		if(ordergoods.getAccountantRemark()!=null){
			ordergoods.setAccountantMan(mg.getManagerId());
			ordergoods.setMyStatus(-6);
			ordergoods.setStatus(-1);
			ordergoods.setAccountantTime(now);
			ordergoodsMapper.updateOrdergoods(ordergoods);
		}
		return "redirect:/Ordergoods/moneyListPage.do";
	}
	/** 执行搜索（库管员） */
	@RequestMapping("/searchStoreList.do")
	public String searchStoreList(Ordergoods ordergoods, Model model) throws Exception {
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		ordergoodsList = ordergoodsService.searchListPage(ordergoods);
		model.addAttribute("ordergoodsList", ordergoodsList);
		model.addAttribute("page", ordergoods.getPage());
		
		if(ordergoods.getMenuId()!=null&&ordergoods.getMenuId()>0){
			subject.getSession().setAttribute("menuId", ordergoods.getMenuId());
			}

		return "/jsp/ordergoods/store";
	}
	/** 专营店订单分页*/
	@RequestMapping("/ordersSvcListPage.do")
	@ResponseBody
	public Ordergoods ordersSvcListPage(Ordergoods ordergoods) throws Exception{
		//ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		ordergoods.setShopMan(mg.getManagerId());
		ordergoodsList = ordergoodsService.getOrdergoodsListPage(ordergoods);
		ordergoods.setOrdergoodList(ordergoodsList);
		ordergoods.setPage(ordergoods.getPage());
		return ordergoods;
	}
	
	/** 市场部订单分页*/
	@RequestMapping("/marketListPage.do")
	public String marketListPage(Ordergoods ordergoods,Model model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		ordergoods.setMyStatus(1);
		ordergoodsList = ordergoodsService.getListPage(ordergoods);
		
		model.addAttribute("ordergoodsList", ordergoodsList);
		model.addAttribute("page", ordergoods.getPage());
		
		if(ordergoods.getMenuId()!=null&&ordergoods.getMenuId()>0){
			subject.getSession().setAttribute("menuId", ordergoods.getMenuId());
		}
		
		return "/jsp/ordergoods/market";
	}
	
	/** 产品中心订单分页*/
	@RequestMapping("/pCenterListPage.do")
	public String pCenterListPage(Ordergoods ordergoods,Model model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		ordergoods.setMyStatus(2);
		ordergoodsList = ordergoodsService.getListPage(ordergoods);
		
		model.addAttribute("ordergoodsList", ordergoodsList);
		model.addAttribute("page", ordergoods.getPage());
		
		if(ordergoods.getMenuId()!=null&&ordergoods.getMenuId()>0){
			subject.getSession().setAttribute("menuId", ordergoods.getMenuId());
			}
		
		return "/jsp/ordergoods/pCenter";
	}
	
	/** 财务部订单分页*/
	@RequestMapping("/moneyListPage.do")
	public String moneyListPage(Ordergoods ordergoods,Model model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		ordergoods.setMyStatus(4);
		ordergoodsList = ordergoodsService.getListPage(ordergoods);
		
		model.addAttribute("ordergoodsList", ordergoodsList);
		model.addAttribute("page", ordergoods.getPage());
		
		if(ordergoods.getMenuId()!=null&&ordergoods.getMenuId()>0){
			subject.getSession().setAttribute("menuId", ordergoods.getMenuId());
			}
		
		return "/jsp/ordergoods/money";
	}
	
	/** 库管员订单分页*/
	@RequestMapping("/storeListPage.do")
	public String storeListPage(Ordergoods ordergoods,Model model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		ordergoods.setMyStatus(6);
		ordergoodsList = ordergoodsService.getListPage(ordergoods);
		
		model.addAttribute("ordergoodsList", ordergoodsList);
		model.addAttribute("page", ordergoods.getPage());
		
		if(ordergoods.getMenuId()!=null&&ordergoods.getMenuId()>0){
			subject.getSession().setAttribute("menuId", ordergoods.getMenuId());
			}
		
		return "/jsp/ordergoods/store";
	}
	
	/** 修改数量*/
	@RequestMapping("/updateNum.do")
	@ResponseBody
	public int updateNum(Ordergoods ordergoods)throws Exception{
		ordergoodsMapper.updateOrdergoodsCode(ordergoods);
		return 1;
	}
	
	/** 修改status的状态*/
	@RequestMapping("/updatestatus.do")
	@ResponseBody
	public int updateStatus(Ordergoods ordergoods) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Ordergoods order=new Ordergoods();
		order.setCode(ordergoods.getCode());
		if(ordergoods.getStatus()==1){
			order.setStatus(0);
			order.setMyStatus(0);
		}else if(ordergoods.getStatus()==0){
			order.setStatus(1);
			order.setMyStatus(1);
			Date now =new Date();
			order.setSubDate(now);
			order.setShopId(Integer.valueOf(mg.getShopIdstr()));
		}
		ordergoodsMapper.setStatus(order);
		return 0;
	}
	
	/** 根据订单号查订单*/
	@RequestMapping("/orderDeatil.do")
	@ResponseBody
	public Ordergoods ordersDetail(Ordergoods ordergoods) throws Exception{
		Ordergoods order= ordergoodsService.getOrdergoodsByCode(ordergoods.getCode());
		return order;
	}
	
	/** 根据订单号获得物流列表*/
	@RequestMapping("/logisticsDetail.do")
	@ResponseBody
	public Logistics logisticsDetail(Ordergoods ordergoods)throws Exception{
		Logistics logisticss=new Logistics();
		logisticss.setOrdergoodsCode(ordergoods.getCode());
		List<Logistics> logisticslist=logisticsService.getLogisticsList(logisticss);
		Logistics logistics=new Logistics();
		logistics.setLogistics(logisticslist);
		return logistics;
	}
	
	/** 获取此分类的所有商品*/
	@RequestMapping("/getProduct.do")
	@ResponseBody
	public List<Product> getProduct(Product product,Model model) throws Exception{
			//根据商品类型查询商品  type=1
			List<Product> productList= productService.getProductByType(product.getTypeId().toString());
			return productList;
	}
	
	/** 根据商品uuid此商品信息*/
	@RequestMapping("/getPrice.do")
	@ResponseBody
	public Product getPrice(Product product,Model model) throws Exception{
			//根据商品uuid查询商品价格
			Product price= productService.getProductByUuid(product.getProductUuid());
			return price;
	}
	
	/** 获取所有分类*/
	@RequestMapping("/getParameter.do")
	@ResponseBody
	public List<Parameter> getParameter(Model model) throws Exception{
			//查询商品分类  type=1
			Parameter parameter =new Parameter();
			parameter.setParameterTypeId(1);
			List<Parameter> pList= parameterService.getParameterList(parameter);
			return pList;
	}
	
	/** 获取所有分类*/
	@RequestMapping("/getProductList.do")
	@ResponseBody
	public List<Product> getProductlist(Model model) throws Exception{
			//查询商品分类  type=1
			List<Product> productList= productService.getProductForIndex("desc");
			return productList;
	}
	
	/** 保存订单*/
	@RequestMapping("/saveordergoods.do")
	@ResponseBody
	public int saveordergoods(Ordergoods ordergoods,ModelAndView model) throws Exception{
		if(ordergoods!=null){
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now =new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
		SecureRandom random = new SecureRandom();
		int i = Math.abs(random.nextInt())%10000;
		ordergoods.setShopMan(mg.getManagerId());
		ordergoods.setShopId(Integer.valueOf(mg.getShopIdstr()));
		ordergoods.setStatus(1);
		ordergoods.setMyStatus(1);
		ordergoods.setCode(mg.getManagerId()+time.format(now)+i);
		ordergoods.setSubDate(now);
		for(OrdergoodsProduct oo:ordergoods.getOrdergoodsProducts()){
			oo.setOrdergoodsCode(mg.getManagerId()+time.format(now)+i);
		}
		ordergoodsMapper.insertOrdergoods(ordergoods);
		ordergoodsProductMapper.insertOrdergoodsProduct(ordergoods.getOrdergoodsProducts());
		
		return 100;
		}
		
		return 101;
	}
	
	/** 保存草稿*/
	@RequestMapping("/saveordergoodsdraft.do")
	@ResponseBody
	public int saveordergoodsdraft(Ordergoods ordergoods,ModelAndView model) throws Exception{
		if(ordergoods!=null){
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now =new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
		SecureRandom random = new SecureRandom();
		int i = Math.abs(random.nextInt())%10000;
		ordergoods.setShopMan(mg.getManagerId());
		ordergoods.setShopId(Integer.valueOf(mg.getShopIdstr()));
		ordergoods.setStatus(0);
		ordergoods.setMyStatus(0);
		ordergoods.setCode(mg.getManagerId()+time.format(now)+i);
		ordergoods.setSubDate(now);
		for(OrdergoodsProduct oo:ordergoods.getOrdergoodsProducts()){
			oo.setOrdergoodsCode(mg.getManagerId()+time.format(now)+i);
		}
		ordergoodsMapper.insertOrdergoods(ordergoods);
		ordergoodsProductMapper.insertOrdergoodsProduct(ordergoods.getOrdergoodsProducts());
		
		return 100;
		}
		
		return 101;
	}
	
	/** 删除订单*/
	@RequestMapping("/orderdel.do")
	@ResponseBody
	public int orderdel(Ordergoods ordergoods) throws Exception{
		if(ordergoods!=null){
			ordergoodsService.deleteOrdergoods(ordergoods.getCode());
		}
		return 0;
	}
	
	/** 编辑前初始化对象*/
	public String ordergoodsAddEditIni() throws Exception{
		Ordergoods ordergoods=null;
		if ("edit".equals(doWhat)) {
			ordergoods = this.ordergoodsService.getOrdergoodsById(ordergoods.getOrdergoodsId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String ordergoodsDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Ordergoods ordergoods=null;
		ordergoods = this.ordergoodsService.getOrdergoodsById(ordergoods.getOrdergoodsId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Ordergoods ordergoods=null;
		ordergoods = this.ordergoodsService.getOrdergoodsById(ordergoods.getOrdergoodsId());
		return "detail";
	}
	/** 保存新增对象 */
	public String ordergoodsAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //ordergoodsForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Ordergoods ordergoods=null;
		this.ordergoodsService.saveOrdergoods(ordergoods, this.doWhat);
		return "detail";
	}
	public List<Ordergoods> getOrdergoodsList() {
		return this.ordergoodsList;
	}

	public void setOrdergoodsList(List<Ordergoods> ordergoodsList) {
		this.ordergoodsList = ordergoodsList;
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
