package com.goods.contorller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goods.model.Cart.pojo.Cart;
import com.goods.model.Cart.pojo.CartDetail;
import com.goods.model.Cart.dao.impl.CartDetailRedis;
import com.store.model.service.StoreService;

@WebServlet("/cart/reduce")
public class ReduceCartServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

	        final Integer storeId = Integer.valueOf(request.getParameter("storeId"));
	        final Integer goodsId = Integer.valueOf(request.getParameter("goodsId"));
	        String userId = String.valueOf(request.getSession().getAttribute("memId"));
	        CartDetailRedis cartSvc = new CartDetailRedis();
	        StoreService storeService = new StoreService();
	        String storeName = storeService.getById(storeId).getStoreName();
	        Cart cart = cartSvc.get(userId);
	        CartDetail cartDetail = new CartDetail();
	        HashMap<Integer, CartDetail> cartItemMap;
	        //判斷有沒有購物車
	        if (cart != null) {
	            //判斷有沒有買過該商店
	            if (cart.getStoreMap().get(storeId) != null) {
	                //判斷有沒有買過該商品
	                if (cart.getStoreMap().get(storeId).get(goodsId) != null) {
	                    cartSvc.reduceQty(userId, storeId, goodsId);
	                    //沒買過該商品
	                } else {
	                    cartDetail = new CartDetail();
	                    cartDetail.setGoodsId(goodsId);
	                    cartDetail.setDetailQuantity(1);
	                    cart.getStoreMap().get(storeId).put(cartDetail.getGoodsId(), cartDetail);
	                    cartSvc.put(storeId, cart);
	                }
	                //有買過東西但沒買過該商店
	            } else {
	                cartItemMap = new HashMap<>();
	                cartDetail.setStoreId(storeId);
	                cartDetail.setStoreName(storeName);
	                cartDetail.setGoodsId(goodsId);
	                cartDetail.setDetailQuantity(1);
	                cartItemMap.put(cartDetail.getGoodsId(), cartDetail);
	                cart.getStoreMap().put(cartDetail.getStoreId(), cartItemMap);
	                cartSvc.put(cartDetail.getStoreId(), cart);
	            }
	            //完全沒買過東西
	        } else {
	            cart = new Cart();
	            cartItemMap = new HashMap<>();
	            cart.setMemId(userId);
	            cartDetail.setStoreId(storeId);
	            cartDetail.setStoreName(storeName);
	            cartDetail.setGoodsId(goodsId);
	            cartDetail.setDetailQuantity(1);
	            cartItemMap.put(cartDetail.getGoodsId(), cartDetail);
	            cart.getStoreMap().put(cartDetail.getStoreId(), cartItemMap);
	            cartSvc.put(cartDetail.getStoreId(), cart);
	        }
	    }
}
