package com.cst.manager.controller;

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

import com.cst.service.model.Manager;
import com.cst.service.model.Parameter;
import com.cst.service.model.Product;
import com.cst.service.model.ProductPic;
import com.cst.service.model.ProductPicForm;
import com.cst.service.util.Common;
import com.cst.service.ProductPicService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/productPic")
public class ProductPicController{
	
	// 业务逻辑对象
	@Autowired
	private ProductPicService productPicService;
	
	// 查询结果
	private List<ProductPic> productPicList;
	
	private ProductPicForm productPicForm=new ProductPicForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String productPicListPage() throws Exception{
		//productPicForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		ProductPic productPic=null;
		productPicList = productPicService.getProductPicListPage(productPic);
		productPicForm.setProductPic(productPic);
		request.setAttribute("page", productPicForm.getPage());
		return "list";
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/productPicAddEditIni.do")
	public String productPicAddEditIni(ProductPic productPic, Model model) throws Exception{
		model.addAttribute("errcode", productPic.getErrcode());
		
		List<ProductPic> productPicList = this.productPicService.getProductPicById(productPic.getProductUuid());
		productPic.setProductPicList(productPicList);
		
		if(productPicList==null || productPicList.size()==0){
			productPic.setDowhat("add");
		}else{
			productPic.setDowhat("edit");
		}
		
		model.addAttribute("productPic", productPic);
		return "/jsp/productPic/productPicEdit";
	}
	
	/** 查看对象*/
	public String productPicDetail() throws Exception {
//		this.setPare_moduleid(14);	
		ProductPic productPic=null;
//		productPic = this.productPicService.getProductPicById(productPic.getProductPicId());
		productPicForm.setProductPic(productPic);
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		ProductPic productPic=null;
//		productPic = this.productPicService.getProductPicById(productPic.getProductPicId());
		productPicForm.setProductPic(productPic);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/saveProductPic.do")
	public ModelAndView productPicAddEdit(ProductPic productPic, ModelAndView model) throws Exception {		
		//this.setPare_moduleid(14);
        //productPicForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		
		String validate = null;
		List <ProductPic> pplistedit=new ArrayList<ProductPic>();
		List <ProductPic> pplistadd=new ArrayList<ProductPic>();
		// add 新增 edit 修改
		if ("add".equals(productPic.getDowhat())) {

			// 后端校验 101 排序 状态 图片 等不能为空
			
			if(productPic.getProductPicList()==null){
				validate = "101";
			}
			
			
			List <ProductPic> pplist=new ArrayList<ProductPic>();
			for(ProductPic temp:productPic.getProductPicList()){
				if (temp.getPicUrl() == null || temp.getPicUrl().length() == 0) {
					validate = "101";
				}
				if (temp.getSort() == null || temp.getSort()< 0) {
					validate = "101";
				}
				
				if("101".equals(validate)){
				pplist.add(temp);
				}
				
			}
			//去除空图片 空值 行
			productPic.getProductPicList().removeAll(pplist);
			
			if(productPic.getProductPicList()==null || productPic.getProductPicList().size()==0){
			
				model.addObject("errcode", "101");
				model.addObject("productUuid", productPic.getProductUuid());
				model.setViewName("redirect:/productPic/productPicAddEditIni.do");
				return model;
			}

			// 校验结束


		} else if ("edit".equals(productPic.getDowhat())) {
			if(productPic.getProductPicList()==null){
				validate = "101";
			}
			
			
			
			for(ProductPic temp:productPic.getProductPicList()){
				
				if (temp.getSort() == null || temp.getSort()< 0) {
					validate = "101";
					continue;
				}
				
				//筛选出有getProductPicId 值，说明是需要修改的
				if (temp.getProductPicId() != null && temp.getProductPicId()> 0) {
					pplistedit.add(temp);
				}
				
				
				//筛选出没有getProductPicId 值，图片有值得，说明是需要新增的
				if ((temp.getProductPicId() == null || temp.getProductPicId()<= 0) && (temp.getPicUrl() != null && temp.getPicUrl().length()> 0)) {
					pplistadd.add(temp);
				}
				
				
			}
			

			if ((pplistadd.size()+pplistedit.size())==0) {
				model.addObject("errcode", "101");
				model.addObject("productUuid", productPic.getProductUuid());
				model.setViewName("redirect:/productPic/productPicAddEditIni.do");
				return model;
			}
		}

		try {
			if("add".equals(productPic.getDowhat())){
			this.productPicService.saveProductPic(productPic, productPic.getDowhat());
			}else{
				//插入 修改时 ，新增的内容
				if(pplistadd.size()>0){
					productPic.setProductPicList(pplistadd);
					this.productPicService.saveProductPic(productPic, "add");
				}
				
				//修改
				if(pplistedit.size()>0){
					productPic.setProductPicList(pplistedit);
					this.productPicService.saveProductPic(productPic,productPic.getDowhat());
				}
			}
			
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");

			if ("edit".equals(productPic.getDowhat())) {
				model.addObject("productUuid", productPic.getProductUuid());
				model.setViewName("redirect:/productPic/productPicAddEditIni.do");
				return model;
			}

		}

//		model.addObject("productUuid", productPic.getProductUuid());
//		model.setViewName("redirect:/productPic/productPicAddEditIni.do");
		
		model.setViewName("redirect:/product/productListPage.do");
		return model;
	}
	/**删除对象*/
	@RequestMapping("/delProductPic.do")
	public ModelAndView productPicDelete(ProductPic productPic, ModelAndView model) throws Exception {	
		
		this.productPicService.deleteProductPic(productPic.getProductPicId());
		
		model.addObject("productUuid", productPic.getProductUuid());
		model.setViewName("redirect:/productPic/productPicAddEditIni.do");
		return model;
	}	

	public ProductPicForm getModel() {
		return productPicForm;
	}
	
	public List<ProductPic> getProductPicList() {
		return this.productPicList;
	}

	public void setProductPicList(List<ProductPic> productPicList) {
		this.productPicList = productPicList;
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
