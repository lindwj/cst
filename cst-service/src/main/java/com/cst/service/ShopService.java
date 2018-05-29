package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShopService {
	
	public abstract List<Shop> getShopListPage(Shop shop);
	
	public abstract List<Shop> isStreetUsed(Shop shop);
	
	public abstract List<Shop> getShopByAddress(Shop shop);
	
	public abstract List<Shop> getShopByStreet(Shop shop);
	
	public abstract List<Shop> getShopListByDistrict(int district);
	
	public abstract Shop getShopById(int shopId);
	
	public abstract int saveShop(Shop shop,String doWhat);

	public abstract void deleteShop(int shopId);
	
}