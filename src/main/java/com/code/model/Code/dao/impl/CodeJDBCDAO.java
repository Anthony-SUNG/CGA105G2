package com.code.model.Code.dao.impl;

import com.code.model.Code.dao.CodeDAO_interface;
import com.code.model.Code.pojo.Code;
import com.core.common.Common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

public class CodeJDBCDAO implements CodeDAO_interface {
    static {
        try {
            Class.forName(Common.DriverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insert(Code pojo) {
        //sql寫mySQL語法
        String sql="INSERT INTO cga105g2.code (STORE_ID,CODE_NUM,CODE_OFF,CODE_TEXT,CODE_NTIME) VALUES (?,?,?,?,?)";;
        //JDBC_mySQL 講義P8 創建連線物件，可重複使用
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);

            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1,pojo.getStoreId());
            pstmt.setString(2,pojo.getCodeNum());
            pstmt.setInt(3,pojo.getCodeOff());
            pstmt.setString(4,pojo.getCodeText());
            pstmt.setDate(5,pojo.getCodeNtime());

            //送出
            pstmt.executeUpdate();
            //接收
            //ResultSet rs=pstmt.executeQuery();
            //while (rs.next()){
                //new一個物件，set進去，方法將void改成回傳VO，最後return new出來的物件
            //}
            //getall則是在try外宣告一個List<VO> list=new ArrayList<VO>();
            //在此處while裡面的最後list.add(new的物件)
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql="DELETE from cga105g2.code where CODE_ID=?";
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
    }

    @Override
    public void update(Code pojo) {
        long miliseconds = System.currentTimeMillis();
        String sql="update cga105g2.code set EMP_ID=?,CODE_STATUS=?,CODE_RTIME=? where CODE_ID=?";
        Code code_new=pojo;
        Code code_old=new Code();
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            code_old=getById(code_new.getCodeId());
            pstmt.setInt(1,code_old.getEmpId());
            pstmt.setInt(2,code_old.getCodeStatus());
            pstmt.setDate(3,code_old.getCodeRtime());
            pstmt.setInt(4,code_old.getCodeId());
            if (code_new.getEmpId()!=null){
                pstmt.setInt(1,code_new.getEmpId());
            };
            if (code_new.getCodeStatus()!=null){
                pstmt.setInt(2,code_new.getCodeStatus());
                pstmt.setDate(3,new Date(miliseconds));
            }
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
    }

    @Override
    public Code getById(Integer id) {
        Code code=new Code();
        code=getByCodeId(id);
        return code;
    }

    public void failUpdate(Integer codeStatus){
        long miliseconds = System.currentTimeMillis();
        Date today=new Date(miliseconds);
        for (Code e:getBy(codeStatus,"狀態")){
            if (e.getCodeNtime().before(today)){
                Code code_old=new Code();
                code_old.setCodeId(e.getCodeId());
                code_old.setEmpId(e.getEmpId());
                code_old.setCodeStatus(3);
                update(code_old);
            }
        }
    }

    public List<Integer> getBycodeNum(String codeNum,Integer storeId) {
        String sql = "select STORE_ID,CODE_NUM,CODE_OFF from cga105g2.code where STORE_ID=? and CODE_STATUS=2";
        List<Integer> list=new ArrayList<>();
        try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("CODE_NUM").equals(codeNum)){
                    list.add(rs.getInt("CODE_OFF"));
                }
            }
            return list;
        }catch (SQLException e){
            list.add(0);
            return list;
        }
    }
    @Override
    public List<Integer> getCodeId(String codeNum,Integer storeId) {
        String sql = "select CODE_ID,STORE_ID,CODE_NUM,CODE_OFF from cga105g2.code where STORE_ID=? and CODE_STATUS=2";
        List<Integer> list=new ArrayList<>();
        try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("CODE_NUM").equals(codeNum)){
                    list.add(rs.getInt("CODE_ID"));
                }
            }
            return list;
        }catch (SQLException e){
            list.add(0);
            return list;
        }
    }


    public Code getByCodeId(Integer id) {
        String sql = "select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code where CODE_ID=?";
        Code code = null;
        try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
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
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
        return code;
    }
    @Override
    public List<Code> getBy(Integer id,String string){
        List<Code> list=new ArrayList<Code>();
        String sql = "select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code";
        if (string.equals("店家")){
            sql+=" where STORE_ID=?;";
        }
        if (string.equals("狀態")){
            sql+=" where CODE_STATUS=?;";
        }
        Code code = null;
        try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
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
                list.add(code);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
        return list;
    }

    @Override
    public List<Code> getAll() {
        String sql="select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code order by CODE_ID";
        List<Code> list=new ArrayList<Code>();
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
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
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        CodeJDBCDAO dao =new CodeJDBCDAO();
        //新增
//            Code code1=new Code(47,"LOVE2",520,"情人節優惠活動，帶她來吃飯，養他一輩子",Date.valueOf("2023-01-01"));
//            dao.insert(code1);
        //修改
//            Code code2=new Code(4,47,3,"LOVE",520,2,"情人節優惠活動，帶她來吃飯，養他一輩子",Date.valueOf("2023-03-30"));
//            Code code2=new Code(8,3);
//            Code code2=new Code(7,3,2);
//            dao.update(code2);
//        //getbyid
//            Code code3=dao.getById(5);
//            Code code3=dao.getById(code2.getCodeId());
//            System.out.println("CODE_ID:"+code3.getCodeId());
//            System.out.println("STORE_ID:"+code3.getStoreId());
//            System.out.println("EMP_ID:"+code3.getEmpId());
//            System.out.println("CODE_NUM:"+code3.getCodeNum());
//            System.out.println("CODE_OFF: $"+code3.getCodeOff());
//            System.out.println("CODE_STATUS:"+code3.getCodeStatus());
//            System.out.println("CODE_TEXT:"+code3.getCodeText());
//            System.out.println("CODE_TIME:"+code3.getCodeTime());
//            System.out.println("CODE_RTIME"+code3.getCodeRtime());
//            System.out.println("CODE_NTIME"+code3.getCodeNtime());
//            System.out.println("*******************************************");
//        //刪除
//            dao.deleteById(4);
          //排程失效failUpdate()
//            dao.failUpdate(2);
//        //getall
            List<Code> list=dao.getAll();
            for(Code e : list){
                System.out.println("CODE_ID:"+e.getCodeId());
                System.out.println("STORE_ID:"+e.getStoreId());
                System.out.println("EMP_ID:"+e.getEmpId());
                System.out.println("CODE_NUM:"+e.getCodeNum());
                System.out.println("CODE_OFF: $"+e.getCodeOff());
                System.out.println("CODE_STATUS:"+e.getCodeStatus());
                System.out.println("CODE_TEXT:"+e.getCodeText());
                System.out.println("CODE_TIME:"+e.getCodeTime());
                System.out.println("CODE_RTIME"+e.getCodeRtime());
                System.out.println("CODE_NTIME"+e.getCodeNtime());
                System.out.println("======================================");
            }

    }
}
