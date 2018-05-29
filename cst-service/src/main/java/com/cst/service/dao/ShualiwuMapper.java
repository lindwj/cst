package com.cst.service.dao;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShualiwuMapper {
	
	public abstract List<Shualiwu> getShualiwuListPage(Shualiwu shualiwu);
	
	public abstract Shualiwu getShualiwuById(int id);
	
	public abstract Shualiwu getShualiwu(Shualiwu shualiwu);
	
	public abstract Shualiwu getShualiwunum(Shualiwu shualiwu);
	
	

	public abstract int insertShualiwu(Shualiwu shualiwu);

	public abstract void updateShualiwu(Shualiwu shualiwu);

	public abstract void deleteShualiwu(int id);
	
}
