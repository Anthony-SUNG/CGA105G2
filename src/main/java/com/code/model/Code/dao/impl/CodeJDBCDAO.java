package com.code.model.Code.dao.impl;

import com.code.model.Code.dao.CodeDAO_interface;
import com.code.model.Code.pojo.Code;
import com.core.common.Common;
import com.core.entity.ErrorTitle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CodeJDBCDAO extends Common implements CodeDAO_interface {
    @Override
    public void insert(Code pojo) {
        String sql = "INSERT INTO cga105g2.code (STORE_ID,CODE_NUM,CODE_OFF,CODE_TEXT,CODE_NTIME) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pojo.getStoreId());
            pstmt.setString(2, pojo.getCodeNum());
            pstmt.setInt(3, pojo.getCodeOff());
            pstmt.setString(4, pojo.getCodeText());
            pstmt.setDate(5, pojo.getCodeNtime());
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
    public void deleteById(Integer id) {
        String sql = "DELETE from cga105g2.code where CODE_ID=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
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
    public void update(Code pojo) {
        long miliseconds = System.currentTimeMillis();
        String sql = "update cga105g2.code set EMP_ID=?,CODE_STATUS=?,CODE_RTIME=? where CODE_ID=?";
        Code code_new = pojo;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            Code code_old = getById(code_new.getCodeId());
            pstmt.setInt(1, code_old.getEmpId());
            pstmt.setInt(2, code_old.getCodeStatus());
            pstmt.setDate(3, code_old.getCodeRtime());
            pstmt.setInt(4, code_old.getCodeId());
            if (code_new.getEmpId() != null)
                pstmt.setInt(1, code_new.getEmpId());
            if (code_new.getCodeStatus() != null) {
                pstmt.setInt(2, code_new.getCodeStatus());
                pstmt.setDate(3, new Date(miliseconds));
            }
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

    public void failUpdate(Integer codeStatus) {
        long miliseconds = System.currentTimeMillis();
        Date today = new Date(miliseconds);
        for (Code e : getBy(codeStatus, "狀態")) {
            if (e.getCodeNtime().before(today)) {
                Code code_old = new Code();
                code_old.setCodeId(e.getCodeId());
                code_old.setEmpId(e.getEmpId());
                code_old.setCodeStatus(3);
                update(code_old);
            }
        }
    }

    public List<Integer> getBycodeNum(String codeNum, Integer storeId) {
        String sql = "select STORE_ID,CODE_NUM,CODE_OFF from cga105g2.code where STORE_ID=? and CODE_STATUS=2";
        List<Integer> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("CODE_NUM").equals(codeNum)) list.add(rs.getInt("CODE_OFF"));
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
    public List<Integer> getCodeId(String codeNum, Integer storeId) {
        String sql = "select CODE_ID,STORE_ID,CODE_NUM,CODE_OFF from cga105g2.code where STORE_ID=? and CODE_STATUS=2";
        List<Integer> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("CODE_NUM").equals(codeNum)) list.add(rs.getInt("CODE_ID"));
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


    public Code getById(Integer id) {
        String sql = "select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code where CODE_ID=?";
        Code code = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                code = new Code();
                code.setCodeId(rs.getInt("CODE_ID"));
                code.setStoreId(rs.getInt("STORE_ID"));
                code.setEmpId(rs.getInt("EMP_ID"));
                code.setCodeNum(rs.getString("CODE_NUM"));
                code.setCodeOff(rs.getInt("CODE_OFF"));
                code.setCodeStatus(rs.getInt("CODE_STATUS"));
                code.setCodeText(rs.getString("CODE_TEXT"));
                code.setCodeTime(rs.getTimestamp("CODE_TIME"));
                code.setCodeRtime(rs.getDate("CODE_RTIME"));
                code.setCodeNtime(rs.getDate("CODE_NTIME"));
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
        return code;
    }

    @Override
    public List<Code> getBy(Integer id, String string) {
        List<Code> list = new ArrayList<>();
        String sql = "select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code";
        if (string.equals("店家")) sql += " where STORE_ID=?;";
        if (string.equals("狀態")) sql += " where CODE_STATUS=?;";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Code code = new Code();
                code.setCodeId(rs.getInt("CODE_ID"));
                code.setStoreId(rs.getInt("STORE_ID"));
                code.setEmpId(rs.getInt("EMP_ID"));
                code.setCodeNum(rs.getString("CODE_NUM"));
                code.setCodeOff(rs.getInt("CODE_OFF"));
                code.setCodeStatus(rs.getInt("CODE_STATUS"));
                code.setCodeText(rs.getString("CODE_TEXT"));
                code.setCodeTime(rs.getTimestamp("CODE_TIME"));
                code.setCodeRtime(rs.getDate("CODE_RTIME"));
                code.setCodeNtime(rs.getDate("CODE_NTIME"));
                list.add(code);
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
    public List<Code> getAll() {
        String sql = "select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code order by CODE_ID";
        List<Code> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Code code = new Code();
                code.setCodeId(rs.getInt("CODE_ID"));
                code.setStoreId(rs.getInt("STORE_ID"));
                code.setEmpId(rs.getInt("EMP_ID"));
                code.setCodeNum(rs.getString("CODE_NUM"));
                code.setCodeOff(rs.getInt("CODE_OFF"));
                code.setCodeStatus(rs.getInt("CODE_STATUS"));
                code.setCodeText(rs.getString("CODE_TEXT"));
                code.setCodeTime(rs.getTimestamp("CODE_TIME"));
                code.setCodeRtime(rs.getDate("CODE_RTIME"));
                code.setCodeNtime(rs.getDate("CODE_NTIME"));
                list.add(code);
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
