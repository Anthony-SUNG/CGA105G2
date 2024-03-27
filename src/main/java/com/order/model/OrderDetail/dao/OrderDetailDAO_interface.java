package com.order.model.OrderDetail.dao;

import java.util.List;
import java.util.Map;

import com.order.model.OrderDetail.pojo.OrderDetail;


public interface OrderDetailDAO_interface {
	public void insert(OrderDetail orderDetail);
    public void update(OrderDetail orderDetail);
    public void delete(Integer orderDetailno,Integer orderDetailno2);
    public OrderDetail getById(Integer orderDetailno, Integer orderDetailno2);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<OrderDetail> getAll(Map<String, String[]> map);
	List<OrderDetail> getAll(Integer orderDetailno);
}

    
  

