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

@Service("ShualiwuService")
public class ShualiwuServiceImpl implements ShualiwuService{
	@Autowired
	private ShualiwuMapper shualiwuMapper;
	
	@Override
	public List<Shualiwu> getShualiwuListPage(Shualiwu shualiwu){
		return shualiwuMapper.getShualiwuListPage(shualiwu);
	}
	
	@Override
	public Shualiwu getShualiwuById(int id){
		return shualiwuMapper.getShualiwuById(id);
	}
	@Override
	public Shualiwu getShualiwu(Shualiwu shualiwu) {
		return shualiwuMapper.getShualiwu(shualiwu);
	}
	
	@Override
	public Shualiwu getShualiwunum(Shualiwu shualiwu) {
		return shualiwuMapper.getShualiwunum(shualiwu);
	}
	

	@Override
	public int saveShualiwu(Shualiwu shualiwu,String doWhat){
		if("add".equals(doWhat)){
			return shualiwuMapper.insertShualiwu(shualiwu);
		}else if("edit".equals(doWhat)){
			shualiwuMapper.updateShualiwu(shualiwu);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteShualiwu(int id){
		shualiwuMapper.deleteShualiwu(id);
	}
	
}