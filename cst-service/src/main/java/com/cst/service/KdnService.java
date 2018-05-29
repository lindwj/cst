package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface KdnService {
	
	public abstract List<Kdn> getKdnListPage(Kdn kdn);
	
	public abstract Kdn getKdnById(int kdnId);
	
	public abstract Kdn getKdnByCode(Kdn kdn);
	
	public abstract int saveKdn(Kdn kdn,String doWhat);

	public abstract void deleteKdn(int kdnId);
	
}