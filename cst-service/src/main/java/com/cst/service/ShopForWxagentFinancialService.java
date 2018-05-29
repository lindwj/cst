package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShopForWxagentFinancialService {
	
	public abstract List<ShopForWxagentFinancial> getShopForWxagentFinancialListPage(ShopForWxagentFinancial shopForWxagentFinancial);
	
	public abstract ShopForWxagentFinancial getShopForWxagentFinancialById(int shopForWxagentFinancialId);
	
	public abstract int saveShopForWxagentFinancial(ShopForWxagentFinancial shopForWxagentFinancial,String doWhat);

	public abstract void deleteShopForWxagentFinancial(int shopForWxagentFinancialId);
	
	public abstract ShopForWxagentFinancial getShopForWxagentTotal(ShopForWxagentFinancial shopForWxagentFinancial);
}