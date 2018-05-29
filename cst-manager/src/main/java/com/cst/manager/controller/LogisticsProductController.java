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

import com.cst.service.model.LogisticsProduct;
import com.cst.service.LogisticsProductService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/LogisticsProduct")
public class LogisticsProductController{
	
	// 业务逻辑对象
	@Autowired
	private LogisticsProductService logisticsProductService;
	
	// 查询结果
	private List<LogisticsProduct> logisticsProductList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String logisticsProductListPage() throws Exception{
		//logisticsProductForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		LogisticsProduct logisticsProduct=null;
		logisticsProductList = logisticsProductService.getLogisticsProductListPage(logisticsProduct);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String logisticsProductAddEditIni() throws Exception{
		LogisticsProduct logisticsProduct=null;
		if ("edit".equals(doWhat)) {
			logisticsProduct = this.logisticsProductService.getLogisticsProductById(logisticsProduct.getLogisticsProductId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String logisticsProductDetail() throws Exception {
//		this.setPare_moduleid(14);	
		LogisticsProduct logisticsProduct=null;
		logisticsProduct = this.logisticsProductService.getLogisticsProductById(logisticsProduct.getLogisticsProductId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		LogisticsProduct logisticsProduct=null;
		logisticsProduct = this.logisticsProductService.getLogisticsProductById(logisticsProduct.getLogisticsProductId());
		return "detail";
	}
	/** 保存新增对象 */
	public String logisticsProductAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //logisticsProductForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        LogisticsProduct logisticsProduct=null;
		this.logisticsProductService.saveLogisticsProduct(logisticsProduct, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String logisticsProductDelete() throws Exception {	
		LogisticsProduct logisticsProduct=null;
		this.logisticsProductService.deleteLogisticsProduct(logisticsProduct.getLogisticsProductId());
		return "list";
	}	
	public List<LogisticsProduct> getLogisticsProductList() {
		return this.logisticsProductList;
	}

	public void setLogisticsProductList(List<LogisticsProduct> logisticsProductList) {
		this.logisticsProductList = logisticsProductList;
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
