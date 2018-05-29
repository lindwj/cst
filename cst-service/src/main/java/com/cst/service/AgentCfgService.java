package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface AgentCfgService {
	
	public abstract List<AgentCfg> getAgentCfgListPage(AgentCfg agentCfg);
	
	public abstract AgentCfg getAgentCfgById(int agentCfgId);
	
	public abstract int saveAgentCfg(AgentCfg agentCfg,String doWhat);

	public abstract void deleteAgentCfg(int agentCfgId);
	
}