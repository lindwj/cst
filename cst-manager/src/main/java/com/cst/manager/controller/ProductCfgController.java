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
import com.cst.service.model.Parameter;
import com.cst.service.model.Product;
import com.cst.service.model.ProductCfg;
import com.cst.service.util.Common;
import com.cst.service.ParameterService;
import com.cst.service.ProductCfgService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/productCfg")
public class ProductCfgController{
	
	// 业务逻辑对象
	@Autowired
	private ProductCfgService productCfgService;
	
	@Autowired
	private ParameterService parameterService;
	
	// 查询结果
	private List<ProductCfg> productCfgList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/productCfgListPage.do")
	public String productCfgListPage(ProductCfg productCfg, Model model) throws Exception{
		//productCfgForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		productCfg.setCreateByAdm(mg.getManagerId());

		productCfgList = productCfgService.getProductCfgListPage(productCfg);

		Parameter parameter = new Parameter();
		parameter.setParameterTypeId(2);
		List<Parameter> parameterList = parameterService.getParameterList(parameter);

		model.addAttribute("productCfgList", productCfgList);
		model.addAttribute("parameterList", parameterList);
		model.addAttribute("page", productCfg.getPage());
		model.addAttribute("errcode", productCfg.getErrcode());
		
		if(productCfg.getMenuId()!=null&&productCfg.getMenuId()>0){
			subject.getSession().setAttribute("menuId", productCfg.getMenuId());
			}

		return "/jsp/productCfg/productCfgIndex";
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/productCfgAddEditIni.do")
	public String productCfgAddEditIni(ProductCfg productCfg, Model model) throws Exception{
		
		model.addAttribute("errcode", productCfg.getErrcode());
		productCfg = this.productCfgService.getProductCfgById(productCfg.getProductCfgId());
		
				
		model.addAttribute("productCfg", productCfg);
		return "/jsp/productCfg/productCfgEdit";
	}
	
	@RequestMapping("/productCfgAdd.do")
	public String productCfgAdd(ProductCfg productCfg, Model model) throws Exception{
		
		Parameter parameter = new Parameter();
		parameter.setParameterTypeId(2);
		List<Parameter> parameterList = parameterService.getParameterList(parameter);

		model.addAttribute("parameterList", parameterList);
		model.addAttribute("errcode", productCfg.getErrcode());
		return "/jsp/productCfg/productCfgAdd";
	}
	
	/** 查看对象*/
	public String productCfgDetail() throws Exception {
//		this.setPare_moduleid(14);	
		ProductCfg productCfg=null;
		productCfg = this.productCfgService.getProductCfgById(productCfg.getProductCfgId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		ProductCfg productCfg=null;
		productCfg = this.productCfgService.getProductCfgById(productCfg.getProductCfgId());
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/saveProductCfg.do")
	public ModelAndView productCfgAddEdit(ProductCfg productCfg, ModelAndView model) throws Exception {		
		//this.setPare_moduleid(14);
        //productCfgForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		String validate = null;
		// add 新增 edit 修改
		if ("add".equals(productCfg.getDowhat())) {

			// 后端校验 101 名称 编号 价格 原价 类型 描述 图片 等不能为空

			if (productCfg.getProductUuid() == null || productCfg.getProductUuid().length() == 0) {
				validate = "101";
			}
			if (productCfg.getSort() == null ||productCfg.getSort() <= 0) {
				validate = "101";
			}
			if (productCfg.getType() == null || productCfg.getType() <= 0) {
				validate = "101";
			}

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/productCfg/productCfgAdd.do");
				return model;
			}

			// 校验结束

			// 1 表示 上架 ，这里直接赋予 上架状态
			productCfg.setState(1);

			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			productCfg.setCreateByAdm(mg.getManagerId());


		}else if ("edit".equals(productCfg.getDowhat())) {

			// 后端校验 101 名称 编号 价格 原价 类型 描述 图片 等不能为空

			if (productCfg.getSort() == null ||productCfg.getSort() <= 0) {
				validate = "101";
			}

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.addObject("productCfgId", productCfg.getProductCfgId());
				model.setViewName("redirect:/productCfg/productCfgAddEditIni.do");
				return model;
			}

			// 校验结束



		}

		try {
			
			this.productCfgService.saveProductCfg(productCfg, productCfg.getDowhat());
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			model.setViewName("redirect:/productCfg/productCfgAdd.do");
			return model;

		}

		model.setViewName("redirect:/productCfg/productCfgListPage.do");
		return model;
	}
	/**删除对象*/
	@RequestMapping("/delProductCfg.do")
	public String productCfgDelete(ProductCfg productCfg) throws Exception {	
		this.productCfgService.deleteProductCfg(productCfg.getProductCfgId());
		return "redirect:/productCfg/productCfgListPage.do";
	}	
	public List<ProductCfg> getProductCfgList() {
		return this.productCfgList;
	}

	public void setProductCfgList(List<ProductCfg> productCfgList) {
		this.productCfgList = productCfgList;
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
