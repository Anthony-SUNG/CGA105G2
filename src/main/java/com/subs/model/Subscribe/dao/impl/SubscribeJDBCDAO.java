package com.subs.model.Subscribe.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.subs.model.Subscribe.dao.Subscribe_interface;
import com.subs.model.Subscribe.pojo.Subscribe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SubscribeJDBCDAO extends Common implements Subscribe_interface {

    @Override
    public void insert(Subscribe Subscribe) {
        String sql =
                "INSERT INTO cga105g2.SUBSCRIBE (STORE_ID, MEM_ID) VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, Subscribe.getStoreId());
            pstmt.setInt(2, Subscribe.getMemId());
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
    public List<Subscribe> getAll() {
        String sql =
                "SELECT * FROM cga105g2.SUBSCRIBE";
        List<Subscribe> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Subscribe Subscribe = new Subscribe();
                Subscribe.setSubId(rs.getInt("SUB_ID"));
                Subscribe.setStoreId(rs.getInt("STORE_ID"));
                Subscribe.setMemId(rs.getInt("MEM_ID"));
                list.add(Subscribe);
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
    public Subscribe getByMemId(Integer memId) {
        String sql =
                "SELECT * FROM cga105g2.SUBSCRIBE where MEM_ID = ?";
        Subscribe Subscribe = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Subscribe = new Subscribe();
                Subscribe.setSubId(rs.getInt("SUB_ID"));
                Subscribe.setStoreId(rs.getInt("STORE_ID"));
                Subscribe.setMemId(rs.getInt("MEM_ID"));
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
        return Subscribe;
    }

    public Subscribe getByMemIdStoreId(Integer storeId, Integer memId) {
        String sql =
                "SELECT * FROM cga105g2.SUBSCRIBE where MEM_ID = ? and STORE_ID = ?";
        Subscribe Subscribe = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storeId);
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Subscribe = new Subscribe();
                Subscribe.setSubId(rs.getInt("SUB_ID"));
                Subscribe.setStoreId(rs.getInt("STORE_ID"));
                Subscribe.setMemId(rs.getInt("MEM_ID"));
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
        return Subscribe;
    }

    public List<Subscribe> getAllByMemId(Integer memId) {
        String sql =
                "SELECT * FROM cga105g2.SUBSCRIBE where MEM_ID = ?";
        List<Subscribe> list = new ArrayList<Subscribe>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Subscribe Subscribe = new Subscribe();
                Subscribe.setSubId(rs.getInt("SUB_ID"));
                Subscribe.setStoreId(rs.getInt("STORE_ID"));
                Subscribe.setMemId(rs.getInt("MEM_ID"));
                list.add(Subscribe);
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

    public List<Subscribe> getAllByMemIdStoreId(Integer storeId, Integer memId) {
        String sql =
                "SELECT * FROM cga105g2.SUBSCRIBE where STORE_ID = ? and MEM_ID = ?";
        List<Subscribe> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storeId);
            pstmt.setInt(2, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Subscribe Subscribe = new Subscribe();
                Subscribe.setStoreId(rs.getInt("STORE_ID"));
                Subscribe.setMemId(rs.getInt("MEM_ID"));
                list.add(Subscribe);
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

    public List<Subscribe> getAllBystoreId(Integer storeId) {
        String sql =
                "SELECT * FROM cga105g2.SUBSCRIBE where STORE_ID = ?";
        List<Subscribe> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Subscribe Subscribe = new Subscribe();
                Subscribe.setSubId(rs.getInt("SUB_ID"));
                Subscribe.setStoreId(rs.getInt("STORE_ID"));
                Subscribe.setMemId(rs.getInt("MEM_ID"));
                list.add(Subscribe);
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
    public void delete(Integer storeId, Integer memId) {
        String sql =
                "DELETE FROM cga105g2.SUBSCRIBE where STORE_ID = ? AND MEM_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storeId);
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
