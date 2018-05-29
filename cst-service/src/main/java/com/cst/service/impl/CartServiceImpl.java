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

@Service("CartService")
public class CartServiceImpl implements CartService{
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<Cart> getCartListPage(Cart cart){
		return cartMapper.getCartListPage(cart);
	}
	@Override
	public List<Cart> getCartList(Cart cart){
		return cartMapper.getCartList(cart);
	}
	
	@Override
	public Cart getCartById(Cart cart){
		return cartMapper.getCartById(cart);
	}

	@Override
	public int saveCart(Cart cart,String doWhat){
		if("add".equals(doWhat)){
			return cartMapper.insertCart(cart);
		}else if("edit".equals(doWhat)){
			cartMapper.updateCart(cart);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteCart(Cart cart){
		cartMapper.deleteCart(cart);
	}
	
	@Override
	public void deleteCartAll(Cart cart){
		cartMapper.deleteCartAll(cart);
	}
	
	
}