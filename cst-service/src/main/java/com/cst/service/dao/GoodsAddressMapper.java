package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface GoodsAddressMapper {
	
	public abstract List<GoodsAddress> getGoodsAddressListPage(GoodsAddress goodsAddress);
	
	public abstract List<GoodsAddress> getGoodsAddressList(GoodsAddress goodsAddress);
	
	public abstract GoodsAddress getGoodsAddressById(String goodsAddressUuid);
	
	public abstract GoodsAddress getGoodsAddressByDefault(GoodsAddress goodsAddress);

	public abstract int insertGoodsAddress(GoodsAddress goodsAddress);

	public abstract void updateGoodsAddress(GoodsAddress goodsAddress);
	
	public abstract void initGoodsAddress(GoodsAddress goodsAddress);

	public abstract void deleteGoodsAddress(String goodsAddressUuid);
	
	public abstract List<GoodsAddress> getGoodsAddressListPageWx(GoodsAddress goodsAddress);
	
	public abstract GoodsAddress getGoodsAddressByIdWx(String goodsAddressUuid);
	
	public abstract List<GoodsAddress> getGoodsAddressListPageWxOpen(GoodsAddress goodsAddress);
	
	public abstract void initGoodsAddressOpen(GoodsAddress goodsAddress);
}
