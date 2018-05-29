package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface OrdersMapper {
	
	public abstract List<Orders> getOrdersListPage(Orders orders);
	
	public abstract Orders getOrdersByCode(Orders orders);
	
	public abstract List<Orders> getOrdersForAdmListPage(Orders orders);
	
	public abstract List<Orders> getOrdersListPageDD(Orders orders);
	
	public abstract List<Orders> getOrdersForBdhListPage(Orders orders);
	
	public abstract void setStatus(Orders orders);
	
	public abstract Orders getOrdersById(String code);

	public abstract int insertOrders(Orders orders);

	public abstract void updateOrders(Orders orders);
	
	public abstract void updateOrdersForwx(Orders orders);
	
	public abstract void updateOrdersLog(Orders orders);
	
	public abstract void deleteOrders(String code);
	
	public abstract List<Orders> getOrdersListPageWxOpen(Orders orders);
	
	public abstract Orders getPrice(Orders orders);
	
	public abstract Orders getPrices(Orders orders);
	
	public abstract Orders getPriceDay(Orders orders);
	
	public abstract Orders getPriceDays(Orders orders);
	
	public abstract Orders getPriceMonth(Orders orders);
	
	public abstract Orders getPriceMonths(Orders orders);
	
	public abstract Orders getPriceNoBuy(Orders orders);
	
	public abstract Orders getPriceNoBuys(Orders orders);
	
}
