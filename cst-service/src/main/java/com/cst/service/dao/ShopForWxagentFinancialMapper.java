package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShopForWxagentFinancialMapper {
	
	public abstract List<ShopForWxagentFinancial> getShopForWxagentFinancialListPage(ShopForWxagentFinancial shopForWxagentFinancial);
	
	public abstract ShopForWxagentFinancial getShopForWxagentFinancialById(int shopForWxagentFinancialId);

	public abstract int insertShopForWxagentFinancial(ShopForWxagentFinancial shopForWxagentFinancial);

	public abstract void updateShopForWxagentFinancial(ShopForWxagentFinancial shopForWxagentFinancial);

	public abstract void deleteShopForWxagentFinancial(int shopForWxagentFinancialId);
	
	public abstract ShopForWxagentFinancial getShopForWxagentTotal(ShopForWxagentFinancial shopForWxagentFinancial);
}
