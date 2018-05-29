package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface AgentCfgMapper {
	
	public abstract List<AgentCfg> getAgentCfgListPage(AgentCfg agentCfg);
	
	public abstract AgentCfg getAgentCfgById(int agentCfgId);

	public abstract int insertAgentCfg(AgentCfg agentCfg);

	public abstract void updateAgentCfg(AgentCfg agentCfg);

	public abstract void deleteAgentCfg(int agentCfgId);
	
}
