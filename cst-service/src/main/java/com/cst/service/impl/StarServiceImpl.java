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

@Service("StarService")
public class StarServiceImpl implements StarService{
	@Autowired
	private StarMapper starMapper;
	
	@Override
	public List<Star> getStarListPage(Star star){
		return starMapper.getStarListPage(star);
	}
	
	@Override
	public List<Star> getStarList(Star star){
		return starMapper.getStarList(star);
	}
	
	
	@Override
	public Star getstarListInfo(Star star) {
		return starMapper.getstarListInfo(star);
	}
	
	@Override
	public Star getStarById(int id){
		return starMapper.getStarById(id);
	}
	
	@Override
	public void updateStarPiaoShu(Star star) {
		 starMapper.updateStarPiaoShu(star);
	}

	@Override
	public void updateStarredu(Star star) {
		 starMapper.updateStarredu(star);
	}
	
	
	@Override
	public int saveStar(Star star,String doWhat){
		if("add".equals(doWhat)){
			return starMapper.insertStar(star);
		}else if("edit".equals(doWhat)){
			starMapper.updateStar(star);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteStar(int id){
		starMapper.deleteStar(id);
	}
	
}