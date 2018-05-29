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

@Service("ProductAttributeService")
public class ProductAttributeServiceImpl implements ProductAttributeService{
	@Autowired
	private ProductAttributeMapper productAttributeMapper;
	
	@Override
	public List<ProductAttribute> getProductAttributeListPage(ProductAttribute productAttribute){
		return productAttributeMapper.getProductAttributeListPage(productAttribute);
	}
	
	@Override
	public List<ProductAttribute> getProductAttributeById(String uuid){
		return productAttributeMapper.getProductAttributeById(uuid);
	}

	@Override
	public int saveProductAttribute(ProductAttribute productAttribute,String doWhat){
		if("add".equals(doWhat)){
			return productAttributeMapper.insertProductAttribute(productAttribute.getProductAttributeList());
		}else if("edit".equals(doWhat)){
			for(ProductAttribute temp:productAttribute.getProductAttributeList()){
			productAttributeMapper.updateProductAttribute(temp);
			}
			
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteProductAttribute(int productAttributeId){
		productAttributeMapper.deleteProductAttribute(productAttributeId);
	}
	
}