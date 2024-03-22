package com.order.model.Order.dao.impl;


import com.code.model.Code.pojo.Code;
import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.order.model.Order.dao.OrderDAO_interface;
import com.order.model.Order.pojo.Order;
import com.order.model.OrderDetail.dao.impl.OrderDetailJDBCDAO;
import com.order.model.OrderDetail.pojo.OrderDetail;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderJDBCDAO extends Common implements OrderDAO_interface {

    private static DataSource ds = null;

    @Override
    public void insert(Order order) {
        final String sql = "INSERT INTO cga105g2.order (mem_id,store_id,order_price,code_id,order_fre,order_fprice,order_text) VALUES (?, ? ,?, ?, ?, ?, ? )";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, order.getMemId());
            pstmt.setInt(2, order.getStoreId());
            pstmt.setInt(3, order.getOrderPrice());
            pstmt.setInt(4, order.getCodeId());
            pstmt.setInt(5, order.getOrderFre());
            pstmt.setInt(6, order.getOrderFprice());
            pstmt.setString(7, order.getOrderText());
            pstmt.executeUpdate();
            // 新增訂單明細
            OrderDetailJDBCDAO orderDetailJDBCDAO = new OrderDetailJDBCDAO();
            orderDetailJDBCDAO.insert(null);
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
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
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    private static final String DELETE_OEDER = "DELETE FROM cga105g2.order where order_id = ?";
    private static final String DELETE_DETAILs = "DELETE FROM cga105g2.order_detail where order_id = ?";

    @Override
    public void delete(Integer orderno) {
        int updateCount_ORDERs;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            pstmt1 = getConnection().prepareStatement(DELETE_DETAILs);
            pstmt2 = getConnection().prepareStatement(DELETE_OEDER);
            pstmt1.setInt(1, orderno);
            updateCount_ORDERs = pstmt1.executeUpdate();
            pstmt2.setInt(1, orderno);
            pstmt2.executeUpdate();
            logger.info("刪除部門編號" + orderno + "時,共有員工" + updateCount_ORDERs + "人同時被刪除");
            pstmt1.getConnection().commit();
            pstmt2.getConnection().commit();
            pstmt1.getConnection().close();
            pstmt2.getConnection().close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(DELETE_OEDER + "," + DELETE_DETAILs), se);
            try {
                if (pstmt1 != null) {
                    pstmt1.getConnection().rollback();
                }
                if (pstmt2 != null) {
                    pstmt2.getConnection().rollback();
                }
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(DELETE_OEDER + "," + DELETE_DETAILs), r);
            }
        }

    }


    @Override
    public Order getById(Integer orderno) {
        final String sql = "SELECT order_id,mem_id,store_id,order_price,code_id,order_fre,order_fprice,order_text,order_status,order_time,order_otime,order_rtime FROM cga105g2.order where order_id = ?";
        Order order = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, orderno);
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
        return order;
    }

    @Override
    public List<Order> getByMemId(Integer memId) {
        final String sql = "SELECT order_id,mem_id,store_id,order_price,code_id,order_fre,order_fprice,order_text,order_status,order_time,order_otime,order_rtime FROM cga105g2.order WHERE mem_id = ?";
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
    public Set<Order> getOrderByStoreId(Integer storeId) {
        return null;
    }

    @Override
    public void updateOrdStat(Order order) {

    }

    @Override
    public Integer genOrderId() {
        return null;
    }

    private static final String INSERT_STMT = "INSERT INTO cga105g2.order (mem_id,store_id,order_price,order_fre,order_fprice) VALUES ( ? ,?, ?, ?, ? )";

    @Override
    public void insertWithDetail(Order order, List<OrderDetail> list) {
        PreparedStatement pstmt;
        String next_orderId = null;
        try {
            pstmt = getConnection().prepareStatement(INSERT_STMT);
            pstmt.setInt(1, order.getMemId());
            pstmt.setInt(2, order.getStoreId());
            pstmt.setInt(3, order.getOrderPrice());
            pstmt.setInt(4, order.getOrderFre());
            pstmt.setInt(5, order.getOrderFprice());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                next_orderId = rs.getString(1);
                logger.info("自增主鍵值= " + next_orderId + "(剛新增成功的訂單編號)");
            } else {
                logger.error("未取得自增主鍵值");
            }
            rs.close();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(INSERT_STMT), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(INSERT_STMT), r);
            }
        }
        // 再同時新增員工
        OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
        logger.info("list.size()-A=" + list.size());
        for (OrderDetail aOrderDetail : list) {
            if (next_orderId != null) aOrderDetail.setOrderId(Integer.valueOf(next_orderId));
            dao.insert2(aOrderDetail);
        }
        logger.info("list.size()-B=" + list.size());
        logger.info("新增" + next_orderId + "時,共有明細" + list.size() + "條同時被新增");
    }

    @Override
    public Code checkCodeDiscount(String codeNum) {
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
        return code;
    }
}
