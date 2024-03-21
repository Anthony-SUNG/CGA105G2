package com.followmem.model.FollowMem.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.followmem.model.FollowMem.dao.FollowMem_interface;
import com.followmem.model.FollowMem.pojo.FollowMem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FollowMemJDBCDAO extends Common implements FollowMem_interface {
    @Override
    public void insert(FollowMem FollowMem) {
        String sql = "INSERT INTO cga105g2.FOLLOW_MEM (MEM_ID1, MEM_ID2) VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, FollowMem.getMemId1());
            pstmt.setInt(2, FollowMem.getMemId2());
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
    public List<FollowMem> getAll() {
        String sql =
                "SELECT * FROM cga105g2.FOLLOW_MEM";
        List<FollowMem> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FollowMem FollowMem = new FollowMem();
                FollowMem.setFollowId(rs.getInt("FOLLOW_ID"));
                FollowMem.setMemId1(rs.getInt("MEM_ID1"));
                FollowMem.setMemId2(rs.getInt("MEM_ID2"));
                list.add(FollowMem);
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
    public FollowMem getByMemId1(Integer memId1) {
        String sql = "SELECT * FROM cga105g2.FOLLOW_MEM where MEM_ID1 = ?";
        FollowMem FollowMem = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FollowMem = new FollowMem();
                FollowMem.setFollowId(rs.getInt("FOLLOW_ID"));
                FollowMem.setMemId1(rs.getInt("MEM_ID1"));
                FollowMem.setMemId2(rs.getInt("MEM_ID2"));
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
        return FollowMem;
    }

    @Override
    public List<FollowMem> getAllByMemId1(Integer memId) {
        String sql = "SELECT * FROM cga105g2.FOLLOW_MEM where MEM_ID1 = ?";
        List<FollowMem> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FollowMem FollowMem = new FollowMem();
                FollowMem.setMemId1(rs.getInt("MEM_ID1"));
                FollowMem.setMemId2(rs.getInt("MEM_ID2"));
                list.add(FollowMem);
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
    public List<FollowMem> getAllByMemId1MemId2(Integer memId1, Integer memId2) {
        String sql = "SELECT * FROM cga105g2.FOLLOW_MEM where MEM_ID1 = ? and MEM_ID2 = ? ";
        List<FollowMem> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId1);
            pstmt.setInt(2, memId2);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FollowMem FollowMem = new FollowMem();
                FollowMem.setMemId1(rs.getInt("MEM_ID1"));
                FollowMem.setMemId2(rs.getInt("MEM_ID2"));
                list.add(FollowMem);
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
    public void delete(Integer memId1, Integer memId2) {
        String sql = "DELETE FROM cga105g2.FOLLOW_MEM where MEM_ID1 = ? and MEM_ID2 = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId1);
            pstmt.setInt(2, memId2);
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


