package com.cst.web.controller;

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

import com.cst.service.model.OrdersDetail;
import com.cst.service.OrdersDetailService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/OrdersDetail")
public class OrdersDetailController{
	
	// 业务逻辑对象
	@Autowired
	private OrdersDetailService ordersDetailService;
	
	// 查询结果
	private List<OrdersDetail> ordersDetailList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String ordersDetailListPage() throws Exception{
		//ordersDetailForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		OrdersDetail ordersDetail=null;
		ordersDetailList = ordersDetailService.getOrdersDetailListPage(ordersDetail);
		return "list";
	}
	
	/** 编辑前初始化对象*/
	public String ordersDetailAddEditIni() throws Exception{
		OrdersDetail ordersDetail=null;
		if ("edit".equals(doWhat)) {
//			ordersDetail = this.ordersDetailService.getOrdersDetailById(ordersDetail.getOrdersDetailId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String ordersDetailDetail() throws Exception {
//		this.setPare_moduleid(14);	
		OrdersDetail ordersDetail=null;
//		ordersDetail = this.ordersDetailService.getOrdersDetailById(ordersDetail.getOrdersDetailId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		OrdersDetail ordersDetail=null;
//		ordersDetail = this.ordersDetailService.getOrdersDetailById(ordersDetail.getOrdersDetailId());
		return "detail";
	}
	/** 保存新增对象 */
	public String ordersDetailAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //ordersDetailForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        OrdersDetail ordersDetail=null;
		this.ordersDetailService.saveOrdersDetail(ordersDetail, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String ordersDetailDelete() throws Exception {	
		OrdersDetail ordersDetail=null;
		this.ordersDetailService.deleteOrdersDetail(ordersDetail.getOrdersDetailId());
		return "list";
	}	
	public List<OrdersDetail> getOrdersDetailList() {
		return this.ordersDetailList;
	}

	public void setOrdersDetailList(List<OrdersDetail> ordersDetailList) {
		this.ordersDetailList = ordersDetailList;
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
