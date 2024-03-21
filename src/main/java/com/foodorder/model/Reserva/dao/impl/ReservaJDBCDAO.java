package com.foodorder.model.Reserva.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.foodorder.model.Reserva.dao.ReservaDAO_interface;
import com.foodorder.model.Reserva.pojo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReservaJDBCDAO extends Common implements ReservaDAO_interface {
    @Override
    public void insert(Reserva reservaVO) {
        if (reservaVO.getCodeId() != null) {
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
                pstmt.setInt(1, reservaVO.getStoreId());
                pstmt.setInt(2, reservaVO.getMemId());
                pstmt.setString(3, reservaVO.getRenName());
                pstmt.setString(4, reservaVO.getRenPhone());
                pstmt.setString(5, reservaVO.getRenTime());
                pstmt.setDate(6, reservaVO.getRenDate());
                pstmt.setInt(7, reservaVO.getRenHeadcount());
                pstmt.setInt(8, reservaVO.getCodeId());
                pstmt.setInt(9, reservaVO.getRenPrice());
                pstmt.setInt(10, reservaVO.getRenFprice());
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
        if (reservaVO.getCodeId() == null) {
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
                pstmt.setInt(1, reservaVO.getStoreId());
                pstmt.setInt(2, reservaVO.getMemId());
                pstmt.setString(3, reservaVO.getRenName());
                pstmt.setString(4, reservaVO.getRenPhone());
                pstmt.setString(5, reservaVO.getRenTime());
                pstmt.setDate(6, reservaVO.getRenDate());
                pstmt.setInt(7, reservaVO.getRenHeadcount());
                pstmt.setInt(8, reservaVO.getRenPrice());
                pstmt.setInt(9, reservaVO.getRenFprice());
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

    @Override
    public void update(Reserva reservaVO) {
        String sql = "UPDATE cga105g2.reserva set REN_STATUS=?, REN_TABLE=?, REN_DATE=? where REN_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            Reserva reserva_old = getById(reservaVO.getRenId());
            pstmt.setInt(1, reserva_old.getRenStatus());
            pstmt.setInt(2, reserva_old.getRenTable());
            pstmt.setDate(3, reserva_old.getRenDate());
            pstmt.setInt(4, reserva_old.getRenId());
            if (reservaVO.getRenStatus() != null) pstmt.setInt(1, reservaVO.getRenStatus());
            if (reservaVO.getRenTable() != null) pstmt.setInt(2, reservaVO.getRenTable());
            else pstmt.setNull(2, Types.NULL);
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
    public void updateRenstatusByRenid(Integer renid, Integer renstatus) {
        String sql = "UPDATE cga105g2.reserva set REN_STATUS=? where REN_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, renstatus);
            pstmt.setInt(2, renid);
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
    public Reserva getById(Integer id) {
        String sql = "SELECT * FROM cga105g2.reserva where REN_ID = ?";
        Reserva reserva = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));

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
        return reserva;
    }

    @Override
    public List<Reserva> getBymemIdrenStatus(Integer memid, Integer renstatus) {
        String sql = "SELECT * FROM cga105g2.reserva where MEM_ID = ? and REN_STATUS = ?";
        List<Reserva> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memid);
            pstmt.setInt(2, renstatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);
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
    public List<Reserva> getBymemId(Integer memid) {
        String sql = "SELECT * FROM cga105g2.reserva where MEM_ID = ?";
        List<Reserva> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, memid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);
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
    public List<Reserva> getAll() {
        String sql = "SELECT * FROM cga105g2.reserva order by REN_ID";
        List<Reserva> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);
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

    public List<Reserva> getByStatus(Integer renStatus) {
        List<Reserva> list = new ArrayList<>();
        String sql = "SELECT * FROM cga105g2.reserva where REN_STATUS = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, renStatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);
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
    public List<Reserva> getByStoreIdRendate(Integer storeid, String rendate, String rentime, Integer renstatus) {
        List<Reserva> list = new ArrayList<>();
        String sql = "SELECT * FROM cga105g2.reserva where STORE_ID = ? and REN_DATE = ? and REN_TIME = ? and REN_STATUS = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, storeid);
            pstmt.setString(2, rendate);
            pstmt.setString(3, rentime);
            pstmt.setInt(4, renstatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);
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
    public void insertWithReservaDetails(Reserva reservaVO, List<Map> list) {
        Connection con1 = null;
        Connection con2 = null;
        try {
            con1 = getConnection();
            con2 = getConnection();
        } catch (SQLException c) {
            logger.error(ErrorTitle.CONNECTION_TITLE.getTitle(con1 + "," + con2), c);
        }


        if (reservaVO.getCodeId() != null) {
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";

            if (con1 != null && con2 != null)
                try (PreparedStatement pstmt = con1.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                     PreparedStatement pstmt2 = con2.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                    // 1●設定於 pstm.executeUpdate()之前
                    con1.setAutoCommit(false);
                    // 先新增
                    pstmt.setInt(1, reservaVO.getStoreId());
                    pstmt.setInt(2, reservaVO.getMemId());
                    pstmt.setString(3, reservaVO.getRenName());
                    pstmt.setString(4, reservaVO.getRenPhone());
                    pstmt.setString(5, reservaVO.getRenTime());
                    pstmt.setDate(6, reservaVO.getRenDate());
                    pstmt.setInt(7, reservaVO.getRenHeadcount());
                    pstmt.setInt(8, reservaVO.getCodeId());
                    pstmt.setInt(9, reservaVO.getRenPrice());
                    pstmt.setInt(10, reservaVO.getRenFprice());
                    pstmt.executeUpdate();
                    //掘取對應的自增主鍵值
                    String next_id = null;
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        next_id = rs.getString(1);
                        logger.info("自增主鍵值= " + next_id + "(剛新增成功的訂單編號)");
                    } else {
                        logger.info("未取得自增主鍵值");
                    }
                    rs.close();
                    // 再同時新增訂單明細
                    for (Map map : list) {
                        pstmt2.setInt(1, Integer.parseInt(next_id == null ? "0" : next_id));
                        pstmt2.setInt(2, (Integer) map.get("mealId"));
                        pstmt2.setInt(3, (Integer) map.get("rdQuantity"));
                        pstmt2.setInt(4, (Integer) map.get("pdPrice"));
                        pstmt2.executeUpdate();
                    }
                    con1.commit();
                    con2.commit();
                    con1.close();
                    con2.close();
                } catch (SQLException se) {
                    logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
                    try {
                        con1.rollback();
                        con2.rollback();
                    } catch (SQLException r) {
                        logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
                    }
                }
        }
        if (reservaVO.getCodeId() == null) {
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";
            if (con1 != null && con2 != null)
                try (PreparedStatement pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                     PreparedStatement pstmt2 = getConnection().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                    // 1●設定於 pstm.executeUpdate()之前
                    // 先新增
                    pstmt.setInt(1, reservaVO.getStoreId());
                    pstmt.setInt(2, reservaVO.getMemId());
                    pstmt.setString(3, reservaVO.getRenName());
                    pstmt.setString(4, reservaVO.getRenPhone());
                    pstmt.setString(5, reservaVO.getRenTime());
                    pstmt.setDate(6, reservaVO.getRenDate());
                    pstmt.setInt(7, reservaVO.getRenHeadcount());
                    pstmt.setInt(8, reservaVO.getRenPrice());
                    pstmt.setInt(9, reservaVO.getRenFprice());
                    pstmt.executeUpdate();
                    //掘取對應的自增主鍵值
                    String next_id = null;
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) next_id = rs.getString(1);
                    else {
                        logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("未取得自增主鍵值"));
                    }
                    rs.close();
                    // 再同時新增訂單明細
                    for (Map map : list) {
                        pstmt2.setInt(1, Integer.parseInt(next_id == null ? "0" : next_id));
                        pstmt2.setInt(2, (Integer) map.get("mealId"));
                        pstmt2.setInt(3, (Integer) map.get("rdQuantity"));
                        pstmt2.setInt(4, (Integer) map.get("pdPrice"));
                        pstmt2.executeUpdate();
                    }
                    con1.commit();
                    con2.commit();
                    con1.close();
                    con2.close();
                } catch (SQLException se) {
                    logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
                    try {
                        con1.rollback();
                        con2.rollback();
                    } catch (SQLException r) {
                        logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
                    }
                }
        }
    }

    @Override
    public List<Reserva> getBystoreId(Integer storeid) {
        String sql = "SELECT * FROM cga105g2.reserva where STORE_ID = ?";
        List<Reserva> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, storeid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);

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
    public List<Reserva> getBystoreIdrenStatus(Integer storeid, Integer renstatus) {
        String sql = "SELECT * FROM cga105g2.reserva where STORE_ID = ? and REN_STATUS = ?";
        List<Reserva> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, storeid);
            pstmt.setInt(2, renstatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);
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
