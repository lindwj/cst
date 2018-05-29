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

@Service("AgentProductCfgService")
public class AgentProductCfgServiceImpl implements AgentProductCfgService{
	@Autowired
	private AgentProductCfgMapper agentProductCfgMapper;
	
	@Override
	public List<AgentProductCfg> getAgentProductCfgListPage(AgentProductCfg agentProductCfg){
		return agentProductCfgMapper.getAgentProductCfgListPage(agentProductCfg);
	}
	
	@Override
	public AgentProductCfg getAgentProductCfgById(int agentProductCfgId){
		return agentProductCfgMapper.getAgentProductCfgById(agentProductCfgId);
	}

	@Override
	public int saveAgentProductCfg(AgentProductCfg agentProductCfg,String doWhat){
		if("add".equals(doWhat)){
			return agentProductCfgMapper.insertAgentProductCfg(agentProductCfg);
		}else if("edit".equals(doWhat)){
			agentProductCfgMapper.updateAgentProductCfg(agentProductCfg);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteAgentProductCfg(AgentProductCfg agentProductCfg){
		agentProductCfgMapper.deleteAgentProductCfg(agentProductCfg);
	}
	
}