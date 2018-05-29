package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShopAgentFinancialService {
	
	public abstract List<ShopAgentFinancial> getShopAgentFinancialListPage(ShopAgentFinancial shopAgentFinancial);
	
	public abstract ShopAgentFinancial getShopAgentFinancialById(int shopAgentFinancialId);
	
	public abstract int saveShopAgentFinancial(ShopAgentFinancial shopAgentFinancial,String doWhat);

	public abstract void deleteShopAgentFinancial(int shopAgentFinancialId);
	
	public abstract List<ShopAgentFinancial> getShopAgentFinancialListPageDD(ShopAgentFinancial shopAgentFinancial);
	
	public abstract List<ShopAgentFinancial> getShopAgentFinancial(ShopAgentFinancial shopAgentFinancial);
}