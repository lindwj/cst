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

@Service("ShopForWxagentFinancialService")
public class ShopForWxagentFinancialServiceImpl implements ShopForWxagentFinancialService{
	@Autowired
	private ShopForWxagentFinancialMapper shopForWxagentFinancialMapper;
	
	@Override
	public List<ShopForWxagentFinancial> getShopForWxagentFinancialListPage(ShopForWxagentFinancial shopForWxagentFinancial){
		return shopForWxagentFinancialMapper.getShopForWxagentFinancialListPage(shopForWxagentFinancial);
	}
	
	@Override
	public ShopForWxagentFinancial getShopForWxagentFinancialById(int shopForWxagentFinancialId){
		return shopForWxagentFinancialMapper.getShopForWxagentFinancialById(shopForWxagentFinancialId);
	}

	@Override
	public int saveShopForWxagentFinancial(ShopForWxagentFinancial shopForWxagentFinancial,String doWhat){
		if("add".equals(doWhat)){
			return shopForWxagentFinancialMapper.insertShopForWxagentFinancial(shopForWxagentFinancial);
		}else if("edit".equals(doWhat)){
			shopForWxagentFinancialMapper.updateShopForWxagentFinancial(shopForWxagentFinancial);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteShopForWxagentFinancial(int shopForWxagentFinancialId){
		shopForWxagentFinancialMapper.deleteShopForWxagentFinancial(shopForWxagentFinancialId);
	}

	@Override
	public ShopForWxagentFinancial getShopForWxagentTotal(ShopForWxagentFinancial shopForWxagentFinancial) {
		return shopForWxagentFinancialMapper.getShopForWxagentTotal(shopForWxagentFinancial);
	}
	
}