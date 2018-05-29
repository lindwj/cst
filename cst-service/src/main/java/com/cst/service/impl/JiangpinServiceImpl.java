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

@Service("JiangpinService")
public class JiangpinServiceImpl implements JiangpinService{
	@Autowired
	private JiangpinMapper jiangpinMapper;
	
	@Override
	public List<Jiangpin> getJiangpinListPage(Jiangpin jiangpin){
		return jiangpinMapper.getJiangpinListPage(jiangpin);
	}
	
	@Override
	public Jiangpin getJiangpinById(int id){
		return jiangpinMapper.getJiangpinById(id);
	}

	@Override
	public int saveJiangpin(Jiangpin jiangpin,String doWhat){
		if("add".equals(doWhat)){
			return jiangpinMapper.insertJiangpin(jiangpin);
		}else if("edit".equals(doWhat)){
			jiangpinMapper.updateJiangpin(jiangpin);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteJiangpin(int id){
		jiangpinMapper.deleteJiangpin(id);
	}
	
}