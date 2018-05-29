package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ParameterTypeService {
	
	public abstract List<ParameterType> getParameterTypeListPage(ParameterType parameterType);
	
	public abstract ParameterType getParameterTypeById(int parameterTypeId);
	
	public abstract int saveParameterType(ParameterType parameterType,String doWhat);

	public abstract void deleteParameterType(int parameterTypeId);
	
}