package com.order.model.Order.dao.impl;


import com.code.model.Code.pojo.Code;
import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.order.model.Order.dao.OrderDAO_interface;
import com.order.model.Order.pojo.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderJDBCDAO extends Common implements OrderDAO_interface {
    private int insertId = 0;
    public int getInsertId() {
        return insertId;
    }

    @Override
    public void insert(Order order) {
        String sql = "INSERT INTO cga105g2.order (mem_id,store_id,order_price,order_fre,order_fprice) VALUES ( ? ,?, ?, ?, ? )";
        int orderId = 0;
        String[] cols = {"ORDER_ID"};
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, cols)) {
            pstmt.setInt(1, order.getMemId());
            pstmt.setInt(2, order.getStoreId());
            pstmt.setInt(3, order.getOrderPrice());
            pstmt.setInt(4, order.getOrderFre());
            pstmt.setInt(5, order.getOrderFprice());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) orderId = rs.getInt(1);
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        logger.info(String.format("新增訂單成功,訂單編號=%s", orderId));
        this.insertId = orderId;
    }


    @Override
    public void update(Order order) {
        final String sql = "UPDATE cga105g2.order SET mem_id=?,store_id=?,order_price=?,code_id=?,order_fre=?,order_fprice=?,order_text=?,order_status=?,order_otime=? WHERE order_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, order.getMemId());
            pstmt.setInt(2, order.getStoreId());
            pstmt.setInt(3, order.getOrderPrice());
            pstmt.setInt(4, order.getCodeId());
            pstmt.setInt(5, order.getOrderFre());
            pstmt.setInt(6, order.getOrderFprice());
            pstmt.setString(7, order.getOrderText());
            pstmt.setInt(8, order.getOrderStatus());
            pstmt.setDate(9, order.getOrderOtime());
            pstmt.setInt(10, order.getOrderId());
            pstmt.executeUpdate();
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        Common common = new Common();
        String sql = "DELETE FROM cga105g2.order_detail where order_id = ?; "
                + "DELETE FROM cga105g2.order where order_id = ?;";
        try (PreparedStatement pstmt = common.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            common.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                common.getConnection().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }

    }


    @Override
    public Order getById(Integer id) {
        final String sql = "SELECT order_id,mem_id,store_id,order_price,code_id,order_fre,order_fprice,order_text,order_status,order_time,order_otime,order_rtime FROM cga105g2.order where order_id = ?";
        Order order = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setMemId(rs.getInt("mem_id"));
                order.setStoreId(rs.getInt("store_id"));
                order.setOrderPrice(rs.getInt("order_price"));
                order.setCodeId(rs.getInt("code_id"));
                order.setOrderFre(rs.getInt("order_fre"));
                order.setOrderFprice(rs.getInt("order_fprice"));
                order.setOrderText(rs.getString("order_text"));
                order.setOrderStatus(rs.getInt("order_status"));
                order.setOrderTime(rs.getTimestamp("order_time"));
                order.setOrderOtime(rs.getDate("order_otime"));
                order.setOrderRtime(rs.getTimestamp("order_rtime"));
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return order;
    }

    @Override
    public List<Order> getByMemId(Integer memId) {
        String sql = "SELECT * FROM cga105g2.order WHERE mem_id = ?";
        List<Order> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setMemId(rs.getInt("mem_id"));
                order.setStoreId(rs.getInt("store_id"));
                order.setOrderPrice(rs.getInt("order_price"));
                order.setCodeId(rs.getInt("code_id"));
                order.setOrderFre(rs.getInt("order_fre"));
                order.setOrderFprice(rs.getInt("order_fprice"));
                order.setOrderText(rs.getString("order_text"));
                order.setOrderStatus(rs.getInt("order_status"));
                order.setOrderTime(rs.getTimestamp("order_time"));
                order.setOrderOtime(rs.getDate("order_otime"));
                order.setOrderRtime(rs.getTimestamp("order_rtime"));
                list.add(order);
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return list;
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM cga105g2.order ";
        List<Order> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setMemId(rs.getInt("mem_id"));
                order.setStoreId(rs.getInt("store_id"));
                order.setOrderPrice(rs.getInt("order_price"));
                order.setOrderStatus(rs.getInt("order_status"));
                list.add(order);
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return list;
    }

    @Override
    public Code getCodeDiscount(String codeNum) {
        final String sql = "SELECT * FROM cga105g2.code  WHERE  code_num  = ? ";
        Code code = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, codeNum);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                code = new Code();
                code.setCodeId(rs.getInt("code_id"));
                code.setStoreId(rs.getInt("store_id"));
                code.setEmpId(rs.getInt("emp_id"));
                code.setCodeNum(rs.getString("code_num"));
                code.setCodeOff(rs.getInt("code_off"));
                code.setCodeStatus(rs.getInt("code_status"));
                code.setCodeText(rs.getString("code_text"));
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return code;
    }
}
