package com.cst.manager.quartzjob;


import org.springframework.beans.factory.annotation.Autowired;

import com.cst.service.ShopFinancialService;
import com.cst.service.model.ShopFinancial;

public class ShopFinancialJop {

	@Autowired
	private ShopFinancialService shopFinancialService;
	

	public void statistics() {
		ShopFinancial shopFinancial=new ShopFinancial();
//		测试用
//		shopFinancial.setShopId(3);
//		List<ShopFinancial> sList=shopFinancialService.getShopFinancialSvcListPage(shopFinancial);
		shopFinancialService.saveShopFinancial(shopFinancial, "add");

	}
}
