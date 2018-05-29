package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface PreOrdersMapper {
	
	public abstract List<PreOrders> getPreOrdersListPage(PreOrders preOrders);
	
	public abstract PreOrders getPreOrdersUserById(int preOrdersId);
	
	public abstract List<PreOrders> getPreOrdersUserListPage(PreOrders preOrders);
	
	public abstract List<PreOrders> getPreOrdersSelListPage(PreOrders preOrders);
	
	public abstract PreOrders getPreOrdersById(int preOrdersId);

	public abstract int insertPreOrders(PreOrders preOrders);

	public abstract void updatePreOrders(PreOrders preOrders);

	public abstract void deletePreOrders(int preOrdersId);
	
}
