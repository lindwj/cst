package com.cst.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.cst.service.model.*;
import com.cst.service.dao.*;
import com.cst.service.*;

/**
 * @author lind
 *
 */

@Service("ShuidianService")
public class ShuidianServiceImpl implements ShuidianService{
	@Autowired
	private ShuidianMapper shuidianMapper;
	
	@Override
	public List<Shuidian> getShuidianListPage(Shuidian shuidian){
		return shuidianMapper.getShuidianListPage(shuidian);
	}
	@Override
	public List<Shuidian> getShuidianbbList(Shuidian shuidian){
		return shuidianMapper.getShuidianbbList(shuidian);
	}
	
	@Override
	public Shuidian getShuidianById(int shuidianId){
		return shuidianMapper.getShuidianById(shuidianId);
	}

	@Override
	public int saveShuidian(Shuidian shuidian,String doWhat){
		if("add".equals(doWhat)){
			return shuidianMapper.insertShuidian(shuidian);
		}else if("edit".equals(doWhat)){
			shuidianMapper.updateShuidian(shuidian);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteShuidian(int shuidianId){
		shuidianMapper.deleteShuidian(shuidianId);
	}
	
}