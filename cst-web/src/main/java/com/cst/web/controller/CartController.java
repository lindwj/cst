package com.cst.web.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
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

import com.cst.service.model.Cart;
import com.cst.service.model.CartForm;
import com.cst.service.model.OrdersDetail;
import com.cst.service.model.Product;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.service.CartService;

/**
 * @author lind
 */

@Controller
@RequestMapping("/cart")
public class CartController {

	// 业务逻辑对象
	@Autowired
	private CartService cartService;

	// 查询结果
	private List<Cart> cartList;

	private CartForm cartForm = new CartForm();

	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;

	/** 执行搜索 */
	public String cartListPage() throws Exception {
		// cartForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Cart cart = null;
		cartList = cartService.getCartListPage(cart);
		cartForm.setCart(cart);
		request.setAttribute("page", cartForm.getPage());
		return "list";
	}

	/** 编辑前初始化对象 */
	public String cartAddEditIni() throws Exception {
		Cart cart = null;
		if ("edit".equals(doWhat)) {
			// cart = this.cartService.getCartById(cart.getCartId());
			cartForm.setCart(cart);
		}
		return "addedit";
	}

	/** 查看对象 */
	public String cartDetail() throws Exception {
		// this.setPare_moduleid(14);
		Cart cart = null;
		// cart = this.cartService.getCartById(cart.getCartId());
		cartForm.setCart(cart);
		return "detail";
	}

	public String editCancel() throws Exception {
		// this.setPare_moduleid(14);
		Cart cart = null;
		// cart = this.cartService.getCartById(cart.getCartId());
		cartForm.setCart(cart);
		return "detail";
	}

	/** 保存新增对象 */
	@RequestMapping("/cartAddEdit.do")
	@ResponseBody
	public int cartAddEdit(Cart cart, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 校验空值
		if (cart.getProductUuid() == null || cart.getProductUuid().length() <= 0) {
			return 0;
		}

		// if(cart.getNum()<=0){
		// return 0;
		// }

		Subject subject = SecurityUtils.getSubject();
		// 是否登陆
		if (subject.isAuthenticated()) {

			// 登陆后 db

			User user = (User) subject.getPrincipal();
			cart.setCreateByUser(user.getUserId());

			Cart temp = this.cartService.getCartById(cart);

			if (temp != null) {
				cart.setNum(temp.getNum() + cart.getNum());
				this.cartService.saveCart(cart, "edit");
			} else {
				this.cartService.saveCart(cart, "add");
			}

		} else {
			// 未登录 cookie
			Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
			if (bdhcookie != null) {

				// cookie 获取数据
				String value = URLDecoder.decode(bdhcookie.getValue());
				if (value.length() > 5) {
					value = value + ";" + cart.getProductUuid() + ":" + cart.getNum();
				} else {
					value = cart.getProductUuid() + ":" + cart.getNum();
					value = URLEncoder.encode(value);
					Common.addCookie(response, "bdhcookie", value);
					return 1;
				}

				String[] bdnlist = value.split(";");
				Map<String, Integer> kv = new HashMap<String, Integer>();

				List<String> strlist = new ArrayList<String>();
				// uuid加入list 去重
				for (String st : bdnlist) {
					strlist.add(st.split(":")[0]);
				}
				// 去重后的list
				List<String> listWithoutDup = new ArrayList<String>(new HashSet<String>(strlist));

				// 计算 去重后的 商品购买数量
				for (String st : listWithoutDup) {
					int num = 0;
					for (String bdh : bdnlist) {

						if (st.equals(bdh.split(":")[0])) {
							num = num + Integer.valueOf(bdh.split(":")[1]);
						}

					}

					// 保存计算的值
					kv.put(st, num);
				}

				// 将之前 计算的 数量值 生成cookie值
				int i = 0;
				value = "";
				for (Map.Entry<String, Integer> kvmap : kv.entrySet()) {

					if (i == 0) {
						value = kvmap.getKey() + ":" + kvmap.getValue();
					} else {
						value = value + ";" + kvmap.getKey() + ":" + kvmap.getValue();
					}
					i++;
				}

				value = URLEncoder.encode(value);
				Common.addCookie(response, "bdhcookie", value);
			} else {
				String value = cart.getProductUuid() + ":" + cart.getNum();
				value = URLEncoder.encode(value);
				Common.addCookie(response, "bdhcookie", value);
			}

		}

		return 1;
	}
	
	
	/** 删除对象 */
	@RequestMapping("/cartDeleteAllSvc.do")
	@ResponseBody
	public String cartDeleteAllSvc(Cart cart, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (cart.getOrdersDetailList().size() > 0) {

			Subject subject = SecurityUtils.getSubject();
			// 是否登陆
			if (subject.isAuthenticated()) {

				// 登陆后 db

				User user = (User) subject.getPrincipal();
				cart.setCreateByUser(user.getUserId());

				// 筛选选中的 商品

				int i = 0;
				StringBuffer sb = new StringBuffer();

				for (OrdersDetail od : cart.getOrdersDetailList()) {
					if ("1".equals(od.getCode())) {

						if (i == 0) {
							sb.append("'").append(od.getProductUuid()).append("'");
						} else {
							sb.append(",").append("'").append(od.getProductUuid()).append("'");
						}
						i++;

					}
				}

				if (sb.toString().length() > 1) {

					cart.setProductUuid(sb.toString());

					// 删除后 结束程序
					this.cartService.deleteCartAll(cart);
				}

			} else {
				// 未登录 cookie
				Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
				if (bdhcookie != null) {

					// cookie 获取数据
					String value = URLDecoder.decode(bdhcookie.getValue());

					if (value.length() > 5) {

						// 删除后 结束程序
						String[] bdnlist = value.split(";");
						Map<String, Integer> kv = new HashMap<String, Integer>();

						int flag = 0;

						for (String st : bdnlist) {
							flag = 0;

							for (OrdersDetail od : cart.getOrdersDetailList()) {
								if ("1".equals(od.getCode())) {

									if (od.getProductUuid().equals(st.split(":")[0])) {
										flag = 1;
										break;
									}
								}
							}

							if (0 == flag) {
								kv.put(st.split(":")[0], Integer.valueOf(st.split(":")[1]));
							}
						}

						// 将删除后的数据 生成cookie值
						int i = 0;
						value = "";
						for (Map.Entry<String, Integer> kvmap : kv.entrySet()) {

							if (i == 0) {
								value = kvmap.getKey() + ":" + kvmap.getValue();
							} else {
								value = value + ";" + kvmap.getKey() + ":" + kvmap.getValue();
							}
							i++;
						}

					}

					value = URLEncoder.encode(value);
					Common.addCookie(response, "bdhcookie", value);

				}

			}

		}

		return "000";
	}
	
	

	/** 删除对象 */
	@RequestMapping("/cartDeleteAll.do")
	public String cartDeleteAll(Cart cart, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (cart.getOrdersDetailList().size() > 0) {

			Subject subject = SecurityUtils.getSubject();
			// 是否登陆
			if (subject.isAuthenticated()) {

				// 登陆后 db

				User user = (User) subject.getPrincipal();
				cart.setCreateByUser(user.getUserId());

				// 筛选选中的 商品

				int i = 0;
				StringBuffer sb = new StringBuffer();

				for (OrdersDetail od : cart.getOrdersDetailList()) {
					if ("1".equals(od.getCode())) {

						if (i == 0) {
							sb.append("'").append(od.getProductUuid()).append("'");
						} else {
							sb.append(",").append("'").append(od.getProductUuid()).append("'");
						}
						i++;

					}
				}

				if (sb.toString().length() > 1) {

					cart.setProductUuid(sb.toString());

					// 删除后 结束程序
					this.cartService.deleteCartAll(cart);
				}

			} else {
				// 未登录 cookie
				Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
				if (bdhcookie != null) {

					// cookie 获取数据
					String value = URLDecoder.decode(bdhcookie.getValue());

					if (value.length() > 5) {

						// 删除后 结束程序
						String[] bdnlist = value.split(";");
						Map<String, Integer> kv = new HashMap<String, Integer>();

						int flag = 0;

						for (String st : bdnlist) {
							flag = 0;

							for (OrdersDetail od : cart.getOrdersDetailList()) {
								if ("1".equals(od.getCode())) {

									if (od.getProductUuid().equals(st.split(":")[0])) {
										flag = 1;
										break;
									}
								}
							}

							if (0 == flag) {
								kv.put(st.split(":")[0], Integer.valueOf(st.split(":")[1]));
							}
						}

						// 将删除后的数据 生成cookie值
						int i = 0;
						value = "";
						for (Map.Entry<String, Integer> kvmap : kv.entrySet()) {

							if (i == 0) {
								value = kvmap.getKey() + ":" + kvmap.getValue();
							} else {
								value = value + ";" + kvmap.getKey() + ":" + kvmap.getValue();
							}
							i++;
						}

					}

					value = URLEncoder.encode(value);
					Common.addCookie(response, "bdhcookie", value);

				}

			}

		}

		return "redirect:/product/cartProductListPage.do";
	}
	
	
	/** 删除对象 */
	@RequestMapping("/cartDeleteSvc.do")
	@ResponseBody
	public String cartDeleteSvc(Cart cart, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 校验空值
		if (cart.getProductUuid() == null || cart.getProductUuid().length() <= 0) {
			return "001";
		}
		Subject subject = SecurityUtils.getSubject();
		// 是否登陆
		if (subject.isAuthenticated()) {

			// 登陆后 db

			User user = (User) subject.getPrincipal();
			cart.setCreateByUser(user.getUserId());

			// 删除后 结束程序
			this.cartService.deleteCart(cart);

		} else {
			// 未登录 cookie
			Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
			if (bdhcookie != null) {

				// cookie 获取数据
				String value = URLDecoder.decode(bdhcookie.getValue());

				if (value.length() > 5) {

					// 删除后 结束程序
					String[] bdnlist = value.split(";");
					Map<String, Integer> kv = new HashMap<String, Integer>();

					for (String st : bdnlist) {
						if (cart.getProductUuid().equals(st.split(":")[0])) {
							continue;
						}
						kv.put(st.split(":")[0], Integer.valueOf(st.split(":")[1]));

					}

					// 将删除后的数据 生成cookie值
					int i = 0;
					value = "";
					for (Map.Entry<String, Integer> kvmap : kv.entrySet()) {

						if (i == 0) {
							value = kvmap.getKey() + ":" + kvmap.getValue();
						} else {
							value = value + ";" + kvmap.getKey() + ":" + kvmap.getValue();
						}
						i++;
					}

				}

				value = URLEncoder.encode(value);
				Common.addCookie(response, "bdhcookie", value);

			}

		}

		return "000";
	}
	
	

	/** 删除对象 */
	@RequestMapping("/cartDelete.do")
	public String cartDelete(Cart cart, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 校验空值
		if (cart.getProductUuid() == null || cart.getProductUuid().length() <= 0) {
			return "";
		}
		Subject subject = SecurityUtils.getSubject();
		// 是否登陆
		if (subject.isAuthenticated()) {

			// 登陆后 db

			User user = (User) subject.getPrincipal();
			cart.setCreateByUser(user.getUserId());

			// 删除后 结束程序
			this.cartService.deleteCart(cart);

		} else {
			// 未登录 cookie
			Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
			if (bdhcookie != null) {

				// cookie 获取数据
				String value = URLDecoder.decode(bdhcookie.getValue());

				if (value.length() > 5) {

					// 删除后 结束程序
					String[] bdnlist = value.split(";");
					Map<String, Integer> kv = new HashMap<String, Integer>();

					for (String st : bdnlist) {
						if (cart.getProductUuid().equals(st.split(":")[0])) {
							continue;
						}
						kv.put(st.split(":")[0], Integer.valueOf(st.split(":")[1]));

					}

					// 将删除后的数据 生成cookie值
					int i = 0;
					value = "";
					for (Map.Entry<String, Integer> kvmap : kv.entrySet()) {

						if (i == 0) {
							value = kvmap.getKey() + ":" + kvmap.getValue();
						} else {
							value = value + ";" + kvmap.getKey() + ":" + kvmap.getValue();
						}
						i++;
					}

				}

				value = URLEncoder.encode(value);
				Common.addCookie(response, "bdhcookie", value);

			}

		}

		return "redirect:/product/cartProductListPage.do";
	}

	public CartForm getModel() {
		return cartForm;
	}

	public List<Cart> getCartList() {
		return this.cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
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
