package com.goods.model.Cart.pojo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String memId;
    private Integer cartsTotalPrice;

    public String getMemId() {
        return this.memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public Integer getCartsTotalPrice() {
        return cartsTotalPrice;
    }

    public void setCartsTotalPrice(Integer cartsTotalPrice) {
        this.cartsTotalPrice = cartsTotalPrice;
    }

    public Map<Integer, HashMap<Integer, CartDetail>> getStoreMap() {
        return new HashMap<>();
    }


}