package com.goods.model.Goods.dao;

import com.core.dao.CoreDao;
import com.goods.model.Goods.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsDAO_interface extends CoreDao<Goods, Integer> {
    public void insert(Goods goods);

    public void update(Goods goods);

    public void deleteById(Integer id);

    public Goods getById(Integer id);

    public List<Goods> getAllGoods(Map<String, String[]> map);

    public List<Goods> getByStoreId(Integer id);
    public List<Goods> getAll();
}
