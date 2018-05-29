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

@Service("ContractNoService")
public class ContractNoServiceImpl implements ContractNoService{
	@Autowired
	private ContractNoMapper contractNoMapper;
	
	@Override
	public List<ContractNo> getContractNoListPage(ContractNo contractNo){
		return contractNoMapper.getContractNoListPage(contractNo);
	}
	
	@Override
	public ContractNo getContractNoById(int contractNoId){
		return contractNoMapper.getContractNoById(contractNoId);
	}
	@Override
	public ContractNo getContractNoByShop(int shopId){
		return contractNoMapper.getContractNoById(shopId);
	}

	@Override
	public int saveContractNo(ContractNo contractNo,String doWhat){
		if("add".equals(doWhat)){
			return contractNoMapper.insertContractNo(contractNo);
		}else if("edit".equals(doWhat)){
			contractNoMapper.updateContractNo(contractNo);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteContractNo(int contractNoId){
		contractNoMapper.deleteContractNo(contractNoId);
	}
	
}