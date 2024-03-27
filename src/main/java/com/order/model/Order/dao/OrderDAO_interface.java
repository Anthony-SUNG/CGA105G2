package com.order.model.Order.dao;

import com.code.model.Code.pojo.Code;
import com.core.dao.CoreDao;
import com.order.model.Order.pojo.Order;
import com.order.model.OrderDetail.pojo.OrderDetail;

import java.util.List;

public interface OrderDAO_interface extends CoreDao<Order, Integer> {
    public void insert(Order order);

    public void update(Order order);

    public void deleteById(Integer id);

    public Order getById(Integer orderId);

    //查詢優惠券是否有效以及折扣為多少
    public Code getCodeDiscount(String codeNum);

    public List<Order> getByMemId(Integer memId);

    public List<Order> getAll();

}