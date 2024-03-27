package com.goods.model.service;

import com.goods.model.Goods.dao.GoodsDAO_interface;
import com.goods.model.Goods.dao.impl.GoodsJDBCDAO;
import com.goods.model.Goods.pojo.Goods;

import java.util.List;
import java.util.Map;


public class GoodsService {

    private GoodsDAO_interface dao;

    public GoodsService() {
        dao = new GoodsJDBCDAO();
    }

    public Goods addGoods(Integer storeId, byte[] goodsImg, String goodsName, Integer goodsStatus, Integer goodsPrice, String goodsText) {
        Goods goods = new Goods();
        goods.setStoreId(storeId);
        goods.setGoodsImg(goodsImg);
        goods.setGoodsName(goodsName);
        goods.setGoodsStatus(goodsStatus);
        goods.setGoodsPrice(goodsPrice);
        goods.setGoodsText(goodsText);
        dao.insert(goods);

        return goods;
    }

    public Goods updateGoods(Integer goodsId, Integer storeId, byte[] goodsImg, String goodsName, Integer goodsStatus, Integer goodsPrice, String goodsText) {
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setStoreId(storeId);
        goods.setGoodsImg(goodsImg);
        goods.setGoodsName(goodsName);
        goods.setGoodsStatus(goodsStatus);
        goods.setGoodsPrice(goodsPrice);
        goods.setGoodsText(goodsText);
        dao.update(goods);
        return goods;
    }

    public void deleteGoods(Integer id) {
        dao.deleteById(id);
    }

    public Goods getOneGoods(Integer id) {
        return dao.getById(id);
    }

    public List<Goods> getAll(Integer storeId) {
        return dao.getByStoreId(storeId);
    }

    public List<Goods> getAllGoods(Map<String, String[]> map) {
        return dao.getAllGoods(map);
    }
}