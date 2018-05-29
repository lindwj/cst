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

@Service("ShopService")
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopMapper shopMapper;
	
	@Override
	public List<Shop> getShopListPage(Shop shop){
		return shopMapper.getShopListPage(shop);
	}
	@Override
	public List<Shop> isStreetUsed(Shop shop){
		return shopMapper.isStreetUsed(shop);	
	}
	
	@Override
	public List<Shop> getShopByAddress(Shop shop){
		return shopMapper.getShopByAddress(shop);
	}
	@Override
	public List<Shop> getShopByStreet(Shop shop){
		return shopMapper.getShopByStreet(shop);
	}
	
	@Override
	public List<Shop> getShopListByDistrict(int district){
		return shopMapper.getShopListByDistrict(district);
	}
	
	@Override
	public Shop getShopById(int shopId){
		return shopMapper.getShopById(shopId);
	}

	@Override
	public int saveShop(Shop shop,String doWhat){
		if("add".equals(doWhat)){
			return shopMapper.insertShop(shop);
		}else if("edit".equals(doWhat)){
			shopMapper.updateShop(shop);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteShop(int shopId){
		shopMapper.deleteShop(shopId);
	}
	
}