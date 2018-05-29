package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface GoodsAddressService {
	
	public abstract List<GoodsAddress> getGoodsAddressListPage(GoodsAddress goodsAddress);
	
	public abstract List<GoodsAddress> getGoodsAddressList(GoodsAddress goodsAddress);
	
	public abstract GoodsAddress getGoodsAddressById(String uuid);
	
	public abstract GoodsAddress getGoodsAddressByDefault(GoodsAddress goodsAddress);
	
	public abstract int saveGoodsAddress(GoodsAddress goodsAddress,String doWhat);

	public abstract void deleteGoodsAddress(String uuid);
	
	public abstract List<GoodsAddress> getGoodsAddressListPageWx(GoodsAddress goodsAddress);
	
	public abstract GoodsAddress getGoodsAddressByIdWx(String goodsAddressUuid);
	
	public abstract List<GoodsAddress> getGoodsAddressListPageWxOpen(GoodsAddress goodsAddress);
	
	public abstract int saveGoodsAddressOpen(GoodsAddress goodsAddress,String doWhat);
	
}