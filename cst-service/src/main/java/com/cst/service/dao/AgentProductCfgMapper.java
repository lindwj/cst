package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface AgentProductCfgMapper {
	
	public abstract List<AgentProductCfg> getAgentProductCfgListPage(AgentProductCfg agentProductCfg);
	
	public abstract AgentProductCfg getAgentProductCfgById(int agentProductCfgId);

	public abstract int insertAgentProductCfg(AgentProductCfg agentProductCfg);

	public abstract void updateAgentProductCfg(AgentProductCfg agentProductCfg);

	public abstract void deleteAgentProductCfg(AgentProductCfg agentProductCfg);
	
}
