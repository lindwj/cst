package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface CartMapper {
	
	public abstract List<Cart> getCartListPage(Cart cart);
	
	public abstract List<Cart> getCartList(Cart cart);
	
	public abstract Cart getCartById(Cart cart);

	public abstract int insertCart(Cart cart);

	public abstract void updateCart(Cart cart);

	public abstract void deleteCart(Cart cart);
	
	public abstract void deleteCartAll(Cart cart);
	
}
