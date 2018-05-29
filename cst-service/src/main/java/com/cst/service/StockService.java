package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface StockService {
	
	public abstract List<Stock> getStockListPage(Stock stock);
	
	public abstract List<Stock> getStockById(int stockId);
	
	public abstract Stock getStockByPrd(Stock stock);
	
	public abstract int saveStock(Stock stock,String doWhat);
	
	public abstract void updateSell(Stock stock);
	
	public abstract void updateSellAll(List<Stock> stockList);

	public abstract void deleteStock(int stockId);
	
	public abstract List<Stock> getStockListPageDD(Stock stock);
	
}