package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShopMapper {
	
	public abstract List<Shop> getShopListPage(Shop shop);
	
	public abstract List<Shop> isStreetUsed(Shop shop);
	
	
	public abstract List<Shop> getShopListByDistrict(int district);
	
	public abstract Shop getShopById(int shopId);
	
	public abstract List<Shop> getShopByAddress(Shop shop);
	
	public abstract List<Shop> getShopByStreet(Shop shop);
	

	public abstract int insertShop(Shop shop);

	public abstract void updateShop(Shop shop);

	public abstract void deleteShop(int shopId);
	
}
