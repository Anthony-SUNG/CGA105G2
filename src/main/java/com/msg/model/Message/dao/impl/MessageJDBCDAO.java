package com.msg.model.Message.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.msg.model.Message.dao.Message_interface;
import com.msg.model.Message.pojo.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MessageJDBCDAO extends Common implements Message_interface {
    @Override
    public void insert(Message Message) {
        String sql = "INSERT INTO cga105g2.MESSAGE (FOLLOW_ID, MES_TEXT) VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, Message.getFollowId());
            pstmt.setString(2, Message.getMesText());
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
    public List<Message> getAll() {
        String sql = "SELECT * FROM cga105g2.MESSAGE";
        List<Message> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Message Message = new Message();
                Message.setMesId(rs.getInt("MES_ID"));
                Message.setFollowId(rs.getInt("FOLLOW_ID"));
                Message.setMesText(rs.getString("MES_TEXT"));
                Message.setMesTime(rs.getTimestamp("MES_TIME"));
                list.add(Message);
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
    public Message getByFollowId(Integer followId) {
        String sql = "SELECT * FROM cga105g2.MESSAGE where FOLLOW_ID = ?";
        Message Message = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, followId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Message = new Message();
                Message.setMesId(rs.getInt("MES_ID"));
                Message.setFollowId(rs.getInt("FOLLOW_ID"));
                Message.setMesText(rs.getString("MES_TEXT"));
                Message.setMesTime(rs.getTimestamp("MES_TIME"));
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
        return Message;
    }

    public List<Message> getAllByFollowId(Integer followId) {
        String sql = "SELECT * FROM cga105g2.MESSAGE where FOLLOW_ID = ?";
        List<Message> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, followId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Message Message = new Message();
                Message.setMesText(rs.getString("MES_TEXT"));
                Message.setMesTime(rs.getTimestamp("MES_TIME"));
                list.add(Message);
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
    public void delete(Integer MesId) {
        String sql = "DELETE FROM cga105g2.MESSAGE where MES_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, MesId);
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
