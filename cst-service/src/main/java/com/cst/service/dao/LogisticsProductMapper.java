package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface LogisticsProductMapper {
	
	public abstract List<LogisticsProduct> getLogisticsProductListPage(LogisticsProduct logisticsProduct);
	
	public abstract LogisticsProduct getLogisticsProductById(int logisticsProductId);

	public abstract int insertLogisticsProduct(List<LogisticsProduct> logisticsProduct);

	public abstract void updateLogisticsProduct(LogisticsProduct logisticsProduct);

	public abstract void deleteLogisticsProduct(int logisticsProductId);
	
	public abstract List<LogisticsProduct> getNum(String ordergoodsCode);
	
	public abstract void deleteLogisticsIds(int logisticsProductId);
	
}
