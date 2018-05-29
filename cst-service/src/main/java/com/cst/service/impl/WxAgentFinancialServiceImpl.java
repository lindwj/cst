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

@Service("WxAgentFinancialService")
public class WxAgentFinancialServiceImpl implements WxAgentFinancialService{
	@Autowired
	private WxAgentFinancialMapper wxAgentFinancialMapper;
	
	@Override
	public List<WxAgentFinancial> getWxAgentFinancialListPage(WxAgentFinancial wxAgentFinancial){
		return wxAgentFinancialMapper.getWxAgentFinancialListPage(wxAgentFinancial);
	}
	
	@Override
	public WxAgentFinancial getWxAgentFinancialById(int wxAgentFinancialId){
		return wxAgentFinancialMapper.getWxAgentFinancialById(wxAgentFinancialId);
	}

	@Override
	public int saveWxAgentFinancial(WxAgentFinancial wxAgentFinancial,String doWhat){
		if("add".equals(doWhat)){
			return wxAgentFinancialMapper.insertWxAgentFinancial(wxAgentFinancial);
		}else if("edit".equals(doWhat)){
			wxAgentFinancialMapper.updateWxAgentFinancial(wxAgentFinancial);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteWxAgentFinancial(int wxAgentFinancialId){
		wxAgentFinancialMapper.deleteWxAgentFinancial(wxAgentFinancialId);
	}

	@Override
	public List<WxAgentFinancial> getWxAgentFinancialListPageDD(WxAgentFinancial wxAgentFinancial) {
		return wxAgentFinancialMapper.getWxAgentFinancialListPageDD(wxAgentFinancial);
	}

	@Override
	public WxAgentFinancial getWxAgentTotal(WxAgentFinancial wxAgentFinancial) {
		return wxAgentFinancialMapper.getWxAgentTotal(wxAgentFinancial);
	}
	
}