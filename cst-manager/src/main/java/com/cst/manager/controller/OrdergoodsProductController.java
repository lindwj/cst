package com.cst.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cst.service.model.OrdergoodsProduct;
import com.cst.service.OrdergoodsProductService;
import com.cst.service.dao.OrdergoodsProductMapper;



/**
 * @author lind
 */

@Controller
@RequestMapping("/OrdergoodsProduct")
public class OrdergoodsProductController{
	
	// 业务逻辑对象
	@Autowired
	private OrdergoodsProductService ordergoodsProductService;
	@Autowired
	private OrdergoodsProductMapper ordergoodsProductMapper;
	
	// 查询结果
	private List<OrdergoodsProduct> ordergoodsProductList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String ordergoodsProductListPage() throws Exception{
		//ordergoodsProductForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		OrdergoodsProduct ordergoodsProduct=null;
		ordergoodsProductList = ordergoodsProductService.getOrdergoodsProductListPage(ordergoodsProduct);
		return "list";
	}
	
	/** 查询商品list */
	@RequestMapping("/orderproductlist.do")
	@ResponseBody
	public List<OrdergoodsProduct> orderProduct(OrdergoodsProduct ordergoodsProduct,Model model) throws Exception{
		List<OrdergoodsProduct> ordergoodsProductList = ordergoodsProductService.getOrderProduct(ordergoodsProduct);
		return ordergoodsProductList;
	}
	
	/** 修改商品数量 */
	@RequestMapping("/updatePNum.do")
	@ResponseBody
	public int updatePNum(OrdergoodsProduct ordergoodsProduct) throws Exception{
		List<OrdergoodsProduct> ordergoodsProductList=ordergoodsProduct.getOrdergoodsProducts();
		if(ordergoodsProductList!=null){
			for(OrdergoodsProduct ordergoodsProducts:ordergoodsProductList){
				ordergoodsProductMapper.updateOrdergoodsProduct(ordergoodsProducts);
			}
			return 0;
		}else{
			return 1;
		}
	}
	
	/** 编辑前初始化对象*/
	public String ordergoodsProductAddEditIni() throws Exception{
		OrdergoodsProduct ordergoodsProduct=null;
		if ("edit".equals(doWhat)) {
			ordergoodsProduct = this.ordergoodsProductService.getOrdergoodsProductById(ordergoodsProduct.getOrdergoodsProductId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String ordergoodsProductDetail() throws Exception {
//		this.setPare_moduleid(14);	
		OrdergoodsProduct ordergoodsProduct=null;
		ordergoodsProduct = this.ordergoodsProductService.getOrdergoodsProductById(ordergoodsProduct.getOrdergoodsProductId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		OrdergoodsProduct ordergoodsProduct=null;
		ordergoodsProduct = this.ordergoodsProductService.getOrdergoodsProductById(ordergoodsProduct.getOrdergoodsProductId());
		return "detail";
	}
	/** 保存新增对象 */
	public String ordergoodsProductAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //ordergoodsProductForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        OrdergoodsProduct ordergoodsProduct=null;
		this.ordergoodsProductService.saveOrdergoodsProduct(ordergoodsProduct, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String ordergoodsProductDelete() throws Exception {	
		OrdergoodsProduct ordergoodsProduct=null;
		this.ordergoodsProductService.deleteOrdergoodsProduct(ordergoodsProduct.getOrdergoodsProductId());
		return "list";
	}	
	public List<OrdergoodsProduct> getOrdergoodsProductList() {
		return this.ordergoodsProductList;
	}

	public void setOrdergoodsProductList(List<OrdergoodsProduct> ordergoodsProductList) {
		this.ordergoodsProductList = ordergoodsProductList;
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
