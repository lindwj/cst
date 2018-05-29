package com.cst.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.cst.service.model.ProductAttribute;
import com.cst.service.model.ProductAttributeForm;
import com.cst.service.model.ProductPic;
import com.cst.service.ProductAttributeService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/productAttribute")
public class ProductAttributeController{
	
	// 业务逻辑对象
	@Autowired
	private ProductAttributeService productAttributeService;
	
	// 查询结果
	private List<ProductAttribute> productAttributeList;
	
	private ProductAttributeForm productAttributeForm=new ProductAttributeForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String productAttributeListPage() throws Exception{
		//productAttributeForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		ProductAttribute productAttribute=null;
		productAttributeList = productAttributeService.getProductAttributeListPage(productAttribute);
		productAttributeForm.setProductAttribute(productAttribute);
		request.setAttribute("page", productAttributeForm.getPage());
		return "list";
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/productAttributeAddEditIni.do")
	public String productAttributeAddEditIni(ProductAttribute productAttribute, Model model) throws Exception{
		model.addAttribute("errcode", productAttribute.getErrcode());
		
		List<ProductAttribute> productAttributeList = this.productAttributeService.getProductAttributeById(productAttribute.getProductUuid());
		productAttribute.setProductAttributeList(productAttributeList);
		
		if(productAttributeList==null || productAttributeList.size()==0){
			productAttribute.setDowhat("add");
		}else{
			productAttribute.setDowhat("edit");
		}
		
		model.addAttribute("productAttribute", productAttribute);
		return "/jsp/productAtr/productAtrEdit";
	}
	
	/** 查看对象*/
	public String productAttributeDetail() throws Exception {
//		this.setPare_moduleid(14);	
		ProductAttribute productAttribute=null;
//		productAttribute = this.productAttributeService.getProductAttributeById(productAttribute.getProductAttributeId());
		productAttributeForm.setProductAttribute(productAttribute);
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		ProductAttribute productAttribute=null;
//		productAttribute = this.productAttributeService.getProductAttributeById(productAttribute.getProductAttributeId());
		productAttributeForm.setProductAttribute(productAttribute);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/saveProductAtr.do")
	public ModelAndView productAttributeAddEdit(ProductAttribute productAttribute, ModelAndView model) throws Exception {		
		String validate = null;
		List <ProductAttribute> pplistedit=new ArrayList<ProductAttribute>();
		List <ProductAttribute> pplistadd=new ArrayList<ProductAttribute>();
		List <ProductAttribute> pplist=new ArrayList<ProductAttribute>();
		// add 新增 edit 修改
		if ("add".equals(productAttribute.getDowhat())) {

			// 后端校验 101 排序 状态 名称 值 单位 等不能为空
			
			if(productAttribute.getProductAttributeList()==null){
				validate = "101";
			}
			
			
			
			
				
			for(ProductAttribute temp:productAttribute.getProductAttributeList()){

			if (temp.getName() == null || temp.getName().length() == 0) {
				validate = "101";
			}
			
			if (temp.getValue() == null || temp.getValue().length() == 0) {
				validate = "101";
			}
			
			if (temp.getUnit() == null || temp.getUnit().length() == 0) {
				validate = "101";
			}
			
			
			if (temp.getSort() == null || temp.getSort()< 0) {
				validate = "101";
			}
			
			//找出 有字段为空的 行
			if ("101".equals(validate)) {
				pplist.add(temp);
			}
			
			
			}
			
			//去除空行
			if(pplist.size()>0){
				productAttribute.getProductAttributeList().removeAll(pplist);
				
			}
			
			//没有要插入的数据
			if (productAttribute.getProductAttributeList().size()==0) {
				model.addObject("errcode", "101");
				model.addObject("productUuid", productAttribute.getProductUuid());
				model.setViewName("redirect:/productAttribute/productAttributeAddEditIni.do");
				return model;
			}

			// 校验结束


		} else if ("edit".equals(productAttribute.getDowhat())) {
			if(productAttribute.getProductAttributeList()==null){
				validate = "101";
			}
			
				
			for(ProductAttribute temp:productAttribute.getProductAttributeList()){

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
					continue;
				}
				
				//筛选出有getproductAttributeId 值，说明是需要修改的
				if (temp.getProductAttributeId() != null && temp.getProductAttributeId()> 0) {
					pplistedit.add(temp);
				}
				
				
				//筛选出没有getproductAttributeId 值，说明是需要新增的
				if ((temp.getProductAttributeId() == null || temp.getProductAttributeId()<= 0)) {
					pplistadd.add(temp);
				}
			
			}
			
			

			if ((pplistadd.size()+pplistedit.size())<=0) {
				model.addObject("errcode", "101");
				model.addObject("productUuid", productAttribute.getProductUuid());
				model.setViewName("redirect:/productAttribute/productAttributeAddEditIni.do");
				return model;
			}
		}

		try {
			if("add".equals(productAttribute.getDowhat())){
			this.productAttributeService.saveProductAttribute(productAttribute, productAttribute.getDowhat());
			}else{
				//插入 修改时 ，新增的内容
				if(pplistadd.size()>0){
					productAttribute.setProductAttributeList(pplistadd);
					this.productAttributeService.saveProductAttribute(productAttribute, "add");
				}
				
				//修改
				if(pplistedit.size()>0){
					productAttribute.setProductAttributeList(pplistedit);
					this.productAttributeService.saveProductAttribute(productAttribute,productAttribute.getDowhat());
				}
			}
			
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");

			if ("edit".equals(productAttribute.getDowhat())) {
				model.addObject("productUuid", productAttribute.getProductUuid());
				model.setViewName("redirect:/productAttribute/productAttributeAddEditIni.do");
				return model;
			}

		}

//		model.addObject("productUuid", productAttribute.getProductUuid());
//		model.setViewName("redirect:/productAttribute/productAttributeAddEditIni.do");
		
		model.setViewName("redirect:/product/productListPage.do");
		return model;
	}
	/**删除对象*/
	@RequestMapping("/delProductAtr.do")
	public ModelAndView productAttributeDelete(ProductAttribute productAttribute, ModelAndView model) throws Exception {	
		this.productAttributeService.deleteProductAttribute(productAttribute.getProductAttributeId());
		model.addObject("productUuid", productAttribute.getProductUuid());
		model.setViewName("redirect:/productAttribute/productAttributeAddEditIni.do");
		return model;
	}	

	public ProductAttributeForm getModel() {
		return productAttributeForm;
	}
	
	public List<ProductAttribute> getProductAttributeList() {
		return this.productAttributeList;
	}

	public void setProductAttributeList(List<ProductAttribute> productAttributeList) {
		this.productAttributeList = productAttributeList;
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
