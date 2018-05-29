package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ProductAttributeMapper {
	
	public abstract List<ProductAttribute> getProductAttributeListPage(ProductAttribute productAttribute);
	
	public abstract List<ProductAttribute> getProductAttributeById(String uuid);

	public abstract int insertProductAttribute(List<ProductAttribute> productAttributeList);

	public abstract void updateProductAttribute(ProductAttribute productAttribute);

	public abstract void deleteProductAttribute(int productAttributeId);
	
}
