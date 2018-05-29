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

@Service("OrdergoodsService")
public class OrdergoodsServiceImpl implements OrdergoodsService{
	@Autowired
	private OrdergoodsMapper ordergoodsMapper;
	
	@Override
	public List<Ordergoods> getOrdergoodsListPage(Ordergoods ordergoods){
		return ordergoodsMapper.getOrdergoodsListPage(ordergoods);
	}
	
	@Override
	public Ordergoods getOrdergoodsById(int ordergoodsId){
		return ordergoodsMapper.getOrdergoodsById(ordergoodsId);
	}

	@Override
	public int saveOrdergoods(Ordergoods ordergoods,String doWhat){
		if("add".equals(doWhat)){
			return ordergoodsMapper.insertOrdergoods(ordergoods);
		}else if("edit".equals(doWhat)){
			ordergoodsMapper.updateOrdergoods(ordergoods);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteOrdergoods(String code){
		ordergoodsMapper.deleteOrdergoods(code);
	}

	@Override
	public Ordergoods getOrdergoodsByCode(String code) {
		// TODO Auto-generated method stub
		return ordergoodsMapper.getOrdergoodsByCode(code);
	}

	@Override
	public void setStatus(Ordergoods ordergoods) {
		// TODO Auto-generated method stub
		ordergoodsMapper.setStatus(ordergoods);
	}

	@Override
	public List<Ordergoods> getListPage(Ordergoods ordergoods) {
		// TODO Auto-generated method stub
		return ordergoodsMapper.getListPage(ordergoods);
	}

	@Override
	public List<Ordergoods> searchListPage(Ordergoods ordergoods) {
		// TODO Auto-generated method stub
		return ordergoodsMapper.searchListPage(ordergoods);
	}

	@Override
	public Ordergoods getOrderByCode(String code) {
		// TODO Auto-generated method stub
		return ordergoodsMapper.getOrderByCode(code);
	}
	
}