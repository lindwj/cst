package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface KdnMapper {
	
	public abstract List<Kdn> getKdnListPage(Kdn kdn);
	
	public abstract Kdn getKdnById(int kdnId);
	
	public abstract Kdn getKdnByCode(Kdn kdn);

	public abstract int insertKdn(Kdn kdn);

	public abstract void updateKdn(Kdn kdn);

	public abstract void deleteKdn(int kdnId);
	
}
