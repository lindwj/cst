package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface BindStreetMapper {
	
	public abstract List<BindStreet> getBindStreetListPage(BindStreet bindStreet);
	
	public abstract BindStreet getBindStreetById(int bindStreetId);

	public abstract int insertBindStreet(List<BindStreet> bindStreetList);

	public abstract void updateBindStreet(BindStreet bindStreet);

	public abstract void deleteBindStreet(BindStreet bindStreet);
	
}
