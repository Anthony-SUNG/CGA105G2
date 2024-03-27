package com.waiting.model.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.waiting.model.dao.StandbyDAO_interface;
import com.waiting.model.pojo.Standby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandbyDAO extends Common implements StandbyDAO_interface {
    @Override
    public Integer insert(Standby standbyVo) {
        String sql = " INSERT INTO cga105g2.standby(`STORE_ID`, `STA_NAME`, `STA_PHONE`, `STA_NUMBER`) VALUES  (?, ?, ?, ?)";
        int id = 0;
        try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
            pstm.setInt(1, standbyVo.getStoreId());
            pstm.setString(2, standbyVo.getStaName());
            pstm.setString(3, standbyVo.getStaPhone());
            pstm.setInt(4, standbyVo.getStaNumber());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return id;

    }

    @Override
    public void update(Standby standbyVo) {
        String sql = "update cga105g2.standby set sta_status =? where sta_id =?;";
        try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
            pstm.setInt(1, standbyVo.getStaStatus());
            pstm.setInt(2, standbyVo.getStaId());
            pstm.executeUpdate();
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

    //打烊案off清空後位表
    @Override
    public void resetStandBy() {
        String sql = "TRUNCATE TABLE cga105g2.standby;";
        try (java.sql.Statement stm = getConnection().createStatement();) {
            stm.executeUpdate(sql);
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.TRUNCATE_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }


    @Override
    public void delete(Integer staId) {
        String sql = "DELETE FROM cga105g2.`standby` where sta_id = ?";
        try (PreparedStatement pstm = getConnection().prepareStatement(sql);) {
            pstm.setInt(1, staId);
            pstm.executeUpdate();
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
    public Standby findByPrimaryKey(Integer staId) {
        String sql = "select  sta_id ,store_id,sta_name,sta_phone,sta_number,sta_number,sta_time,sta_status from cga105g2.`standby`where STA_ID = ?;";
        Standby standbyVo = null;
        try (PreparedStatement pstm = getConnection().prepareStatement(sql);) {
            pstm.setInt(1, staId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                standbyVo = new Standby();
                standbyVo.setStaId(rs.getInt("sta_Id"));
                standbyVo.setStoreId(rs.getInt("store_Id"));
                standbyVo.setStaName(rs.getString("sta_Name"));
                standbyVo.setStaPhone(rs.getString("sta_Phone"));
                standbyVo.setStaNumber(rs.getInt("sta_Number"));
                standbyVo.setStaTime(rs.getTimestamp("sta_Time"));
                standbyVo.setStaStatus(rs.getInt("sta_Status"));
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
        return standbyVo;
    }

    @Override
    public List<Standby> getAll() {
        String sql = "SELECT `STA_ID`, `STORE_ID`, `STA_NAME`, `STA_PHONE`, `STA_NUMBER`, `STA_TIME`, `STA_STATUS` FROM cga105g2.`standby` where STA_STATUS = 0    order by sta_id";
        List<Standby> list = new ArrayList<>();
        try (PreparedStatement pstm = getConnection().prepareStatement(sql);) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Standby standbyVo = new Standby();
                standbyVo.setStaId(rs.getInt("sta_Id"));
                standbyVo.setStoreId(rs.getInt("store_Id"));
                standbyVo.setStaName(rs.getString("Sta_Name"));
                standbyVo.setStaPhone(rs.getString("sta_Phone"));
                standbyVo.setStaNumber(rs.getInt("sta_Number"));
                standbyVo.setStaTime(rs.getTimestamp("sta_Time"));
                standbyVo.setStaStatus(rs.getInt("sta_Status"));
                list.add(standbyVo);
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
    public Integer standByCount() {
        String sql = "SELECT COUNT(1) FROM cga105g2.STANDBY where sta_status = 0;";
        int staCount = 0;
        try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                staCount = rs.getInt(1);
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
        return staCount;
    }

    @Override
    public Integer getStandbyId() {
        String sql = "SELECT LAST_INSERT_ID();";
        int lastInsertId = 0;
        try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                lastInsertId = rs.getInt(1);
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
        return lastInsertId;
    }

}
