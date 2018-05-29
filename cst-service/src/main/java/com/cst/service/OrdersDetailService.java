package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface OrdersDetailService {
	
	public abstract List<OrdersDetail> getOrdersDetailListPage(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailList(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailListNoshop(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailStockList(OrdersDetail ordersDetail);
	
	public abstract List<OrdersDetail> getOrdersDetailNoList(OrdersDetail ordersDetail);
	
	public abstract int insertOrdersDetailAll(List<OrdersDetail> ordersDetailList);
	
	public abstract List<OrdersDetail> getOrdersDetailById(String code);
	
	public abstract int saveOrdersDetail(OrdersDetail ordersDetail,String doWhat);

	public abstract void deleteOrdersDetail(int ordersDetailId);
	
}