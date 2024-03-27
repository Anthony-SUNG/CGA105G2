package com.emp.model.Root.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.emp.model.Root.dao.RootDAO_interface;
import com.emp.model.Root.pojo.Root;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RootJDBCDAO extends Common implements RootDAO_interface {


    public List<Root> findByROOT_TEXT(String ROOT_TEXT) {
        String sql = "SELECT * FROM cga105g2.root where ROOT_TEXT LIKE  ? ";
        List<Root> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, ROOT_TEXT);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Root rootvo = new Root();
                rootvo.setRootId(rs.getInt("ROOT_ID"));
                rootvo.setRootText(rs.getString("ROOT_TEXT"));
                list.add(rootvo);
            }
            getCon().commit();
            getCon().close();
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
    public List<Root> getAll() {
        String sql = "SELECT * FROM cga105g2.root order by ROOT_ID";
        List<Root> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Root rootVO = new Root();
                rootVO.setRootId(rs.getInt("ROOT_ID"));
                rootVO.setRootText(rs.getString("ROOT_TEXT"));
                list.add(rootVO);
            }
            getCon().commit();
            getCon().close();
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


    public Root findByRootId(Integer integer) {
        String sql = "SELECT * FROM cga105g2.root where ROOT_ID LIKE  ? ";
        Root rootvo = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, integer);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                rootvo = new Root();
                rootvo.setRootId(rs.getInt("ROOT_ID"));
                rootvo.setRootText(rs.getString("ROOT_TEXT"));
            }
            getCon().commit();
            getCon().close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return rootvo;
    }


}
