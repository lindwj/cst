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

@Service("LogisticsService")
public class LogisticsServiceImpl implements LogisticsService{
	@Autowired
	private LogisticsMapper logisticsMapper;
	
	@Override
	public List<Logistics> getLogisticsListPage(Logistics logistics){
		return logisticsMapper.getLogisticsListPage(logistics);
	}
	
	@Override
	public Logistics getLogisticsById(int logisticsId){
		return logisticsMapper.getLogisticsById(logisticsId);
	}

	@Override
	public int saveLogistics(Logistics logistics,String doWhat){
		if("add".equals(doWhat)){
			return logisticsMapper.insertLogistics(logistics);
		}else if("edit".equals(doWhat)){
			logisticsMapper.updateLogistics(logistics);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteLogistics(int logisticsId){
		logisticsMapper.deleteLogistics(logisticsId);
	}

	@Override
	public List<Logistics> getLogisticsList(Logistics logistics) {
		// TODO Auto-generated method stub
		return logisticsMapper.getLogisticsList(logistics);
	}
	
}