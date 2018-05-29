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

@Service("LogisticsProductService")
public class LogisticsProductServiceImpl implements LogisticsProductService{
	@Autowired
	private LogisticsProductMapper logisticsProductMapper;
	
	@Override
	public List<LogisticsProduct> getLogisticsProductListPage(LogisticsProduct logisticsProduct){
		return logisticsProductMapper.getLogisticsProductListPage(logisticsProduct);
	}
	
	@Override
	public LogisticsProduct getLogisticsProductById(int logisticsProductId){
		return logisticsProductMapper.getLogisticsProductById(logisticsProductId);
	}

	@Override
	public int saveLogisticsProduct(LogisticsProduct logisticsProduct,String doWhat){
		if("add".equals(doWhat)){
			return logisticsProductMapper.insertLogisticsProduct(logisticsProduct.getLogisticsProducts());
		}else if("edit".equals(doWhat)){
			logisticsProductMapper.updateLogisticsProduct(logisticsProduct);
		}		
		return 0;
	}
	
	@Override
	public void deleteLogisticsProduct(int logisticsProductId){
		logisticsProductMapper.deleteLogisticsProduct(logisticsProductId);
	}

	@Override
	public List<LogisticsProduct> getNum(String ordergoodsCode) {
		// TODO Auto-generated method stub
		return logisticsProductMapper.getNum(ordergoodsCode);
	}

	@Override
	public void deleteLogisticsIds(int logisticsProductId) {
		// TODO Auto-generated method stub
		logisticsProductMapper.deleteLogisticsIds(logisticsProductId);
	}
	
}