package com.cst.service;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface StarService {
	
	public abstract List<Star> getStarListPage(Star star);
	
	public abstract List<Star> getStarList(Star star);
	
	public abstract Star getstarListInfo(Star star);
	
	public abstract Star getStarById(int id);
	
	public abstract int saveStar(Star star,String doWhat);
	
	public abstract void updateStarPiaoShu(Star star);
	
	public abstract void updateStarredu(Star star);

	public abstract void deleteStar(int id);
	
}