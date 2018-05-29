package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface LogisticsInfoMapper {
	
	public abstract List<LogisticsInfo> getLogisticsInfoListPage(LogisticsInfo logisticsInfo);
	
	public abstract LogisticsInfo getLogisticsInfoById(int logisticsInfoId);
	
	public abstract LogisticsInfo getLogisticsInfoBykdnId(LogisticsInfo logisticsInfo);

	public abstract int insertLogisticsInfo(LogisticsInfo logisticsInfo);

	public abstract void updateLogisticsInfo(LogisticsInfo logisticsInfo);

	public abstract void deleteLogisticsInfo(int logisticsInfoId);
	
}
