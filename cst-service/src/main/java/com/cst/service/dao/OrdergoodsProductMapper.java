package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface OrdergoodsProductMapper {
	
	public abstract List<OrdergoodsProduct> getOrdergoodsProductListPage(OrdergoodsProduct ordergoodsProduct);
	
	public abstract OrdergoodsProduct getOrdergoodsProductById(int ordergoodsProductId);

	public abstract int insertOrdergoodsProduct(List<OrdergoodsProduct> ordergoodsProduct);

	public abstract void updateOrdergoodsProduct(OrdergoodsProduct ordergoodsProduct);

	public abstract void deleteOrdergoodsProduct(int ordergoodsProductId);
	
	public abstract List<OrdergoodsProduct> getOrderProduct(OrdergoodsProduct ordergoodsProduct);
	
}
