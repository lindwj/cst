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

@Service("PreOrdersService")
public class PreOrdersServiceImpl implements PreOrdersService{
	@Autowired
	private PreOrdersMapper preOrdersMapper;
	
	@Override
	public List<PreOrders> getPreOrdersListPage(PreOrders preOrders){
		return preOrdersMapper.getPreOrdersListPage(preOrders);
	}
	
	@Override
	public  PreOrders getPreOrdersUserById(int preOrdersId){
		return preOrdersMapper.getPreOrdersUserById(preOrdersId);
	}
	
	@Override
	public List<PreOrders> getPreOrdersUserListPage(PreOrders preOrders){
		return preOrdersMapper.getPreOrdersUserListPage(preOrders);
	}
	
	@Override
	public List<PreOrders> getPreOrdersSelListPage(PreOrders preOrders){
		return preOrdersMapper.getPreOrdersSelListPage(preOrders);
	}
	
	@Override
	public PreOrders getPreOrdersById(int preOrdersId){
		return preOrdersMapper.getPreOrdersById(preOrdersId);
	}

	@Override
	public int savePreOrders(PreOrders preOrders,String doWhat){
		if("add".equals(doWhat)){
			return preOrdersMapper.insertPreOrders(preOrders);
		}else if("edit".equals(doWhat)){
			preOrdersMapper.updatePreOrders(preOrders);
		}		
		return 0;
  	
	}
	
	@Override
	public void deletePreOrders(int preOrdersId){
		preOrdersMapper.deletePreOrders(preOrdersId);
	}
	
}