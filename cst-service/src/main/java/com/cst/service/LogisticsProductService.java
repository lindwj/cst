package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface LogisticsProductService {
	
	public abstract List<LogisticsProduct> getLogisticsProductListPage(LogisticsProduct logisticsProduct);
	
	public abstract LogisticsProduct getLogisticsProductById(int logisticsProductId);
	
	public abstract int saveLogisticsProduct(LogisticsProduct logisticsProduct,String doWhat);

	public abstract void deleteLogisticsProduct(int logisticsProductId);
	
	public abstract List<LogisticsProduct> getNum(String ordergoodsCode);
	
	public abstract void deleteLogisticsIds(int logisticsProductId);
	
}