package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface WxAgentFinancialMapper {
	
	public abstract List<WxAgentFinancial> getWxAgentFinancialListPage(WxAgentFinancial wxAgentFinancial);
	
	public abstract WxAgentFinancial getWxAgentFinancialById(int wxAgentFinancialId);

	public abstract int insertWxAgentFinancial(WxAgentFinancial wxAgentFinancial);

	public abstract void updateWxAgentFinancial(WxAgentFinancial wxAgentFinancial);

	public abstract void deleteWxAgentFinancial(int wxAgentFinancialId);
	
	public abstract List<WxAgentFinancial> getWxAgentFinancialListPageDD(WxAgentFinancial wxAgentFinancial);
	
	public abstract WxAgentFinancial getWxAgentTotal(WxAgentFinancial wxAgentFinancial);
}
