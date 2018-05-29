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

@Service("OrdergoodsProductService")
public class OrdergoodsProductServiceImpl implements OrdergoodsProductService{
	@Autowired
	private OrdergoodsProductMapper ordergoodsProductMapper;
	
	@Override
	public List<OrdergoodsProduct> getOrdergoodsProductListPage(OrdergoodsProduct ordergoodsProduct){
		return ordergoodsProductMapper.getOrdergoodsProductListPage(ordergoodsProduct);
	}
	
	@Override
	public OrdergoodsProduct getOrdergoodsProductById(int ordergoodsProductId){
		return ordergoodsProductMapper.getOrdergoodsProductById(ordergoodsProductId);
	}

	@Override
	public int saveOrdergoodsProduct(OrdergoodsProduct ordergoodsProduct,String doWhat){
		if("add".equals(doWhat)){
			return ordergoodsProductMapper.insertOrdergoodsProduct(ordergoodsProduct.getOrdergoodsProducts());
		}else if("edit".equals(doWhat)){
			ordergoodsProductMapper.updateOrdergoodsProduct(ordergoodsProduct);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteOrdergoodsProduct(int ordergoodsProductId){
		ordergoodsProductMapper.deleteOrdergoodsProduct(ordergoodsProductId);
	}

	@Override
	public List<OrdergoodsProduct> getOrderProduct(OrdergoodsProduct ordergoodsProduct) {
		return ordergoodsProductMapper.getOrderProduct(ordergoodsProduct);
	}
	
}