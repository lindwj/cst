package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface TellUsMapper {
	
	public abstract List<TellUs> getTellUsListPage(TellUs tellUs);
	
	public abstract TellUs getTellUsById(int tellUsId);

	public abstract int insertTellUs(TellUs tellUs);

	public abstract void updateTellUs(TellUs tellUs);

	public abstract void deleteTellUs(int tellUsId);
	
}
