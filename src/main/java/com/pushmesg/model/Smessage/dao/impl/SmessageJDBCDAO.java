package com.pushmesg.model.Smessage.dao.impl;

import com.core.common.Common;
import com.pushmesg.model.Smessage.dao.SmessageDAO_interface;
import com.pushmesg.model.Smessage.pojo.Smessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmessageJDBCDAO implements SmessageDAO_interface {

	@Override
	public void insert(Smessage smessageVO) {
		String sql = "INSERT INTO cga105g2.smessage (SUB_ID, SMESSAGE_TXET) VALUES (?,?);";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, smessageVO.getSubId());
			pstmt.setString(2, smessageVO.getSmessageTxet());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public List<Smessage> getById(Integer id) {
		String sql = "SELECT SMESSAGE_ID, SUB_ID, SMESSAGE_TXET, SMESSAGE_TIME FROM cga105g2.smessage where SUB_ID=? order by SMESSAGE_TIME";
		Smessage smessage = null;
		List<Smessage> list = new ArrayList<Smessage>();
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				smessage = new Smessage();
				smessage.setSmessageId(rs.getInt("SMESSAGE_ID"));
				smessage.setSubId(rs.getInt("SUB_ID"));
				smessage.setSmessageTxet(rs.getString("SMESSAGE_TXET"));
				smessage.setSmessageTime(rs.getTimestamp("SMESSAGE_TIME"));
				list.add(smessage);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	public static void main(String[] args) {
		SmessageJDBCDAO dao = new SmessageJDBCDAO();
		// 新增
//		Smessage smessage1 = new Smessage(3, "店家56發送第二次優惠碼給會員囉");
//		dao.insert(smessage1);
		// 查詢訂閱id=3的所有被推文且按照時間取
		List<Smessage> list = dao.getById(3);
		for (Smessage e : list) {
			System.out.println(e.getSmessageId());
			System.out.println(e.getSubId());
			System.out.println(e.getSmessageTxet());
			System.out.println(e.getSmessageTime());
			System.out.println("======================================");
		}

	}

}
