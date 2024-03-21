package com.code.model.MemCode.dao.impl;


import com.code.model.MemCode.dao.MemCodeDAO_interface;
import com.code.model.MemCode.pojo.MemCode;
import com.core.common.Common;
import com.core.entity.ErrorTitle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemCodeJDBCDAO extends Common implements MemCodeDAO_interface {
    @Override
    public void insert(MemCode pojo) {
        String sql = "INSERT INTO cga105g2.mem_code (CODE_ID,MEM_ID) VALUES (?,?);";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pojo.getCodeId());
            pstmt.setInt(2, pojo.getMemId());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
        }
    }

    @Override
    public void deleteById(Integer id) {
        logger.error(ErrorTitle.UNDEF_TITLE.getTitle(getClass().getName()));
    }

    @Override
    public void delete(MemCode pojo) {
        String sql = "DELETE from cga105g2.mem_code where CODE_ID=? and MEM_ID=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pojo.getCodeId());
            pstmt.setInt(2, pojo.getMemId());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.DELETE_TITLE.getTitle(sql), se);
        }
    }

    @Override
    public void update(MemCode pojo) {
        logger.error(ErrorTitle.UNDEF_TITLE.getTitle(getClass().getName()));
    }

    @Override
    public void update(MemCode pojo1, MemCode pojo2) {
        String sql = "update cga105g2.mem_code set CODE_ID=?,MEM_ID=? where CODE_ID=? and MEM_ID=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pojo2.getCodeId());
            pstmt.setInt(2, pojo2.getMemId());
            pstmt.setInt(3, pojo1.getCodeId());
            pstmt.setInt(4, pojo1.getMemId());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
        }
    }

    @Override
    public MemCode getById(Integer id) {
        return new MemCode();
    }

    @Override
    public List<MemCode> getByPK(Integer id, String pk) {
        List<MemCode> list = new ArrayList<>();
        String sql = "select * from cga105g2.mem_code where";
        String where = null;
        if (pk.equals("codeId")) {
            where = " CODE_ID=?;";
        } else {
            where = " MEM_ID=?;";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql + where)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MemCode memcode = new MemCode();
                memcode.setCodeId(rs.getInt("CODE_ID"));
                memcode.setMemId(rs.getInt("MEM_ID"));
                list.add(memcode);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }

    @Override
    public List<MemCode> getAll() {
        String sql = "select * from cga105g2.mem_code";
        List<MemCode> list = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MemCode memcode = new MemCode();
                memcode.setCodeId(rs.getInt("CODE_ID"));
                memcode.setMemId(rs.getInt("MEM_ID"));
                list.add(memcode);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }


    public static void main(String[] args) {
        MemCodeJDBCDAO dao = new MemCodeJDBCDAO();
//        新增
        for (int i = 5; i <= 8; i++) {
            MemCode memCode1 = new MemCode(3, i);
            dao.insert(memCode1);
        }
        //修改
        MemCode memCode2 = new MemCode(1, 1);
        MemCode memCode3 = new MemCode(1, 4);
        dao.update(memCode2, memCode3);
        //刪除
        MemCode memCode4 = new MemCode(1, 4);
        dao.delete(memCode4);

        //getByPK
        //(1)codeId:查詢有優惠券id=1的會員有哪些
        List<MemCode> list1 = dao.getByPK(1, "codeId");
        logger.info("有此優惠券的會員如下:");
        for (MemCode e : list1) {
            logger.info(+e.getMemId() + "號、");
        }
        logger.info("*******************************************");
        //(2)memId:查詢會員id=5的會員有哪些優惠券id
        List<MemCode> list2 = dao.getByPK(5, "memId");
        logger.info("此會員擁有的優惠券如下:");
        for (MemCode e : list2) {
            logger.info(e.getCodeId() + "號、");
        }
        logger.info("*******************************************");
        //getall
        List<MemCode> list = dao.getAll();
        for (MemCode e : list) {
            logger.info("CodeId:" + e.getCodeId() + "\t");
            logger.info("MemId:" + e.getMemId() + "\n");
        }
    }
}
