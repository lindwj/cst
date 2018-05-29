package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface AgentProductCfgService {
	
	public abstract List<AgentProductCfg> getAgentProductCfgListPage(AgentProductCfg agentProductCfg);
	
	public abstract AgentProductCfg getAgentProductCfgById(int agentProductCfgId);
	
	public abstract int saveAgentProductCfg(AgentProductCfg agentProductCfg,String doWhat);

	public abstract void deleteAgentProductCfg(AgentProductCfg agentProductCfg);
	
}