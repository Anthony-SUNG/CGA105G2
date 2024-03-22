package com.member.model.Member.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.member.model.Member.dao.MemberDAO_interface;
import com.member.model.Member.pojo.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends Common implements MemberDAO_interface {
    @Override
    public void insert(Member member) {
        String sql = "INSERT INTO cga105g2.member (MEM_ACC,MEM_PWD,MEM_MAIL,MEM_NAME,MEM_RECIPIENT,MEM_TW_ID,MEM_BIRTHDAY,MEM_PHONE,MEM_POSTAL_CODE,MEM_CITY,MEM_DISTRICT,MEM_ADDRESS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, member.getMemAcc());
            pstmt.setString(2, member.getMemPwd());
            pstmt.setString(3, member.getMemMail());
            pstmt.setString(4, member.getMemName());
            pstmt.setString(5, member.getMemRecipient());
            pstmt.setString(6, member.getMemTwId());
            pstmt.setDate(7, member.getMemBirthday());
            pstmt.setString(8, member.getMemPhone());
            pstmt.setInt(9, member.getMemPostalCode());
            pstmt.setString(10, member.getMemCity());
            pstmt.setString(11, member.getMemDistrict());
            pstmt.setString(12, member.getMemAddress());
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
    public void update(Member member) {
        String sql = "UPDATE cga105g2.member set MEM_ACC=?,MEM_PWD=?,MEM_MAIL=?,MEM_PIC=?,MEM_NAME=?,MEM_RECIPIENT=?,MEM_TW_ID=?,MEM_BIRTHDAY=?,MEM_PHONE=?,MEM_POSTAL_CODE=?,MEM_CITY=?,MEM_DISTRICT=?,MEM_ADDRESS=?,MEM_TEXT=? where MEM_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, member.getMemAcc());
            pstmt.setString(2, getById(member.getMemId()).getMemPwd());
            pstmt.setString(3, member.getMemMail());
            pstmt.setBytes(4, member.getMemPic());
            pstmt.setString(5, member.getMemName());
            pstmt.setString(6, member.getMemRecipient());
            pstmt.setString(7, member.getMemTwId());
            pstmt.setDate(8, member.getMemBirthday());
            pstmt.setString(9, member.getMemPhone());
            pstmt.setInt(10, member.getMemPostalCode());
            pstmt.setString(11, member.getMemCity());
            pstmt.setString(12, member.getMemDistrict());
            pstmt.setString(13, member.getMemAddress());
            pstmt.setString(14, member.getMemText());
            pstmt.setInt(15, member.getMemId());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public void delete(Integer memId) {
        String sql = "DELETE FROM cga105g2.member where MEM_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
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

    @Override
    public Member getById(Integer memId) {
        String sql = "SELECT * FROM cga105g2.member where MEM_ID = ?";
        Member member = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                member = new Member();
                member.setMemId(rs.getInt("MEM_ID"));
                member.setMemStatus(rs.getInt("MEM_STATUS"));
                member.setMemAcc(rs.getString("MEM_ACC"));
                member.setMemPwd(rs.getString("MEM_PWD"));
                member.setMemMail(rs.getString("MEM_MAIL"));
                member.setMemPic(rs.getBytes("MEM_PIC"));
                member.setMemName(rs.getString("MEM_NAME"));
                member.setMemRecipient(rs.getString("MEM_RECIPIENT"));
                member.setMemTwId(rs.getString("MEM_TW_ID"));
                member.setMemBirthday(rs.getDate("MEM_BIRTHDAY"));
                member.setMemPhone(rs.getString("MEM_PHONE"));
                member.setMemPostalCode(rs.getInt("MEM_POSTAL_CODE"));
                member.setMemCity(rs.getString("MEM_CITY"));
                member.setMemDistrict(rs.getString("MEM_DISTRICT"));
                member.setMemAddress(rs.getString("MEM_ADDRESS"));
                member.setMemText(rs.getString("MEM_TEXT"));
                member.setMemTime(rs.getTimestamp("MEM_TIME"));
                member.setMemPoint(rs.getInt("MEM_POINT"));
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return member;
    }

    @Override
    public List<Member> getAll() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM cga105g2.member order by MEM_ID";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setMemId(rs.getInt("MEM_ID"));
                member.setMemStatus(rs.getInt("MEM_STATUS"));
                member.setMemAcc(rs.getString("MEM_ACC"));
                member.setMemPwd(rs.getString("MEM_PWD"));
                member.setMemMail(rs.getString("MEM_MAIL"));
                member.setMemPic(rs.getBytes("MEM_PIC"));
                member.setMemName(rs.getString("MEM_NAME"));
                member.setMemRecipient(rs.getString("MEM_RECIPIENT"));
                member.setMemTwId(rs.getString("MEM_TW_ID"));
                member.setMemBirthday(rs.getDate("MEM_BIRTHDAY"));
                member.setMemPhone(rs.getString("MEM_PHONE"));
                member.setMemPostalCode(rs.getInt("MEM_POSTAL_CODE"));
                member.setMemCity(rs.getString("MEM_CITY"));
                member.setMemDistrict(rs.getString("MEM_DISTRICT"));
                member.setMemAddress(rs.getString("MEM_ADDRESS"));
                member.setMemText(rs.getString("MEM_TEXT"));
                member.setMemTime(rs.getTimestamp("MEM_TIME"));
                member.setMemPoint(rs.getInt("MEM_POINT"));
                list.add(member);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }


    public List<Member> getAllByName(String memName) {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM cga105g2.member where MEM_NAME like ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, memName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setMemId(rs.getInt("MEM_ID"));
                member.setMemStatus(rs.getInt("MEM_STATUS"));
                member.setMemAcc(rs.getString("MEM_ACC"));
                member.setMemPwd(rs.getString("MEM_PWD"));
                member.setMemMail(rs.getString("MEM_MAIL"));
                member.setMemPic(rs.getBytes("MEM_PIC"));
                member.setMemName(rs.getString("MEM_NAME"));
                member.setMemRecipient(rs.getString("MEM_RECIPIENT"));
                member.setMemTwId(rs.getString("MEM_TW_ID"));
                member.setMemBirthday(rs.getDate("MEM_BIRTHDAY"));
                member.setMemPhone(rs.getString("MEM_PHONE"));
                member.setMemPostalCode(rs.getInt("MEM_POSTAL_CODE"));
                member.setMemCity(rs.getString("MEM_CITY"));
                member.setMemDistrict(rs.getString("MEM_DISTRICT"));
                member.setMemAddress(rs.getString("MEM_ADDRESS"));
                member.setMemText(rs.getString("MEM_TEXT"));
                member.setMemTime(rs.getTimestamp("MEM_TIME"));
                member.setMemPoint(rs.getInt("MEM_POINT"));
                list.add(member);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }

    @Override
    public List<Member> getAllByAcc(String memAcc) {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM cga105g2.member where MEM_ACC like ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, "%" + memAcc + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setMemId(rs.getInt("MEM_ID"));
                member.setMemStatus(rs.getInt("MEM_STATUS"));
                member.setMemAcc(rs.getString("MEM_ACC"));
                member.setMemPwd(rs.getString("MEM_PWD"));
                member.setMemMail(rs.getString("MEM_MAIL"));
                member.setMemPic(rs.getBytes("MEM_PIC"));
                member.setMemName(rs.getString("MEM_NAME"));
                member.setMemRecipient(rs.getString("MEM_RECIPIENT"));
                member.setMemTwId(rs.getString("MEM_TW_ID"));
                member.setMemBirthday(rs.getDate("MEM_BIRTHDAY"));
                member.setMemPhone(rs.getString("MEM_PHONE"));
                member.setMemPostalCode(rs.getInt("MEM_POSTAL_CODE"));
                member.setMemCity(rs.getString("MEM_CITY"));
                member.setMemDistrict(rs.getString("MEM_DISTRICT"));
                member.setMemAddress(rs.getString("MEM_ADDRESS"));
                member.setMemText(rs.getString("MEM_TEXT"));
                member.setMemTime(rs.getTimestamp("MEM_TIME"));
                member.setMemPoint(rs.getInt("MEM_POINT"));
                list.add(member);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }

    @Override
    public void update(Integer id, Integer status) {
        String sql = "UPDATE cga105g2.member set MEM_STATUS = ? where MEM_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, status);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public Member signin(String memAcc, String memPwd) {
        String sql = "SELECT * FROM cga105g2.member where MEM_ACC = ? AND MEM_PWD = ?";
        Member member = new Member();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, memAcc);
            pstmt.setString(2, memPwd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                member.setMemId(rs.getInt("MEM_ID"));
                member.setMemStatus(rs.getInt("MEM_STATUS"));
                member.setMemName(rs.getString("MEM_NAME"));
            } else {
                member.setMemId(0);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return member;
    }

    @Override
    public void update3(Member member) {
        String sql = "UPDATE cga105g2.member set MEM_PWD=? where MEM_ACC=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, member.getMemPwd());
            pstmt.setString(2, member.getMemAcc());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public void update4(Member member) {
        String sql = "UPDATE cga105g2.member set MEM_PWD=? where MEM_ID=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, member.getMemPwd());
            pstmt.setInt(2, member.getMemId());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                con.rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }
}
