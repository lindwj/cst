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

@Service("StockService")
public class StockServiceImpl implements StockService{
	@Autowired
	private StockMapper stockMapper;
	
	@Override
	public List<Stock> getStockListPage(Stock stock){
		return stockMapper.getStockListPage(stock);
	}
	
	@Override
	public List<Stock> getStockById(int stockId){
		return stockMapper.getStockById(stockId);
	}
	
	@Override
	public Stock getStockByPrd(Stock stock){
		return stockMapper.getStockByPrd(stock);
	}
	
	@Override
	public int saveStock(Stock stock,String doWhat){
		if("add".equals(doWhat)){
			return stockMapper.insertStock(stock.getStockList());
		}else if("edit".equals(doWhat)){
			for(Stock temp:stock.getStockList()){
			stockMapper.updateStock(temp);
			}
		}		
		return 0;
  	
	}
	@Override
	public void updateSell(Stock stock){
		stockMapper.updateSell(stock);
	}
	@Override
	public void updateSellAll(List<Stock> stockList){
		stockMapper.updateSellAll(stockList);
	}
	
	@Override
	public void deleteStock(int stockId){
		stockMapper.deleteStock(stockId);
	}

	@Override
	public List<Stock> getStockListPageDD(Stock stock) {
		return stockMapper.getStockListPageDD(stock);
	}
	
}