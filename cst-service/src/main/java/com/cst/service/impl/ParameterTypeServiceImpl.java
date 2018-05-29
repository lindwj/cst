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

@Service("ParameterTypeService")
public class ParameterTypeServiceImpl implements ParameterTypeService{
	@Autowired
	private ParameterTypeMapper parameterTypeMapper;
	
	@Override
	public List<ParameterType> getParameterTypeListPage(ParameterType parameterType){
		return parameterTypeMapper.getParameterTypeListPage(parameterType);
	}
	
	@Override
	public ParameterType getParameterTypeById(int parameterTypeId){
		return parameterTypeMapper.getParameterTypeById(parameterTypeId);
	}

	@Override
	public int saveParameterType(ParameterType parameterType,String doWhat){
		if("add".equals(doWhat)){
			return parameterTypeMapper.insertParameterType(parameterType);
		}else if("edit".equals(doWhat)){
			parameterTypeMapper.updateParameterType(parameterType);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteParameterType(int parameterTypeId){
		parameterTypeMapper.deleteParameterType(parameterTypeId);
	}
	
}