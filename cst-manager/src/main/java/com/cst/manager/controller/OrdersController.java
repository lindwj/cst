package com.cst.manager.controller;

import java.security.SecureRandom;
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

import com.cst.service.model.GoodsAddress;
import com.cst.service.model.Manager;
import com.cst.service.model.Nation;
import com.cst.service.model.Orders;
import com.cst.service.model.Product;
import com.cst.service.model.ProductAttribute;
import com.cst.service.model.ProductPic;
import com.cst.service.model.Shop;
import com.cst.service.model.Stock;
import com.cst.service.model.User;
import com.cst.service.GoodsAddressService;
import com.cst.service.OrdersService;
import com.cst.service.ProductService;
import com.cst.service.ShopService;
import com.cst.service.StockService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/orders")
public class OrdersController{
	
	// 业务逻辑对象
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private GoodsAddressService goodsAddressService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private StockService stockService;
	
	// 查询结果
	private List<Orders> ordersList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	
	
	
	@RequestMapping("/ordersForBdhListPage.do")
	public String ordersForBdhListPage(Orders orders, Model model) throws Exception{
		//ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		if(orders.getStatusstr()==null || orders.getStatusstr().length()<=0){
			//查询全部订单
			orders.setStatus(1);
		}else{
			orders.setStatus(Integer.valueOf(orders.getStatusstr()));
		}
		
		Subject subject = SecurityUtils.getSubject();
//		Manager mg = (Manager) subject.getPrincipal();
		
//		orders.setShopId(Integer.valueOf(mg.getShopIdstr()));
		
		ordersList = ordersService.getOrdersForBdhListPage(orders);
		
		if(orders.getMenuId()!=null&&orders.getMenuId()>0){
			subject.getSession().setAttribute("menuId", orders.getMenuId());
			}
		
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("page", orders.getPage());
		
		return "/jsp/orders/ordersearch";
		
	}
	
	
	
	/** 执行搜索 */
	@RequestMapping("/ordersListPage.do")
	public String ordersListPage(Orders orders, Model model) throws Exception{
		//ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		if(orders.getStatusstr()==null || orders.getStatusstr().length()<=0){
			//查询全部订单
			orders.setStatus(100);
		}else{
			orders.setStatus(Integer.valueOf(orders.getStatusstr()));
		}
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		
		orders.setShopId(Integer.valueOf(mg.getShopIdstr()));
		
		ordersList = ordersService.getOrdersForAdmListPage(orders);
		
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("page", orders.getPage());
		
		return "/jsp/orders/ordersList";
		
	}
	
	
	
	
	@RequestMapping("/ordersSvcListPage.do")
	@ResponseBody
	public Orders ordersSvcListPage(Orders orders) throws Exception{
		//ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		if(orders.getStatusstr()==null || orders.getStatusstr().length()<=0){
			//查询全部订单
			orders.setStatus(100);
		}else{
			orders.setStatus(Integer.valueOf(orders.getStatusstr()));
		}
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		
//		orders.setShopId(Integer.valueOf(mg.getShopIdstr()));
//		
//		ordersList = ordersService.getOrdersForAdmListPage(orders);
		
//		orders.setOrdersList(ordersList);
		orders.setOrdersList(null);
//		orders.setPage(orders.getPage());
		orders.setPage(null);
		
		
		return orders;
		
	}
	
	@RequestMapping("/getListDD.do")
	@ResponseBody
	public Orders getListDD(Orders orders) throws Exception{
		//ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		if(orders.getStatusstr()==null || orders.getStatusstr().length()<=0){
			//查询全部订单
			orders.setStatus(100);
		}else{
			orders.setStatus(Integer.valueOf(orders.getStatusstr()));
		}
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		
		orders.setShopId(mg.getManagerId());
		
		ordersList = ordersService.getOrdersListPageDD(orders);
		
		orders.setOrdersList(ordersList);
		orders.setPage(orders.getPage());
		
		
		return orders;
		
	}
	
	
	/** 省市县联动 */
	@RequestMapping("/getShop.do")
	@ResponseBody
	public Orders getShop(Orders orders) throws Exception {

		//默认地址
		GoodsAddress goodsAddress=this.goodsAddressService.getGoodsAddressById(orders.getGoodsAddressUuid());
		
		if(goodsAddress!=null){
			Shop shop =new Shop();
			shop.setDistrict(goodsAddress.getDistrict());
			shop.setStreet(goodsAddress.getStreet());
			List<Shop> shopList = this.shopService.getShopByAddress(shop);
			
			if(shopList!=null && shopList.size()>0){
				orders.setShopName( shopList.get(0).getName());
				orders.setShopId(shopList.get(0).getShopId());
				
				//查询库存
				Stock stock=new Stock();
				stock.setProductUuid(orders.getProductUuid());
				stock.setShopId(shopList.get(0).getShopId());
				stock =stockService.getStockByPrd(stock);
				//显示库存
				if(stock!=null){
//				stock.setShowCapacity(stock.getShowCapacity()-stock.getSellCapacity());
					//设置库存值
				orders.setCapacity(stock.getShowCapacity()-stock.getSellCapacity());
				}else{
					orders.setCapacity(0);
				}
			}else{
				orders.setShopName( "此地区不支持配送");
				orders.setShopId(0);
			}
		}
		
		return orders;
	
	}
	
	
	@RequestMapping("/setStatus.do")
	@ResponseBody
	public int setStatus(Orders orders) throws Exception {

		
		orders.setStatus(Integer.valueOf(orders.getStatusstr()));
		this.ordersService.setStatus(orders);
		
		return 0;
	
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/ordersAddEditIni.do")
	public String ordersAddEditIni(Orders orders, Model model) throws Exception{
		model.addAttribute("errcode", orders.getErrcode());
		
		
		Product product = this.productService.getProductByUuid(orders.getProductUuid());
		
		//如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			User user = (User) subject.getPrincipal();
			
			GoodsAddress goodsAddress =new GoodsAddress();
			goodsAddress.setCreateByUser(user.getUserId());
			
			//地址列表
			List<GoodsAddress> goodsAddressList= this.goodsAddressService.getGoodsAddressList(goodsAddress);
			model.addAttribute("goodsAddressList", goodsAddressList);
			
			//默认地址
			goodsAddress=this.goodsAddressService.getGoodsAddressByDefault(goodsAddress);
			model.addAttribute("goodsAddress", goodsAddress);
			
			
			if(goodsAddress!=null){
				Shop shop =new Shop();
				shop.setDistrict(goodsAddress.getDistrict());
				shop.setStreet(goodsAddress.getStreet());
				List<Shop> shopList = this.shopService.getShopByAddress(shop);
				
				if(shopList!=null && shopList.size()>0){
					model.addAttribute("shopName", shopList.get(0).getName());
					model.addAttribute("shopId", shopList.get(0).getShopId());
					
					//查询库存
					Stock stock=new Stock();
					stock.setProductUuid(product.getProductUuid());
					stock.setShopId(shopList.get(0).getShopId());
					stock =stockService.getStockByPrd(stock);
					//显示库存
					if(stock!=null){
//					stock.setShowCapacity(stock.getShowCapacity()-stock.getSellCapacity());
					model.addAttribute("showCapacity", stock.getShowCapacity()-stock.getSellCapacity());
					}else{
						model.addAttribute("showCapacity", 0);
					}
				}else{
					model.addAttribute("shopName", "此地区不支持配送");
					model.addAttribute("shopId", 0);
				}
			}else{
				//没有默认地址 跳转到地址维护页面
				
				return "redirect:/goodsAddress/goodsAddressListPage.do";
				
				
			}
		}
		
		//总金额
		orders.setTotalFromBdh(orders.getCapacity()*product.getPrice());
		
		
		model.addAttribute("product", product);
		
		model.addAttribute("orders", orders);
		
		
		return "/jsp/buy/buy";
	}
	
	/** 查看对象*/
	@RequestMapping("/ordersDetail.do")
	public String ordersDetail(Orders orders, Model model) throws Exception {
//		this.setPare_moduleid(14);	
		orders = this.ordersService.getOrdersById(orders.getCode());
		
		model.addAttribute("orders", orders);
		return "/jsp/orders/ordersDetail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Orders orders=null;
//		orders = this.ordersService.getOrdersById(orders.getOrdersId());
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/ordersAddEdit.do")
	public String ordersAddEdit(Orders orders, Model model) throws Exception {		
		//this.setPare_moduleid(14);
        //ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		//校验是否有匹配的店 104
		if(orders.getShopId()==null || orders.getShopId()<=0){
			return "redirect:/orders/ordersAddEditIni.do?productUuid="+orders.getProductUuid()+"&capacity="+orders.getCapacity()+"&errcode=104";
		}
		
		Stock stock=new Stock();
		
		//如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
			if(subject.isAuthenticated()){
			User user = (User) subject.getPrincipal();
			
			//订单编号
			Date now =new Date();
			SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt())%10000;
			orders.setCode(user.getUserId()+time.format(now)+i);
			
			orders.setCreateByUser(user.getUserId());
			
			//0 支付宝
			orders.setPayType(0);
			
			
			//查询库存
			
			stock.setProductUuid(orders.getProductUuid());
			stock.setShopId(orders.getShopId());
			stock =stockService.getStockByPrd(stock);
			//显示库存
			if(stock!=null){
				if(orders.getCapacity()>(stock.getShowCapacity()-stock.getSellCapacity())){
					//库存不足 103
					return "redirect:/orders/ordersAddEditIni.do?productUuid="+orders.getProductUuid()+"&capacity="+orders.getCapacity()+"&errcode=103";
				}
			}else{
				//库存未设置
				return "redirect:/orders/ordersAddEditIni.do?productUuid="+orders.getProductUuid()+"&capacity="+orders.getCapacity()+"&errcode=103";
			}
			
			
			//计算总金额  规避前端恶意修改总金额
			
			double total= orders.getPrice()*orders.getCapacity();
			
			if(total != orders.getTotalFromBdh()){
				orders.setTotalFromBdh(total);
			}
			
		}
		
		
		this.ordersService.saveOrders(orders, "add");
		
		//增加售卖数量
		stock.setSellCapacity(orders.getCapacity());
		stockService.updateSell(stock);
		
		
		return "/jsp/buy/pay";
	}
	
	
	/** 订单支付 */
	@RequestMapping("/ordersPay.do")
	public String ordersPay(Orders orders, Model model) throws Exception {		
		//this.setPare_moduleid(14);
        //ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		orders = this.ordersService.getOrdersById(orders.getCode());
		
		model.addAttribute("orders", orders);
		return "/jsp/buy/pay";
	}
	
	
	/**删除对象*/
	@RequestMapping("/ordersDelete.do")
	public String ordersDelete(Orders orders, Model model) throws Exception {	
		this.ordersService.deleteOrders(orders.getCode());
		
		return "redirect:/orders/ordersListPage.do?status="+9;
	}	
	public List<Orders> getOrdersList() {
		return this.ordersList;
	}

	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
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
