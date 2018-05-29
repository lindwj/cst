package com.cst.service;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShualiwuService {
	
	public abstract List<Shualiwu> getShualiwuListPage(Shualiwu shualiwu);
	
	public abstract Shualiwu getShualiwuById(int id);
	public abstract Shualiwu getShualiwu(Shualiwu shualiwu);
	public abstract Shualiwu getShualiwunum(Shualiwu shualiwu);
	
	public abstract int saveShualiwu(Shualiwu shualiwu,String doWhat);

	public abstract void deleteShualiwu(int id);
	
}