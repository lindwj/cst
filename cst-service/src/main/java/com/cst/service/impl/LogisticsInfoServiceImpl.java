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

@Service("LogisticsInfoService")
public class LogisticsInfoServiceImpl implements LogisticsInfoService{
	@Autowired
	private LogisticsInfoMapper logisticsInfoMapper;
	
	@Override
	public List<LogisticsInfo> getLogisticsInfoListPage(LogisticsInfo logisticsInfo){
		return logisticsInfoMapper.getLogisticsInfoListPage(logisticsInfo);
	}
	
	@Override
	public LogisticsInfo getLogisticsInfoById(int logisticsInfoId){
		return logisticsInfoMapper.getLogisticsInfoById(logisticsInfoId);
	}

	@Override
	public int saveLogisticsInfo(LogisticsInfo logisticsInfo,String doWhat){
		if("add".equals(doWhat)){
			return logisticsInfoMapper.insertLogisticsInfo(logisticsInfo);
		}else if("edit".equals(doWhat)){
			logisticsInfoMapper.updateLogisticsInfo(logisticsInfo);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteLogisticsInfo(int logisticsInfoId){
		logisticsInfoMapper.deleteLogisticsInfo(logisticsInfoId);
	}

	@Override
	public LogisticsInfo getLogisticsInfoBykdnId(LogisticsInfo logisticsInfo) {
		return logisticsInfoMapper.getLogisticsInfoBykdnId(logisticsInfo);
	}
	
}