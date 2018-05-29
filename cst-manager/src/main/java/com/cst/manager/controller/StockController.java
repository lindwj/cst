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
import com.cst.service.model.Orders;
import com.cst.service.model.Stock;
import com.cst.service.model.StockForm;
import com.cst.service.util.Common;
import com.cst.service.StockService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/stock")
public class StockController{
	
	// 业务逻辑对象
	@Autowired
	private StockService stockService;
	
	// 查询结果
	private List<Stock> stockList;
	
	private StockForm stockForm=new StockForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	public String stockListPage() throws Exception{
		//stockForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Stock stock=null;
		stockList = stockService.getStockListPage(stock);
		stockForm.setStock(stock);
		request.setAttribute("page", stockForm.getPage());
		return "list";
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/stockAddEditIni.do")
	public String stockAddEditIni(Stock stock,Model model) throws Exception{
		
		model.addAttribute("errcode", stock.getErrcode());
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		stock.setShopId(Integer.valueOf(mg.getShopIdstr()));
		stock.setUpdateByAdm(mg.getManagerId());
		
		List<Stock> stockList = this.stockService.getStockListPage(stock);
		stock.setStockList(stockList);
		
		model.addAttribute("stock", stock);
		
		model.addAttribute("page", stock.getPage());
		return "/jsp/stock/stockEdit";
	}
	

	
	@RequestMapping("/stockAddEditIniSvc.do")
	@ResponseBody
	public Stock stockAddEditIniSvc(Stock stock) throws Exception{
		
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		stock.setShopId(Integer.valueOf(mg.getShopIdstr()));
		stock.setUpdateByAdm(mg.getManagerId());
		
		List<Stock> stockList = this.stockService.getStockListPage(stock);
		stock.setStockList(stockList);
		stock.setPage(stock.getPage());
		return stock;
	}
	
	
	
	@RequestMapping("/stockEditDD.do")
	@ResponseBody
	public Stock stockEditDD(Stock stock) throws Exception{
		
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();
		stock.setShopId(mg.getManagerId());
		stock.setUpdateByAdm(mg.getManagerId());
		
		List<Stock> stockList = this.stockService.getStockListPageDD(stock);
		stock.setStockList(stockList);
		stock.setPage(stock.getPage());
		return stock;
	}
	
	
	/** 查看对象*/
	public String stockDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Stock stock=null;
//		stock = this.stockService.getStockById(stock.getStockId());
		stockForm.setStock(stock);
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Stock stock=null;
//		stock = this.stockService.getStockById(stock.getStockId());
		stockForm.setStock(stock);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/stockAddEdit.do")
	public ModelAndView stockAddEdit(Stock stock, ModelAndView model) throws Exception {		
		//this.setPare_moduleid(14);
        //stockForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		String validate = null;
		
		if(stock.getStockList()==null || stock.getStockList().size()==0){
			validate = "101";
		}
		
		List<Stock> stocknull =new ArrayList<Stock>();
		List<Stock> stockadd =new ArrayList<Stock>();
		List<Stock> stockedit =new ArrayList<Stock>();
		
		for(Stock stocktemp:stock.getStockList()){
			//筛选出空行
			if((stocktemp.getShopCapacity()==null|| stocktemp.getShopCapacity()==0)
					&&(stocktemp.getShowCapacity()==null|| stocktemp.getShowCapacity()==0)){
				stocknull.add(stocktemp);
				continue;
			}
			//筛选新增行
			if(stocktemp.getStockId()==null||stocktemp.getStockId()<=0){
				stockadd.add(stocktemp);
			}else{
				//筛选修改行
				stockedit.add(stocktemp);
			}
			
			
		}
		
		//提交的数据全部为空
		if(stock.getStockList().size()==stocknull.size()){
			validate = "101";
		}
		
		
			
			
		if ("101".equals(validate)) {
			model.addObject("errcode", "101");
			model.setViewName("redirect:/stock/stockAddEditIni.do");
			return model;
		}

			

		try {
			
			if(stockadd.size()>0){
				stock.setStockList(stockadd);
				this.stockService.saveStock(stock, "add");
			}
			
			if(stockedit.size()>0){
				stock.setStockList(stockedit);
				this.stockService.saveStock(stock, "edit");
			}
			
			
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			model.setViewName("redirect:/stock/stockAddEditIni.do");
			return model;

		}
		
		

		model.setViewName("redirect:/stock/stockAddEditIni.do");
		return model;
	}
	
	
	
	@RequestMapping("/stockAddEditSvc.do")
	@ResponseBody
	public int stockAddEditSvc(Stock stock) throws Exception {		
		//this.setPare_moduleid(14);
        //stockForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		if(stock.getStockList()==null || stock.getStockList().size()==0){
			return 301;
		}
		
		List<Stock> stocknull =new ArrayList<Stock>();
		List<Stock> stockadd =new ArrayList<Stock>();
		List<Stock> stockedit =new ArrayList<Stock>();
		
		for(Stock stocktemp:stock.getStockList()){
			//筛选出空行
			if((stocktemp.getShopCapacity()==null|| stocktemp.getShopCapacity()==0)
					&&(stocktemp.getShowCapacity()==null|| stocktemp.getShowCapacity()==0)){
				stocknull.add(stocktemp);
				continue;
			}
			//筛选新增行
			if(stocktemp.getStockId()==null||stocktemp.getStockId()<=0){
				stockadd.add(stocktemp);
			}else{
				//筛选修改行
				stockedit.add(stocktemp);
			}
			
			
		}
		
		//提交的数据全部为空
		if(stock.getStockList().size()==stocknull.size()){
			return 301;
		}
		
		
			
			

		try {
			
			if(stockadd.size()>0){
				stock.setStockList(stockadd);
				this.stockService.saveStock(stock, "add");
			}
			
			if(stockedit.size()>0){
				stock.setStockList(stockedit);
				this.stockService.saveStock(stock, "edit");
			}
			
			
		} catch (DuplicateKeyException be) {
			// 重复值
			return 302;

		}
		
		

		return 300;
	}
	
	
	
	
	/**删除对象*/
	public String stockDelete() throws Exception {	
		Stock stock=null;
		this.stockService.deleteStock(stock.getStockId());
		return "list";
	}	

	public StockForm getModel() {
		return stockForm;
	}
	
	public List<Stock> getStockList() {
		return this.stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
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
