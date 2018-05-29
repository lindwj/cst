package com.cst.web.controller;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Cart;
import com.cst.service.model.GoodsAddress;
import com.cst.service.model.Orders;
import com.cst.service.model.OrdersDetail;
import com.cst.service.model.Product;
import com.cst.service.model.Shop;
import com.cst.service.model.Stock;
import com.cst.service.model.User;
import com.cst.service.CartService;
import com.cst.service.GoodsAddressService;
import com.cst.service.OrdersDetailService;
import com.cst.service.OrdersService;
import com.cst.service.ProductService;
import com.cst.service.ShopService;
import com.cst.service.StockService;

/**
 * @author lind
 */

@Controller
@RequestMapping("/orders")
public class OrdersController {

	// 业务逻辑对象
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrdersDetailService ordersDetailService;

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

	/** 执行搜索 */
	@RequestMapping("/ordersListPage.do")
	public String ordersListPage(Orders orders, Model model) throws Exception {
		// ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		if (orders.getStatus() == null || orders.getStatus() <= 0) {
			// 查询全部订单
			orders.setStatus(100);
		}

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		orders.setCreateByUser(user.getUserId());

		ordersList = ordersService.getOrdersListPage(orders);

		model.addAttribute("ordersList", ordersList);
		model.addAttribute("page", orders.getPage());

		return "/jsp/orders/ordersList";

	}
	
	
	
	@RequestMapping("/ordersListPageSvc.do")
	@ResponseBody
	public Orders ordersListPageSvc(Orders orders) throws Exception {
		// ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		if (orders.getStatus() == null || orders.getStatus() <= 0) {
			// 查询全部订单
			orders.setStatus(100);
		}

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		orders.setCreateByUser(user.getUserId());

		ordersList = ordersService.getOrdersListPage(orders);
		
		orders.setOrdersList(ordersList);

//		model.addAttribute("ordersList", ordersList);
//		model.addAttribute("page", orders.getPage());

		return orders;

	}
	
	
	

	/** 省市县联动 */
	@RequestMapping("/getShop.do")
	@ResponseBody
	public Orders getShop(Orders orders) throws Exception {

		// 默认地址
		GoodsAddress goodsAddress = this.goodsAddressService.getGoodsAddressById(orders.getGoodsAddressUuid());

		if (goodsAddress != null) {
			Shop shop = new Shop();
//			shop.setDistrict(goodsAddress.getDistrict());
//			shop.setStreet(goodsAddress.getStreet());
			
			List<Shop> shopList=null;
			//根据 街道查询店
			if(goodsAddress.getStreet()!=null && goodsAddress.getStreet()>0){
				shop.setStreet(goodsAddress.getStreet());				
				shopList = this.shopService.getShopByStreet(shop);
			}
			
			
			//如果 街道没有匹配的店
			if (shopList == null || shopList.size() == 0) {
			shop.setCity(goodsAddress.getCity());
			shopList = this.shopService.getShopByAddress(shop);
			
			}
			
			//如果 区县也没有匹配的店  ，就匹配总部
			if (shopList == null || shopList.size() == 0) {

				shopList = new ArrayList<Shop>();
				Shop sh =new Shop();
				sh.setShopId(3);
				sh.setName("无锡市通江大道店");
				sh.setNickName("无锡市通江大道店");
				
				shopList.add(sh);
				}

			if (shopList != null && shopList.size() > 0) {
				orders.setShopName(shopList.get(0).getName());
				orders.setShopId(shopList.get(0).getShopId());

				// 查询库存
				Stock stock = new Stock();
				stock.setProductUuid(orders.getProductUuid());
				stock.setShopId(shopList.get(0).getShopId());
				stock = stockService.getStockByPrd(stock);
				// 显示库存
				if (stock != null) {
					// stock.setShowCapacity(stock.getShowCapacity()-stock.getSellCapacity());
					// 设置库存值
					orders.setCapacity(stock.getShowCapacity() - stock.getSellCapacity());
				} else {
					orders.setCapacity(0);
				}
			} else {
				orders.setShopName("此地区不支持配送");
				orders.setShopId(0);
			}
		}

		return orders;

	}
	
	
	
	
	/** 编辑前初始化对象 */
	@RequestMapping("/ordersAddEditIniSvc.do")
	@ResponseBody
	public Orders ordersAddEditIniSvc(Orders orders) throws Exception {
//		model.addAttribute("errcode", orders.getErrcode());

		Product product = this.productService.getProductByUuid(orders.getProductUuid());

		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();

			GoodsAddress goodsAddress = new GoodsAddress();
			goodsAddress.setCreateByUser(user.getUserId());

			// 地址列表
			List<GoodsAddress> goodsAddressList = this.goodsAddressService.getGoodsAddressList(goodsAddress);
			

			// 默认地址
			goodsAddress = this.goodsAddressService.getGoodsAddressByDefault(goodsAddress);
//			model.addAttribute("goodsAddress", goodsAddress);
			orders.setGoodsAddress(goodsAddress);
			
			
			List<GoodsAddress> goodsAddressListadd =new ArrayList<GoodsAddress>();
			
			if(goodsAddressList!=null && goodsAddressList.size()>3){
				goodsAddressListadd.add(goodsAddressList.get(0));
				goodsAddressListadd.add(goodsAddressList.get(1));
				goodsAddressListadd.add(goodsAddressList.get(2));
				goodsAddressList.removeAll(goodsAddressListadd);
				
			}else if (goodsAddressList!=null && goodsAddressList.size()>0){
				
				for( GoodsAddress ga:goodsAddressList){
					goodsAddressListadd.add(ga);
				}
				goodsAddressList = null;
			}
			
//			model.addAttribute("goodsAddressListadd", goodsAddressListadd);
//			model.addAttribute("goodsAddressList", goodsAddressList);
			
			orders.setGoodsAddressList(goodsAddressList);
			orders.setGoodsAddressListadd(goodsAddressListadd);

			if (goodsAddress != null) {
				Shop shop = new Shop();
//				shop.setDistrict(goodsAddress.getDistrict());
//				shop.setStreet(goodsAddress.getStreet());
				List<Shop> shopList=null;
				//根据 街道查询店
				if(goodsAddress.getStreet()!=null && goodsAddress.getStreet()>0){
					shop.setStreet(goodsAddress.getStreet());				
					shopList = this.shopService.getShopByStreet(shop);
				}
				
				
				//如果 街道没有匹配的店
				if (shopList == null || shopList.size() == 0) {
				shop.setCity(goodsAddress.getCity());
				shopList = this.shopService.getShopByAddress(shop);
				
				}
				
				//如果 区县也没有匹配的店  ，就匹配总部
				if (shopList == null || shopList.size() == 0) {

					shopList = new ArrayList<Shop>();
					Shop sh =new Shop();
					sh.setShopId(3);
					sh.setName("无锡市通江大道店");
					sh.setNickName("无锡市通江大道店");
					
					shopList.add(sh);
					}

				if (shopList != null && shopList.size() > 0) {
					
					
//					model.addAttribute("shopName", shopList.get(0).getName());
//					model.addAttribute("shopId", shopList.get(0).getShopId());
					
					orders.setShopName(shopList.get(0).getName());
					orders.setShopId(shopList.get(0).getShopId());

					// 查询库存
					Stock stock = new Stock();
					stock.setProductUuid(product.getProductUuid());
					stock.setShopId(shopList.get(0).getShopId());
					stock = stockService.getStockByPrd(stock);
					// 显示库存
					if (stock != null) {
						// stock.setShowCapacity(stock.getShowCapacity()-stock.getSellCapacity());
//						model.addAttribute("showCapacity", stock.getShowCapacity() - stock.getSellCapacity());
						
						orders.setShowCapacity(stock.getShowCapacity() - stock.getSellCapacity());
					} else {
						orders.setShowCapacity(0);
					}
				} else {
//					model.addAttribute("shopName", "您的默认地址附近没有匹配的店，不支持配送。");
//					model.addAttribute("shopId", 0);
					
					orders.setShopName("您的默认地址附近没有匹配的店，不支持配送。");
					orders.setShopId(0);
				}
			} else {
				// 没有默认地址 跳转到地址维护页面
				orders.setShopId(-1);
//				return "redirect:/goodsAddress/goodsAddressListPage.do";

			}
		}
		//未登陆
		else{
			subject.getSession().setAttribute("preUrlFlag", orders.getProductUuid()+","+orders.getCapacity());
			//未登录 返回错误码，跳转到登陆页面
			orders.setState("001");
//			return "redirect:/resource/jsp/login.jsp";	
		}

		// 总金额
		orders.setTotalFromBdh(orders.getCapacity() * product.getPrice());

		
		orders.setProduct(product);
//		model.addAttribute("product", product);

//		model.addAttribute("orders", orders);

		return orders;
	}
	
	
	
	
	

	/** 编辑前初始化对象 */
	@RequestMapping("/ordersAddEditIni.do")
	public String ordersAddEditIni(Orders orders, Model model) throws Exception {
		model.addAttribute("errcode", orders.getErrcode());

		Product product = this.productService.getProductByUuid(orders.getProductUuid());

		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();

			GoodsAddress goodsAddress = new GoodsAddress();
			goodsAddress.setCreateByUser(user.getUserId());

			// 地址列表
			List<GoodsAddress> goodsAddressList = this.goodsAddressService.getGoodsAddressList(goodsAddress);
			

			// 默认地址
			goodsAddress = this.goodsAddressService.getGoodsAddressByDefault(goodsAddress);
			model.addAttribute("goodsAddress", goodsAddress);
			
			
			List<GoodsAddress> goodsAddressListadd =new ArrayList<GoodsAddress>();
			
			if(goodsAddressList!=null && goodsAddressList.size()>3){
				goodsAddressListadd.add(goodsAddressList.get(0));
				goodsAddressListadd.add(goodsAddressList.get(1));
				goodsAddressListadd.add(goodsAddressList.get(2));
				goodsAddressList.removeAll(goodsAddressListadd);
				
			}else if (goodsAddressList!=null && goodsAddressList.size()>0){
				
				for( GoodsAddress ga:goodsAddressList){
					goodsAddressListadd.add(ga);
				}
				goodsAddressList = null;
			}
			
			model.addAttribute("goodsAddressListadd", goodsAddressListadd);
			model.addAttribute("goodsAddressList", goodsAddressList);

			if (goodsAddress != null) {
				Shop shop = new Shop();
//				shop.setDistrict(goodsAddress.getDistrict());
//				shop.setStreet(goodsAddress.getStreet());
				List<Shop> shopList=null;
				//根据 街道查询店
				if(goodsAddress.getStreet()!=null && goodsAddress.getStreet()>0){
					shop.setStreet(goodsAddress.getStreet());				
					shopList = this.shopService.getShopByStreet(shop);
				}
				
				
				//如果 街道没有匹配的店
				if (shopList == null || shopList.size() == 0) {
				shop.setCity(goodsAddress.getCity());
				shopList = this.shopService.getShopByAddress(shop);
				
				}
				
				//如果 区县也没有匹配的店  ，就匹配总部
				if (shopList == null || shopList.size() == 0) {

					shopList = new ArrayList<Shop>();
					Shop sh =new Shop();
					sh.setShopId(3);
					sh.setName("无锡市通江大道店");
					sh.setNickName("无锡市通江大道店");
					
					shopList.add(sh);
					}

				if (shopList != null && shopList.size() > 0) {
					model.addAttribute("shopName", shopList.get(0).getName());
					model.addAttribute("shopId", shopList.get(0).getShopId());

					// 查询库存
					Stock stock = new Stock();
					stock.setProductUuid(product.getProductUuid());
					stock.setShopId(shopList.get(0).getShopId());
					stock = stockService.getStockByPrd(stock);
					// 显示库存
					if (stock != null) {
						// stock.setShowCapacity(stock.getShowCapacity()-stock.getSellCapacity());
						model.addAttribute("showCapacity", stock.getShowCapacity() - stock.getSellCapacity());
					} else {
						model.addAttribute("showCapacity", 0);
					}
				} else {
					model.addAttribute("shopName", "您的默认地址附近没有匹配的店，不支持配送。");
					model.addAttribute("shopId", 0);
				}
			} else {
				// 没有默认地址 跳转到地址维护页面
				model.addAttribute("shopId", -1);
//				return "redirect:/goodsAddress/goodsAddressListPage.do";

			}
		}
		//未登陆
		else{
			subject.getSession().setAttribute("preUrlFlag", orders.getProductUuid()+","+orders.getCapacity());
			return "redirect:/resource/jsp/login.jsp";	
		}

		// 总金额
		orders.setTotalFromBdh(orders.getCapacity() * product.getPrice());

		model.addAttribute("product", product);

		model.addAttribute("orders", orders);

		return "/jsp/buy/buy";
	}

	@RequestMapping("/ordersAddEditDetailIni.do")
	public String ordersAddEditDetailIni(Orders orders, Model model) throws Exception {
		model.addAttribute("errcode", orders.getErrcode());

		// Product product =
		// this.productService.getProductByUuid(orders.getProductUuid());

		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();

			GoodsAddress goodsAddress = new GoodsAddress();
			goodsAddress.setCreateByUser(user.getUserId());

			// 地址列表
			List<GoodsAddress> goodsAddressList = this.goodsAddressService.getGoodsAddressList(goodsAddress);
			

			// 默认地址
			goodsAddress = this.goodsAddressService.getGoodsAddressByDefault(goodsAddress);
			model.addAttribute("goodsAddress", goodsAddress);
			
			List<GoodsAddress> goodsAddressListadd =new ArrayList<GoodsAddress>();
			
			if(goodsAddressList!=null && goodsAddressList.size()>3){
				goodsAddressListadd.add(goodsAddressList.get(0));
				goodsAddressListadd.add(goodsAddressList.get(1));
				goodsAddressListadd.add(goodsAddressList.get(2));
				goodsAddressList.removeAll(goodsAddressListadd);
				
			}else if (goodsAddressList!=null && goodsAddressList.size()>0){
				
				for( GoodsAddress ga:goodsAddressList){
					goodsAddressListadd.add(ga);
				}
				goodsAddressList = null;
			}
			
			model.addAttribute("goodsAddressListadd", goodsAddressListadd);
			
			model.addAttribute("goodsAddressList", goodsAddressList);

			if (goodsAddress != null) {
				Shop shop = new Shop();
//				shop.setDistrict(goodsAddress.getDistrict());
//				shop.setStreet(goodsAddress.getStreet());
				List<Shop> shopList=null;
				//根据 街道查询店
				if(goodsAddress.getStreet()!=null && goodsAddress.getStreet()>0){
					shop.setStreet(goodsAddress.getStreet());				
					shopList = this.shopService.getShopByStreet(shop);
				}
				
				
				//如果 街道没有匹配的店
				if (shopList == null || shopList.size() == 0) {
				shop.setCity(goodsAddress.getCity());
				shopList = this.shopService.getShopByAddress(shop);
				
				}
				
				//如果 区县也没有匹配的店  ，就匹配总部
				if (shopList == null || shopList.size() == 0) {

					shopList = new ArrayList<Shop>();
					Shop sh =new Shop();
					sh.setShopId(3);
					sh.setName("无锡市通江大道店");
					sh.setNickName("无锡市通江大道店");
					
					shopList.add(sh);
					}

				if (shopList != null && shopList.size() > 0) {
					model.addAttribute("shopName", shopList.get(0).getName());
					model.addAttribute("shopId", shopList.get(0).getShopId());

					// 查询 商品及 库存list
					int i = 0;
					StringBuffer sb = new StringBuffer();
					if (orders.getOrdersDetailList().size() > 0) {
						// 筛选选中的 商品

						for (OrdersDetail od : orders.getOrdersDetailList()) {
							if ("1".equals(od.getCode())) {
								if (i == 0) {
									sb.append("'").append(od.getProductUuid()).append("'");
								} else {
									sb.append(",").append("'").append(od.getProductUuid()).append("'");
								}
								i++;
							}

						}
					}

					OrdersDetail ordersDetail = new OrdersDetail();
					ordersDetail.setProductUuid(sb.toString());
					ordersDetail.setShopId(shopList.get(0).getShopId());

					List<OrdersDetail> ordersDetailList = null;
					if (sb.toString().length() > 1) {
						ordersDetailList = this.ordersDetailService.getOrdersDetailList(ordersDetail);
					}


					// 赋值 购买的数量
					for (OrdersDetail od : ordersDetailList) {
					for (OrdersDetail oldod : orders.getOrdersDetailList()) {

								if (od.getProductUuid().equals(oldod.getProductUuid())) {
									od.setCapacity(oldod.getCapacity());

									// 计算库存
									if (od.getShowCapacity() != null && od.getShowCapacity() > 0) {
										od.setShowCapacity(od.getShowCapacity() - od.getSellCapacity());
									} else {
										od.setShowCapacity(0);
									}

									break;
								}

							}
						}



					// 清空入参

					orders.getOrdersDetailList().clear();
					if (ordersDetailList != null && ordersDetailList.size() > 0) {
						orders.getOrdersDetailList().addAll(ordersDetailList);
					}

				} else {
					model.addAttribute("shopName", "该地区暂不支持配送");
					model.addAttribute("shopId", 0);
//					model.addAttribute("errcode", 104);
//					return "redirect:/product/cartProductListPage.do?errcode=104";
					//没有店铺


					// 查询 商品及 库存list
					int i = 0;
					StringBuffer sb = new StringBuffer();
					if (orders.getOrdersDetailList().size() > 0) {
						// 筛选选中的 商品

						for (OrdersDetail od : orders.getOrdersDetailList()) {
							if ("1".equals(od.getCode())) {
								if (i == 0) {
									sb.append("'").append(od.getProductUuid()).append("'");
								} else {
									sb.append(",").append("'").append(od.getProductUuid()).append("'");
								}
								i++;
							}

						}
					}

					OrdersDetail ordersDetail = new OrdersDetail();
					ordersDetail.setProductUuid(sb.toString());

					List<OrdersDetail> ordersDetailList = null;
					if (sb.toString().length() > 1) {
						ordersDetailList = this.ordersDetailService.getOrdersDetailListNoshop(ordersDetail);
					}


					// 赋值 购买的数量
					for (OrdersDetail od : ordersDetailList) {
					for (OrdersDetail oldod : orders.getOrdersDetailList()) {

								if (od.getProductUuid().equals(oldod.getProductUuid())) {
									od.setCapacity(oldod.getCapacity());

									// 计算库存
									if (od.getShowCapacity() != null && od.getShowCapacity() > 0) {
										od.setShowCapacity(od.getShowCapacity() - od.getSellCapacity());
									} else {
										od.setShowCapacity(0);
									}

									break;
								}

							}
						}



					// 清空入参

					orders.getOrdersDetailList().clear();
					if (ordersDetailList != null && ordersDetailList.size() > 0) {
						orders.getOrdersDetailList().addAll(ordersDetailList);
					}

				
					
				}
			} else {
				// 没有默认地址 跳转到地址维护页面
				
				

//				model.addAttribute("shopName", "该地区暂不支持配送");
				model.addAttribute("shopId", -1);
//				model.addAttribute("errcode", 104);
//				return "redirect:/product/cartProductListPage.do?errcode=104";
				//没有店铺


				// 查询 商品及 库存list
				int i = 0;
				StringBuffer sb = new StringBuffer();
				if (orders.getOrdersDetailList().size() > 0) {
					// 筛选选中的 商品

					for (OrdersDetail od : orders.getOrdersDetailList()) {
						if ("1".equals(od.getCode())) {
							if (i == 0) {
								sb.append("'").append(od.getProductUuid()).append("'");
							} else {
								sb.append(",").append("'").append(od.getProductUuid()).append("'");
							}
							i++;
						}

					}
				}

				OrdersDetail ordersDetail = new OrdersDetail();
				ordersDetail.setProductUuid(sb.toString());

				List<OrdersDetail> ordersDetailList = null;
				if (sb.toString().length() > 1) {
					ordersDetailList = this.ordersDetailService.getOrdersDetailListNoshop(ordersDetail);
				}


				// 赋值 购买的数量
				for (OrdersDetail od : ordersDetailList) {
				for (OrdersDetail oldod : orders.getOrdersDetailList()) {

							if (od.getProductUuid().equals(oldod.getProductUuid())) {
								od.setCapacity(oldod.getCapacity());

								// 计算库存
								if (od.getShowCapacity() != null && od.getShowCapacity() > 0) {
									od.setShowCapacity(od.getShowCapacity() - od.getSellCapacity());
								} else {
									od.setShowCapacity(0);
								}

								break;
							}

						}
					}



				// 清空入参

				orders.getOrdersDetailList().clear();
				if (ordersDetailList != null && ordersDetailList.size() > 0) {
					orders.getOrdersDetailList().addAll(ordersDetailList);
				}

			
				
			

//				return "redirect:/goodsAddress/goodsAddressListPage.do";

			}
		}
		//未登陆
		else{
			//标识 登陆后 跳转回 购物车	
			subject.getSession().setAttribute("preUrlFlag", "0");
				return "redirect:/resource/jsp/login.jsp";	
		}

		// 总金额
		double total = 0;
		for (OrdersDetail od : orders.getOrdersDetailList()) {
			total = total + od.getCapacity() * od.getPrice();
		}

		orders.setTotalFromBdh(total);

		model.addAttribute("orders", orders);

		return "/jsp/buy/buyDetail";
	}
	
	
	
	@RequestMapping("/ordersAddEditDetailIniSvc.do")
	@ResponseBody
	public Orders ordersAddEditDetailIniSvc(Orders orders, Model model) throws Exception {
		model.addAttribute("errcode", orders.getErrcode());

		// Product product =
		// this.productService.getProductByUuid(orders.getProductUuid());

		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();

			GoodsAddress goodsAddress = new GoodsAddress();
			goodsAddress.setCreateByUser(user.getUserId());

			// 地址列表
			List<GoodsAddress> goodsAddressList = this.goodsAddressService.getGoodsAddressList(goodsAddress);
			

			// 默认地址
			goodsAddress = this.goodsAddressService.getGoodsAddressByDefault(goodsAddress);
			
			
//			model.addAttribute("goodsAddress", goodsAddress);
			orders.setGoodsAddress(goodsAddress);
			
			List<GoodsAddress> goodsAddressListadd =new ArrayList<GoodsAddress>();
			
			if(goodsAddressList!=null && goodsAddressList.size()>3){
				goodsAddressListadd.add(goodsAddressList.get(0));
				goodsAddressListadd.add(goodsAddressList.get(1));
				goodsAddressListadd.add(goodsAddressList.get(2));
				goodsAddressList.removeAll(goodsAddressListadd);
				
			}else if (goodsAddressList!=null && goodsAddressList.size()>0){
				
				for( GoodsAddress ga:goodsAddressList){
					goodsAddressListadd.add(ga);
				}
				goodsAddressList = null;
			}
			
//			model.addAttribute("goodsAddressListadd", goodsAddressListadd);
//			
//			model.addAttribute("goodsAddressList", goodsAddressList);
			
			orders.setGoodsAddressListadd(goodsAddressListadd);
			orders.setGoodsAddressList(goodsAddressList);
			

			if (goodsAddress != null) {
				Shop shop = new Shop();
//				shop.setDistrict(goodsAddress.getDistrict());
//				shop.setStreet(goodsAddress.getStreet());
				List<Shop> shopList=null;
				//根据 街道查询店
				if(goodsAddress.getStreet()!=null && goodsAddress.getStreet()>0){
					shop.setStreet(goodsAddress.getStreet());				
					shopList = this.shopService.getShopByStreet(shop);
				}
				
				
				//如果 街道没有匹配的店
				if (shopList == null || shopList.size() == 0) {
				shop.setCity(goodsAddress.getCity());
				shopList = this.shopService.getShopByAddress(shop);
				
				}
				
				//如果 区县也没有匹配的店  ，就匹配总部
				if (shopList == null || shopList.size() == 0) {

					shopList = new ArrayList<Shop>();
					Shop sh =new Shop();
					sh.setShopId(3);
					sh.setName("无锡市通江大道店");
					sh.setNickName("无锡市通江大道店");
					
					shopList.add(sh);
					}

				if (shopList != null && shopList.size() > 0) {
					
//					model.addAttribute("shopName", shopList.get(0).getName());
//					model.addAttribute("shopId", shopList.get(0).getShopId());
					
					orders.setShopName(shopList.get(0).getName());
					orders.setShopId(shopList.get(0).getShopId());

					// 查询 商品及 库存list
					int i = 0;
					StringBuffer sb = new StringBuffer();
					if (orders.getOrdersDetailList().size() > 0) {
						// 筛选选中的 商品

						for (OrdersDetail od : orders.getOrdersDetailList()) {
							if ("1".equals(od.getCode())) {
								if (i == 0) {
									sb.append("'").append(od.getProductUuid()).append("'");
								} else {
									sb.append(",").append("'").append(od.getProductUuid()).append("'");
								}
								i++;
							}

						}
					}

					OrdersDetail ordersDetail = new OrdersDetail();
					ordersDetail.setProductUuid(sb.toString());
					ordersDetail.setShopId(shopList.get(0).getShopId());

					List<OrdersDetail> ordersDetailList = null;
					if (sb.toString().length() > 1) {
						ordersDetailList = this.ordersDetailService.getOrdersDetailList(ordersDetail);
					}


					// 赋值 购买的数量
					for (OrdersDetail od : ordersDetailList) {
					for (OrdersDetail oldod : orders.getOrdersDetailList()) {

								if (od.getProductUuid().equals(oldod.getProductUuid())) {
									od.setCapacity(oldod.getCapacity());

									// 计算库存
									if (od.getShowCapacity() != null && od.getShowCapacity() > 0) {
										od.setShowCapacity(od.getShowCapacity() - od.getSellCapacity());
									} else {
										od.setShowCapacity(0);
									}

									break;
								}

							}
						}



					// 清空入参

					orders.getOrdersDetailList().clear();
					if (ordersDetailList != null && ordersDetailList.size() > 0) {
						orders.getOrdersDetailList().addAll(ordersDetailList);
					}

				} else {
					model.addAttribute("shopName", "该地区暂不支持配送");
					model.addAttribute("shopId", 0);
//					model.addAttribute("errcode", 104);
//					return "redirect:/product/cartProductListPage.do?errcode=104";
					//没有店铺


					// 查询 商品及 库存list
					int i = 0;
					StringBuffer sb = new StringBuffer();
					if (orders.getOrdersDetailList().size() > 0) {
						// 筛选选中的 商品

						for (OrdersDetail od : orders.getOrdersDetailList()) {
							if ("1".equals(od.getCode())) {
								if (i == 0) {
									sb.append("'").append(od.getProductUuid()).append("'");
								} else {
									sb.append(",").append("'").append(od.getProductUuid()).append("'");
								}
								i++;
							}

						}
					}

					OrdersDetail ordersDetail = new OrdersDetail();
					ordersDetail.setProductUuid(sb.toString());

					List<OrdersDetail> ordersDetailList = null;
					if (sb.toString().length() > 1) {
						ordersDetailList = this.ordersDetailService.getOrdersDetailListNoshop(ordersDetail);
					}


					// 赋值 购买的数量
					for (OrdersDetail od : ordersDetailList) {
					for (OrdersDetail oldod : orders.getOrdersDetailList()) {

								if (od.getProductUuid().equals(oldod.getProductUuid())) {
									od.setCapacity(oldod.getCapacity());

									// 计算库存
									if (od.getShowCapacity() != null && od.getShowCapacity() > 0) {
										od.setShowCapacity(od.getShowCapacity() - od.getSellCapacity());
									} else {
										od.setShowCapacity(0);
									}

									break;
								}

							}
						}



					// 清空入参

					orders.getOrdersDetailList().clear();
					if (ordersDetailList != null && ordersDetailList.size() > 0) {
						orders.getOrdersDetailList().addAll(ordersDetailList);
					}

				
					
				}
			} else {
				// 没有默认地址 跳转到地址维护页面
				
				

//				model.addAttribute("shopName", "该地区暂不支持配送");
				model.addAttribute("shopId", -1);
//				model.addAttribute("errcode", 104);
//				return "redirect:/product/cartProductListPage.do?errcode=104";
				//没有店铺


				// 查询 商品及 库存list
				int i = 0;
				StringBuffer sb = new StringBuffer();
				if (orders.getOrdersDetailList().size() > 0) {
					// 筛选选中的 商品

					for (OrdersDetail od : orders.getOrdersDetailList()) {
						if ("1".equals(od.getCode())) {
							if (i == 0) {
								sb.append("'").append(od.getProductUuid()).append("'");
							} else {
								sb.append(",").append("'").append(od.getProductUuid()).append("'");
							}
							i++;
						}

					}
				}

				OrdersDetail ordersDetail = new OrdersDetail();
				ordersDetail.setProductUuid(sb.toString());

				List<OrdersDetail> ordersDetailList = null;
				if (sb.toString().length() > 1) {
					ordersDetailList = this.ordersDetailService.getOrdersDetailListNoshop(ordersDetail);
				}


				// 赋值 购买的数量
				for (OrdersDetail od : ordersDetailList) {
				for (OrdersDetail oldod : orders.getOrdersDetailList()) {

							if (od.getProductUuid().equals(oldod.getProductUuid())) {
								od.setCapacity(oldod.getCapacity());

								// 计算库存
								if (od.getShowCapacity() != null && od.getShowCapacity() > 0) {
									od.setShowCapacity(od.getShowCapacity() - od.getSellCapacity());
								} else {
									od.setShowCapacity(0);
								}

								break;
							}

						}
					}



				// 清空入参

				orders.getOrdersDetailList().clear();
				if (ordersDetailList != null && ordersDetailList.size() > 0) {
					orders.getOrdersDetailList().addAll(ordersDetailList);
				}

			
				
			

//				return "redirect:/goodsAddress/goodsAddressListPage.do";

			}
		}
		//未登陆
		else{
			//标识 登陆后 跳转回 购物车	
			subject.getSession().setAttribute("preUrlFlag", "0");
			
			//未登录返回登陆
			orders.setStatus(001);
			
//				return "redirect:/resource/jsp/login.jsp";	
		}

		// 总金额
		double total = 0;
		for (OrdersDetail od : orders.getOrdersDetailList()) {
			total = total + od.getCapacity() * od.getPrice();
		}

		orders.setTotalFromBdh(total);

//		model.addAttribute("orders", orders);

		return orders;
	}
	
	
	

	/** 查看对象 */
	@RequestMapping("/ordersDetail.do")
	public String ordersDetail(Orders orders, Model model) throws Exception {
		// this.setPare_moduleid(14);
		orders = this.ordersService.getOrdersByCode(orders);


		model.addAttribute("orders", orders);
		return "/jsp/orders/ordersDetail";
	}
	
	
	/** 查看对象 */
	@RequestMapping("/ordersDetailSvc.do")
	@ResponseBody
	public Orders ordersDetailSvc(Orders orders) throws Exception {
		// this.setPare_moduleid(14);
		orders = this.ordersService.getOrdersByCode(orders);


		return orders;
	}
	

	public String editCancel() throws Exception {
		// this.setPare_moduleid(14);
		Orders orders = null;
		// orders = this.ordersService.getOrdersById(orders.getOrdersId());
		return "detail";
	}
	
	
	@RequestMapping("/ordersAddEdit.do")
	public String ordersAddEdit(Orders orders, Model model) throws Exception {
		// this.setPare_moduleid(14);
		// ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		// 校验是否有匹配的店 104
		if (orders.getShopId() == null || orders.getShopId() <= 0) {
			return "redirect:/orders/ordersAddEditIni.do?productUuid=" + orders.getProductUuid() + "&capacity="
					+ orders.getCapacity() + "&errcode=104";
		}

		Stock stock = new Stock();

		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();

			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 10000;
			orders.setCode(user.getUserId() + time.format(now) + i);

			orders.setCreateByUser(user.getUserId());

			// 0 支付宝
			orders.setPayType(0);

			// 查询库存

			stock.setProductUuid(orders.getProductUuid());
			stock.setShopId(orders.getShopId());
			stock = stockService.getStockByPrd(stock);
			// 显示库存
			if (stock != null) {
				if (orders.getCapacity() > (stock.getShowCapacity() - stock.getSellCapacity())) {
					// 库存不足 103
					return "redirect:/orders/ordersAddEditIni.do?productUuid=" + orders.getProductUuid() + "&capacity="
							+ orders.getCapacity() + "&errcode=103";
				}
			} else {
				// 库存未设置
				return "redirect:/orders/ordersAddEditIni.do?productUuid=" + orders.getProductUuid() + "&capacity="
						+ orders.getCapacity() + "&errcode=103";
			}
			// 计算总金额 规避前端恶意修改总金额
			Product product = this.productService.getProductByUuid(orders.getProductUuid());

			double total = product.getPrice() * orders.getCapacity();

			if (total != orders.getTotalFromBdh()) {
				orders.setTotalFromBdh(total);
			}

		}

		this.ordersService.saveOrders(orders, "add");

		// 增加售卖数量
//		stock.setSellCapacity(orders.getCapacity());
//		stockService.updateSell(stock);

		return "/jsp/buy/pay";
	}
	
	

	/** 保存新增对象 */
//	@RequestMapping("/ordersAddEdit.do")
//	@ResponseBody
//	public String ordersAddEdit(Orders orders, Model model) throws Exception {
//		// this.setPare_moduleid(14);
//		// ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//
//		// 校验是否有匹配的店 104
//		if (orders.getShopId() == null || orders.getShopId() <= 0) {
//			return "104";
//		}
//
//		Stock stock = new Stock();
//
//		// 如果已登陆 匹配 默认地址 附近的店
//		Subject subject = SecurityUtils.getSubject();
//		if (subject.isAuthenticated()) {
//			User user = (User) subject.getPrincipal();
//
//			// 订单编号
//			Date now = new Date();
//			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
//			SecureRandom random = new SecureRandom();
//			int i = Math.abs(random.nextInt()) % 10000;
//			orders.setCode(user.getUserId() + time.format(now) + i);
//
//			orders.setCreateByUser(user.getUserId());
//
//			// 0 支付宝
//			orders.setPayType(0);
//
//			// 查询库存
//
//			stock.setProductUuid(orders.getProductUuid());
//			stock.setShopId(orders.getShopId());
//			stock = stockService.getStockByPrd(stock);
//			// 显示库存
//			if (stock != null) {
//				if (orders.getCapacity() > (stock.getShowCapacity() - stock.getSellCapacity())) {
//					// 库存不足 103
//					return "103";
//				}
//			} else {
//				// 库存未设置
//				return "103";
//			}
//			// 计算总金额 规避前端恶意修改总金额
//			Product product = this.productService.getProductByUuid(orders.getProductUuid());
//
//			double total = product.getPrice() * orders.getCapacity();
//
//			if (total != orders.getTotalFromBdh()) {
//				orders.setTotalFromBdh(total);
//			}
//
//		}
//
//		this.ordersService.saveOrders(orders, "add");
//
//		// 增加售卖数量
////		stock.setSellCapacity(orders.getCapacity());
////		stockService.updateSell(stock);
//
//		return orders.getCode();
//	}

	@RequestMapping("/ordersAddEditDetail.do")
	public String ordersAddEditDetail(Orders orders, Model model) throws Exception {
		// this.setPare_moduleid(14);
		// ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		// 校验是否有匹配的店 104
		if (orders.getShopId() == null || orders.getShopId() <= 0) {
			return "redirect:/product/cartProductListPage.do?errcode=104";
		}

		Stock stock = null;

		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();

			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 10000;
			orders.setCode(user.getUserId() + time.format(now) + i);

			orders.setCreateByUser(user.getUserId());

			// 0 支付宝
			orders.setPayType(0);
			orders.setProductUuid("0");

			// 查询库存 计算总金额

			double total = 0;
			Product product=null;
			int flag = 0;
			StringBuffer sb = new StringBuffer();
			i = 0;
//			List<Stock> stkList=new ArrayList<Stock>();
			for (OrdersDetail od : orders.getOrdersDetailList()) {
				

				// 金额
				product = this.productService.getProductByUuid(od.getProductUuid());
				total = total + product.getPrice() * od.getCapacity();

				// 设置订单编号
				od.setCode(orders.getCode());

				// 生成 购物车删除 uuid 入参
				if (i == 0) {
					sb.append("'").append(od.getProductUuid()).append("'");
				} else {
					sb.append(",").append("'").append(od.getProductUuid()).append("'");
				}
				i++;

			}
			
			
			OrdersDetail ordersDetail = new OrdersDetail();
			ordersDetail.setProductUuid(sb.toString());
			ordersDetail.setShopId(orders.getShopId());
//批量查询库存
			List<OrdersDetail> ordersDetailList = null;
			if (sb.toString().length() > 1) {
				ordersDetailList = this.ordersDetailService.getOrdersDetailList(ordersDetail);
			}
			//验证库存
			for (OrdersDetail od : orders.getOrdersDetailList()) {
				for (OrdersDetail st : ordersDetailList) {	
					
					if(od.getProductUuid().equals(st.getProductUuid())){
						
						// 显示库存
						if (st.getShowCapacity() != null&&st.getShowCapacity()>0) {
							if (od.getCapacity() > (st.getShowCapacity() - st.getSellCapacity())) {

								// 库存不足 103
								flag = 1;
							}
						} else {
							// 库存未设置
							flag = 1;
						}
						
						if (flag == 1) {
							return "redirect:/product/cartProductListPage.do?errcode=103";
						}
						
						// 生成 批量 修改 售卖数量 list
//						stock=new Stock();
//						stock.setSellCapacity(od.getCapacity());
//						stock.setProductUuid(od.getProductUuid());
//						stock.setShopId(orders.getShopId());
//						stkList.add(stock);
						
						break;
					}


			}
			}

			//批量修改库存
//			stockService.updateSellAll(stkList);

			// 计算总金额 规避前端恶意修改总金额

			if (total != orders.getTotalFromBdh()) {
				orders.setTotalFromBdh(total);
			}

			// 删除购物车对应数据
			Cart cart = new Cart();
			cart.setCreateByUser(user.getUserId());
			cart.setProductUuid(sb.toString());
			cartService.deleteCartAll(cart);

		}

		this.ordersService.saveOrders(orders, "add");

		// 保存订单 商品 list
		ordersDetailService.insertOrdersDetailAll(orders.getOrdersDetailList());

		return "/jsp/buy/payDetail";
	}
	
	
	@RequestMapping("/ordersAddEditDetailSvc.do")
	@ResponseBody
	public String ordersAddEditDetailSvc(Orders orders) throws Exception {
		// this.setPare_moduleid(14);
		// ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		// 校验是否有匹配的店 104
		if (orders.getShopId() == null || orders.getShopId() <= 0) {
			return "104";
		}

		Stock stock = null;

		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();

			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 10000;
			orders.setCode(user.getUserId() + time.format(now) + i);

			orders.setCreateByUser(user.getUserId());

			// 0 支付宝
			orders.setPayType(0);
			orders.setProductUuid("0");

			// 查询库存 计算总金额

			double total = 0;
			Product product=null;
			int flag = 0;
			StringBuffer sb = new StringBuffer();
			i = 0;
//			List<Stock> stkList=new ArrayList<Stock>();
			for (OrdersDetail od : orders.getOrdersDetailList()) {
				

				// 金额
				product = this.productService.getProductByUuid(od.getProductUuid());
				total = total + product.getPrice() * od.getCapacity();

				// 设置订单编号
				od.setCode(orders.getCode());

				// 生成 购物车删除 uuid 入参
				if (i == 0) {
					sb.append("'").append(od.getProductUuid()).append("'");
				} else {
					sb.append(",").append("'").append(od.getProductUuid()).append("'");
				}
				i++;

			}
			
			
			OrdersDetail ordersDetail = new OrdersDetail();
			ordersDetail.setProductUuid(sb.toString());
			ordersDetail.setShopId(orders.getShopId());
//批量查询库存
			List<OrdersDetail> ordersDetailList = null;
			if (sb.toString().length() > 1) {
				ordersDetailList = this.ordersDetailService.getOrdersDetailList(ordersDetail);
			}
			//验证库存
			for (OrdersDetail od : orders.getOrdersDetailList()) {
				for (OrdersDetail st : ordersDetailList) {	
					
					if(od.getProductUuid().equals(st.getProductUuid())){
						
						// 显示库存
						if (st.getShowCapacity() != null&&st.getShowCapacity()>0) {
							if (od.getCapacity() > (st.getShowCapacity() - st.getSellCapacity())) {

								// 库存不足 103
								flag = 1;
							}
						} else {
							// 库存未设置
							flag = 1;
						}
						
						if (flag == 1) {
							return "103";
						}
						
						// 生成 批量 修改 售卖数量 list
//						stock=new Stock();
//						stock.setSellCapacity(od.getCapacity());
//						stock.setProductUuid(od.getProductUuid());
//						stock.setShopId(orders.getShopId());
//						stkList.add(stock);
						
						break;
					}


			}
			}

			//批量修改库存
//			stockService.updateSellAll(stkList);

			// 计算总金额 规避前端恶意修改总金额

			if (total != orders.getTotalFromBdh()) {
				orders.setTotalFromBdh(total);
			}

			// 删除购物车对应数据
			Cart cart = new Cart();
			cart.setCreateByUser(user.getUserId());
			cart.setProductUuid(sb.toString());
			cartService.deleteCartAll(cart);

		}

		this.ordersService.saveOrders(orders, "add");

		// 保存订单 商品 list
		ordersDetailService.insertOrdersDetailAll(orders.getOrdersDetailList());

		return orders.getCode();
	}
	
	
	

	/** 订单支付 */
	@RequestMapping("/ordersPay.do")
	public String ordersPay(Orders orders, Model model) throws Exception {
		// this.setPare_moduleid(14);
		// ordersForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		orders = this.ordersService.getOrdersByCode(orders);
		
		model.addAttribute("orders", orders);

		// 合并支付
		if ("0".equals(orders.getProductUuid())) {

			return "/jsp/buy/payDetail";
		}

		
		return "/jsp/buy/pay";
	}

	/** 删除对象 */
	@RequestMapping("/ordersDelete.do")
	public String ordersDelete(Orders orders, Model model) throws Exception {
		orders = this.ordersService.getOrdersByCode(orders);

		this.ordersService.deleteOrders(orders.getCode());
		Stock stock = null;
		// 合并支付
		if ("0".equals(orders.getProductUuid())) {
			List<Stock> stkList=new ArrayList<Stock>();
			
			//库存
			for (OrdersDetail od : orders.getOrdersDetailList()) {
						
						// 生成 批量 修改 售卖数量 list
						stock=new Stock();
						stock.setSellCapacity(od.getCapacity() * -1);
						stock.setProductUuid(od.getProductUuid());
						stock.setShopId(orders.getShopId());
						stkList.add(stock);
			}

			//批量修改库存
			stockService.updateSellAll(stkList);
			

		} else {
			stock = new Stock();
			stock.setProductUuid(orders.getProductUuid());
			stock.setShopId(orders.getShopId());
			stock = stockService.getStockByPrd(stock);
			// 库存
			if (stock != null) {

				// 回退售卖数量
				stock.setSellCapacity(orders.getCapacity() * -1);
				stockService.updateSell(stock);
			}
		}

		return "redirect:/orders/ordersListPage.do?status=" + 100;
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
