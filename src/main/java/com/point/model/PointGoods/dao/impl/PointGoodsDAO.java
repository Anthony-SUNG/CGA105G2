package com.point.model.PointGoods.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.point.model.PointGoods.dao.PointGoodsDAO_interface;
import com.point.model.PointGoods.pojo.PointGoods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointGoodsDAO extends Common implements PointGoodsDAO_interface {

    @Override
    public void insert(PointGoods pointgoods) {
        String sql = "INSERT INTO cga105g2.point_goods (PD_IMG, PD_NAME, PD_PRICE, PD_TEXT, PD_TIME, PD_RTIME, PD_STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setBytes(1, pointgoods.getPdImg());
            pstmt.setString(2, pointgoods.getPdName());
            pstmt.setInt(3, pointgoods.getPdPrice());
            pstmt.setString(4, pointgoods.getPdText());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            pstmt.setTimestamp(5, sqlTimestamp);
            pstmt.setTimestamp(6, sqlTimestamp);
            pstmt.setInt(7, pointgoods.getPdStatus());
            pstmt.executeUpdate();
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public void update(PointGoods pointgoods) {
        String sql = "UPDATE cga105g2.point_goods set PD_IMG=?, PD_NAME=?, PD_PRICE=?, PD_TEXT=?, PD_RTIME=?, PD_STATUS=? where PD_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setBytes(1, pointgoods.getPdImg());
            pstmt.setString(2, pointgoods.getPdName());
            pstmt.setInt(3, pointgoods.getPdPrice());
            pstmt.setString(4, pointgoods.getPdText());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            pstmt.setTimestamp(5, sqlTimestamp);
            pstmt.setInt(6, pointgoods.getPdStatus());
            pstmt.setInt(7, pointgoods.getPdId());
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
    public void delete(Integer pdId) {
        String sql = "DELETE FROM cga105g2.point_goods where PD_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pdId);
            pstmt.executeUpdate();
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.DELETE_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public PointGoods getByPK(Integer pdId) {
        PointGoods pointgood = null;
        String sql = "SELECT * FROM cga105g2.point_goods WHERE PD_ID = ? ";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pdId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PointGoods pointgoods = new PointGoods();
                pointgoods.setPdId(rs.getInt("PD_ID"));
                pointgoods.setPdImg(rs.getBytes("PD_IMG"));
                pointgoods.setPdName(rs.getString("PD_NAME"));
                pointgoods.setPdPrice(rs.getInt("PD_PRICE"));
                pointgoods.setPdText(rs.getString("PD_TEXT"));
                pointgoods.setPdTime(rs.getTimestamp("PD_TIME"));
                pointgoods.setPdRtime(rs.getTimestamp("PD_RTIME"));
                pointgoods.setPdStatus(rs.getInt("PD_STATUS"));
                pointgood = pointgoods;
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
        return pointgood;
    }

    @Override
    public List<PointGoods> getAll() {
        List<PointGoods> list = new ArrayList<PointGoods>();
        String sql = "SELECT PD_ID, PD_IMG, PD_NAME, PD_PRICE, PD_TEXT, PD_TIME, PD_RTIME, PD_STATUS FROM cga105g2.point_goods order by PD_ID";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PointGoods pointgoods = new PointGoods();
                pointgoods.setPdId(rs.getInt("PD_ID"));
                pointgoods.setPdImg(rs.getBytes("PD_IMG"));
                pointgoods.setPdName(rs.getString("PD_NAME"));
                pointgoods.setPdPrice(rs.getInt("PD_PRICE"));
                pointgoods.setPdText(rs.getString("PD_TEXT"));
                pointgoods.setPdTime(rs.getTimestamp("PD_TIME"));
                pointgoods.setPdRtime(rs.getTimestamp("PD_RTIME"));
                pointgoods.setPdStatus(rs.getInt("PD_STATUS"));
                list.add(pointgoods);
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
    public List<PointGoods> getAlready() {
        List<PointGoods> list = new ArrayList<PointGoods>();
        String sql = "SELECT PD_ID, PD_IMG, PD_NAME, PD_PRICE, PD_TEXT, PD_TIME, PD_RTIME, PD_STATUS FROM cga105g2.point_goods WHERE PD_STATUS=1 order by PD_ID";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PointGoods pointgoods = new PointGoods();
                pointgoods.setPdId(rs.getInt("PD_ID"));
                pointgoods.setPdImg(rs.getBytes("PD_IMG"));
                pointgoods.setPdName(rs.getString("PD_NAME"));
                pointgoods.setPdPrice(rs.getInt("PD_PRICE"));
                pointgoods.setPdText(rs.getString("PD_TEXT"));
                pointgoods.setPdTime(rs.getTimestamp("PD_TIME"));
                pointgoods.setPdRtime(rs.getTimestamp("PD_RTIME"));
                pointgoods.setPdStatus(rs.getInt("PD_STATUS"));
                list.add(pointgoods);
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

}
