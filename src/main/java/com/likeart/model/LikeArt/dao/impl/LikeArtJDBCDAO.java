package com.likeart.model.LikeArt.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.likeart.model.LikeArt.dao.LikeArt_interface;
import com.likeart.model.LikeArt.pojo.LikeArt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LikeArtJDBCDAO extends Common implements LikeArt_interface {
    @Override
    public void insert(LikeArt LikeArt) {
        String sql = "INSERT INTO cga105g2.LIKE_ART (ART_ID, MEM_ID) VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, LikeArt.getArtId());
            pstmt.setInt(2, LikeArt.getMemId());
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
    public List<LikeArt> getAll() {
        String sql = "SELECT * FROM cga105g2.LIKE_ART";
        List<LikeArt> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LikeArt LikeArt = new LikeArt();
                LikeArt.setLikeId(rs.getInt("LIKE_ID"));
                LikeArt.setArtId(rs.getInt("ART_ID"));
                LikeArt.setMemId(rs.getInt("MEM_ID"));
                list.add(LikeArt);
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }

    @Override
    public LikeArt getById(Integer ArtId) {
        String sql = "SELECT * FROM cga105g2.LIKE_ART where ART_ID = ?";
        LikeArt LikeArt = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, ArtId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LikeArt = new LikeArt();
                LikeArt.setLikeId(rs.getInt("LIKE_ID"));
                LikeArt.setArtId(rs.getInt("ART_ID"));
                LikeArt.setMemId(rs.getInt("MEM_ID"));
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return LikeArt;
    }

    @Override
    public void delete(Integer likeId) {
        String sql = "DELETE FROM cga105g2.LIKE_ART where LIKE_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, likeId);
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
}
