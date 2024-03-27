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

@WebServlet("/cart/add")
public class AddCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final Integer storeId = Integer.valueOf(request.getParameter("storeId"));
        final Integer goodsId = Integer.valueOf(request.getParameter("goodsId"));
        String memId = String.valueOf(request.getSession().getAttribute("memId"));
        CartDetailRedis cartSvc = new CartDetailRedis();
        Cart cart = cartSvc.get(memId);
        StoreService storeService = new StoreService();
        String storeName = storeService.getById(storeId).getStoreName();
        CartDetail cartDetail = new CartDetail();
        HashMap<Integer, CartDetail> itemMap;
        //判斷有沒有購物車
        if (cart != null) {
            if (cart.getStoreMap().get(storeId) != null) {
                if (cart.getStoreMap().get(storeId).get(goodsId) != null) {
                    cartSvc.addQty(memId, storeId, goodsId);
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
                itemMap = new HashMap();
                cartDetail.setStoreId(storeId);
                cartDetail.setStoreName(storeName);
                cartDetail.setGoodsId(goodsId);
                cartDetail.setDetailQuantity(1);
                itemMap.put(cartDetail.getGoodsId(), cartDetail);
                cart.getStoreMap().put(cartDetail.getStoreId(), itemMap);
                cartSvc.put(cartDetail.getStoreId(), cart);
            }
            //完全沒買過東西
        } else {
            cart = new Cart();
            itemMap = new HashMap();
            cart.setMemId(memId);
            cartDetail.setStoreId(storeId);
            cartDetail.setStoreName(storeName);
            cartDetail.setGoodsId(goodsId);
            cartDetail.setDetailQuantity(1);
            itemMap.put(cartDetail.getGoodsId(), cartDetail);
            cart.getStoreMap().put(cartDetail.getStoreId(), itemMap);
            cartSvc.put(cartDetail.getStoreId(), cart);
        }
    }
}