package com.cst.service.dao;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ShuidianMapper {
	
	public abstract List<Shuidian> getShuidianListPage(Shuidian shuidian);
	
	public abstract List<Shuidian> getShuidianbbList(Shuidian shuidian);
	
	public abstract Shuidian getShuidianById(int shuidianId);

	public abstract int insertShuidian(Shuidian shuidian);

	public abstract void updateShuidian(Shuidian shuidian);

	public abstract void deleteShuidian(int shuidianId);
	
}
