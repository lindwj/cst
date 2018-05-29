package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ProductCfgService {
	
	public abstract List<ProductCfg> getProductCfgListPage(ProductCfg productCfg);
	
	public abstract List<ProductCfg> getProductCfgListByType(ProductCfg productCfg);
	
	public abstract ProductCfg getProductCfgById(int productCfgId);
	
	public abstract int saveProductCfg(ProductCfg productCfg,String doWhat);

	public abstract void deleteProductCfg(int productCfgId);
	
}