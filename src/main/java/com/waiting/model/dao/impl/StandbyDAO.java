package com.waiting.model.dao.impl;

import com.waiting.model.dao.StandbyDAO_interface;
import com.waiting.model.pojo.Standby;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.URL;
import static com.core.common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandbyDAO implements StandbyDAO_interface {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Standby standbyVo) {
		String INSERT_STMT = " INSERT INTO cga105g2.standby(`STORE_ID`, `STA_NAME`, `STA_PHONE`, `STA_NUMBER`) VALUES  (?, ?, ?, ?)";

//		java.util.Date utilDate = new java.util.Date();
//
//		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(INSERT_STMT);) {

			pstm.setInt(1, standbyVo.getStoreId());
			pstm.setString(2, standbyVo.getStaName());
			pstm.setString(3, standbyVo.getStaPhone());
			pstm.setInt(4, standbyVo.getStaNumber());

			pstm.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}

	}

	@Override
	public void update(Standby standbyVo) {
		String UPDATE = "update cga105g2.`standby` set sta_status =? where sta_id =?;";
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(UPDATE)) {

			pstm.setInt(1, standbyVo.getStaStatus());
			pstm.setInt(2, standbyVo.getStaId());

			pstm.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public void delete(Integer staId) {
		String DELETE = "DELETE FROM cga105g2.`standby` where sta_id = ?";
//						"DELETE FROM STANDBY LIMIT 1;"
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(DELETE);) {

			pstm.setInt(1, staId);

			pstm.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public Standby findByPrimaryKey(Integer staId) {
		String GET_ONE_STMT = "select  sta_id ,store_id,sta_name,sta_phone,sta_number,sta_number,sta_time,sta_status from cga105g2.`standby`where STA_ID = ?;";

		Standby standbyVo = null;

		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(GET_ONE_STMT);) {

			pstm.setInt(1, staId);

			rs = pstm.executeQuery();

			while (rs.next()) {
				standbyVo = new Standby();
				standbyVo.setStaId(rs.getInt("sta_Id"));
				standbyVo.setStoreId(rs.getInt("store_Id"));
				standbyVo.setStaName(rs.getString("sta_Name"));
				standbyVo.setStaPhone(rs.getString("sta_Phone"));
				standbyVo.setStaNumber(rs.getInt("sta_Number"));
				standbyVo.setStaTime(rs.getTimestamp("sta_Time"));
				standbyVo.setStaStatus(rs.getInt("sta_Status"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return standbyVo;

	}

	@Override
	public List<Standby> getAll() {
		String GET_ALL_STMT = "SELECT `STA_ID`, `STORE_ID`, `STA_NAME`, `STA_PHONE`, `STA_NUMBER`, `STA_TIME`, `STA_STATUS`\r\n"
				+ "FROM cga105g2.`standby` order by sta_id";
		List<Standby> list = new ArrayList<Standby>();
		Standby standbyVo = null;

		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(GET_ALL_STMT);) {

			rs = pstm.executeQuery();
			while (rs.next()) {
				standbyVo = new Standby();
				standbyVo.setStaId(rs.getInt("sta_Id"));
				standbyVo.setStoreId(rs.getInt("store_Id"));
				standbyVo.setStaName(rs.getString("Sta_Name"));
				standbyVo.setStaPhone(rs.getString("sta_Phone"));
				standbyVo.setStaNumber(rs.getInt("sta_Number"));
				standbyVo.setStaTime(rs.getTimestamp("sta_Time"));
				standbyVo.setStaStatus(rs.getInt("sta_Status"));

				list.add(standbyVo);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return list;
	}

	@Override
	public Integer standByCount() {
		String sql = "SELECT COUNT(1) FROM cga105g2.STANDBY;";
		int staCount = 0;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(sql)) {

			rs = pstm.executeQuery();
			while (rs.next()) {

				staCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staCount;
	}

	public static void main(String[] args) {
		StandbyDAO dao = new StandbyDAO();

		// insert
		Standby standbyVo = new Standby();
//		java.util.Date utilDate = new java.util.Date();
//
//		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
		standbyVo.setStoreId(1);
		standbyVo.setStaName("887");
		standbyVo.setStaPhone("099990005");
		standbyVo.setStaNumber(1);
//		waitingVo1.setSta_time(sqlTimestamp);
//		waitingVo1.setSta_status(1);

		dao.insert(standbyVo);
		System.out.println("insert ok");
////		

//		// update
//		Standby standbyVo2 = new Standby();
////		
//		standbyVo2.setStaId(1);
//		standbyVo2.setStaStatus(2);
//		dao.update(standbyVo2);
//		System.out.println("update OK");

		// delete
//		dao.delete(1);
//		System.out.println("delete ok");

//		findByPrimaryKey
		Standby standbyVo3 = dao.findByPrimaryKey(1);
//		System.out.println(standbyVo3.getStoreId());
//		System.out.println(standbyVo3.getStaName());
//		System.out.println(standbyVo3.getStaPhone());
//		System.out.println(standbyVo3.getStaNumber());
//		System.out.println(standbyVo3.getStaTime());
//		System.out.println(standbyVo3.getStaStatus());

		// getall
		List<Standby> list = dao.getAll();
		for (Standby waitingVo4 : list) {
			System.out.println(waitingVo4.getStaId());
			System.out.println(waitingVo4.getStoreId());
			System.out.println(waitingVo4.getStaName());
			System.out.println(waitingVo4.getStaPhone());
			System.out.println(waitingVo4.getStaNumber());
			System.out.println(waitingVo4.getStaStatus());
			System.out.println("------------------------");
		}

//		count
		System.out.println(dao.standByCount());
	}
}
