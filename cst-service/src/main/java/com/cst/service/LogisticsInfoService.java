package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface LogisticsInfoService {
	
	public abstract List<LogisticsInfo> getLogisticsInfoListPage(LogisticsInfo logisticsInfo);
	
	public abstract LogisticsInfo getLogisticsInfoById(int logisticsInfoId);
	
	public abstract LogisticsInfo getLogisticsInfoBykdnId(LogisticsInfo logisticsInfo);
	
	public abstract int saveLogisticsInfo(LogisticsInfo logisticsInfo,String doWhat);

	public abstract void deleteLogisticsInfo(int logisticsInfoId);
	
}