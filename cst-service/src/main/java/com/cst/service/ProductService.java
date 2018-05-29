package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ProductService {
	
	public abstract List<Product> getProductListPage(Product product);
	
	public abstract List<Product> getCartcookieProductListPage(Product product);
	
	public abstract List<Product> getCartdbProductListPage(Product product);
	
	public abstract Product getProductById(String uuid);
	
	public abstract Product getProductByUuid(String uuid);
	
	public abstract List<Product> getProductForIndex(String temp);
	
	public abstract List<Product> getProductByType(String typeId);
	
	public abstract int saveProduct(Product product,String doWhat);

	public abstract void deleteProduct(String uuid);
	
	public abstract Product getCartcookieProduct(String uuid);
	
}