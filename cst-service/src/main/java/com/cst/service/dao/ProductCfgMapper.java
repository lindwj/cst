package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ProductCfgMapper {
	
	public abstract List<ProductCfg> getProductCfgListPage(ProductCfg productCfg);
	
	public abstract List<ProductCfg> getProductCfgListByType(ProductCfg productCfg);
	
	public abstract ProductCfg getProductCfgById(int productCfgId);

	public abstract int insertProductCfg(ProductCfg productCfg);

	public abstract void updateProductCfg(ProductCfg productCfg);

	public abstract void deleteProductCfg(int productCfgId);
	
}
