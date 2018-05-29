package com.cst.service;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShuidianService {
	
	public abstract List<Shuidian> getShuidianListPage(Shuidian shuidian);
	public abstract List<Shuidian> getShuidianbbList(Shuidian shuidian);
	
	public abstract Shuidian getShuidianById(int shuidianId);
	
	public abstract int saveShuidian(Shuidian shuidian,String doWhat);

	public abstract void deleteShuidian(int shuidianId);
	
}