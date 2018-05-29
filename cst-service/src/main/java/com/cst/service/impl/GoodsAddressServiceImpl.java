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

@Service("GoodsAddressService")
public class GoodsAddressServiceImpl implements GoodsAddressService{
	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	
	@Override
	public List<GoodsAddress> getGoodsAddressListPage(GoodsAddress goodsAddress){
		return goodsAddressMapper.getGoodsAddressListPage(goodsAddress);
	}
	
	@Override
	public List<GoodsAddress> getGoodsAddressList(GoodsAddress goodsAddress){
		return goodsAddressMapper.getGoodsAddressList(goodsAddress);
	}
	
	@Override
	public GoodsAddress getGoodsAddressById(String uuid){
		return goodsAddressMapper.getGoodsAddressById(uuid);
	}
	@Override
	public GoodsAddress getGoodsAddressByDefault(GoodsAddress goodsAddress){
		return goodsAddressMapper.getGoodsAddressByDefault(goodsAddress);
	}

	@Override
	public int saveGoodsAddress(GoodsAddress goodsAddress,String doWhat){
		if("add".equals(doWhat)){
			//设为默认 
			if(goodsAddress.getIsDefault()==1){
				goodsAddressMapper.initGoodsAddress(goodsAddress);	
			}
			return goodsAddressMapper.insertGoodsAddress(goodsAddress);
		}else if("edit".equals(doWhat)){
			//设为默认 
			if(goodsAddress.getIsDefault()==1){
				goodsAddressMapper.initGoodsAddress(goodsAddress);	
			}
			goodsAddressMapper.updateGoodsAddress(goodsAddress);
		}		
		return 0;
  	
	}
	
	
	
	@Override
	public void deleteGoodsAddress(String uuid){
		goodsAddressMapper.deleteGoodsAddress(uuid);
	}

	@Override
	public List<GoodsAddress> getGoodsAddressListPageWx(GoodsAddress goodsAddress) {
		return goodsAddressMapper.getGoodsAddressListPageWx(goodsAddress);
	}

	@Override
	public GoodsAddress getGoodsAddressByIdWx(String goodsAddressUuid) {
		return goodsAddressMapper.getGoodsAddressByIdWx(goodsAddressUuid);
	}

	@Override
	public List<GoodsAddress> getGoodsAddressListPageWxOpen(GoodsAddress goodsAddress) {
		// TODO Auto-generated method stub
		return goodsAddressMapper.getGoodsAddressListPageWxOpen(goodsAddress);
	}
	
	@Override
	public int saveGoodsAddressOpen(GoodsAddress goodsAddress,String doWhat){
		if("add".equals(doWhat)){
			//设为默认 
			if(goodsAddress.getIsDefault()==1){
				goodsAddressMapper.initGoodsAddressOpen(goodsAddress);	
			}
			return goodsAddressMapper.insertGoodsAddress(goodsAddress);
		}else if("edit".equals(doWhat)){
			//设为默认 
			if(goodsAddress.getIsDefault()==1){
				goodsAddressMapper.initGoodsAddressOpen(goodsAddress);	
			}
			goodsAddressMapper.updateGoodsAddress(goodsAddress);
		}		
		return 0;
  	
	}

}