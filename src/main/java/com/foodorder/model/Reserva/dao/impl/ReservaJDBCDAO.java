package com.foodorder.model.Reserva.dao.impl;

import com.core.common.Common;
import com.foodorder.model.Reserva.dao.ReservaDAO_interface;
import com.foodorder.model.Reserva.pojo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReservaJDBCDAO implements ReservaDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/cga105g2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Aa12345";
//
//	private static final String INSERT_STMT =
//		"INSERT INTO reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_STATUS, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT =
//		"SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM reserva order by REN_ID";
//	private static final String GET_ONE_STMT =
//		"SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM reserva where REN_ID = ?";
//	private static final String DELETE =
//		"DELETE FROM reserva where REN_ID = ?";
//	private static final String UPDATE =
//		"UPDATE reserva set STORE_ID=?, MEM_ID=?, REN_NAME=?, REN_PHONE=?, REN_TIME=?, REN_STATUS=?, REN_TABLE=?, REN_DATE=?, REN_CURDATE=?, REN_HEADCOUNT=?, CODE_ID=?, REN_PRICE=?, REN_FPRICE=? where REN_ID = ?";
static {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	@Override
	public void insert(Reserva reservaVO) {
		if (reservaVO.getCodeId() != null) {
			// sql寫mySQL語法
			String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// JDBC_mySQL 講義P8 創建連線物件，可重複使用
			System.out.println("2");
			try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				 // JDBC_mySQL 講義P15
				 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY)) {
				// Class.forName("com.mysql.cj.jdbc.Driver"); 不用寫，請參考JDBC_mySQL 講義P7 JDBC
				// 4.0開始會⾃動註冊，呼叫 Class.forName() 是之前載入JDBC Driver的⽅式，現在可以省略不寫
				// 本專案jar檔為8.0.31版本
				// 以下為寫pstmt.set內容
				System.out.println("1");
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
				System.out.println("3");
				// 送出
				pstmt.executeUpdate();
				// 接收
				// ResultSet rs=pstmt.executeQuery();
				// while (rs.next()){
				// new一個物件，set進去，方法將void改成回傳VO，最後return new出來的物件
				// }
				// getall則是在try外宣告一個List<VO> list=new ArrayList<VO>();
				// 在此處while裡面的最後list.add(new的物件)
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			}
		}
		if (reservaVO.getCodeId() == null) {
			// sql寫mySQL語法
			String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// JDBC_mySQL 講義P8 創建連線物件，可重複使用
			try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
					// JDBC_mySQL 講義P15
					PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY)) {
				// Class.forName("com.mysql.cj.jdbc.Driver"); 不用寫，請參考JDBC_mySQL 講義P7 JDBC
				// 4.0開始會⾃動註冊，呼叫 Class.forName() 是之前載入JDBC Driver的⽅式，現在可以省略不寫
				// 本專案jar檔為8.0.31版本
				// 以下為寫pstmt.set內容
				pstmt.setInt(1, reservaVO.getStoreId());
				pstmt.setInt(2, reservaVO.getMemId());
				pstmt.setString(3, reservaVO.getRenName());
				pstmt.setString(4, reservaVO.getRenPhone());
				pstmt.setString(5, reservaVO.getRenTime());
				pstmt.setDate(6, reservaVO.getRenDate());
				pstmt.setInt(7, reservaVO.getRenHeadcount());
				pstmt.setInt(8, reservaVO.getRenPrice());
				pstmt.setInt(9, reservaVO.getRenFprice());
				System.out.println("3");
				// 送出
				pstmt.executeUpdate();
				// 接收
				// ResultSet rs=pstmt.executeQuery();
				// while (rs.next()){
				// new一個物件，set進去，方法將void改成回傳VO，最後return new出來的物件
				// }
				// getall則是在try外宣告一個List<VO> list=new ArrayList<VO>();
				// 在此處while裡面的最後list.add(new的物件)
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			}
		}

	}

	@Override
	public void update(Reserva reservaVO) {
		long miliseconds = System.currentTimeMillis();
		String sql = "UPDATE cga105g2.reserva set REN_STATUS=?, REN_TABLE=?, REN_DATE=? where REN_ID = ?";

		Reserva reserva_new = reservaVO;
		Reserva reserva_old = new Reserva();
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			reserva_old = getById(reserva_new.getRenId());
			pstmt.setInt(1, reserva_old.getRenStatus());
			pstmt.setInt(2, reserva_old.getRenTable());
			pstmt.setDate(3, reserva_old.getRenDate());
			pstmt.setInt(4, reserva_old.getRenId());
			if (reserva_new.getRenStatus() != null) {
				pstmt.setInt(1, reserva_new.getRenStatus());
//				pstmt.setDate(3, new Date(miliseconds));
			}
			if (reserva_new.getRenTable() != null) {
				pstmt.setInt(2, reserva_new.getRenTable());
			} else {
				// 因為初始值為null,如果不寫預設帶入0
				pstmt.setNull(2, Types.NULL);
			}
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public Reserva getById(Integer id) {
		String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where REN_ID = ?";
		Reserva reserva = null;
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
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
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return reserva;
	}

	@Override
	public List<Reserva> getAll() {
		String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva order by REN_ID";
		Reserva reserva = null;
		List<Reserva> list = new ArrayList<Reserva>();
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
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
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	public List<Reserva> getByStatus(Integer renStatus) {
		List<Reserva> list = new ArrayList<Reserva>();
		String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where REN_STATUS = ?";
		Reserva reserva = null;
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, renStatus);
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
				System.out.println(rs.getInt("REN_ID"));
				list.add(reserva);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		System.out.println(list.size());
		return list;
	}

	public void failUpdate(Integer renStatus) {
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);
		System.out.println(today);
		for (Reserva e : getByStatus(renStatus)) {
			System.out.println(e.getRenDate());
			System.out.println(today);
			System.out.println(e.getRenTable());
			if (e.getRenDate().before(today)) {
				Reserva reserva_old = new Reserva();
				reserva_old.setRenId(e.getRenId());
				reserva_old.setRenStatus(4);// 4為失效
				System.out.println(e.getRenId());
				update(reserva_old);
			}
		}
	}

	@Override
	public List<Reserva> getByStoreIdRendate(Integer storeid, String rendate,String rentime, Integer renstatus) {
		List<Reserva> list = new ArrayList<Reserva>();
		String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where STORE_ID = ? and REN_DATE = ? and REN_TIME = ? and REN_STATUS = ?";
		Reserva reserva = null;
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
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
//				System.out.println(rs.getInt("REN_ID"));
				list.add(reserva);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
//		System.out.println(list.size());
		return list;
	}

	public void insertWithReservaDetails(Reserva reservaVO, List<Map> list) {
		String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				PreparedStatement pstmt2 = con.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY)) {
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
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
			Integer next_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_id = rs.getInt(1);
				System.out.println("自增主鍵值= " + next_id +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			for (int i = 0; i<list.size(); i++) {
				pstmt2.setInt(1, next_id);
				pstmt2.setInt(2, (Integer) list.get(i).get("mealId"));
				pstmt2.setInt(3, (Integer) list.get(i).get("rdQuantity"));
				pstmt2.setInt(4, (Integer) list.get(i).get("pdPrice"));
				pstmt2.executeUpdate();
			}
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	public static void main(String[] args) {
		ReservaJDBCDAO dao = new ReservaJDBCDAO();
//		// 新增
//		Reserva reserva1 = new Reserva(6, 4, "林嘉誠", "0912345678", "12:00", Date.valueOf("2023-01-01"), 4, 800, 800);
//		dao.insert(reserva1);
//		Reserva reserva2 = new Reserva(6, 5, "高瑜蔓", "0912345678", "11:00", Date.valueOf("2023-01-01"), 4, 2, 800, 700);
//		dao.insert(reserva2);
//		// 修改
//		Reserva reserva3 = new Reserva(3,2,2);
//		dao.update(reserva3);
//		// getbyid
//		Reserva reserva3 = new Reserva(3,2,2);
//		Reserva reserva4 = dao.getById(reserva3.getCodeId());
//		Reserva reserva4 = dao.getById(6);
//		System.out.println(reserva4.getRenId());
//		System.out.println(reserva4.getRenName());
//		System.out.println("*******************************************");
//		// 排程失效failUpdate()
//		dao.failUpdate(0);
//		dao.failUpdate(2);
		// getall
//		List<Reserva> list = dao.getAll();
//		for (Reserva e : list) {
//			System.out.println(e.getRenId());
//			System.out.println(e.getRenName());
//			System.out.println("======================================");
//		}

	}

}
