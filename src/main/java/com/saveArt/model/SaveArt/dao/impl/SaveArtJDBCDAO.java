package com.saveArt.model.SaveArt.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.saveArt.model.SaveArt.dao.SaveArt_interface;
import com.saveArt.model.SaveArt.pojo.SaveArt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SaveArtJDBCDAO extends Common implements SaveArt_interface {
    @Override
    public void insert(SaveArt SaveArt) {
        String sql = "INSERT INTO cga105g2.SAVE_ART (ART_ID, MEM_ID) VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, SaveArt.getArtId());
            pstmt.setInt(2, SaveArt.getMemId());
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
    public List<SaveArt> getAll() {
        String sql = "SELECT * FROM cga105g2.SAVE_ART";
        List<SaveArt> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SaveArt SaveArt = new SaveArt();
                SaveArt.setArtId(rs.getInt("ART_ID"));
                SaveArt.setMemId(rs.getInt("MEM_ID"));
                list.add(SaveArt);
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
    public SaveArt getByMemId(Integer memId) {
        String sql = "SELECT * FROM cga105g2.SAVE_ART where MEM_ID = ?";
        SaveArt SaveArt = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SaveArt = new SaveArt();
                SaveArt.setArtId(rs.getInt("ART_ID"));
                SaveArt.setMemId(rs.getInt("MEM_ID"));
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
        return SaveArt;
    }

    public List<SaveArt> getAllById(Integer memId) {
        String sql = "SELECT * FROM cga105g2.SAVE_ART where MEM_ID = ?";
        List<SaveArt> list = new ArrayList<SaveArt>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SaveArt SaveArt = new SaveArt();
                SaveArt.setArtId(rs.getInt("ART_ID"));
                SaveArt.setMemId(rs.getInt("MEM_ID"));
                list.add(SaveArt);
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
    public void delete(Integer artId, Integer memId) {
        String sql = "DELETE FROM cga105g2.save_art where ART_ID = ? and MEM_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, artId);
            pstmt.setInt(2, memId);
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
