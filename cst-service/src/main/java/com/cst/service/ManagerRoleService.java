package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ManagerRoleService {
	
	public abstract List<ManagerRole> getManagerRoleListPage(ManagerRole managerRole);
	
	public abstract ManagerRole getManagerRoleById(int managerRoleId);
	
	public abstract int saveManagerRole(ManagerRole managerRole,String doWhat);

	public abstract void deleteManagerRole(int managerRoleId);
	
}