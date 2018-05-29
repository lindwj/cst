package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface ProductPicService {
	
	public abstract List<ProductPic> getProductPicListPage(ProductPic productPic);
	
	public abstract List<ProductPic> getProductPicById(String uuid);
	
	public abstract int saveProductPic(ProductPic productPic,String doWhat);

	public abstract void deleteProductPic(int productPicId);
	
}