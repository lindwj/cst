package com.cst.manager.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.cst.service.model.AgentCfg;
import com.cst.service.model.AgentProductCfg;
import com.cst.service.model.Manager;
import com.cst.service.model.Parameter;
import com.cst.service.model.Product;
import com.cst.service.model.ProductAttribute;
import com.cst.service.model.ProductForm;
import com.cst.service.model.ProductPic;
import com.cst.service.model.Shop;
import com.cst.service.util.Common;
import com.cst.service.AgentCfgService;
import com.cst.service.AgentProductCfgService;
import com.cst.service.ManagerService;
import com.cst.service.ParameterService;
import com.cst.service.ProductService;
import com.cst.service.common.Page;
import com.cst.service.dao.ProductAttributeMapper;
import com.cst.service.dao.ProductPicMapper;

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
	private AgentCfgService agentCfgService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private AgentProductCfgService agentProductCfgService;

	@Autowired
	private ParameterService parameterService;
	
	
	@Autowired
	private ProductAttributeMapper productAttributeMapper;
	
	@Autowired
	private ProductPicMapper productPicMapper;

	// 查询结果
	private List<Product> productList;

	private ProductForm productForm = new ProductForm();

	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;

	@RequestMapping("/productListPageAll.do")
	public String productListPageAll(Product product, Model model) throws Exception {
		AgentCfg a=agentCfgService.getAgentCfgById(product.getManagerId());
		if(a==null){
			model.addAttribute("manager", product.getManagerId());
		}else{
			if(a.getType()==1){
				model.addAttribute("manager", product.getManagerId());
			}else{
				AgentProductCfg agentProductCfg=new AgentProductCfg();
				agentProductCfg.setAgentCfgId(a.getAgentCfgId());
				List<AgentProductCfg> agentProductCfgs=agentProductCfgService.getAgentProductCfgListPage(agentProductCfg);
				List<Product> pList=new ArrayList<>();
				for(AgentProductCfg agen:agentProductCfgs){
					pList.add(productService.getProductByUuid(agen.getProductUuid()));
				}
				model.addAttribute("manager", product.getManagerId());
				model.addAttribute("productList",pList);
			}
		}
		return "/jsp/manager/managerAgent";
	}
	
	@RequestMapping("/productListPageS.do")
	public String productListPageS(Product product, Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		product.setCreateByAdm(mg.getManagerId());
		productList = productService.getProductListPage(product);
		Parameter parameter = new Parameter();
		parameter.setParameterTypeId(1);
		List<Parameter> parameterList = parameterService.getParameterList(parameter);
		model.addAttribute("manager", product.getManagerId());
		model.addAttribute("productList", productList);
		model.addAttribute("parameterList", parameterList);
		model.addAttribute("page", product.getPage());
		model.addAttribute("errcode", product.getErrcode());
		if(product.getMenuId()!=null&&product.getMenuId()>0){
			subject.getSession().setAttribute("menuId", product.getMenuId());
		}
		return "/jsp/manager/managerCheck";
	}
	
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
		
		if(product.getMenuId()!=null&&product.getMenuId()>0){
			subject.getSession().setAttribute("menuId", product.getMenuId());
			}

		return "/jsp/product/productIndex";
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
		Product p=new Product();
		p = this.productService.getProductById(product.getProductUuid());
		p.setPage(product.getPage());
		p.setSeoName(product.getName());
		p.setSeoStatus(product.getStatus());
		p.setSeoTypeId(product.getTypeId());
		Parameter parameter = new Parameter();
		parameter.setParameterTypeId(1);
		List<Parameter> parameterList = parameterService.getParameterList(parameter);
		model.addAttribute("parameterList", parameterList);
		

		model.addAttribute("product", p);
		return "/jsp/product/productEdit";
	}
	
	
	@RequestMapping("/productAdd.do")
	public String productAdd(Product product, Model model) throws Exception {

		model.addAttribute("errcode", product.getErrcode());
		Parameter parameter = new Parameter();
		parameter.setParameterTypeId(1);
		List<Parameter> parameterList = parameterService.getParameterList(parameter);

		model.addAttribute("parameterList", parameterList);

		model.addAttribute("product", product);
		return "/jsp/product/productAdd";
	}

	/** 查看对象 */
	public String productDetail() throws Exception {
		// this.setPare_moduleid(14);
		Product product = null;
		// product = this.productService.getProductById(product.getProductId());
		productForm.setProduct(product);
		return "detail";
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
		Page page=new Page();
		page=product.getPage();
		String validate = null;
		
		List <ProductAttribute> pplistadd=new ArrayList<ProductAttribute>();
		List <ProductAttribute> pplist=new ArrayList<ProductAttribute>();
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
				model.setViewName("redirect:/product/productAdd.do");
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
			
			
			
			
			
			
			// 商品属性 add 新增  

				// 后端校验 101 排序 状态 名称 值 单位 等不能为空
				
				if(product.getProductAttributeList()==null){
					validate = "101";
				}
				
					
				for(ProductAttribute temp:product.getProductAttributeList()){

				if (temp.getName() == null || temp.getName().length() == 0) {
					validate = "101";
				}
				
				if (temp.getValue() == null || temp.getValue().length() == 0) {
					validate = "101";
				}
				
				if (temp.getUnit() == null || temp.getUnit().length() == 0) {
					validate = "101";
				}
				
				
//				if (temp.getSort() == null || temp.getSort()< 0) {
//					validate = "101";
//				}
				
				//找出 有字段为空的 行
				if ("101".equals(validate)) {
					pplist.add(temp);
				}
				
				
				}
				
				//去除空行
				if(pplist.size()>0){
					product.getProductAttributeList().removeAll(pplist);
					
				}
				
				//没有要插入的数据
				if (product.getProductAttributeList().size()==0) {
					model.addObject("errcode", "101");
					model.setViewName("redirect:/product/productAdd.do");
					return model;
				}

				// 校验结束
				
				
				
				
				
				
				
				// 橱窗图组 新增
				
				
				// add 新增 

					// 后端校验 101 排序 状态 图片 等不能为空
					
					if(product.getProductPicList()==null){
						validate = "101";
					}
					
					
					List <ProductPic> pplistpic=new ArrayList<ProductPic>();
					for(ProductPic temp:product.getProductPicList()){
						if (temp.getPicUrl() == null || temp.getPicUrl().length() == 0) {
							validate = "101";
						}
						if (temp.getSort() == null || temp.getSort()< 0) {
							validate = "101";
						}
						
						if("101".equals(validate)){
							pplistpic.add(temp);
						}
						
					}
					//去除空图片 空值 行
					product.getProductPicList().removeAll(pplistpic);
					
					if(product.getProductPicList()==null || product.getProductPicList().size()==0){
					
						model.addObject("errcode", "101");
						model.setViewName("redirect:/product/productAdd.do");
						return model;
					}

					// 校验结束


					model.setViewName("redirect:/product/productListPage.do");


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
				model.addObject("page", product.getPage());
				model.addObject("seoName", product.getSeoName());
				model.addObject("seoStatus", product.getSeoStatus());
				model.addObject("seoTypeId", product.getSeoTypeId());
				model.setViewName("redirect:/product/productAddEditIni.do");
				return model;
			}
			model.setViewName("redirect:/product/productListPage.do?&status="+product.getSeoStatus()+"&typeId="+product.getSeoTypeId()+"&page.currentPage="+page.getCurrentPage()+"&page.totalPage="+page.getTotalPage());
		}

		try {
			this.productService.saveProduct(product, product.getDowhat());
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			model.addObject("seoName", product.getSeoName());
			model.addObject("page", product.getPage());
			model.addObject("seoStatus", product.getSeoStatus());
			model.addObject("seoTypeId", product.getSeoTypeId());
			if ("edit".equals(product.getDowhat())) {
				model.addObject("productUuid", product.getProductUuid());
				model.setViewName("redirect:/product/productAddEditIni.do");
				return model;
			}

		}
		
		
		//保存商品 属性 新增   目前只有一条
		try {
			if("add".equals(product.getDowhat())){
				//赋值 uuid
				product.getProductAttributeList().get(0).setProductUuid(product.getProductUuid());
				product.getProductAttributeList().get(0).setSort(0);;
				product.getProductAttributeList().get(0).setStatus(1);
				
				productAttributeMapper.insertProductAttribute(product.getProductAttributeList());
			}
			
		} catch (DuplicateKeyException be) {

		}
		
		
		//保存 商品 橱窗图组 新增
		try {
			if("add".equals(product.getDowhat())){
				
			for(ProductPic pp:product.getProductPicList()){
				pp.setProductUuid(product.getProductUuid());
				pp.setStatus(1);
			}
				
			productPicMapper.insertProductPic(product.getProductPicList());
			}
			
		} catch (DuplicateKeyException be) {
			
		}
		
		return model;
	}
	/** 保存新增草稿对象 */
	@RequestMapping("/saveProductTxt.do")
	public ModelAndView productAddEditTxt(Product product, ModelAndView model) throws Exception {
		// this.setPare_moduleid(14);
		// productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		String validate = null;
		
		List <ProductAttribute> pplistadd=new ArrayList<ProductAttribute>();
		List <ProductAttribute> pplist=new ArrayList<ProductAttribute>();
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
				model.setViewName("redirect:/product/productAdd.do");
				return model;
			}
			
			// 校验结束
			
			// 1 表示 上架 ，这里直接赋予 上架状态
			product.setStatus(0);
			
			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();
			
			product.setCreateByAdm(mg.getManagerId());
			
			// 生成uuid
			product.setProductUuid(Common.getUUID());
			
			
			
			
			
			
			// 商品属性 add 新增  
			
			// 后端校验 101 排序 状态 名称 值 单位 等不能为空
			
			if(product.getProductAttributeList()==null){
				validate = "101";
			}
			
			
			for(ProductAttribute temp:product.getProductAttributeList()){
				
				if (temp.getName() == null || temp.getName().length() == 0) {
					validate = "101";
				}
				
				if (temp.getValue() == null || temp.getValue().length() == 0) {
					validate = "101";
				}
				
				if (temp.getUnit() == null || temp.getUnit().length() == 0) {
					validate = "101";
				}
				
				
//				if (temp.getSort() == null || temp.getSort()< 0) {
//					validate = "101";
//				}
				
				//找出 有字段为空的 行
				if ("101".equals(validate)) {
					pplist.add(temp);
				}
				
				
			}
			
			//去除空行
			if(pplist.size()>0){
				product.getProductAttributeList().removeAll(pplist);
				
			}
			
			//没有要插入的数据
			if (product.getProductAttributeList().size()==0) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/product/productAdd.do");
				return model;
			}
			
			// 校验结束
			
			
			
			
			
			
			
			// 橱窗图组 新增
			
			
			// add 新增 
			
			// 后端校验 101 排序 状态 图片 等不能为空
			
			if(product.getProductPicList()==null){
				validate = "101";
			}
			
			
			List <ProductPic> pplistpic=new ArrayList<ProductPic>();
			for(ProductPic temp:product.getProductPicList()){
				if (temp.getPicUrl() == null || temp.getPicUrl().length() == 0) {
					validate = "101";
				}
				if (temp.getSort() == null || temp.getSort()< 0) {
					validate = "101";
				}
				
				if("101".equals(validate)){
					pplistpic.add(temp);
				}
				
			}
			//去除空图片 空值 行
			product.getProductPicList().removeAll(pplistpic);
			
			if(product.getProductPicList()==null || product.getProductPicList().size()==0){
				
				model.addObject("errcode", "101");
				model.setViewName("redirect:/product/productAdd.do");
				return model;
			}
			
			// 校验结束
			
			
			
			
			
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
		
		
		//保存商品 属性 新增   目前只有一条
		try {
			if("add".equals(product.getDowhat())){
				//赋值 uuid
				product.getProductAttributeList().get(0).setProductUuid(product.getProductUuid());
				product.getProductAttributeList().get(0).setSort(0);;
				product.getProductAttributeList().get(0).setStatus(0);
				
				productAttributeMapper.insertProductAttribute(product.getProductAttributeList());
			}
			
		} catch (DuplicateKeyException be) {
			
		}
		
		
		//保存 商品 橱窗图组 新增
		try {
			if("add".equals(product.getDowhat())){
				
				for(ProductPic pp:product.getProductPicList()){
					pp.setProductUuid(product.getProductUuid());
					pp.setStatus(0);
				}
				
				productPicMapper.insertProductPic(product.getProductPicList());
			}
			
		} catch (DuplicateKeyException be) {
			
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
