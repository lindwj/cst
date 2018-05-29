package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface CartService {
	
	public abstract List<Cart> getCartListPage(Cart cart);
	
	public abstract List<Cart> getCartList(Cart cart);
	
	public abstract Cart getCartById(Cart cart);
	
	public abstract int saveCart(Cart cart,String doWhat);

	public abstract void deleteCart(Cart cart);
	
	public abstract void deleteCartAll(Cart cart);
	
}