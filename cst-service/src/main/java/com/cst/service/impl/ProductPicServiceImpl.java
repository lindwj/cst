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

@Service("ProductPicService")
public class ProductPicServiceImpl implements ProductPicService{
	@Autowired
	private ProductPicMapper productPicMapper;
	
	@Override
	public List<ProductPic> getProductPicListPage(ProductPic productPic){
		return productPicMapper.getProductPicListPage(productPic);
	}
	
	@Override
	public List<ProductPic> getProductPicById(String uuid){
		return productPicMapper.getProductPicById(uuid);
	}

	@Override
	public int saveProductPic(ProductPic productPic,String doWhat){
		if("add".equals(doWhat)){
			return productPicMapper.insertProductPic(productPic.getProductPicList());
		}else if("edit".equals(doWhat)){
			
			for(ProductPic temp:productPic.getProductPicList()){
			productPicMapper.updateProductPic(temp);
			}
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteProductPic(int productPicId){
		productPicMapper.deleteProductPic(productPicId);
	}
	
}