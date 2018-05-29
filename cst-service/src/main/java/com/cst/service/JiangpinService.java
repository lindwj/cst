package com.cst.service;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface JiangpinService {
	
	public abstract List<Jiangpin> getJiangpinListPage(Jiangpin jiangpin);
	
	public abstract Jiangpin getJiangpinById(int id);
	
	public abstract int saveJiangpin(Jiangpin jiangpin,String doWhat);

	public abstract void deleteJiangpin(int id);
	
}