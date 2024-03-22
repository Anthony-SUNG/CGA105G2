package com.foodorder.model.ReservaDetail.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.foodorder.model.ReservaDetail.dao.ReservaDetailDAO_interface;
import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReservaDetailJDBCDAO extends Common implements ReservaDetailDAO_interface {


    @Override
    public void insert(ReservaDetail reservaDetailVO) {
        String sql = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, reservaDetailVO.getRenId());
            pstmt.setInt(2, reservaDetailVO.getMealId());
            pstmt.setInt(3, reservaDetailVO.getRdQuantity());
            pstmt.setInt(4, reservaDetailVO.getPdPrice());
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
    public List<ReservaDetail> getByPK(Integer id, String pk) {
        List<ReservaDetail> list = new ArrayList<>();
        String sql = "select * from cga105g2.reserva_detail where";
        String where;
        if (pk.equals("renId")) where = " REN_ID=?;";
        else where = " MEAL_ID=?;";
        sql = sql + where;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReservaDetail reservaDetail = new ReservaDetail();
                reservaDetail.setRenId(rs.getInt("REN_ID"));
                reservaDetail.setMealId(rs.getInt("MEAL_ID"));
                reservaDetail.setRdQuantity(rs.getInt("RD_QUANTITY"));
                reservaDetail.setPdPrice(rs.getInt("PD_PRICE"));
                list.add(reservaDetail);
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
    public List<ReservaDetail> getAll() {
        String sql = "select * from cga105g2.reserva_detail";
        List<ReservaDetail> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReservaDetail reservaDetail = new ReservaDetail();
                reservaDetail.setRenId(rs.getInt("REN_ID"));
                reservaDetail.setMealId(rs.getInt("MEAL_ID"));
                reservaDetail.setRdQuantity(rs.getInt("RD_QUANTITY"));
                reservaDetail.setPdPrice(rs.getInt("PD_PRICE"));
                list.add(reservaDetail);
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


}
