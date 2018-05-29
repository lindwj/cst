package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface LogisticsMapper {
	
	public abstract List<Logistics> getLogisticsListPage(Logistics logistics);
	
	public abstract Logistics getLogisticsById(int logisticsId);

	public abstract int insertLogistics(Logistics logistics);

	public abstract void updateLogistics(Logistics logistics);

	public abstract void deleteLogistics(int logisticsId);
	
	public abstract List<Logistics> getLogisticsList(Logistics logistics);
}
