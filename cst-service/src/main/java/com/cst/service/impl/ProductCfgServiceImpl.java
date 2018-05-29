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

@Service("ProductCfgService")
public class ProductCfgServiceImpl implements ProductCfgService{
	@Autowired
	private ProductCfgMapper productCfgMapper;
	
	@Override
	public List<ProductCfg> getProductCfgListPage(ProductCfg productCfg){
		return productCfgMapper.getProductCfgListPage(productCfg);
	}
	
	@Override
	public List<ProductCfg> getProductCfgListByType(ProductCfg productCfg){
		return productCfgMapper.getProductCfgListByType(productCfg);
	}
	
	@Override
	public ProductCfg getProductCfgById(int productCfgId){
		return productCfgMapper.getProductCfgById(productCfgId);
	}

	@Override
	public int saveProductCfg(ProductCfg productCfg,String doWhat){
		if("add".equals(doWhat)){
			return productCfgMapper.insertProductCfg(productCfg);
		}else if("edit".equals(doWhat)){
			productCfgMapper.updateProductCfg(productCfg);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteProductCfg(int productCfgId){
		productCfgMapper.deleteProductCfg(productCfgId);
	}
	
}