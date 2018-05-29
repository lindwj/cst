package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface BindStreetService {
	
	public abstract List<BindStreet> getBindStreetListPage(BindStreet bindStreet);
	
	public abstract BindStreet getBindStreetById(int bindStreetId);
	
	public abstract int saveBindStreet(List<BindStreet> bindStreetList,String doWhat);

	public abstract void deleteBindStreet(BindStreet bindStreet);
	
}