package com.cst.service;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface LiwuService {
	
	public abstract List<Liwu> getLiwuListPage(Liwu liwu);
	
	public abstract Liwu getLiwuById(int id);
	
	public abstract int saveLiwu(Liwu liwu,String doWhat);

	public abstract void deleteLiwu(int id);
	
}