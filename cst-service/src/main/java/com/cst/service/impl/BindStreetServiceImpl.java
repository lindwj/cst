package com.cst.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.cst.service.*;
import com.cst.service.dao.*;
import com.cst.service.model.*;

/**
 * @author lind
 *
 */

@Service("BindStreetService")
public class BindStreetServiceImpl implements BindStreetService{
	@Autowired
	private BindStreetMapper bindStreetMapper;
	
	@Override
	public List<BindStreet> getBindStreetListPage(BindStreet bindStreet){
		return bindStreetMapper.getBindStreetListPage(bindStreet);
	}
	
	@Override
	public BindStreet getBindStreetById(int bindStreetId){
		return bindStreetMapper.getBindStreetById(bindStreetId);
	}

	@Override
	public int saveBindStreet(List<BindStreet> bindStreetList,String doWhat){
		if("add".equals(doWhat)){
			return bindStreetMapper.insertBindStreet(bindStreetList);
		}else if("edit".equals(doWhat)){
//			bindStreetMapper.updateBindStreet(bindStreet);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteBindStreet(BindStreet bindStreet){
		bindStreetMapper.deleteBindStreet(bindStreet);
	}
	
}