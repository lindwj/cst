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

@Service("LiwuService")
public class LiwuServiceImpl implements LiwuService{
	@Autowired
	private LiwuMapper liwuMapper;
	
	@Override
	public List<Liwu> getLiwuListPage(Liwu liwu){
		return liwuMapper.getLiwuListPage(liwu);
	}
	
	@Override
	public Liwu getLiwuById(int id){
		return liwuMapper.getLiwuById(id);
	}

	@Override
	public int saveLiwu(Liwu liwu,String doWhat){
		if("add".equals(doWhat)){
			return liwuMapper.insertLiwu(liwu);
		}else if("edit".equals(doWhat)){
			liwuMapper.updateLiwu(liwu);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteLiwu(int id){
		liwuMapper.deleteLiwu(id);
	}
	
}