package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface LogisticsService {
	
	public abstract List<Logistics> getLogisticsListPage(Logistics logistics);
	
	public abstract Logistics getLogisticsById(int logisticsId);
	
	public abstract int saveLogistics(Logistics logistics,String doWhat);

	public abstract void deleteLogistics(int logisticsId);
	
	public abstract List<Logistics> getLogisticsList(Logistics logistics);
}