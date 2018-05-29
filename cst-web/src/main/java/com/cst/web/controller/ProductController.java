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
import org.json.JSONObject;
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

import com.cst.service.model.Cart;
import com.cst.service.model.GoodsAddress;
import com.cst.service.model.Manager;
import com.cst.service.model.Nation;
import com.cst.service.model.Parameter;
import com.cst.service.model.Product;
import com.cst.service.model.ProductAttribute;
import com.cst.service.model.ProductCfg;
import com.cst.service.model.ProductForm;
import com.cst.service.model.ProductPic;
import com.cst.service.model.Shop;
import com.cst.service.model.Stock;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.xmlrpc.base.Array;
import com.cst.service.CartService;
import com.cst.service.GoodsAddressService;
import com.cst.service.NationService;
import com.cst.service.ParameterService;
import com.cst.service.ProductAttributeService;
import com.cst.service.ProductCfgService;
import com.cst.service.ProductPicService;
import com.cst.service.ProductService;
import com.cst.service.ShopService;
import com.cst.service.StockService;

/**
 * @author lind
 */

@Controller
@RequestMapping("/product")
public class ProductController {

	// 业务逻辑对象
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private NationService nationService;
	
	
	@Autowired
	private ProductAttributeService productAttributeService;
	
	@Autowired
	private ProductPicService productPicService;
	
	@Autowired
	private GoodsAddressService goodsAddressService;
	
	@Autowired
	private ShopService shopService;

	@Autowired
	private StockService stockService;
	
	@Autowired
	private ProductCfgService productCfgService;

	
	@Autowired
	private ParameterService parameterService;

	// 查询结果
	private List<Product> productList;

	private ProductForm productForm = new ProductForm();

	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;

	/** 执行搜索 */
	@RequestMapping("/productListPage.do")
	public String productListPage(Product product, Model model) throws Exception {
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		product.setCreateByAdm(mg.getManagerId());

		productList = productService.getProductListPage(product);

		Parameter parameter = new Parameter();
		parameter.setParameterTypeId(1);
		List<Parameter> parameterList = parameterService.getParameterList(parameter);

		model.addAttribute("productList", productList);
		model.addAttribute("parameterList", parameterList);
		model.addAttribute("page", product.getPage());
		model.addAttribute("errcode", product.getErrcode());

		return "/jsp/product/productIndex";
	}
	
	
	@RequestMapping("/cartProductListPage.do")
	public String cartProductListPage(Product product,HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		Subject subject = SecurityUtils.getSubject();
		//是否登陆
		if(subject.isAuthenticated()){
			
			User user = (User)subject.getPrincipal();
			
			//获取购物车内商品
			product.setCreateByAdm(user.getUserId());
			productList = productService.getCartdbProductListPage(product);
			
					
		}else{
			//cookie 获取数据
			Cookie bdhcookie= Common.getCookieByName(request, "bdhcookie");
			if(bdhcookie!=null){
			String value= URLDecoder.decode(bdhcookie.getValue());
			
			//cookie不能是空值
			if(value.length()>5){
				
			String[] bdnlist = value.split(";");
			Map<String, Integer> kv =new HashMap<String, Integer>();
			
			StringBuffer uuids=new StringBuffer();
			
			//计算   商品列表查询参数
			int i=0;
			for(String st : bdnlist){
				kv.put(st.split(":")[0], Integer.valueOf(st.split(":")[1]));
				
				if(i==0){
					uuids.append("'").append(st.split(":")[0]).append("'");
				}else{
					uuids.append(",").append("'").append(st.split(":")[0]).append("'");
				}
				i++;
			}
			
			
			//查询商品
			product.setProductUuid(uuids.toString());
			productList = productService.getCartcookieProductListPage(product);
			
			//将之前 计算的 数量值  赋值给 list
			for(Map.Entry<String,Integer> kvmap : kv.entrySet()){
				for(Product prd : productList){
					
					if(kvmap.getKey().equals(prd.getProductUuid())){
						prd.setCapacity(kvmap.getValue());
					}
					
				}
			}
			
			
			}else{
				productList=null;
			}
			}
			
		}
		

		model.addAttribute("productList", productList);
		model.addAttribute("page", product.getPage());

		return "/jsp/cart/cart";
	}
	
	
	
	
	
	@RequestMapping("/cartProductSvcListPage.do")
	@ResponseBody
	public Product cartProductSvcListPage(Product product,HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		Subject subject = SecurityUtils.getSubject();
		//是否登陆
		if(subject.isAuthenticated()){
			
			User user = (User)subject.getPrincipal();
			
			//获取购物车内商品
			product.setCreateByAdm(user.getUserId());
			productList = productService.getCartdbProductListPage(product);
			
					
		}else{
			//cookie 获取数据
			Cookie bdhcookie= Common.getCookieByName(request, "bdhcookie");
			if(bdhcookie!=null){
			String value= URLDecoder.decode(bdhcookie.getValue());
			
			//cookie不能是空值
			if(value.length()>5){
				
			String[] bdnlist = value.split(";");
			Map<String, Integer> kv =new HashMap<String, Integer>();
			
			StringBuffer uuids=new StringBuffer();
			
			//计算   商品列表查询参数
			int i=0;
			for(String st : bdnlist){
				kv.put(st.split(":")[0], Integer.valueOf(st.split(":")[1]));
				
				if(i==0){
					uuids.append("'").append(st.split(":")[0]).append("'");
				}else{
					uuids.append(",").append("'").append(st.split(":")[0]).append("'");
				}
				i++;
			}
			
			
			//查询商品
			product.setProductUuid(uuids.toString());
			productList = productService.getCartcookieProductListPage(product);
			
			//将之前 计算的 数量值  赋值给 list
			for(Map.Entry<String,Integer> kvmap : kv.entrySet()){
				for(Product prd : productList){
					
					if(kvmap.getKey().equals(prd.getProductUuid())){
						prd.setCapacity(kvmap.getValue());
					}
					
				}
			}
			
			
			}else{
				productList=null;
			}
			}
			
		}
		

		product.setProducts(productList);
//		model.addAttribute("productList", productList);
//		model.addAttribute("page", product.getPage());

		return product;
	}
	
	
	
	
	@RequestMapping("/productForPicListPage.do")
	public String productForPicListPage(Product product, Model model) throws Exception {
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));

		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		product.setCreateByAdm(mg.getManagerId());

		productList = productService.getProductListPage(product);

		model.addAttribute("productList", productList);
		model.addAttribute("page", product.getPage());
		model.addAttribute("errcode", product.getErrcode());

		return "/jsp/productPic/productPicIndex";
	}

	/** 编辑前初始化对象 */
	@RequestMapping("/productAddEditIni.do")
	public String productAddEditIni(Product product, Model model) throws Exception {

		model.addAttribute("errcode", product.getErrcode());
		product = this.productService.getProductById(product.getProductUuid());
		
		Parameter parameter = new Parameter();
		parameter.setParameterTypeId(1);
		List<Parameter> parameterList = parameterService.getParameterList(parameter);
		model.addAttribute("parameterList", parameterList);
		

		model.addAttribute("product", product);
		return "/jsp/product/productEdit";
	}
	
	/** 查看对象 */
	@RequestMapping("/productDetailSvc.do")
	@ResponseBody
	public Product productDetailSvc(Product product,HttpServletRequest request) throws Exception {
		// this.setPare_moduleid(14);
		// product = this.productService.getProductById(product.getProductId());
		
		product = this.productService.getProductById(product.getProductUuid());
		
		
		//转化Description 为 html代码
		if(product.getDescription()!=null&&product.getDescription().length()>0){
			
		String[] des = product.getDescription().split("\n");
		
		StringBuffer sb =new StringBuffer();
		
		for(String temp : des){
			sb.append("<img src=\"");
			sb.append(temp);
			sb.append("\" />");
		}
		
		product.setDescription(sb.toString());
		
		}
		
		
		
		//为测试方便 替换访问根路径
//		product.setDescription(product.getDescription().replaceAll("business-manager", "business-web"));
		
		//商品属性
		List<ProductAttribute> productAttributeList= productAttributeService.getProductAttributeById(product.getProductUuid());
		//商品轮播图片
		List<ProductPic> productPicList= productPicService.getProductPicById(product.getProductUuid());
		//如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			User user = (User) subject.getPrincipal();
			
			GoodsAddress goodsAddress =new GoodsAddress();
			goodsAddress.setCreateByUser(user.getUserId());
			goodsAddress=this.goodsAddressService.getGoodsAddressByDefault(goodsAddress);
			
			if(goodsAddress!=null){
				Shop shop =new Shop();
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
				
				
				if(shopList!=null && shopList.size()>0){
					
					
					product.setShopName(shopList.get(0).getName());
//					model.addAttribute("shopName", shopList.get(0).getName());
					
					//查询库存
					Stock stock=new Stock();
					stock.setProductUuid(product.getProductUuid());
					stock.setShopId(shopList.get(0).getShopId());
					stock =stockService.getStockByPrd(stock);
					//显示库存
					if(stock!=null){
						product.setShowCapacity(stock.getShowCapacity()-stock.getSellCapacity());
//					model.addAttribute("showCapacity", stock.getShowCapacity()-stock.getSellCapacity());
					}else{
						product.setShowCapacity(0);
//						model.addAttribute("showCapacity", 0);
					}
					
					product.setDowhat("1");
				}else{
					product.setDowhat("0");
//					model.addAttribute("shopName", "您的默认地址附近没有匹配的店，不支持配送。");
					product.setShowCapacity(0);
//					model.addAttribute("showCapacity", 0);
				}
				
				product.setGoodsAddress(goodsAddress);
//				model.addAttribute("goodsAddress", goodsAddress);
			}
			else{
				
//				model.addAttribute("shopName", "通江大道店（点击维护收货地址）");
//				model.addAttribute("showCapacity", 4539275);
				
				//没有默认地址 跳转到地址维护页面
				
//				return "redirect:/goodsAddress/goodsAddressListPage.do";
				//获取ip 定位 位置
				String ip = Common.getIpAddr(request);
				
				String json = Common.getJsonString("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
				
				ObjectMapper mapper = new ObjectMapper();  
	            JsonNode rootNode = mapper.readTree(json); // 读取Json
	            
				if(0==rootNode.path("code").asInt()){
					
					JsonNode data = rootNode.path("data"); 
					GoodsAddress ga =new GoodsAddress();
					ga.setProvincestr(data.path("region").asText());
					ga.setCitystr(data.path("city").asText());
					ga.setDistrictstr(data.path("county").asText());
					
					product.setGoodsAddress(ga);
//					model.addAttribute("goodsAddress", ga);
					
					//匹配店
					String citycode=data.path("city_id").asText();
					Nation nation= nationService.getNationByCode(citycode);
					
					
					if(nation!=null && nation.getId()>0){
					Shop shop =new Shop();
//					shop.setDistrict(goodsAddress.getDistrict());
//					shop.setStreet(goodsAddress.getStreet());
					shop.setCity(nation.getId());
					List<Shop> shopList = this.shopService.getShopByAddress(shop);
					
					//如果 区县也没有匹配的店  ，就匹配总部
					if (shopList == null || shopList.size() == 0) {

						shopList = new ArrayList<Shop>();
						Shop sh =new Shop();
						sh.setShopId(3);
						sh.setName("无锡市通江大道店");
						sh.setNickName("无锡市通江大道店");
						
						shopList.add(sh);
						}
					
					if(shopList!=null && shopList.size()>0){
						product.setDowhat("2");
						
						product.setShopName(shopList.get(0).getName());
//						model.addAttribute("shopName", shopList.get(0).getName());
						
						//查询库存
						Stock stock=new Stock();
						stock.setProductUuid(product.getProductUuid());
						stock.setShopId(shopList.get(0).getShopId());
						stock =stockService.getStockByPrd(stock);
						//显示库存
						if(stock!=null){
							product.setShowCapacity(stock.getShowCapacity()-stock.getSellCapacity());
//						model.addAttribute("showCapacity", stock.getShowCapacity()-stock.getSellCapacity());
						}else{
							product.setShowCapacity(0);
//							model.addAttribute("showCapacity", 0);
						}
						
					}else{
						product.setDowhat("0");
//						model.addAttribute("shopName", "您的默认地址附近没有匹配的店，不支持配送。");
						product.setShowCapacity(0);
//						model.addAttribute("showCapacity", 0);
					}
					
					}else{
						product.setShowCapacity(4539);
//						model.addAttribute("showCapacity", 4539);
					}
					
				
				}
			}
		}else{
			product.setShopName("无锡市通江大道店");
			
//			model.addAttribute("shopName", "无锡市通江大道店");
			GoodsAddress ga =new GoodsAddress();
			ga.setProvincestr("江苏省");
			ga.setCitystr("无锡市");
			
			product.setGoodsAddress(ga);
//			model.addAttribute("goodsAddress", ga);
			product.setShowCapacity(4539);
//			model.addAttribute("showCapacity", 4539);
			
		}
		
		
		//是否登陆
		if(subject.isAuthenticated()){
			
			User user = (User)subject.getPrincipal();
			
			//获取购物车内商品
			Product p =new Product();
			p.setCreateByAdm(user.getUserId());
			List<Product> productList = productService.getCartdbProductListPage(p);
			if(productList==null){
				product.setNum(0);
//				model.addAttribute("num", 0);	
			}else{
				product.setNum(productList.size());
//				model.addAttribute("num", productList.size());
			}
			
		}else{
			//cookie 获取数据
			Cookie bdhcookie= Common.getCookieByName(request, "bdhcookie");
			if(bdhcookie!=null){
			String value= URLDecoder.decode(bdhcookie.getValue());
			
			//cookie不能是空值
			if(value.length()>5){
				
			String[] bdnlist = value.split(";");
			
			product.setNum(bdnlist.length);
//			model.addAttribute("num", bdnlist.length);
			
			}else{
				product.setNum(0);
//				model.addAttribute("num", 0);
			}
			}else{
				product.setNum(0);
//				model.addAttribute("num", 0);
			}
			
		}
		
		
		
		
		ProductCfg productCfg =new ProductCfg();
		List<ProductCfg> productCfgList=productCfgService.getProductCfgListByType(productCfg);
		product.setProductCfgList(productCfgList);
//		
//		model.addAttribute("productCfgList", productCfgList);
		
		
//		model.addAttribute("product", product);
		
		product.setProductAttributeList(productAttributeList);
//		model.addAttribute("productAttributeList", productAttributeList);
		product.setProductPicList(productPicList);
//		model.addAttribute("productPicList", productPicList);
		return product;
	}
	
	

	/** 查看对象 */
	@RequestMapping("/productDetail.do")
	public String productDetail(Product product, Model model,HttpServletRequest request) throws Exception {
		// this.setPare_moduleid(14);
		// product = this.productService.getProductById(product.getProductId());
		
		product = this.productService.getProductById(product.getProductUuid());
		
		
		//转化Description 为 html代码
		if(product.getDescription()!=null&&product.getDescription().length()>0){
			
		String[] des = product.getDescription().split("\n");
		
		StringBuffer sb =new StringBuffer();
		
		for(String temp : des){
			sb.append("<img src=\"");
			sb.append(temp);
			sb.append("\" />");
		}
		
		product.setDescription(sb.toString());
		
		}
		
		
		
		//为测试方便 替换访问根路径
//		product.setDescription(product.getDescription().replaceAll("business-manager", "business-web"));
		
		//商品属性
		List<ProductAttribute> productAttributeList= productAttributeService.getProductAttributeById(product.getProductUuid());
		//商品轮播图片
		List<ProductPic> productPicList= productPicService.getProductPicById(product.getProductUuid());
		//如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			User user = (User) subject.getPrincipal();
			
			GoodsAddress goodsAddress =new GoodsAddress();
			goodsAddress.setCreateByUser(user.getUserId());
			goodsAddress=this.goodsAddressService.getGoodsAddressByDefault(goodsAddress);
			
			if(goodsAddress!=null){
				Shop shop =new Shop();
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
				
				
				if(shopList!=null && shopList.size()>0){
					model.addAttribute("shopName", shopList.get(0).getName());
					
					//查询库存
					Stock stock=new Stock();
					stock.setProductUuid(product.getProductUuid());
					stock.setShopId(shopList.get(0).getShopId());
					stock =stockService.getStockByPrd(stock);
					//显示库存
					if(stock!=null){
					model.addAttribute("showCapacity", stock.getShowCapacity()-stock.getSellCapacity());
					}else{
						model.addAttribute("showCapacity", 0);
					}
					
					product.setDowhat("1");
				}else{
					product.setDowhat("0");
//					model.addAttribute("shopName", "您的默认地址附近没有匹配的店，不支持配送。");
					model.addAttribute("showCapacity", 0);
				}
				
				model.addAttribute("goodsAddress", goodsAddress);
			}
			else{
				
//				model.addAttribute("shopName", "通江大道店（点击维护收货地址）");
//				model.addAttribute("showCapacity", 4539275);
				
				//没有默认地址 跳转到地址维护页面
				
//				return "redirect:/goodsAddress/goodsAddressListPage.do";
				//获取ip 定位 位置
				String ip = Common.getIpAddr(request);
				
				String json = Common.getJsonString("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
				
				ObjectMapper mapper = new ObjectMapper();  
	            JsonNode rootNode = mapper.readTree(json); // 读取Json
	            
				if(0==rootNode.path("code").asInt()){
					
					JsonNode data = rootNode.path("data"); 
					GoodsAddress ga =new GoodsAddress();
					ga.setProvincestr(data.path("region").asText());
					ga.setCitystr(data.path("city").asText());
					ga.setDistrictstr(data.path("county").asText());
					model.addAttribute("goodsAddress", ga);
					
					//匹配店
					String citycode=data.path("city_id").asText();
					Nation nation= nationService.getNationByCode(citycode);
					
					
					if(nation!=null && nation.getId()>0){
					Shop shop =new Shop();
//					shop.setDistrict(goodsAddress.getDistrict());
//					shop.setStreet(goodsAddress.getStreet());
					shop.setCity(nation.getId());
					List<Shop> shopList = this.shopService.getShopByAddress(shop);
					
					//如果 区县也没有匹配的店  ，就匹配总部
					if (shopList == null || shopList.size() == 0) {

						shopList = new ArrayList<Shop>();
						Shop sh =new Shop();
						sh.setShopId(3);
						sh.setName("无锡市通江大道店");
						sh.setNickName("无锡市通江大道店");
						
						shopList.add(sh);
						}
					
					if(shopList!=null && shopList.size()>0){
						product.setDowhat("2");
						model.addAttribute("shopName", shopList.get(0).getName());
						
						//查询库存
						Stock stock=new Stock();
						stock.setProductUuid(product.getProductUuid());
						stock.setShopId(shopList.get(0).getShopId());
						stock =stockService.getStockByPrd(stock);
						//显示库存
						if(stock!=null){
						model.addAttribute("showCapacity", stock.getShowCapacity()-stock.getSellCapacity());
						}else{
							model.addAttribute("showCapacity", 0);
						}
						
					}else{
						product.setDowhat("0");
//						model.addAttribute("shopName", "您的默认地址附近没有匹配的店，不支持配送。");
						model.addAttribute("showCapacity", 0);
					}
					
					}else{
						model.addAttribute("showCapacity", 4539);
					}
					
				
				}
			}
		}else{
			model.addAttribute("shopName", "无锡市通江大道店");
			GoodsAddress ga =new GoodsAddress();
			ga.setProvincestr("江苏省");
			ga.setCitystr("无锡市");
			model.addAttribute("goodsAddress", ga);
			model.addAttribute("showCapacity", 4539);
			
		}
		
		
		//是否登陆
		if(subject.isAuthenticated()){
			
			User user = (User)subject.getPrincipal();
			
			//获取购物车内商品
			Product p =new Product();
			p.setCreateByAdm(user.getUserId());
			List<Product> productList = productService.getCartdbProductListPage(p);
			if(productList==null){
				model.addAttribute("num", 0);	
			}else{
				model.addAttribute("num", productList.size());
			}
			
		}else{
			//cookie 获取数据
			Cookie bdhcookie= Common.getCookieByName(request, "bdhcookie");
			if(bdhcookie!=null){
			String value= URLDecoder.decode(bdhcookie.getValue());
			
			//cookie不能是空值
			if(value.length()>5){
				
			String[] bdnlist = value.split(";");
			model.addAttribute("num", bdnlist.length);
			
			}else{
				model.addAttribute("num", 0);
			}
			}else{
				model.addAttribute("num", 0);
			}
			
		}
		
		
		
		model.addAttribute("product", product);
		model.addAttribute("productAttributeList", productAttributeList);
		model.addAttribute("productPicList", productPicList);
		return "/jsp/product/productDetail";
	}
	
	
	
	
	/** 查看对象 */
	@RequestMapping("/preProductDetail.do")
	public String preProductDetail(Product product, Model model,HttpServletRequest request) throws Exception {
		// this.setPare_moduleid(14);
		// product = this.productService.getProductById(product.getProductId());
		
		product = this.productService.getProductById(product.getProductUuid());
		
		
		//转化Description 为 html代码
				if(product.getDescription()!=null&&product.getDescription().length()>0){
					
				String[] des = product.getDescription().split("\n");
				
				StringBuffer sb =new StringBuffer();
				
				for(String temp : des){
					sb.append("<img src=\"");
					sb.append(temp);
					sb.append("\" />");
				}
				
				product.setDescription(sb.toString());
				
				}
		
		//为测试方便 替换访问根路径
//		product.setDescription(product.getDescription().replaceAll("business-manager", "business-web"));
		
		//商品属性
		List<ProductAttribute> productAttributeList= productAttributeService.getProductAttributeById(product.getProductUuid());
		//商品轮播图片
		List<ProductPic> productPicList= productPicService.getProductPicById(product.getProductUuid());
			
			GoodsAddress ga =new GoodsAddress();
			ga.setProvincestr("江苏省");
			ga.setCitystr("无锡市");
			model.addAttribute("goodsAddress", ga);
			model.addAttribute("showCapacity", 4539);
		
			Subject subject = SecurityUtils.getSubject();
		//是否登陆
		if(subject.isAuthenticated()){
			
			User user = (User)subject.getPrincipal();
			
			//获取购物车内商品
			Product p =new Product();
			p.setCreateByAdm(user.getUserId());
			List<Product> productList = productService.getCartdbProductListPage(p);
			if(productList==null){
				model.addAttribute("num", 0);	
			}else{
				model.addAttribute("num", productList.size());
			}
			
		}else{
			//cookie 获取数据
			Cookie bdhcookie= Common.getCookieByName(request, "bdhcookie");
			if(bdhcookie!=null){
			String value= URLDecoder.decode(bdhcookie.getValue());
			
			//cookie不能是空值
			if(value.length()>5){
				
			String[] bdnlist = value.split(";");
			model.addAttribute("num", bdnlist.length);
			
			}else{
				model.addAttribute("num", 0);
			}
			}else{
				model.addAttribute("num", 0);
			}
			
		}
		
		
		
		model.addAttribute("product", product);
		model.addAttribute("productAttributeList", productAttributeList);
		model.addAttribute("productPicList", productPicList);
		return "/jsp/product/preProductDetail";
	}
	
	
	
	

	public String editCancel() throws Exception {
		// this.setPare_moduleid(14);
		Product product = null;
		// product = this.productService.getProductById(product.getProductId());
		productForm.setProduct(product);
		return "detail";
	}

	/** 保存新增对象 */
	@RequestMapping("/saveProduct.do")
	public ModelAndView productAddEdit(Product product, ModelAndView model) throws Exception {
		// this.setPare_moduleid(14);
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		String validate = null;
		// add 新增 edit 修改
		if ("add".equals(product.getDowhat())) {

			// 后端校验 101 名称 编号 价格 原价 类型 描述 图片 等不能为空

			if (product.getName() == null || product.getName().length() == 0) {
				validate = "101";
			}
			if (product.getPrice() <= 0) {
				validate = "101";
			}
			if (product.getCostPrice() <= 0) {
				validate = "101";
			}

			if (product.getCode() == null || product.getCode().length() == 0) {
				validate = "101";
			}
			if (product.getTypeId() == null || product.getTypeId() <= 0) {
				validate = "101";
			}
			if (product.getPic() == null || product.getPic().length() == 0) {
				validate = "101";
			}
			if (product.getDescription() == null || product.getDescription().length() == 0) {
				validate = "101";
			}

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/product/productListPage.do");
				return model;
			}

			// 校验结束

			// 1 表示 上架 ，这里直接赋予 上架状态
			product.setStatus(1);

			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			product.setCreateByAdm(mg.getManagerId());

			// 生成uuid
			product.setProductUuid(Common.getUUID());

		} else if ("edit".equals(product.getDowhat())) {
			// 后端校验 101 名称 编号 价格 原价 类型 描述 图片 等不能为空

			if (product.getName() == null || product.getName().length() == 0) {
				validate = "101";
			}
			if (product.getPrice() <= 0) {
				validate = "101";
			}
			if (product.getCostPrice() <= 0) {
				validate = "101";
			}

			if (product.getCode() == null || product.getCode().length() == 0) {
				validate = "101";
			}
			if (product.getTypeId() == null || product.getTypeId() <= 0) {
				validate = "101";
			}
//			if (product.getPic() == null || product.getPic().length() == 0) {
//				validate = "101";
//			}
			if (product.getDescription() == null || product.getDescription().length() == 0) {
				validate = "101";
			}

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.addObject("productUuid", product.getProductUuid());
				model.setViewName("redirect:/product/productAddEditIni.do");
				return model;
			}
		}

		try {
			this.productService.saveProduct(product, product.getDowhat());
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");

			if ("edit".equals(product.getDowhat())) {
				model.addObject("productUuid", product.getProductUuid());
				model.setViewName("redirect:/product/productAddEditIni.do");
				return model;
			}

		}

		model.setViewName("redirect:/product/productListPage.do");
		return model;
	}

	/** 删除对象 */
	@RequestMapping("/delProduct.do")
	public String productDelete(Product product) throws Exception {
		this.productService.deleteProduct(product.getProductUuid());
		return "redirect:/product/productListPage.do";
	}

	public ProductForm getModel() {
		return productForm;
	}

	public List<Product> getProductList() {
		return this.productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
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
