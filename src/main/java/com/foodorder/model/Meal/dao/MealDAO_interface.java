package com.foodorder.model.Meal.dao;

import com.core.dao.CoreDao;
import com.foodorder.model.Meal.pojo.Meal;

import java.util.List;


public interface MealDAO_interface extends CoreDao<Meal, Integer> {
    public void insert(Meal mealVO);

    // 更改餐點 要先將原本餐點下架 新增餐點上架
    public void update(Integer id, Integer status);

    public void update(Meal pojo);

    public void deleteById(Integer id);

    //在byId的情況下 status : 0(下架) 1(上架) 的查詢
    public List<Meal> getByStoreIdStatus(Integer id, Integer status);

    // 如果會員訂位查詢要帶出餐點名稱 訂位id->所有餐點id->不管上架下架都要秀
    public Meal getById(Integer id);

    public List<Meal> getAll();

    void MealUpdateCommit(Meal mealVO, Integer mealid);
}
