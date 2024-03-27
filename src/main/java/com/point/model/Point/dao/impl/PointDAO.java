package com.point.model.Point.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.point.model.Point.dao.PointDAO_interface;
import com.point.model.Point.pojo.Point;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointDAO extends Common implements PointDAO_interface {
    @Override
    public void insert(Point point) {
        String sql = "INSERT INTO cga105g2.point (MEM_ID, POINT_CHANGE, POINT_NUMBER) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, point.getMemId());
            pstmt.setString(2, point.getPointChange());
            pstmt.setInt(3, point.getPointNumber());
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
    public void update(Point point) {
        String sql = "UPDATE cga105g2.point set POINT_CHANGE=?, POINT_NUMBER=? where POINT_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, point.getPointChange());
            pstmt.setInt(2, point.getPointNumber());
            pstmt.setInt(3, point.getPointId());
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
    public void delete(Integer pointid) {
        String sql = "DELETE FROM cga105g2.point where POINT_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pointid);
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
    public Point getByPK(Integer pointid) {
        Point point = null;
        String sql = "SELECT POINT_ID, MEM_ID, POINT_CHANGE, POINT_NUMBER FROM cga105g2.point WHERE POINT_ID = ? ";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pointid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                point = new Point();
                point.setPointId(rs.getInt("POINT_ID"));
                point.setMemId(rs.getInt("MEM_ID"));
                point.setPointChange(rs.getString("POINT_CHANGE"));
                point.setPointNumber(rs.getInt("POINT_NUMBER"));
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
        return point;
    }

    @Override
    public List<Point> getAll() {
        List<Point> list = new ArrayList<>();
        String sql = "SELECT POINT_ID, MEM_ID, POINT_CHANGE, POINT_NUMBER FROM cga105g2.point order by POINT_ID ";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Point point = new Point();
                point.setPointId(rs.getInt("POINT_ID"));
                point.setMemId(rs.getInt("MEM_ID"));
                point.setPointChange(rs.getString("POINT_CHANGE"));
                point.setPointNumber(rs.getInt("POINT_NUMBER"));
                list.add(point);
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
    public List<Point> getAllByMemId(Integer memId) {
        List<Point> list = new ArrayList<>();
        String sql = "SELECT POINT_ID, MEM_ID, POINT_CHANGE, POINT_NUMBER FROM cga105g2.point WHERE MEM_ID = ? ";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Point point = new Point();
                point.setPointId(rs.getInt("POINT_ID"));
                point.setMemId(rs.getInt("MEM_ID"));
                point.setPointChange(rs.getString("POINT_CHANGE"));
                point.setPointNumber(rs.getInt("POINT_NUMBER"));
                list.add(point);
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
