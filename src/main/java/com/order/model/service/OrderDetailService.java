package com.order.model.service;

import com.order.model.OrderDetail.dao.impl.OrderDetailJDBCDAO;
import com.order.model.OrderDetail.pojo.OrderDetail;

import java.util.List;

public class OrderDetailService {
    private final OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();

    public OrderDetail addOrderDetail(Integer orderId, Integer goodsId, Integer detailQuantity, Integer detailPrice) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setGoodsId(goodsId);
        orderDetail.setDetailQuantity(detailQuantity);
        orderDetail.setDetailPrice(detailPrice);
        dao.insert(orderDetail);
        return orderDetail;
    }
    public List<OrderDetail> getAll(Integer orderDetailno) {
        return dao.getAll(orderDetailno);
    }
}
