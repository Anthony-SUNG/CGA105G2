package com.point.model.PointOrder.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.point.model.PointOrder.dao.PointOrderDAO_interface;
import com.point.model.PointOrder.pojo.PointOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointOrderDAO extends Common implements PointOrderDAO_interface {

    @Override
    public void insert(PointOrder pointorder) {
        String sql = "INSERT INTO cga105g2.point_order (MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pointorder.getMemId());
            pstmt.setInt(2, pointorder.getPdId());
            pstmt.setInt(3, pointorder.getPoPrice());
            pstmt.setString(4, pointorder.getPoText());
            pstmt.setInt(5, pointorder.getPoStatus());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            pstmt.setTimestamp(6, sqlTimestamp);
            pstmt.executeUpdate();
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
    public void update(PointOrder pointorder) {
        String sql = "UPDATE cga105g2.point_order set MEM_ID=?, PD_ID=?, PO_PRICE=?, PO_TEXT=?, PO_STATUS=?, PO_UTIME=? where PO_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pointorder.getMemId());
            pstmt.setInt(2, pointorder.getPdId());
            pstmt.setInt(3, pointorder.getPoPrice());
            pstmt.setString(4, pointorder.getPoText());
            pstmt.setInt(5, pointorder.getPoStatus());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            pstmt.setTimestamp(6, sqlTimestamp);
            pstmt.setInt(7, pointorder.getPoId());
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

    @Override
    public void delete(Integer po_id) {
        String sql = "DELETE FROM cga105g2.point_order where PO_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, po_id);
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.DELETE_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public PointOrder getByPK(Integer po_id) {
        PointOrder pointorder = null;
        String sql = "SELECT PO_ID, MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME, PO_UTIME, EMP_ID FROM cga105g2.point_order WHERE PO_ID = ? ";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, po_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pointorder = new PointOrder();
                pointorder.setPoId(rs.getInt("PO_ID"));
                pointorder.setMemId(rs.getInt("MEM_ID"));
                pointorder.setPdId(rs.getInt("PD_ID"));
                pointorder.setPoPrice(rs.getInt("PO_PRICE"));
                pointorder.setPoText(rs.getString("PO_TEXT"));
                pointorder.setPoStatus(rs.getInt("PO_STATUS"));
                pointorder.setPoTime(rs.getTimestamp("PO_TIME"));
                pointorder.setPoUtime(rs.getTimestamp("PO_UTIME"));
                pointorder.setEmpId(rs.getInt("EMP_ID"));
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
        return pointorder;
    }

    @Override
    public List<PointOrder> getAll() {
        List<PointOrder> list = new ArrayList<PointOrder>();
        String sql = "SELECT PO_ID, MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME, PO_UTIME, EMP_ID FROM cga105g2.point_order order by PO_ID";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PointOrder pointorder = new PointOrder();
                pointorder.setPoId(rs.getInt("PO_ID"));
                pointorder.setMemId(rs.getInt("MEM_ID"));
                pointorder.setPdId(rs.getInt("PD_ID"));
                pointorder.setPoPrice(rs.getInt("PO_PRICE"));
                pointorder.setPoText(rs.getString("PO_TEXT"));
                pointorder.setPoStatus(rs.getInt("PO_STATUS"));
                pointorder.setPoTime(rs.getTimestamp("PO_TIME"));
                pointorder.setPoUtime(rs.getTimestamp("PO_UTIME"));
                pointorder.setEmpId(rs.getInt("EMP_ID"));
                list.add(pointorder);
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
    public List<PointOrder> getBackOrder() {
        List<PointOrder> list = new ArrayList<PointOrder>();
        String sql = "SELECT PO_ID, MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME, PO_UTIME, EMP_ID FROM cga105g2.point_order WHERE PO_STATUS = 0";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PointOrder pointorder = new PointOrder();
                pointorder.setPoId(rs.getInt("PO_ID"));
                pointorder.setMemId(rs.getInt("MEM_ID"));
                pointorder.setPdId(rs.getInt("PD_ID"));
                pointorder.setPoPrice(rs.getInt("PO_PRICE"));
                pointorder.setPoText(rs.getString("PO_TEXT"));
                pointorder.setPoStatus(rs.getInt("PO_STATUS"));
                pointorder.setPoTime(rs.getTimestamp("PO_TIME"));
                pointorder.setPoUtime(rs.getTimestamp("PO_UTIME"));
                pointorder.setEmpId(rs.getInt("EMP_ID"));
                list.add(pointorder);
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
    public void updateStatus(PointOrder pointorder) {
        String sql = "UPDATE cga105g2.point_order set PO_STATUS=?, PO_UTIME=? where PO_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pointorder.getPoStatus());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            pstmt.setTimestamp(2, sqlTimestamp);
            pstmt.setInt(3, pointorder.getPoId());
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

    public List<PointOrder> getMemOrder(Integer memId) {
        List<PointOrder> list = new ArrayList<PointOrder>();
        String sql = "SELECT PO_ID, MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME, PO_UTIME, EMP_ID FROM cga105g2.point_order WHERE MEM_ID= ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PointOrder pointorder = new PointOrder();
                pointorder.setPoId(rs.getInt("PO_ID"));
                pointorder.setMemId(rs.getInt("MEM_ID"));
                pointorder.setPdId(rs.getInt("PD_ID"));
                pointorder.setPoPrice(rs.getInt("PO_PRICE"));
                pointorder.setPoText(rs.getString("PO_TEXT"));
                pointorder.setPoStatus(rs.getInt("PO_STATUS"));
                pointorder.setPoTime(rs.getTimestamp("PO_TIME"));
                pointorder.setPoUtime(rs.getTimestamp("PO_UTIME"));
                pointorder.setEmpId(rs.getInt("EMP_ID"));
                list.add(pointorder);
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

}
