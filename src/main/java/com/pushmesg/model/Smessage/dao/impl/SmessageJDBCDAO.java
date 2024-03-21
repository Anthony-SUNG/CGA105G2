package com.pushmesg.model.Smessage.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.pushmesg.model.Smessage.dao.SmessageDAO_interface;
import com.pushmesg.model.Smessage.pojo.Smessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SmessageJDBCDAO extends Common implements SmessageDAO_interface {
    @Override
    public void insert(Smessage smessageVO) {
        String sql = "INSERT INTO cga105g2.smessage (SUB_ID, SMESSAGE_TXET) VALUES (?,?);";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, smessageVO.getSubId());
            pstmt.setString(2, smessageVO.getSmessageTxet());
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
    public List<Smessage> getById(Integer id) {
        String sql = "SELECT * FROM cga105g2.smessage where SUB_ID=? order by SMESSAGE_TIME";
        Smessage smessage = null;
        List<Smessage> list = new ArrayList<Smessage>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                smessage = new Smessage();
                smessage.setSmessageId(rs.getInt("SMESSAGE_ID"));
                smessage.setSubId(rs.getInt("SUB_ID"));
                smessage.setSmessageTxet(rs.getString("SMESSAGE_TXET"));
                smessage.setSmessageTime(rs.getTimestamp("SMESSAGE_TIME"));
                list.add(smessage);
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

    public void deleteById(Integer id) {
        String sql = "DELETE from cga105g2.smessage where SMESSAGE_ID=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
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

}
