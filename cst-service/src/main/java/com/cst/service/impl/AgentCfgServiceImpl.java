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

@Service("AgentCfgService")
public class AgentCfgServiceImpl implements AgentCfgService{
	@Autowired
	private AgentCfgMapper agentCfgMapper;
	
	@Override
	public List<AgentCfg> getAgentCfgListPage(AgentCfg agentCfg){
		return agentCfgMapper.getAgentCfgListPage(agentCfg);
	}
	
	@Override
	public AgentCfg getAgentCfgById(int agentCfgId){
		return agentCfgMapper.getAgentCfgById(agentCfgId);
	}

	@Override
	public int saveAgentCfg(AgentCfg agentCfg,String doWhat){
		if("add".equals(doWhat)){
			return agentCfgMapper.insertAgentCfg(agentCfg);
		}else if("edit".equals(doWhat)){
			agentCfgMapper.updateAgentCfg(agentCfg);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteAgentCfg(int agentCfgId){
		agentCfgMapper.deleteAgentCfg(agentCfgId);
	}
	
}