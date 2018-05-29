package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface TellUsService {
	
	public abstract List<TellUs> getTellUsListPage(TellUs tellUs);
	
	public abstract TellUs getTellUsById(int tellUsId);
	
	public abstract int saveTellUs(TellUs tellUs,String doWhat);

	public abstract void deleteTellUs(int tellUsId);
	
}