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

@Service("ProductService")
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Product> getProductListPage(Product product){
		return productMapper.getProductListPage(product);
	}
	@Override
	public List<Product> getCartcookieProductListPage(Product product){
		return productMapper.getCartcookieProductListPage(product);
	}
	
	@Override
	public List<Product> getCartdbProductListPage(Product product){
		return productMapper.getCartdbProductListPage(product);
	}
	
	
	@Override
	public Product getProductById(String uuid){
		return productMapper.getProductById(uuid);
	}
	@Override
	public Product getProductByUuid(String uuid){
		return productMapper.getProductByUuid(uuid);
	}
	
	@Override
	public List<Product> getProductForIndex(String temp){
		return productMapper.getProductForIndex(temp);
	}
	
	@Override
	public List<Product> getProductByType(String typeId){
		return productMapper.getProductByType(typeId);
	}

	@Override
	public int saveProduct(Product product,String doWhat){
		if("add".equals(doWhat)){
			return productMapper.insertProduct(product);
		}else if("edit".equals(doWhat)){
			productMapper.updateProduct(product);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteProduct(String uuid){
		productMapper.deleteProduct(uuid);
	}
	
	@Override
	public Product getCartcookieProduct(String uuid){
		return productMapper.getCartcookieProduct(uuid);
	}
	
}