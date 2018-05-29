package com.cst.service.dao;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface StarMapper {
	
	public abstract List<Star> getStarListPage(Star star);
	
	public abstract List<Star> getStarList(Star star);
	
	
	public abstract Star getstarListInfo(Star star);
	
	public abstract Star getStarById(int id);

	public abstract int insertStar(Star star);

	public abstract void updateStar(Star star);
	
	public abstract void updateStarPiaoShu(Star star);
	
	
	public abstract void updateStarredu(Star star);
	
	
	

	public abstract void deleteStar(int id);
	
}
