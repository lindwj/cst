package com.cst.wap.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.cst.service.model.IndexBanner;
import com.cst.service.model.IndexBannerForm;
import com.cst.service.model.Manager;
import com.cst.service.model.Parameter;
import com.cst.service.model.Product;
import com.cst.service.model.ProductCfg;
import com.cst.service.model.Stock;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.service.IndexBannerService;
import com.cst.service.ParameterService;
import com.cst.service.ProductCfgService;
import com.cst.service.ProductService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/indexBanner")
public class IndexBannerController{
	
	// 业务逻辑对象
	@Autowired
	private IndexBannerService indexBannerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCfgService productCfgService;
	
	@Autowired
	private ParameterService parameterService;
	
	// 查询结果
	private List<IndexBanner> indexBannerList;
	
	private IndexBannerForm indexBannerForm=new IndexBannerForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String indexBannerListPage() throws Exception{
		//indexBannerForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		IndexBanner indexBanner=null;
		indexBannerList = indexBannerService.getIndexBannerListPage(indexBanner);
		indexBannerForm.setIndexBanner(indexBanner);
		request.setAttribute("page", indexBannerForm.getPage());
		return "list";
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/indexBannerAddEditIni.do")
	public String indexBannerAddEditIni(IndexBanner indexBanner,Model model,HttpServletRequest request) throws Exception{
//		indexBanner = this.indexBannerService.getIndexBannerById(indexBanner.getIndexBannerId());
		
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
		
		//查询商品
		List<Product> productList= productService.getProductForIndex("");
		
		//查询商品分类  type=1
		Parameter parameter =new Parameter();
		parameter.setParameterTypeId(1);
		List<Parameter> pList= parameterService.getParameterList(parameter);
		
		//临时变量
		List<Product> productListTemp=new ArrayList<Product>();
		
		//按分类 分别拆分商品数据
		int i=0;
		for(Parameter ptemp:pList){
			//最后一个分类  没必要迭代
			if((i+1)==pList.size()){
				ptemp.setProductList(productList);
				continue;
			}
			
			for(Product product:productList){
				if((int)ptemp.getParameterId()==(int)product.getTypeId()){
					productListTemp.add(product);
				}
			}
			//每次迭代  赋值对应分类  商品list
			ptemp.setProductList(productListTemp);
			//删除已分过类的商品  提高性能
			productList.removeAll(productListTemp);
			
			productListTemp.clear();
			
			i++;
		}
		
		
		model.addAttribute("pList", pList);
		
		
		
		
		//首页轮播
//		List<IndexBanner> indexBannerList = this.indexBannerService.getIndexBannerListPage(indexBanner);
//		
//		model.addAttribute("indexBannerList", indexBannerList);
		
		
		//预订商品  已无用  设计已改   del
		
//		ProductCfg productCfg =new ProductCfg();
//		productCfg.setType(7);
//		List<ProductCfg> productCfgList=productCfgService.getProductCfgListByType(productCfg);
//		
//		model.addAttribute("productCfgList", productCfgList);
		
		return "/jsp/index";
	}
	
	
	
	//首页 异步接口
	
	@RequestMapping("/indexBannerAddEditIniSvc.do")
	@ResponseBody
	public IndexBanner indexBannerAddEditIniSvc(IndexBanner indexBanner,Model model,HttpServletRequest request) throws Exception{
//		indexBanner = this.indexBannerService.getIndexBannerById(indexBanner.getIndexBannerId());
		
		Subject subject = SecurityUtils.getSubject();
		//是否登陆
				if(subject.isAuthenticated()){
					
					User user = (User)subject.getPrincipal();
					
					//获取购物车内商品
					Product p =new Product();
					p.setCreateByAdm(user.getUserId());
					List<Product> productList = productService.getCartdbProductListPage(p);
					if(productList==null){
						indexBanner.setNum(0);
//						model.addAttribute("num", 0);	
					}else{
//						model.addAttribute("num", productList.size());
						indexBanner.setNum(productList.size());
					}
					
				}else{
					//cookie 获取数据
					Cookie bdhcookie= Common.getCookieByName(request, "bdhcookie");
					if(bdhcookie!=null){
					String value= URLDecoder.decode(bdhcookie.getValue());
					
					//cookie不能是空值
					if(value.length()>5){
						
					String[] bdnlist = value.split(";");
					
//					model.addAttribute("num", bdnlist.length);
					indexBanner.setNum(bdnlist.length);
					
					}else{
//						model.addAttribute("num", 0);
						indexBanner.setNum(0);
					}
					}else{
//						model.addAttribute("num", 0);
						indexBanner.setNum(0);
					}
					
				}
		
		//查询商品
		List<Product> productList= productService.getProductForIndex("");
		
		//查询商品分类  type=1
		Parameter parameter =new Parameter();
		parameter.setParameterTypeId(1);
		List<Parameter> pList= parameterService.getParameterList(parameter);
		
		//临时变量
		List<Product> productListTemp=new ArrayList<Product>();
		
		//按分类 分别拆分商品数据
		int i=0;
		for(Parameter ptemp:pList){
			//最后一个分类  没必要迭代
			if((i+1)==pList.size()){
				ptemp.setProductList(productList);
				continue;
			}
			
			for(Product product:productList){
				if((int)ptemp.getParameterId()==(int)product.getTypeId()){
					productListTemp.add(product);
				}
			}
			//每次迭代  赋值对应分类  商品list
			ptemp.setProductList(productListTemp);
			//删除已分过类的商品  提高性能
			productList.removeAll(productListTemp);
			
			productListTemp.clear();
			
			i++;
		}
		
		
//		model.addAttribute("pList", pList);
		
		indexBanner.setpList(pList);
		
		
		
		
		//首页轮播
//		List<IndexBanner> indexBannerList = this.indexBannerService.getIndexBannerListPage(indexBanner);
//		
//		model.addAttribute("indexBannerList", indexBannerList);
		
		
		//预订商品  已无用  设计已改   del
		
//		ProductCfg productCfg =new ProductCfg();
//		productCfg.setType(7);
//		List<ProductCfg> productCfgList=productCfgService.getProductCfgListByType(productCfg);
//		
//		model.addAttribute("productCfgList", productCfgList);
		
		return indexBanner;
	}
	
	/** 查看对象*/
	public String indexBannerDetail() throws Exception {
//		this.setPare_moduleid(14);	
		IndexBanner indexBanner=null;
		indexBanner = this.indexBannerService.getIndexBannerById(indexBanner.getIndexBannerId());
		indexBannerForm.setIndexBanner(indexBanner);
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		IndexBanner indexBanner=null;
		indexBanner = this.indexBannerService.getIndexBannerById(indexBanner.getIndexBannerId());
		indexBannerForm.setIndexBanner(indexBanner);
		return "detail";
	}
	/** 保存新增对象 */
	public String indexBannerAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //indexBannerForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        IndexBanner indexBanner=null;
		this.indexBannerService.saveIndexBanner(indexBanner, this.doWhat);
		indexBannerForm.setIndexBanner(indexBanner);
		return "detail";
	}
	/**删除对象*/
	public String indexBannerDelete() throws Exception {	
		IndexBanner indexBanner=null;
		this.indexBannerService.deleteIndexBanner(indexBanner.getIndexBannerId());
		return "list";
	}	

	public IndexBannerForm getModel() {
		return indexBannerForm;
	}
	
	public List<IndexBanner> getIndexBannerList() {
		return this.indexBannerList;
	}

	public void setIndexBannerList(List<IndexBanner> indexBannerList) {
		this.indexBannerList = indexBannerList;
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
