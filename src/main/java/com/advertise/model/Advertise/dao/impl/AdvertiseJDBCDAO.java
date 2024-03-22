package com.advertise.model.Advertise.dao.impl;


import com.advertise.model.Advertise.dao.Advertise_interface;
import com.advertise.model.Advertise.pojo.Advertise;
import com.core.common.Common;
import com.core.entity.ErrorTitle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdvertiseJDBCDAO extends Common implements Advertise_interface {
    @Override
    public void insert(Advertise pojo) {
        String sql = "INSERT INTO cga105g2.ADVERTISE (STORE_ID, ADV_STATUS, ADV_IMG, ADV_TEXT, ADV_STIME, ADV_NTIME) VALUES ( ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pojo.getStoreId());
            pstmt.setInt(2, pojo.getAdvStatus());
            pstmt.setBytes(3, pojo.getAdvImg());
            pstmt.setString(4, pojo.getAdvText());
            pstmt.setDate(5, pojo.getAdvStime());
            pstmt.setDate(6, pojo.getAdvNtime());
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
    public List<Advertise> getAll() {
        String sql = "SELECT * FROM cga105g2.ADVERTISE";
        List<Advertise> list = new ArrayList<>();


        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Advertise Advertise = new Advertise();
                Advertise.setAdvId(rs.getInt("ADV_ID"));
                Advertise.setStoreId(rs.getInt("STORE_ID"));
                Advertise.setEmpId(rs.getInt("EMP_ID"));
                Advertise.setAdvStatus(rs.getInt("ADV_STATUS"));
                Advertise.setAdvImg(rs.getBytes("ADV_IMG"));
                Advertise.setAdvText(rs.getString("ADV_TEXT"));
                Advertise.setAdvTime(rs.getTimestamp("ADV_TIME"));
                Advertise.setAdvStime(rs.getDate("ADV_STIME"));
                Advertise.setAdvNtime(rs.getDate("ADV_NTIME"));
                list.add(Advertise);
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

    public List<Advertise> getByStatus(Integer advStatus) {
        String sql = "SELECT * FROM cga105g2.ADVERTISE where ADV_STATUS = ?";
        List<Advertise> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, advStatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Advertise Advertise = new Advertise();
                Advertise.setAdvId(rs.getInt("ADV_ID"));
                Advertise.setStoreId(rs.getInt("STORE_ID"));
                Advertise.setEmpId(rs.getInt("EMP_ID"));
                Advertise.setAdvStatus(rs.getInt("ADV_STATUS"));
                Advertise.setAdvImg(rs.getBytes("ADV_IMG"));
                Advertise.setAdvText(rs.getString("ADV_TEXT"));
                Advertise.setAdvTime(rs.getTimestamp("ADV_TIME"));
                Advertise.setAdvStime(rs.getDate("ADV_STIME"));
                Advertise.setAdvNtime(rs.getDate("ADV_NTIME"));
                list.add(Advertise);
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
    public Advertise getByAdvId(Integer advId) {
        String sql = "SELECT * FROM cga105g2.ADVERTISE where ADV_ID = ?";
        Advertise Advertise = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, advId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Advertise = new Advertise();
                Advertise.setAdvId(rs.getInt("ADV_ID"));
                Advertise.setStoreId(rs.getInt("STORE_ID"));
                Advertise.setEmpId(rs.getInt("EMP_ID"));
                Advertise.setAdvStatus(rs.getInt("ADV_STATUS"));
                Advertise.setAdvImg(rs.getBytes("ADV_IMG"));
                Advertise.setAdvText(rs.getString("ADV_TEXT"));
                Advertise.setAdvTime(rs.getTimestamp("ADV_TIME"));
                Advertise.setAdvStime(rs.getDate("ADV_STIME"));
                Advertise.setAdvNtime(rs.getDate("ADV_NTIME"));
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
        return Advertise;
    }

    public Advertise getByStoreId(Integer storeId) {
        String sql = "SELECT * FROM cga105g2.ADVERTISE where STORE_ID = ?";
        Advertise Advertise = null;

        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Advertise = new Advertise();
                Advertise.setAdvId(rs.getInt("ADV_ID"));
                Advertise.setStoreId(rs.getInt("STORE_ID"));
                Advertise.setEmpId(rs.getInt("EMP_ID"));
                Advertise.setAdvStatus(rs.getInt("ADV_STATUS"));
                Advertise.setAdvImg(rs.getBytes("ADV_IMG"));
                Advertise.setAdvText(rs.getString("ADV_TEXT"));
                Advertise.setAdvTime(rs.getTimestamp("ADV_TIME"));
                Advertise.setAdvStime(rs.getDate("ADV_STIME"));
                Advertise.setAdvNtime(rs.getDate("ADV_NTIME"));
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
        return Advertise;
    }

    public void update(Advertise Advertise) {
        String sql = "UPDATE cga105g2.ADVERTISE set EMP_ID=?, ADV_STATUS=? where ADV_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            Advertise Advertise_old = getByAdvId(Advertise.getAdvId());
            pstmt.setInt(1, Advertise_old.getEmpId());
            pstmt.setInt(2, Advertise_old.getAdvStatus());
            pstmt.setInt(3, Advertise_old.getAdvId());
            if (Advertise.getEmpId() != null) {
                pstmt.setInt(1, Advertise.getEmpId());
            }
            if (Advertise.getAdvStatus() != null) {
                pstmt.setInt(2, Advertise.getAdvStatus());
            }
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
    public void delete(Integer advId) {
        String sql = "DELETE FROM cga105g2.ADVERTISE where ADV_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, advId);
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

}
