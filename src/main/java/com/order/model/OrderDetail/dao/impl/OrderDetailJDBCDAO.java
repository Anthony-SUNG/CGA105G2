package com.order.model.OrderDetail.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.order.model.OrderDetail.dao.OrderDetailDAO_interface;
import com.order.model.OrderDetail.pojo.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDetailJDBCDAO extends Common implements OrderDetailDAO_interface {

    @Override
    public void insert(OrderDetail orderDetail) {
        final String sql = "INSERT INTO cga105g2.order_detail (order_id,goods_id,detail_quantity) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setInt(2, orderDetail.getGoodsId());
            pstmt.setInt(3, orderDetail.getDetailQuantity());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public void update(OrderDetail orderDetail) {
        final String sql = "UPDATE cga105g2.order_detail set order_id=?, goods_id=?, detail_quantity=? where order_id = ? AND goods_id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setInt(2, orderDetail.getGoodsId());
            pstmt.setInt(3, orderDetail.getDetailQuantity());
            pstmt.setInt(4, orderDetail.getOrderId());
            pstmt.setInt(5, orderDetail.getGoodsId());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public void delete(Integer orderDetailVO, Integer orderDetailVO2) {
        final String sql = "DELETE FROM cga105g2.order_detail where order_id = ? AND goods_id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, orderDetailVO);
            pstmt.setInt(2, orderDetailVO2);
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public OrderDetail getById(Integer orderDetailno, Integer orderDetailno2) {
        final String sql = "SELECT order_id,goods_id,detail_quantity,detailprice FROM cga105g2.order_detail where order_id = ? AND goods_id=? order by order_id";
        OrderDetail orderDetail = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, orderDetailno);
            pstmt.setInt(2, orderDetailno2);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setGoodsId(rs.getInt("goods_id"));
                orderDetail.setDetailQuantity(rs.getInt("detail_quantity"));
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> getAll(Integer orderId) {
        final String sql = "SELECT order_id,goods_id,detail_quantity,detailprice FROM cga105g2.order_detail WHERE order_id = ?";
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setGoodsId(rs.getInt("goods_id"));
                orderDetail.setDetailQuantity(rs.getInt("detail_quantity"));
                orderDetail.setDetailPrice(rs.getInt("detailprice"));
                list.add(orderDetail); // Store the row in the list
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return list;
    }

    @Override
    public List<OrderDetail> getAll(Map<String, String[]> map) {
        // TODO Auto-generated method stub
        return null;
    }

    private static final String INSERT_STMT = "INSERT INTO cga105g2.order_detail (order_id,goods_id,detail_quantity,detailPrice) VALUES (?, ?, ?, ?)";

    @Override
    public void insert2(OrderDetail orderDetail) {
        PreparedStatement pstmt;
        try {
            pstmt = getConnection().prepareStatement(INSERT_STMT);
            logger.info("orderId:" + orderDetail.getOrderId());
            logger.info("orderDetail:" + orderDetail.getGoodsId());
            logger.info("--------");
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setInt(2, orderDetail.getGoodsId());
            pstmt.setInt(3, orderDetail.getDetailQuantity());
            pstmt.setInt(4, orderDetail.getDetailPrice());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(INSERT_STMT), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(INSERT_STMT), r);
            }
        }
    }

}
