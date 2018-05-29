package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface OrdersDetailMapper {
	
	public abstract List<OrdersDetail> getOrdersDetailListPage(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailList(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailListNoshop(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailStockList(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailNoList(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailById(String code);

	public abstract int insertOrdersDetail(OrdersDetail ordersDetail);
	
	public abstract int insertOrdersDetailAll(List<OrdersDetail> ordersDetailList);

	public abstract void updateOrdersDetail(OrdersDetail ordersDetail);

	public abstract void deleteOrdersDetail(int ordersDetailId);
	
}
