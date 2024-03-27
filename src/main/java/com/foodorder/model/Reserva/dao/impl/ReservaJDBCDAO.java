package com.foodorder.model.Reserva.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.foodorder.model.Reserva.dao.ReservaDAO_interface;
import com.foodorder.model.Reserva.pojo.Reserva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
                getCon().commit();
                getCon().close();
            } catch (SQLException se) {
                logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
                try {
                    getCon().rollback();
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
                getCon().commit();
                getCon().close();
            } catch (SQLException se) {
                logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
                try {
                    getCon().rollback();
                } catch (SQLException r) {
                    logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
                }
            }
        }

    }

    @Override
    public void update(Reserva reservaVO) {
        String sql = "UPDATE cga105g2.reserva set REN_STATUS=?, REN_TABLE=?, REN_DATE=? where REN_ID = ?";
        Reserva reserva_new = reservaVO;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            Reserva reserva_old = getById(reserva_new.getRenId());
            pstmt.setInt(1, reserva_old.getRenStatus());
            pstmt.setInt(2, reserva_old.getRenTable());
            pstmt.setDate(3, reserva_old.getRenDate());
            pstmt.setInt(4, reserva_old.getRenId());
            if (reserva_new.getRenStatus() != null) {
                pstmt.setInt(1, reserva_new.getRenStatus());
            }
            if (reserva_new.getRenTable() != null) {
                pstmt.setInt(2, reserva_new.getRenTable());
            } else {
                // 因為初始值為null,如果不寫預設帶入0
                pstmt.setNull(2, Types.NULL);
            }
            pstmt.executeUpdate();
            getCon().commit();
            getCon().close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    public void wattingupdate(Reserva reservaVO) {
        String sql = "UPDATE cga105g2.reserva set REN_PRICE=?, REN_FPRICE=?";
        if (reservaVO.getCodeId() != 0) sql += ",CODE_ID=?";
        sql += " where REN_ID = ?";
        Reserva reserva_new = reservaVO;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            //原價
            pstmt.setInt(1, reserva_new.getRenPrice());
            //支付金額
            pstmt.setInt(2, reserva_new.getRenFprice());
            //優惠券
            if (reservaVO.getCodeId() != 0) {
                pstmt.setInt(3, reserva_new.getCodeId());
                pstmt.setInt(4, reserva_new.getRenId());
            } else {
                pstmt.setInt(3, reserva_new.getRenId());
            }
            pstmt.executeUpdate();
            getCon().commit();
            getCon().close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
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
            getCon().commit();
            getCon().close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public void deleteById(Integer id) {

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
    public List<Reserva> getByStoreIdRendate(Integer storeid, String rendate, String rentime, Integer renstatus) {
        List<Reserva> list = new ArrayList<>();
        String sql = "SELECT * FROM cga105g2.reserva where STORE_ID = ? and REN_DATE = ? and REN_TIME = ? and REN_STATUS = ?";
        Reserva reserva = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, storeid);
            pstmt.setString(2, rendate);
            pstmt.setString(3, rentime);
            pstmt.setInt(4, renstatus);
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
                list.add(reserva);
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
    public void insertWithReservaDetails(Reserva reservaVO, List<Map> list) {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        if (reservaVO.getCodeId() != null) {
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";
            try {
                pstmt = getConnection().prepareStatement(sql);
                pstmt2 = getConnection().prepareStatement(sql2);
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
                if (rs.next()) next_id = rs.getString(1);
                rs.close();

                // 再同時新增訂單明細
                for (Map map : list) {
                    pstmt2.setInt(1, Integer.parseInt(next_id == null ? "0" : next_id));
                    pstmt2.setInt(2, (Integer) map.get("mealId"));
                    pstmt2.setInt(3, (Integer) map.get("rdQuantity"));
                    pstmt2.setInt(4, (Integer) map.get("pdPrice"));
                    pstmt2.executeUpdate();
                }
                pstmt.getConnection().commit();
                pstmt2.getConnection().commit();
                pstmt.getConnection().close();
                pstmt2.getConnection().close();
            } catch (SQLException se) {
                logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql + "\n" + sql2), se);
                try {
                    if (pstmt != null) pstmt.getConnection().rollback();
                    if (pstmt2 != null) pstmt2.getConnection().rollback();
                } catch (SQLException r) {
                    logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql + "\n" + sql2), r);
                }
            }
        }
        if (reservaVO.getCodeId() == null) {
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";
            try {
                pstmt = getConnection().prepareStatement(sql);
                pstmt2 = getConnection().prepareStatement(sql2);

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
                rs.close();
                // 再同時新增訂單明細
                for (Map map : list) {
                    pstmt2.setInt(1, Integer.parseInt(next_id == null ? "0" : next_id));
                    pstmt2.setInt(2, (Integer) map.get("mealId"));
                    pstmt2.setInt(3, (Integer) map.get("rdQuantity"));
                    pstmt2.setInt(4, (Integer) map.get("pdPrice"));
                    pstmt2.executeUpdate();
                }
                pstmt.getConnection().commit();
                pstmt2.getConnection().commit();
                pstmt.getConnection().close();
                pstmt2.getConnection().close();
            } catch (SQLException se) {
                logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql + "\n" + sql2), se);
                try {
                    if (pstmt != null) pstmt.getConnection().rollback();
                    if (pstmt2 != null) pstmt2.getConnection().rollback();
                } catch (SQLException r) {
                    logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql + "\n" + sql2), r);
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
    public void insertToSta(Reserva reservaVO) {
        String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE,REN_STATUS,REN_TABLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            int s = Integer.parseInt(reservaVO.getRenPhone());
            pstmt.setInt(1, reservaVO.getStoreId());
            pstmt.setInt(2, reservaVO.getMemId());
            pstmt.setString(3, reservaVO.getRenName());
            pstmt.setString(4, reservaVO.getRenPhone());
            pstmt.setString(5, reservaVO.getRenTime());
            pstmt.setDate(6, reservaVO.getRenDate());
            pstmt.setInt(7, reservaVO.getRenHeadcount());
            pstmt.setInt(8, reservaVO.getRenPrice());
            pstmt.setInt(9, reservaVO.getRenFprice());
            pstmt.setInt(10, 2);
            pstmt.setInt(11, s);
            pstmt.executeUpdate();
            getCon().commit();
            getCon().close();
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
    public Reserva getTable(Integer id) {
        String sql = "SELECT * FROM cga105g2.reserva where REN_TABLE = ?";
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
        return reserva;
    }

    public Reserva getphone(Integer sid, String phone, Integer pn) {
        String sql = "SELECT * FROM cga105g2.reserva where STORE_ID = ? and REN_PHONE = ? and REN_STATUS = ? and REN_HEADCOUNT = ?";
        Reserva reserva = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, sid);
            pstmt.setString(2, phone);
            pstmt.setInt(3, 2);
            pstmt.setInt(4, pn);
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
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
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
        return reserva;
    }

}
