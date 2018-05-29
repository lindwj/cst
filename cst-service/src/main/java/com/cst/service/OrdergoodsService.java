package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface OrdergoodsService {
	
	public abstract List<Ordergoods> getOrdergoodsListPage(Ordergoods ordergoods);
	
	public abstract Ordergoods getOrdergoodsById(int ordergoodsId);
	
	public abstract int saveOrdergoods(Ordergoods ordergoods,String doWhat);

	public abstract void deleteOrdergoods(String code);
	
	public abstract Ordergoods getOrdergoodsByCode(String code);
	
	public abstract void setStatus(Ordergoods ordergoods);
	
	public abstract List<Ordergoods> getListPage(Ordergoods ordergoods);
	
	public abstract List<Ordergoods> searchListPage(Ordergoods ordergoods);
	
	public abstract Ordergoods getOrderByCode(String code);
}