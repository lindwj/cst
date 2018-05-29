package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShopAgentFinancialMapper {
	
	public abstract List<ShopAgentFinancial> getShopAgentFinancialListPage(ShopAgentFinancial shopAgentFinancial);
	
	public abstract ShopAgentFinancial getShopAgentFinancialById(int shopAgentFinancialId);

	public abstract int insertShopAgentFinancial(ShopAgentFinancial shopAgentFinancial);

	public abstract void updateShopAgentFinancial(ShopAgentFinancial shopAgentFinancial);

	public abstract void deleteShopAgentFinancial(int shopAgentFinancialId);
	
	public abstract List<ShopAgentFinancial> getShopAgentFinancialListPageDD(ShopAgentFinancial shopAgentFinancial);
	
	public abstract List<ShopAgentFinancial> getShopAgentFinancial(ShopAgentFinancial shopAgentFinancial);
}
