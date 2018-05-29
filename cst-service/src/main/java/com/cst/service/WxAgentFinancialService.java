package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface WxAgentFinancialService {
	
	public abstract List<WxAgentFinancial> getWxAgentFinancialListPage(WxAgentFinancial wxAgentFinancial);
	
	public abstract WxAgentFinancial getWxAgentFinancialById(int wxAgentFinancialId);
	
	public abstract int saveWxAgentFinancial(WxAgentFinancial wxAgentFinancial,String doWhat);

	public abstract void deleteWxAgentFinancial(int wxAgentFinancialId);
	
	public abstract List<WxAgentFinancial> getWxAgentFinancialListPageDD(WxAgentFinancial wxAgentFinancial);
	
	public abstract WxAgentFinancial getWxAgentTotal(WxAgentFinancial wxAgentFinancial);
}