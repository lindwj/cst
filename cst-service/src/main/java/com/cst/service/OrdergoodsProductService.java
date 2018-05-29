package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface OrdergoodsProductService {
	
	public abstract List<OrdergoodsProduct> getOrdergoodsProductListPage(OrdergoodsProduct ordergoodsProduct);
	
	public abstract OrdergoodsProduct getOrdergoodsProductById(int ordergoodsProductId);
	
	public abstract int saveOrdergoodsProduct(OrdergoodsProduct ordergoodsProduct,String doWhat);

	public abstract void deleteOrdergoodsProduct(int ordergoodsProductId);
	
	public abstract List<OrdergoodsProduct> getOrderProduct(OrdergoodsProduct ordergoodsProduct);
	
}