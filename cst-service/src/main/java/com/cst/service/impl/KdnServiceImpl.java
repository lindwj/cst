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

@Service("KdnService")
public class KdnServiceImpl implements KdnService{
	@Autowired
	private KdnMapper kdnMapper;
	
	@Override
	public List<Kdn> getKdnListPage(Kdn kdn){
		return kdnMapper.getKdnListPage(kdn);
	}
	
	@Override
	public Kdn getKdnById(int kdnId){
		return kdnMapper.getKdnById(kdnId);
	}

	@Override
	public int saveKdn(Kdn kdn,String doWhat){
		if("add".equals(doWhat)){
			return kdnMapper.insertKdn(kdn);
		}else if("edit".equals(doWhat)){
			kdnMapper.updateKdn(kdn);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteKdn(int kdnId){
		kdnMapper.deleteKdn(kdnId);
	}

	@Override
	public Kdn getKdnByCode(Kdn kdn) {
		return kdnMapper.getKdnByCode(kdn);
	}
	
}