package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface PreOrdersService {
	
	public abstract List<PreOrders> getPreOrdersListPage(PreOrders preOrders);
	
	public abstract List<PreOrders> getPreOrdersUserListPage(PreOrders preOrders);
	
	public abstract PreOrders getPreOrdersUserById(int preOrdersId);
	
	public abstract List<PreOrders> getPreOrdersSelListPage(PreOrders preOrders);
	
	public abstract PreOrders getPreOrdersById(int preOrdersId);
	
	public abstract int savePreOrders(PreOrders preOrders,String doWhat);

	public abstract void deletePreOrders(int preOrdersId);
	
}