package com.cst.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.cst.service.*;
import com.cst.service.dao.*;
import com.cst.service.model.*;

/**
 * @author lind
 *
 */

@Service("OrdersDetailService")
public class OrdersDetailServiceImpl implements OrdersDetailService{
	@Autowired
	private OrdersDetailMapper ordersDetailMapper;
	
	@Override
	public List<OrdersDetail> getOrdersDetailListPage(OrdersDetail ordersDetail){
		return ordersDetailMapper.getOrdersDetailListPage(ordersDetail);
	}
	
	@Override
	public List<OrdersDetail> getOrdersDetailList(OrdersDetail ordersDetail){
		return ordersDetailMapper.getOrdersDetailList(ordersDetail);
	}
	
	@Override
	public List<OrdersDetail> getOrdersDetailListNoshop(OrdersDetail ordersDetail){
		return ordersDetailMapper.getOrdersDetailListNoshop(ordersDetail);
	}
	
	
	@Override
	public List<OrdersDetail> getOrdersDetailNoList(OrdersDetail ordersDetail){
		return ordersDetailMapper.getOrdersDetailNoList(ordersDetail);
	}
	@Override
	public int insertOrdersDetailAll(List<OrdersDetail> ordersDetailList){
		return ordersDetailMapper.insertOrdersDetailAll(ordersDetailList);
	}
	
	@Override
	public List<OrdersDetail> getOrdersDetailById(String code){
		return ordersDetailMapper.getOrdersDetailById(code);
	}
	@Override
	public List<OrdersDetail> getOrdersDetailStockList(OrdersDetail ordersDetail){
		return ordersDetailMapper.getOrdersDetailStockList(ordersDetail);
	}

	@Override
	public int saveOrdersDetail(OrdersDetail ordersDetail,String doWhat){
		if("add".equals(doWhat)){
			return ordersDetailMapper.insertOrdersDetail(ordersDetail);
		}else if("edit".equals(doWhat)){
			ordersDetailMapper.updateOrdersDetail(ordersDetail);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteOrdersDetail(int ordersDetailId){
		ordersDetailMapper.deleteOrdersDetail(ordersDetailId);
	}
	
}