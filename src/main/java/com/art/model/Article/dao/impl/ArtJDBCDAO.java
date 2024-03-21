package com.art.model.Article.dao.impl;

import com.art.model.Article.dao.ArtDAO_interface;
import com.art.model.Article.pojo.Article;
import com.core.common.Common;
import com.core.entity.ErrorTitle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtJDBCDAO extends Common implements ArtDAO_interface {
    @Override
    public void insert(Article artVO) {
        String sql = "INSERT INTO cga105g2.ARTICLE (MEM_ID, STORE_ID, ART_HEADER, ART_TEXT, ART_IMG, ART_SCORE, ART_TAG) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, artVO.getMemId());
            pstmt.setInt(2, artVO.getStoreId());
            pstmt.setString(3, artVO.getArtHeader());
            pstmt.setString(4, artVO.getArtText());
            pstmt.setBytes(5, artVO.getArtImg());
            pstmt.setInt(6, artVO.getArtScore());
            pstmt.setString(7, artVO.getArtTag());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
    }

    @Override
    public List<Article> getAll() {
        String sql = "SELECT * FROM cga105g2.article";
        List<Article> list = new ArrayList<>();
        Article Article;
        ResultSet rs;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Article = new Article();
                Article.setArtId(rs.getInt("art_Id"));
                Article.setMemId(rs.getInt("mem_Id"));
                Article.setStoreId(rs.getInt("store_Id"));
                Article.setArtHeader(rs.getString("art_Header"));
                Article.setArtText(rs.getString("art_Text"));
                Article.setArtImg(rs.getBytes("art_Img"));
                Article.setArtTag(rs.getString("art_Tag"));
                Article.setArtStatus(rs.getInt("art_Status"));
                Article.setArtTime(rs.getTimestamp("art_Time"));
                Article.setArtRetime(rs.getTimestamp("art_Retime"));
                Article.setArtSumlike(rs.getInt("art_Sumlike"));
                Article.setArtScore(rs.getInt("art_Score"));
                list.add(Article);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }

    @Override
    public Article getByMemId(Integer memId) {
        String sql = "SELECT * FROM cga105g2.article where MEM_ID = ?";
        Article Article = null;
        ResultSet rs;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Article = new Article();
                Article.setArtId(rs.getInt("art_Id"));
                Article.setMemId(rs.getInt("mem_Id"));
                Article.setStoreId(rs.getInt("store_Id"));
                Article.setArtHeader(rs.getString("art_Header"));
                Article.setArtText(rs.getString("art_Text"));
                Article.setArtImg(rs.getBytes("art_Img"));
                Article.setArtTag(rs.getString("art_Tag"));
                Article.setArtStatus(rs.getInt("art_Status"));
                Article.setArtTime(rs.getTimestamp("art_Time"));
                Article.setArtRetime(rs.getTimestamp("art_Retime"));
                Article.setArtSumlike(rs.getInt("art_Sumlike"));
                Article.setArtScore(rs.getInt("art_Score"));
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return Article;
    }

    @Override
    public Article getByArtId(Integer artId) {
        String sql = "SELECT * FROM cga105g2.article where ART_ID = ?";
        Article Article = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, artId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article = new Article();
                Article.setArtId(rs.getInt("art_Id"));
                Article.setMemId(rs.getInt("mem_Id"));
                Article.setStoreId(rs.getInt("store_Id"));
                Article.setArtHeader(rs.getString("art_Header"));
                Article.setArtText(rs.getString("art_Text"));
                Article.setArtImg(rs.getBytes("art_Img"));
                Article.setArtTag(rs.getString("art_Tag"));
                Article.setArtStatus(rs.getInt("art_Status"));
                Article.setArtTime(rs.getTimestamp("art_Time"));
                Article.setArtRetime(rs.getTimestamp("art_Retime"));
                Article.setArtSumlike(rs.getInt("art_Sumlike"));
                Article.setArtScore(rs.getInt("art_Score"));
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return Article;
    }

    @Override
    public List<Article> getAllByStoreId(Integer storeId) {
        String sql = "SELECT * FROM cga105g2.article where STORE_ID = ? order by ART_TIME DESC;";
        List<Article> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article Article = new Article();
                Article.setArtId(rs.getInt("art_Id"));
                Article.setMemId(rs.getInt("mem_Id"));
                Article.setStoreId(rs.getInt("store_Id"));
                Article.setArtHeader(rs.getString("art_Header"));
                Article.setArtText(rs.getString("art_Text"));
                Article.setArtImg(rs.getBytes("art_Img"));
                Article.setArtTag(rs.getString("art_Tag"));
                Article.setArtStatus(rs.getInt("art_Status"));
                Article.setArtTime(rs.getTimestamp("art_Time"));
                Article.setArtRetime(rs.getTimestamp("art_Retime"));
                Article.setArtSumlike(rs.getInt("art_Sumlike"));
                Article.setArtScore(rs.getInt("art_Score"));
                list.add(Article);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }

    public List<Article> getAllByMemId(Integer memId) {
        String sql = "SELECT * FROM cga105g2.article where MEM_ID = ? order by ART_TIME DESC;";
        List<Article> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article Article = new Article();
                Article.setArtId(rs.getInt("art_Id"));
                Article.setMemId(rs.getInt("mem_Id"));
                Article.setStoreId(rs.getInt("store_Id"));
                Article.setArtHeader(rs.getString("art_Header"));
                Article.setArtText(rs.getString("art_Text"));
                Article.setArtImg(rs.getBytes("art_Img"));
                Article.setArtTag(rs.getString("art_Tag"));
                Article.setArtStatus(rs.getInt("art_Status"));
                Article.setArtTime(rs.getTimestamp("art_Time"));
                Article.setArtRetime(rs.getTimestamp("art_Retime"));
                Article.setArtSumlike(rs.getInt("art_Sumlike"));
                Article.setArtScore(rs.getInt("art_Score"));
                list.add(Article);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }

    @Override
    public List<Article> getAllByMemIdArtId(Integer memId, Integer artId) {
        String sql = "SELECT * FROM cga105g2.article where MEM_ID = ? and ART_ID = ?";
        List<Article> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            pstmt.setInt(2, artId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article Article = new Article();
                Article.setArtId(rs.getInt("art_Id"));
                Article.setMemId(rs.getInt("mem_Id"));
                Article.setStoreId(rs.getInt("store_Id"));
                Article.setArtHeader(rs.getString("art_Header"));
                Article.setArtText(rs.getString("art_Text"));
                Article.setArtImg(rs.getBytes("art_Img"));
                Article.setArtTag(rs.getString("art_Tag"));
                Article.setArtStatus(rs.getInt("art_Status"));
                Article.setArtTime(rs.getTimestamp("art_Time"));
                Article.setArtRetime(rs.getTimestamp("art_Retime"));
                Article.setArtSumlike(rs.getInt("art_Sumlike"));
                Article.setArtScore(rs.getInt("art_Score"));
                list.add(Article);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }

    public void update(Article Article) {
        String sql = "UPDATE cga105g2.ARTICLE set MEM_ID= ?,STORE_ID= ?,ART_HEADER= ?, ART_TEXT= ?, ART_IMG= ?, ART_SCORE= ? where ART_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, Article.getMemId());
            pstmt.setInt(2, Article.getStoreId());
            pstmt.setString(3, Article.getArtHeader());
            pstmt.setString(4, Article.getArtText());
            pstmt.setBytes(5, Article.getArtImg());
            pstmt.setInt(6, Article.getArtScore());
            pstmt.setInt(7, Article.getArtId());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
        }
    }

    @Override
    public void delete(Integer artId) {
        String sql = "DELETE FROM cga105g2.ARTICLE where ART_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, artId);
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.DELETE_TITLE.getTitle(sql), se);
        }
    }

    public List<Article> getAllByMemIdStoreId(Integer memId, Integer storeId) {
        String sql = "SELECT * FROM cga105g2.article where MEM_ID = ? and STORE_ID = ? order by ART_TIME;";
        List<Article> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            pstmt.setInt(2, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article Article = new Article();
                Article.setArtId(rs.getInt("art_Id"));
                Article.setMemId(rs.getInt("mem_Id"));
                Article.setStoreId(rs.getInt("store_Id"));
                Article.setArtHeader(rs.getString("art_Header"));
                Article.setArtText(rs.getString("art_Text"));
                Article.setArtImg(rs.getBytes("art_Img"));
                Article.setArtTag(rs.getString("art_Tag"));
                Article.setArtStatus(rs.getInt("art_Status"));
                Article.setArtTime(rs.getTimestamp("art_Time"));
                Article.setArtRetime(rs.getTimestamp("art_Retime"));
                Article.setArtSumlike(rs.getInt("art_Sumlike"));
                Article.setArtScore(rs.getInt("art_Score"));
                list.add(Article);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }
}