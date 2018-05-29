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

@Service("TellUsService")
public class TellUsServiceImpl implements TellUsService{
	@Autowired
	private TellUsMapper tellUsMapper;
	
	@Override
	public List<TellUs> getTellUsListPage(TellUs tellUs){
		return tellUsMapper.getTellUsListPage(tellUs);
	}
	
	@Override
	public TellUs getTellUsById(int tellUsId){
		return tellUsMapper.getTellUsById(tellUsId);
	}

	@Override
	public int saveTellUs(TellUs tellUs,String doWhat){
		if("add".equals(doWhat)){
			return tellUsMapper.insertTellUs(tellUs);
		}else if("edit".equals(doWhat)){
			tellUsMapper.updateTellUs(tellUs);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteTellUs(int tellUsId){
		tellUsMapper.deleteTellUs(tellUsId);
	}
	
}