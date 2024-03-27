package com.goods.model.Cart.dao.impl;

import java.util.concurrent.atomic.AtomicInteger;

import com.goods.model.Cart.dao.CartDetailRedis_interface;
import com.goods.model.Cart.pojo.Cart;
import com.goods.model.Cart.pojo.CartDetail;
import com.goods.model.Goods.dao.impl.GoodsJDBCDAO;
import com.goods.model.Goods.pojo.Goods;
import com.store.model.service.StoreService;

public class CartDetailRedis implements CartDetailRedis_interface {

	private final CartRedis cartRedis = new CartRedis();

	private final GoodsJDBCDAO goodsDao = new GoodsJDBCDAO();

	 @Override
	    public void put(Integer storeId, Cart cart) {
	        AtomicInteger storeTotalPrc = new AtomicInteger();
	        cart.getStoreMap().get(storeId).forEach((goodsId, cartDetail) -> {
	            Goods product = goodsDao.getById(goodsId);
	            StoreService storeService = new StoreService();
	            String storeName = storeService.getById(storeId).getStoreName();
	            cartDetail.setStoreName(storeName);
	            cartDetail.setStoreId(storeId);
	            cartDetail.setGoodsName(product.getGoodsName());
	            cartDetail.setGoodsPrice(product.getGoodsPrice());
	            cartDetail.setGoodsTotalPrice(product.getGoodsPrice() * cartDetail.getDetailQuantity());
	        });
	        cart.getStoreMap().forEach((k,v)->v.forEach((k1,v2)->storeTotalPrc.getAndAdd(v2.getGoodsTotalPrice())));
	        cart.setCartsTotalPrice(storeTotalPrc.get());
	        cartRedis.put(cart);
	    }

	    @Override
	    public Cart get(String userId) {
	        return cartRedis.get(userId);
	    }
	    @Override
	    public String getCart(String userId) {
	        return cartRedis.getCart(userId);
	    }

	    @Override
	    public void clear(String userId) {
	        cartRedis.clear(userId);
	    }


	    @Override
	    public void reduceQty(String userId, Integer storeId, Integer goodsId) {
	        Cart cart = get(userId);
	        CartDetail cartDetail = cart.getStoreMap().get(storeId).get(goodsId);
	        int cartItemQty = cartDetail.getDetailQuantity()-1;
	        if(cartItemQty == 0){
	            cart.getStoreMap().get(storeId).remove(cartDetail.getGoodsId());
	        }else {
	            cartDetail.setDetailQuantity(cartItemQty);
	        }
	        put(storeId, cart);
	    }

	    @Override
	    public void addQty(String userId, Integer storeId, Integer goodsId) {
	        Cart cart = get(userId);
	        CartDetail cartDetail = cart.getStoreMap().get(storeId).get(goodsId);
	        cartDetail.setDetailQuantity(cartDetail.getDetailQuantity() + 1);
	        put(storeId, cart);
	    }

	}
