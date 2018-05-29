package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShopFinancialMapper {
	
	public abstract List<ShopFinancial> getShopFinancialListPage(ShopFinancial shopFinancial);
	
	public abstract List<ShopFinancial> getShopFinancialSvcListPage(ShopFinancial shopFinancial);
	
	public abstract List<ShopFinancial> getShopFinancialSvcAllListPage(ShopFinancial shopFinancial);
	
	public abstract ShopFinancial getShopFinancialById(int shopFinancialId);

	public abstract int insertShopFinancial(ShopFinancial shopFinancial);
	
	public abstract int insertShopFinancialForWx(ShopFinancial shopFinancial);
	
	public abstract int insertWxAgentFinancial(ShopFinancial shopFinancial);
	
	public abstract int insertShopForWxagentFinancial(ShopFinancial shopFinancial);
	public abstract int insertShopAgentFinancial(ShopFinancial shopFinancial);
	
	

	public abstract void updateShopFinancial(ShopFinancial shopFinancial);

	public abstract void deleteShopFinancial(int shopFinancialId);
	
}
