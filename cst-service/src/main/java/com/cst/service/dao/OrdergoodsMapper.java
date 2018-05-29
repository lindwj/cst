package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface OrdergoodsMapper {
	
	public abstract List<Ordergoods> getOrdergoodsListPage(Ordergoods ordergoods);
	
	public abstract Ordergoods getOrdergoodsById(int ordergoodsId);

	public abstract int insertOrdergoods(Ordergoods ordergoods);

	public abstract void updateOrdergoods(Ordergoods ordergoods);
	
	public abstract void updateOrdergoodsCode(Ordergoods ordergoods);

	public abstract void deleteOrdergoods(String code);
	
	public abstract Ordergoods getOrdergoodsByCode(String code);
	
	public abstract void setStatus(Ordergoods ordergoods);
	
	public abstract List<Ordergoods> getListPage(Ordergoods ordergoods);
	
	public abstract List<Ordergoods> searchListPage(Ordergoods ordergoods);
	
	public abstract Ordergoods getOrderByCode(String code);
}
