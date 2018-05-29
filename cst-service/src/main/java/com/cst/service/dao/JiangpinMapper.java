package com.cst.service.dao;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface JiangpinMapper {
	
	public abstract List<Jiangpin> getJiangpinListPage(Jiangpin jiangpin);
	
	public abstract Jiangpin getJiangpinById(int id);

	public abstract int insertJiangpin(Jiangpin jiangpin);

	public abstract void updateJiangpin(Jiangpin jiangpin);

	public abstract void deleteJiangpin(int id);
	
}
