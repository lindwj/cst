package com.cst.service.dao;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface LiwuMapper {
	
	public abstract List<Liwu> getLiwuListPage(Liwu liwu);
	
	public abstract Liwu getLiwuById(int id);

	public abstract int insertLiwu(Liwu liwu);

	public abstract void updateLiwu(Liwu liwu);

	public abstract void deleteLiwu(int id);
	
}
