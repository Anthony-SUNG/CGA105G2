package com.store.model.Store.dao.impl;

import com.core.common.Common;
import com.store.model.Store.dao.StoreDAO_interface;
import com.store.model.Store.pojo.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO implements StoreDAO_interface {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Store Store) {

        String sql = "INSERT INTO cga105g2.store (EMP_ID,STORE_NAME,STORE_PHONE1,STORE_HOURS,STORE_CITY,STORE_DISTRICT,STORE_ADDRESS,STORE_URL,STORE_WEB,STORE_ACC,STORE_PWD,STORE_MAIL,STORE_COM_ID,STORE_COM_ADDRESS,STORE_TW_ID,STORE_PHONE2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, Store.getEmpId());
            pstmt.setString(2, Store.getStoreName());
            pstmt.setString(3, Store.getStorePhone1());
            pstmt.setString(4, Store.getStoreHours());
            pstmt.setString(5, Store.getStoreCity());
            pstmt.setString(6, Store.getStoreDistrict());
            pstmt.setString(7, Store.getStoreAddress());
            pstmt.setString(8, Store.getStoreUrl());
            pstmt.setString(9, Store.getStoreWeb());
            pstmt.setString(10, Store.getStoreAcc());
            pstmt.setString(11, Store.getStorePwd());
            pstmt.setString(12, Store.getStoreMail());
            pstmt.setString(13, Store.getStoreComId());
            pstmt.setString(14, Store.getStoreComAddress());
            pstmt.setString(15, Store.getStoreTwId());
            pstmt.setString(16, Store.getStorePhone2());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

        }

    }

    @Override
    public void update(Store Store) {

        String sql = "UPDATE cga105g2.store set EMP_ID=?,STORE_NAME=?,STORE_PHONE1=?,STORE_HOURS=?,STORE_MAP=?,STORE_CITY=?,STORE_DISTRICT=?,STORE_ADDRESS=?,STORE_URL=?,STORE_WEB=?,STORE_ACC=?,STORE_PWD=?,STORE_MAIL=?,STORE_COM_ID=?,STORE_COM_ADDRESS=?,"
                + "STORE_TW_ID=?,STORE_PHONE2=?,STORE_TEXT=?,STORE_PLAN=?,STORE_NPLAN=?,STORE_ONTIME=?,STORE_ETIME=?,STORE_TABLE=?,STORE_ETABLE=? where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, Store.getEmpId());
            pstmt.setString(2, Store.getStoreName());
            pstmt.setString(3, Store.getStorePhone1());
            pstmt.setString(4, Store.getStoreHours());
            pstmt.setString(5, Store.getStoreMap());
            pstmt.setString(6, Store.getStoreCity());
            pstmt.setString(7, Store.getStoreDistrict());
            pstmt.setString(8, Store.getStoreAddress());
            pstmt.setString(9, Store.getStoreUrl());
            pstmt.setString(10, Store.getStoreWeb());
            pstmt.setString(11, Store.getStoreAcc());
            pstmt.setString(12, Store.getStorePwd());
            pstmt.setString(13, Store.getStoreMail());
            pstmt.setString(14, Store.getStoreComId());
            pstmt.setString(15, Store.getStoreComAddress());
            pstmt.setString(16, Store.getStoreTwId());
            pstmt.setString(17, Store.getStorePhone2());
            pstmt.setString(18, Store.getStoreText());
            pstmt.setInt(19, Store.getStorePlan());
            pstmt.setInt(20, Store.getStoreNplan());
            pstmt.setDate(21, Store.getStoreOntime());
            pstmt.setString(22, Store.getStoreEtime());
            pstmt.setInt(23, Store.getStoreTable());
            pstmt.setInt(24, Store.getStoreEtable());
            pstmt.setInt(25, Store.getStoreId());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void delete(Integer storeId) {

        String sql = "DELETE FROM cga105g2.store where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, storeId);

            pstmt.executeUpdate();

            // Handle any driver errors

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }

    }

    @Override
    public Store getById(Integer storeId) {

        String sql = "SELECT STORE_ID,EMP_ID,STORE_STATUS,STORE_NAME,STORE_PHONE1,STORE_HOURS,STORE_MAP,STORE_CITY,STORE_DISTRICT,STORE_ADDRESS,STORE_URL,STORE_WEB,STORE_ACC,STORE_PWD,STORE_MAIL,STORE_COM_ID,STORE_COM_ADDRESS,STORE_TW_ID,STORE_PHONE2,STORE_TEXT,STORE_PLAN,STORE_NPLAN,STORE_TIME,STORE_ONTIME,STORE_RETIME,STORE_ETIME,STORE_TABLE,STORE_ETABLE FROM cga105g2.store where STORE_ID = ?";

        Store store = null;
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, storeId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setEmpId(rs.getInt("EMP_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStorePhone1(rs.getString("STORE_PHONE1"));
                store.setStoreHours(rs.getString("STORE_HOURS"));
                store.setStoreMap(rs.getString("STORE_MAP"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAddress(rs.getString("STORE_ADDRESS"));
                store.setStoreUrl(rs.getString("STORE_URL"));
                store.setStoreWeb(rs.getString("STORE_WEB"));
                store.setStoreAcc(rs.getString("STORE_ACC"));
                store.setStorePwd(rs.getString("STORE_PWD"));
                store.setStoreMail(rs.getString("STORE_MAIL"));
                store.setStoreComId(rs.getString("STORE_COM_ID"));
                store.setStoreComAddress(rs.getString("STORE_COM_ADDRESS"));
                store.setStoreTwId(rs.getString("STORE_TW_ID"));
                store.setStorePhone2(rs.getString("STORE_PHONE2"));
                store.setStoreText(rs.getString("STORE_TEXT"));
                store.setStorePlan(rs.getInt("STORE_PLAN"));
                store.setStoreNplan(rs.getInt("STORE_NPLAN"));
                store.setStoreTime(rs.getTimestamp("STORE_TIME"));
                store.setStoreOntime(rs.getDate("STORE_ONTIME"));
                store.setStoreRetime(rs.getTimestamp("STORE_RETIME"));
                store.setStoreEtime(rs.getString("STORE_ETIME"));
                store.setStoreTable(rs.getInt("STORE_TABLE"));
                store.setStoreEtable(rs.getInt("STORE_ETABLE"));

            }
            // Handle any driver errors

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return store;
    }


    public List<Store> getByStoreName(String storeName) { //要叫louie新增
        String sql = "SELECT * FROM cga105g2.store where STORE_NAME like ?";
        List<Store> list = new ArrayList<Store>();
        Store store = new Store();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, "%" + storeName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setEmpId(rs.getInt("EMP_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStorePhone1(rs.getString("STORE_PHONE1"));
                store.setStoreHours(rs.getString("STORE_HOURS"));
                store.setStoreMap(rs.getString("STORE_MAP"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAddress(rs.getString("STORE_ADDRESS"));
                store.setStoreUrl(rs.getString("STORE_URL"));
                store.setStoreWeb(rs.getString("STORE_WEB"));
                store.setStoreAcc(rs.getString("STORE_ACC"));
                store.setStorePwd(rs.getString("STORE_PWD"));
                store.setStoreMail(rs.getString("STORE_MAIL"));
                store.setStoreComId(rs.getString("STORE_COM_ID"));
                store.setStoreComAddress(rs.getString("STORE_COM_ADDRESS"));
                store.setStoreTwId(rs.getString("STORE_TW_ID"));
                store.setStorePhone2(rs.getString("STORE_PHONE2"));
                store.setStoreText(rs.getString("STORE_TEXT"));
                store.setStorePlan(rs.getInt("STORE_PLAN"));
                store.setStoreNplan(rs.getInt("STORE_NPLAN"));
                store.setStoreTime(rs.getTimestamp("STORE_TIME"));
                store.setStoreOntime(rs.getDate("STORE_ONTIME"));
                store.setStoreRetime(rs.getTimestamp("STORE_RETIME"));
                store.setStoreEtime(rs.getString("STORE_ETIME"));
                store.setStoreTable(rs.getInt("STORE_TABLE"));
                store.setStoreEtable(rs.getInt("STORE_ETABLE"));
                list.add(store); // Store the row in the list
            }
            return list;
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }
    }


    @Override
    public List<Store> getAll() {

        String sql = "SELECT STORE_ID,EMP_ID,STORE_STATUS,STORE_NAME,STORE_PHONE1,STORE_HOURS,STORE_MAP,STORE_CITY,STORE_DISTRICT,STORE_ADDRESS,STORE_URL,STORE_WEB,STORE_ACC,STORE_PWD,STORE_MAIL,STORE_COM_ID,STORE_COM_ADDRESS,STORE_TW_ID,STORE_PHONE2,STORE_TEXT,STORE_PLAN,STORE_NPLAN,STORE_TIME,STORE_ONTIME,STORE_RETIME,STORE_ETIME,STORE_TABLE,STORE_ETABLE FROM cga105g2.store order by STORE_ID";

        List<Store> list = new ArrayList<Store>();
        Store store = null;

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setEmpId(rs.getInt("EMP_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStorePhone1(rs.getString("STORE_PHONE1"));
                store.setStoreHours(rs.getString("STORE_HOURS"));
                store.setStoreMap(rs.getString("STORE_MAP"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAddress(rs.getString("STORE_ADDRESS"));
                store.setStoreUrl(rs.getString("STORE_URL"));
                store.setStoreWeb(rs.getString("STORE_WEB"));
                store.setStoreAcc(rs.getString("STORE_ACC"));
                store.setStorePwd(rs.getString("STORE_PWD"));
                store.setStoreMail(rs.getString("STORE_MAIL"));
                store.setStoreComId(rs.getString("STORE_COM_ID"));
                store.setStoreComAddress(rs.getString("STORE_COM_ADDRESS"));
                store.setStoreTwId(rs.getString("STORE_TW_ID"));
                store.setStorePhone2(rs.getString("STORE_PHONE2"));
                store.setStoreText(rs.getString("STORE_TEXT"));
                store.setStorePlan(rs.getInt("STORE_PLAN"));
                store.setStoreNplan(rs.getInt("STORE_NPLAN"));
                store.setStoreTime(rs.getTimestamp("STORE_TIME"));
                store.setStoreOntime(rs.getDate("STORE_ONTIME"));
                store.setStoreRetime(rs.getTimestamp("STORE_RETIME"));
                store.setStoreEtime(rs.getString("STORE_ETIME"));
                store.setStoreTable(rs.getInt("STORE_TABLE"));
                store.setStoreEtable(rs.getInt("STORE_ETABLE"));
                list.add(store); // Store the row in the list
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }
        return list;
    }

    @Override
    public void updateStatus(Integer storeId, Integer storeStatus) {
        String sql = "UPDATE cga105g2.store set STORE_STATUS=? where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, storeStatus);
            pstmt.setInt(2, storeId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void updateempId(Integer storeId, Integer empId) {
        String sql = "UPDATE cga105g2.store set EMP_ID=? where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, empId);
            pstmt.setInt(2, storeId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void updatePlan(Integer storeId, Integer storePlan) {
        String sql = "UPDATE cga105g2.store set STORE_PLAN=? where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, storePlan);
            pstmt.setInt(2, storeId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void updateNplan(Integer storeId, Integer storeNplan) {
        String sql = "UPDATE cga105g2.store set STORE_NPLAN=? where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, storeNplan);
            pstmt.setInt(2, storeId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void updateordersetting(Integer storeId, String storeEtime, Integer storeTable, Integer storeEtable) {
        String sql = "UPDATE cga105g2.store set STORE_ETIME=?,STORE_TABLE=?,STORE_ETABLE=? where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setString(1, storeEtime);
            pstmt.setInt(2, storeTable);
            pstmt.setInt(3, storeEtable);
            pstmt.setInt(4, storeId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public Store signin(String storeAcc, String storePwd) {
        String sql = "SELECT * FROM cga105g2.store where STORE_ACC = ? AND STORE_PWD = ?";
        Store store = new Store();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, storeAcc);
            pstmt.setString(2, storePwd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // memberVO 也稱為 Domain objects
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
            } else {
                store.setStoreId(0);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return store;
    }
    @Override
    public List<Store> getBySsta(Integer sta) {
        String sql = "SELECT * FROM cga105g2.store where STORE_STATUS=?";
        List<Store> list = new ArrayList<Store>();
        Store store = new Store();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1,sta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setEmpId(rs.getInt("EMP_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAcc(rs.getString("STORE_ACC"));
                store.setStoreComAddress(rs.getString("STORE_COM_ADDRESS"));
                store.setStorePhone2(rs.getString("STORE_PHONE2"));
                store.setStoreMail(rs.getString("STORE_MAIL"));
                store.setStoreComId(rs.getString("STORE_COM_ID"));
                store.setStoreTwId(rs.getString("STORE_TW_ID"));
                list.add(store); // Store the row in the list
            }
            return list;
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }
    }





    public static void main(String[] args) {
        StoreDAO dao = new StoreDAO();

        // 新增
//		Store storeVO1 = new Store();
//		storeVO1.setEmpId(1);
//		storeVO1.setStoreName("素食");
//		storeVO1.setStorePhone1("28888888");
//		storeVO1.setStoreHours("基本每天");
//		storeVO1.setStoreCity("桃園市");
//		storeVO1.setStoreDistrict("中壢區");
//		storeVO1.setStoreAddress("麥旁邊");
//		storeVO1.setStoreUrl("https://maps.google.com/?cid=666666");
//		storeVO1.setStoreWeb("https://www.facebook.com/%%%%%%%%%%");
//		storeVO1.setStoreAcc("sufood");
//		storeVO1.setStorePwd("123456");
//		storeVO1.setStoreMail("sufood@gmail.com");
//		storeVO1.setStoreComId("");
//		storeVO1.setStoreComAddress("");
//		storeVO1.setStoreTwId("");
//		storeVO1.setStorePhone2("0966666666");
//
//		dao.insert(storeVO1);

        // 修改
//		Store storeVO2 = new Store();
//		
//		storeVO2.setEmpId(1);
//		storeVO2.setStoreName("素食");
//		storeVO2.setStorePhone1("28000000");
//		storeVO2.setStoreHours("不開了");
//		storeVO2.setStoreMap("66.66 ,66.66");
//		storeVO2.setStoreCity("桃園市");
//		storeVO2.setStoreDistrict("中壢區");
//		storeVO2.setStoreAddress("麥旁邊");
//		storeVO2.setStoreUrl("https://maps.google.com/?cid=666666");
//		storeVO2.setStoreWeb("https://www.facebook.com/%%%%%%%%%%");
//		storeVO2.setStoreAcc("sufood123");
//		storeVO2.setStorePwd("123456789");
//		storeVO2.setStoreMail("sufood@gmail.com");
//		storeVO2.setStoreComId("");
//		storeVO2.setStoreComAddress("");
//		storeVO2.setStoreTwId("");
//		storeVO2.setStorePhone2("0966666666");
//		storeVO2.setStoreText("壞壞");
//		storeVO2.setStorePlan(0);
//		storeVO2.setStoreNplan(0);
//		storeVO2.setStoreOntime(java.sql.Date.valueOf("2023-01-01"));
//		storeVO2.setStoreEtime("");
//		storeVO2.setStoreTable(55);
//		storeVO2.setStoreEtable(55);
//		storeVO2.setStoreId(377);
//		
//		dao.update(storeVO2);
//
//		// 刪除
//		dao.delete(376);
//
//		// 查詢
//		Store storeVO3 = dao.getById(1);
//		System.out.print(storeVO3.getStoreId() + ",");
//		System.out.print(storeVO3.getEmpId() + ",");
//		System.out.print(storeVO3.getStoreStatus() + ",");
//		System.out.print(storeVO3.getStoreName() + ",");
//		System.out.print(storeVO3.getStorePhone1() + ",");
//		System.out.print(storeVO3.getStoreHours() + ",");
//		System.out.print(storeVO3.getStoreMap() + ",");
//		System.out.print(storeVO3.getStoreCity() + ",");
//		System.out.print(storeVO3.getStoreDistrict() + ",");
//		System.out.print(storeVO3.getStoreAddress() + ",");
//		System.out.print(storeVO3.getStoreUrl() + ",");
//		System.out.print(storeVO3.getStoreWeb() + ",");
//		System.out.print(storeVO3.getStoreAcc() + ",");
//		System.out.print(storeVO3.getStorePwd() + ",");
//		System.out.print(storeVO3.getStoreMail() + ",");
//		System.out.print(storeVO3.getStoreComId() + ",");
//		System.out.print(storeVO3.getStoreComAddress() + ",");
//		System.out.print(storeVO3.getStoreTwId() + ",");
//		System.out.print(storeVO3.getStorePhone2() + ",");
//		System.out.print(storeVO3.getStoreText() + ",");
//		System.out.print(storeVO3.getStorePlan() + ",");
//		System.out.print(storeVO3.getStoreNplan() + ",");
//		System.out.print(storeVO3.getStoreTime() + ",");
//		System.out.print(storeVO3.getStoreOntime() + ",");
//		System.out.print(storeVO3.getStoreRetime() + ",");
//		System.out.print(storeVO3.getStoreEtime() + ",");
//		System.out.print(storeVO3.getStoreTable() + ",");
//		System.out.print(storeVO3.getStoreEtable() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
        List<Store> list = dao.getAll();
        for (Store astore : list) {
            System.out.print(astore.getStoreId() + ",");
            System.out.print(astore.getEmpId() + ",");
            System.out.print(astore.getStoreStatus() + ",");
            System.out.print(astore.getStoreName() + ",");
            System.out.print(astore.getStorePhone1() + ",");
            System.out.print(astore.getStoreHours() + ",");
            System.out.print(astore.getStoreMap() + ",");
            System.out.print(astore.getStoreCity() + ",");
            System.out.print(astore.getStoreDistrict() + ",");
            System.out.print(astore.getStoreAddress() + ",");
            System.out.print(astore.getStoreUrl() + ",");
            System.out.print(astore.getStoreWeb() + ",");
            System.out.print(astore.getStoreAcc() + ",");
            System.out.print(astore.getStorePwd() + ",");
            System.out.print(astore.getStoreMail() + ",");
            System.out.print(astore.getStoreComId() + ",");
            System.out.print(astore.getStoreComAddress() + ",");
            System.out.print(astore.getStoreTwId());
            System.out.println();
        }

//		storeId修改storeStatus
//		dao.updateStatus(4, 1);
//		storeId修改empId
//		dao.updateempId(4, 3);
//		storeId修改目前/次月方案
//		dao.updatePlan(4, 2);
//		dao.updateNplan(4, 3);
//		storeId修改時段/桌數/定位上限數	
//		dao.updateordersetting(4, "11:00", 66, 56);
    }

	@Override
	public void update3(Store store) {
		String sql = "UPDATE cga105g2.store set STORE_PWD=? where STORE_ACC=?";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			pstmt.setString(1, store.getStorePwd());
			pstmt.setString(2, store.getStoreAcc());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public void update4(Store store) {
		String sql = "UPDATE cga105g2.store set STORE_PWD=? where STORE_ID=?";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			pstmt.setString(1, store.getStorePwd());
			pstmt.setInt(2, store.getStoreId());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}


}
