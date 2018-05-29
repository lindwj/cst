package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ContractNoService {
	
	public abstract List<ContractNo> getContractNoListPage(ContractNo contractNo);
	
	public abstract ContractNo getContractNoById(int contractNoId);
	
	public abstract ContractNo getContractNoByShop(int shopId);
	
	public abstract int saveContractNo(ContractNo contractNo,String doWhat);

	public abstract void deleteContractNo(int contractNoId);
	
}