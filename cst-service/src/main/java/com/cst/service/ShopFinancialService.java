package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShopFinancialService {
	
	public abstract List<ShopFinancial> getShopFinancialListPage(ShopFinancial shopFinancial);
	
	public abstract List<ShopFinancial> getShopFinancialSvcListPage(ShopFinancial shopFinancial);
	
	public abstract ShopFinancial getShopFinancialById(int shopFinancialId);
	
	public abstract int saveShopFinancial(ShopFinancial shopFinancial,String doWhat);

	public abstract void deleteShopFinancial(int shopFinancialId);
	
	public abstract List<ShopFinancial> getShopFinancialSvcAllListPage(ShopFinancial shopFinancial);
	
}