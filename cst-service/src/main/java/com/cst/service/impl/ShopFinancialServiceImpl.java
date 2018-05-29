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

@Service("ShopFinancialService")
public class ShopFinancialServiceImpl implements ShopFinancialService{
	@Autowired
	private ShopFinancialMapper shopFinancialMapper;
	
	@Override
	public List<ShopFinancial> getShopFinancialListPage(ShopFinancial shopFinancial){
		return shopFinancialMapper.getShopFinancialListPage(shopFinancial);
	}
	@Override
	public List<ShopFinancial> getShopFinancialSvcListPage(ShopFinancial shopFinancial){
		return shopFinancialMapper.getShopFinancialSvcListPage(shopFinancial);
	}
	
	@Override
	public ShopFinancial getShopFinancialById(int shopFinancialId){
		return shopFinancialMapper.getShopFinancialById(shopFinancialId);
	}

	@Override
	public int saveShopFinancial(ShopFinancial shopFinancial,String doWhat){
		if("add".equals(doWhat)){
			//店 每日 支付宝收入
			shopFinancialMapper.insertShopFinancial(shopFinancial);
			
			
			//店 每日 微信收入
			shopFinancialMapper.insertShopFinancialForWx(shopFinancial);
			
			
			//每日 微代佣金收入
			shopFinancialMapper.insertWxAgentFinancial(shopFinancial);
			//每日 店 支付 微代佣金
			
			shopFinancialMapper.insertShopForWxagentFinancial (shopFinancial);
			
			
			//独代每日收入
			return shopFinancialMapper.insertShopAgentFinancial(shopFinancial);
			
		}else if("edit".equals(doWhat)){
			shopFinancialMapper.updateShopFinancial(shopFinancial);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteShopFinancial(int shopFinancialId){
		shopFinancialMapper.deleteShopFinancial(shopFinancialId);
	}
	@Override
	public List<ShopFinancial> getShopFinancialSvcAllListPage(ShopFinancial shopFinancial) {
		return shopFinancialMapper.getShopFinancialSvcAllListPage(shopFinancial);
	}
}