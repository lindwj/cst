package com.cst.manager.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.cst.service.model.Logistics;
import com.cst.service.model.LogisticsProduct;
import com.cst.service.model.Manager;
import com.cst.service.model.Ordergoods;
import com.cst.service.LogisticsProductService;
import com.cst.service.LogisticsService;
import com.cst.service.dao.LogisticsMapper;
import com.cst.service.dao.LogisticsProductMapper;
import com.cst.service.dao.OrdergoodsMapper;



/**
 * @author lind
 */

@Controller
@RequestMapping("/Logistics")
public class LogisticsController{
	
	// 业务逻辑对象
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private LogisticsProductService logisticsProductService;
	@Autowired
	private LogisticsMapper logisticsMapper;
	@Autowired
	private LogisticsProductMapper logisticsProductMapper;
	@Autowired
	private OrdergoodsMapper ordergoodsMapper;
	
	// 查询结果
	private List<Logistics> logisticsList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String logisticsListPage() throws Exception{
		//logisticsForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Logistics logistics=null;
		logisticsList = logisticsService.getLogisticsListPage(logistics);
		return "list";
	}
	
	/** 编辑签收信息*/
	@RequestMapping("/signDetail.do")
	@ResponseBody
	public String signDetail(Logistics logistics)throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now=new Date();
		if(logistics.getLogisticsId()!=null){
			logistics.setShopMan(mg.getManagerId());
			logistics.setShopTime(now);
			logistics.setLogisticsStatus(1);
			logisticsMapper.updateLogistics(logistics);
			Logistics l=logisticsMapper.getLogisticsById(logistics.getLogisticsId());
			return l.getOrdergoodsCode();
		}else{
			return "没有数据";
		}
	}
	
	/** 查看签收信息*/
	@RequestMapping("/getSign.do")
	@ResponseBody
	public Logistics getSign(Logistics logistics)throws Exception{
		if(logistics.getLogisticsId()!=null){
		Logistics l=logisticsMapper.getLogisticsById(logistics.getLogisticsId());
			return l;
		}else{
			return logistics;
		}
	}
	
	/** 查看签收信息*/
	@RequestMapping("/getSignDetail.do")
	public String getSignDetail(Logistics logistics,Model model)throws Exception{
		Logistics l=logisticsMapper.getLogisticsById(logistics.getLogisticsId());
		model.addAttribute("sign", l);
		return "/jsp/ordergoods/signView";
	}
	
	/** 保存物流信息*/
	@RequestMapping("/savelogistics.do")
	public String savelogistics(Logistics logistics,Model model) throws Exception{
		String code =logistics.getOrdergoodsCode();
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		Date now=new Date();
		logistics.setLogisticsStatus(0);
		logistics.setLogisticsMan(mg.getManagerId());
		logistics.setLogisticsTime(now);
		logisticsMapper.insertLogistics(logistics);
		Iterator<LogisticsProduct> it=logistics.getLogisticsProducts().iterator();
		double total = 0;
		int a=0;
		while(it.hasNext()){
			LogisticsProduct logisticsProduct=it.next();
			if(logisticsProduct.getTotalNum()==null){
				logisticsProduct.setTotalNum(0);
			}
			if(logisticsProduct.getTotalNum()+logisticsProduct.getLogisticsNum()!=logisticsProduct.getApplyNum()){
				a=-1;
			}
			if(logisticsProduct.getHidden()==0){
				a=-1;
				it.remove();
			}else if(logisticsProduct.getHidden()==1){
				logisticsProduct.setLogisticsId(logistics.getLogisticsId());
				logisticsProduct.setOrdergoodsCode(code);
				total=total+logisticsProduct.getProductPrice()*logisticsProduct.getLogisticsNum();
			}
		}
		if(a==0){
			Ordergoods ordergoods=new Ordergoods();
			ordergoods.setStatus(3);
			ordergoods.setMyStatus(8);
			ordergoods.setCode(code);
			ordergoodsMapper.setStatus(ordergoods);
		}
		Logistics l=new Logistics();
		l.setTotalPrice(total);
		l.setLogisticsId(logistics.getLogisticsId());
		logisticsMapper.updateLogistics(l);
		logisticsProductMapper.insertLogisticsProduct(logistics.getLogisticsProducts());
		model.addAttribute("code",logistics.getOrdergoodsCode());
		return "redirect:/Ordergoods/getOrderDetail.do";
	}
	/**删除物流*/
	@RequestMapping("/logisticsDel.do")
	public String logisticsDel(Logistics logistics,Model model) throws Exception {	
		logisticsService.deleteLogistics(logistics.getLogisticsId());
		logisticsProductService.deleteLogisticsIds(logistics.getLogisticsId());
		model.addAttribute("code",logistics.getOrdergoodsCode());
		return "redirect:/Ordergoods/getOrderDetail.do";
	}
	/** 编辑前初始化对象*/
	public String logisticsAddEditIni() throws Exception{
		Logistics logistics=null;
		if ("edit".equals(doWhat)) {
			logistics = this.logisticsService.getLogisticsById(logistics.getLogisticsId());
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String logisticsDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Logistics logistics=null;
		logistics = this.logisticsService.getLogisticsById(logistics.getLogisticsId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Logistics logistics=null;
		logistics = this.logisticsService.getLogisticsById(logistics.getLogisticsId());
		return "detail";
	}
	/** 保存新增对象 */
	public String logisticsAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //logisticsForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Logistics logistics=null;
		this.logisticsService.saveLogistics(logistics, this.doWhat);
		return "detail";
	}
	/**删除对象*/
	public String logisticsDelete() throws Exception {	
		Logistics logistics=null;
		this.logisticsService.deleteLogistics(logistics.getLogisticsId());
		return "list";
	}	
	public List<Logistics> getLogisticsList() {
		return this.logisticsList;
	}

	public void setLogisticsList(List<Logistics> logisticsList) {
		this.logisticsList = logisticsList;
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
