package com.cst.manager.controller;

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

import com.cst.service.model.Manager;
import com.cst.service.model.Shop;
import com.cst.service.model.ShopForm;
import com.cst.service.ShopService;

/**
 * @author lind
 */

@Controller
@RequestMapping("/shop")
public class ShopController {

	// 业务逻辑对象
	@Autowired
	private ShopService shopService;

	// 查询结果
	private List<Shop> shopList;

	private ShopForm shopForm = new ShopForm();

	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;

	/** 执行搜索 */
	@RequestMapping("/shopListPage.do")
	public String shopListPage(Shop shop, Model model) throws Exception {
		// shopForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		shop.setCreateByAdm(mg.getManagerId());

		shopList = shopService.getShopListPage(shop);
		
		model.addAttribute("shopList", shopList);
		model.addAttribute("page", shop.getPage());
		model.addAttribute("errcode", shop.getErrcode());
		
		if(shop.getMenuId()!=null&&shop.getMenuId()>0){
		subject.getSession().setAttribute("menuId", shop.getMenuId());
		}

		// request.setAttribute("page", shopForm.getPage());
		return "/jsp/shop/shopIndex";
	}

	/** 编辑前初始化对象 */
	@RequestMapping("/shopEditIni.do")
	public String shopAddEditIni(Shop shop,Model model) throws Exception {
		
		model.addAttribute("errcode", shop.getErrcode());
		
		shop = this.shopService.getShopById(shop.getShopId());
		
		model.addAttribute("shop", shop);
		return "/jsp/shop/shopEdit";
	}
	
	
	
	@RequestMapping("/shopAdd.do")
	public String shopAdd(Shop shop,Model model) throws Exception {
		
		model.addAttribute("errcode", shop.getErrcode());
		
		model.addAttribute("shop", shop);
		return "/jsp/shop/shopAdd";
	}
	
	

	/** 查看对象 */
	public String shopDetail() throws Exception {
		// this.setPare_moduleid(14);
		Shop shop = null;
		shop = this.shopService.getShopById(shop.getShopId());
		shopForm.setShop(shop);
		return "detail";
	}

	public String editCancel() throws Exception {
		// this.setPare_moduleid(14);
		Shop shop = null;
		shop = this.shopService.getShopById(shop.getShopId());
		shopForm.setShop(shop);
		return "detail";
	}

	/** 保存新增对象 */
	@RequestMapping("/saveShop.do")
	public ModelAndView shopAddEdit(Shop shop, ModelAndView model) throws Exception {
		// this.setPare_moduleid(14);
		// shopForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		String validate = null;
		// add 新增 edit 修改
		if ("add".equals(shop.getDowhat())) {

			// 后端校验 101 省市县 街道 名称 编号 等不能为空

			if (shop.getProvince() == null || shop.getProvince() <= 0) {
				validate = "101";
			}
			if (shop.getCity() == null || shop.getCity() <= 0) {
				validate = "101";
			}
			if (shop.getDistrict() == null || shop.getDistrict() <= 0) {
				validate = "101";
			}
//			if (shop.getStreet() == null || shop.getStreet() <= 0) {
//				validate = "101";
//			}

			if (shop.getName() == null || shop.getName().length() == 0) {
				validate = "101";
			}

			if (shop.getCode() == null || shop.getCode().length() == 0) {
				validate = "101";
			}
			if (shop.getAddress() == null || shop.getAddress().length() == 0) {
				validate = "101";
			}
			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/shop/shopAdd.do");
				return model;
			}

			// 校验结束

			// 1 表示 上架 ，这里直接赋予 上架状态
			shop.setStatus(1);

			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			shop.setCreateByAdm(mg.getManagerId());

		} else if ("edit".equals(shop.getDowhat())) {
			if (shop.getName() == null || shop.getName().length() == 0) {
				validate = "101";
			}

			if (shop.getCode() == null || shop.getCode().length() == 0) {
				validate = "101";
			}
			if (shop.getAddress() == null || shop.getAddress().length() == 0) {
				validate = "101";
			}

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.addObject("shopId", shop.getShopId());
				model.setViewName("redirect:/shop/shopEditIni.do");
				return model;
			}
		}

		try {
			this.shopService.saveShop(shop, shop.getDowhat());
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			
			if("edit".equals(shop.getDowhat())){
				model.addObject("shopId", shop.getShopId());
				model.setViewName("redirect:/shop/shopEditIni.do");
				return model;
			}else{
				
				model.setViewName("redirect:/shop/shopAdd.do");
				return model;
			}

		}
		
		

		model.setViewName("redirect:/shop/shopListPage.do");
		return model;
	}

	/** 删除对象 */
	@RequestMapping("/delShop.do")
	public String shopDelete(Shop shop) throws Exception {
		this.shopService.deleteShop(shop.getShopId());
		return "redirect:/shop/shopListPage.do";
	}

	public ShopForm getModel() {
		return shopForm;
	}

	public List<Shop> getShopList() {
		return this.shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
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
