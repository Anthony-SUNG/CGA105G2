package com.order.model.service;

import com.goods.model.Cart.dao.impl.CartDetailRedis;
import com.goods.model.Cart.pojo.Cart;
import com.goods.model.Goods.dao.impl.GoodsJDBCDAO;
import com.goods.model.Goods.pojo.Goods;
import com.order.model.Order.dao.impl.OrderJDBCDAO;
import com.order.model.Order.pojo.Order;
import com.order.model.OrderDetail.dao.impl.OrderDetailJDBCDAO;
import com.order.model.OrderDetail.pojo.OrderDetail;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {
    private final OrderJDBCDAO dao = new OrderJDBCDAO();
    private final OrderDetailService orderDetailService = new OrderDetailService();

    public void addOrder(Cart cart) {
        cart.getStoreMap().forEach((storeId, cartItemMap) -> {
            OrderJDBCDAO orderDao = new OrderJDBCDAO();
            Order order = new Order();
            AtomicInteger storeTotalPrc = new AtomicInteger();
            cartItemMap.forEach((goodsId, cartItem) -> storeTotalPrc.getAndAdd(cartItem.getGoodsTotalPrice()));


            order.setMemId(Integer.valueOf(cart.getMemId()));
            order.setStoreId(storeId);
            order.setOrderFprice(storeTotalPrc.get());
            order.setOrderTime(new Timestamp(System.currentTimeMillis()));
            order.setOrderStatus(1);
            orderDao.insert(order);

            cartItemMap.forEach((goodsId, cartItem) -> orderDetailService.addOrderDetail(orderDao.getInsertId(), goodsId, cartItem.getDetailQuantity(), cartItem.getGoodsTotalPrice()));
        });
    }

    public void toReFish(List<String> goodList, List<String> qList, Map<Integer, List<OrderDetail>> orderDetailMap, List<String> stroeIdList) {
        for (int i = 0; i < goodList.size(); i++) {
            GoodsJDBCDAO goodsJDBCDAO = new GoodsJDBCDAO();
            Goods goods = goodsJDBCDAO.getById(Integer.valueOf(goodList.get(i)));
            Integer storeId = goods.getStoreId();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setGoodsId(goods.getGoodsId());
            orderDetail.setDetailPrice(goods.getGoodsPrice());
            orderDetail.setDetailQuantity(Integer.valueOf(qList.get(i)));

            if (orderDetailMap.get(storeId) == null) {
                List<OrderDetail> orderDetails = new ArrayList<>();
                orderDetails.add(orderDetail);
                orderDetailMap.put(storeId, orderDetails);
            } else {
                orderDetailMap.get(storeId).add(orderDetail);
            }
            stroeIdList.add(String.valueOf(storeId));
        }
    }

    public void insertWithDetail(List<String> stroeIdList, Map<Integer, List<OrderDetail>> orderDetailMap, Integer memId) {
        for (String storeIdTxt : stroeIdList) {
            Integer storeId = Integer.valueOf(storeIdTxt);
            //總金額
            int tallPrice = 0;
            List<OrderDetail> storeIdBuyList = orderDetailMap.get(storeId);
            for (OrderDetail detail : storeIdBuyList)
                tallPrice += (detail.getDetailPrice() * detail.getDetailQuantity());
            Order order = new Order();
            order.setMemId(memId);
            order.setStoreId(storeId);
            order.setOrderPrice(tallPrice);
            //運費
            order.setOrderFre(80);
            order.setOrderFprice(tallPrice + order.getOrderFre());
            //狀態
            order.setOrderStatus(0);
            //新增資料
            OrderJDBCDAO orderDao = new OrderJDBCDAO();
            orderDao.insert(order);
            OrderDetailJDBCDAO detailDao = new OrderDetailJDBCDAO();
            detailDao.insertAll(orderDao.getInsertId(), storeIdBuyList);
            CartDetailRedis cartSvc = new CartDetailRedis();
            Cart cart = cartSvc.get(String.valueOf(memId));
            for (OrderDetail j : storeIdBuyList) cart.getStoreMap().get(storeId).remove(j.getGoodsId());
            cartSvc.put(storeId, cart);
        }
    }

    public List<Order> getByMemId(Integer memId) {
        return dao.getByMemId(memId);
    }

    public Order getOneOrder(Integer ordId) {
        return dao.getById(ordId);
    }


    public List<Order> getAll() {
        return dao.getAll();
    }
}
