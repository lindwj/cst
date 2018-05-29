package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ProductAttributeService {
	
	public abstract List<ProductAttribute> getProductAttributeListPage(ProductAttribute productAttribute);
	
	public abstract List<ProductAttribute> getProductAttributeById(String uuid);
	
	public abstract int saveProductAttribute(ProductAttribute productAttribute,String doWhat);

	public abstract void deleteProductAttribute(int productAttributeId);
	
}