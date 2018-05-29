package com.cst.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.cst.service.*;
import com.cst.service.dao.*;
import com.cst.service.model.*;
import com.cst.service.util.Common;

/**
 * @author lind
 *
 */

@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private ManagerService managerService;
	
	//支付完成后  核销库存 发送短信通知
	@Override
	public void payOver(Orders orders){
		//核销库存

		orders = this.getOrdersByCode(orders);

		Stock stock = null;
		StringBuffer prd = new StringBuffer("");
		// 合并支付
		if ("0".equals(orders.getProductUuid())) {
			List<Stock> stkList=new ArrayList<Stock>();
			
			//库存
			for (OrdersDetail od : orders.getOrdersDetailList()) {
						
						// 生成 批量 修改 售卖数量 list
						stock=new Stock();
						stock.setSellCapacity(od.getCapacity());
						stock.setProductUuid(od.getProductUuid());
						stock.setShopId(orders.getShopId());
						stkList.add(stock);
						
						prd.append(od.getProductName()).append(" * ").append(od.getCapacity()).append(",");
			}

			//批量修改库存
			stockService.updateSellAll(stkList);
			

		} else {
			
			prd.append(orders.getSubject()).append(" * ").append(orders.getCapacity()).append(",");
			
			
			stock = new Stock();
			stock.setProductUuid(orders.getProductUuid());
			stock.setShopId(orders.getShopId());
			stock = stockService.getStockByPrd(stock);
			// 库存
			if (stock != null) {

				// 回退售卖数量
				stock.setSellCapacity(orders.getCapacity());
				stockService.updateSell(stock);
			}
		}

	
		//发送短信通知
		
		StringBuffer content = new StringBuffer("已付款:");
		content.append(prd.toString());
		content.append(orders.getReceiveName()).append(",");
		content.append(orders.getReceiveMobile()).append(",");
		content.append(orders.getReceiveAddress());
		
		
		List<Manager> mngs = managerService.getManagerForMess(orders.getShopId());
		
		if(mngs!=null&&mngs.size()>0){
			
			for(Manager mng:mngs){
				
				if(mng.getMobile()!=null&&mng.getMobile().length()>5){
					try {
						Common.sendMess(mng.getMobile(),content.toString());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			
			
			
		}
		
	}
	
	@Override
	public List<Orders> getOrdersListPage(Orders orders){
		return ordersMapper.getOrdersListPage(orders);
	}
	@Override
	public Orders getOrdersByCode(Orders orders){
		return ordersMapper.getOrdersByCode(orders);
	}
	
	@Override
	public void updateOrdersForwx(Orders orders){
		 ordersMapper.updateOrdersForwx(orders);
	}
	
	@Override
	public List<Orders> getOrdersForAdmListPage(Orders orders){
		return ordersMapper.getOrdersForAdmListPage(orders);
	}
	
	@Override
	public List<Orders> getOrdersForBdhListPage(Orders orders){
		return ordersMapper.getOrdersForBdhListPage(orders);
	}
	
	@Override
	public void setStatus(Orders orders){
		 ordersMapper.setStatus(orders);
	}
	
	@Override
	public Orders getOrdersById(String code){
		return ordersMapper.getOrdersById(code);
	}

	@Override
	public int saveOrders(Orders orders,String doWhat){
		if("add".equals(doWhat)){
			return ordersMapper.insertOrders(orders);
		}else if("edit".equals(doWhat)){
			ordersMapper.updateOrders(orders);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteOrders(String code){
		ordersMapper.deleteOrders(code);
	}
	
	@Override
	public List<Orders> getOrdersListPageWxOpen(Orders orders) {
		return ordersMapper.getOrdersListPageWxOpen(orders);
	}

	@Override
	public Orders getPrice(Orders orders) {
		return ordersMapper.getPrice(orders);
	}

	@Override
	public Orders getPrices(Orders orders) {
		return ordersMapper.getPrices(orders);
	}

	@Override
	public Orders getPriceDay(Orders orders) {
		return ordersMapper.getPriceDay(orders);
	}

	@Override
	public Orders getPriceDays(Orders orders) {
		return ordersMapper.getPriceDays(orders);
	}

	@Override
	public Orders getPriceMonth(Orders orders) {
		return ordersMapper.getPriceMonth(orders);
	}

	@Override
	public Orders getPriceMonths(Orders orders) {
		return ordersMapper.getPriceMonths(orders);
	}

	@Override
	public Orders getPriceNoBuy(Orders orders) {
		return ordersMapper.getPriceNoBuy(orders);
	}

	@Override
	public Orders getPriceNoBuys(Orders orders) {
		return ordersMapper.getPriceNoBuys(orders);
	}

	@Override
	public List<Orders> getOrdersListPageDD(Orders orders) {
		return ordersMapper.getOrdersListPageDD(orders);
	}

	@Override
	public void updateOrdersLog(Orders orders) {
		ordersMapper.updateOrdersLog(orders);
	}
	
}