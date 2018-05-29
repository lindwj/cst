package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ProductPicMapper {
	
	public abstract List<ProductPic> getProductPicListPage(ProductPic productPic);
	
	public abstract List<ProductPic> getProductPicById(String uuid);

	public abstract int insertProductPic(List<ProductPic> productPicList);

	public abstract void updateProductPic(ProductPic productPic);

	public abstract void deleteProductPic(int productPicId);
	
}
