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

@Service("ShopAgentFinancialService")
public class ShopAgentFinancialServiceImpl implements ShopAgentFinancialService{
	@Autowired
	private ShopAgentFinancialMapper shopAgentFinancialMapper;
	
	@Override
	public List<ShopAgentFinancial> getShopAgentFinancialListPage(ShopAgentFinancial shopAgentFinancial){
		return shopAgentFinancialMapper.getShopAgentFinancialListPage(shopAgentFinancial);
	}
	
	@Override
	public ShopAgentFinancial getShopAgentFinancialById(int shopAgentFinancialId){
		return shopAgentFinancialMapper.getShopAgentFinancialById(shopAgentFinancialId);
	}

	@Override
	public int saveShopAgentFinancial(ShopAgentFinancial shopAgentFinancial,String doWhat){
		if("add".equals(doWhat)){
			return shopAgentFinancialMapper.insertShopAgentFinancial(shopAgentFinancial);
		}else if("edit".equals(doWhat)){
			shopAgentFinancialMapper.updateShopAgentFinancial(shopAgentFinancial);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteShopAgentFinancial(int shopAgentFinancialId){
		shopAgentFinancialMapper.deleteShopAgentFinancial(shopAgentFinancialId);
	}

	@Override
	public List<ShopAgentFinancial> getShopAgentFinancialListPageDD(ShopAgentFinancial shopAgentFinancial) {
		return shopAgentFinancialMapper.getShopAgentFinancialListPageDD(shopAgentFinancial);
	}

	@Override
	public List<ShopAgentFinancial> getShopAgentFinancial(ShopAgentFinancial shopAgentFinancial) {
		return shopAgentFinancialMapper.getShopAgentFinancial(shopAgentFinancial);
	}
	
}