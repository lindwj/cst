package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ContractNoMapper {
	
	public abstract List<ContractNo> getContractNoListPage(ContractNo contractNo);
	
	public abstract ContractNo getContractNoById(int contractNoId);
	
	public abstract ContractNo getContractNoByShop(int shopId);
	
	public abstract int insertContractNo(ContractNo contractNo);

	public abstract void updateContractNo(ContractNo contractNo);

	public abstract void deleteContractNo(int contractNoId);
	
}
