package com.foodorder.model.Meal.dao.impl;

import com.core.common.Common;
import com.foodorder.model.Meal.dao.MealDAO_interface;
import com.foodorder.model.Meal.pojo.Meal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MealJDBCDAO implements MealDAO_interface {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void insert(Meal mealVO) {
		String sql = "INSERT INTO cga105g2.meal (STORE_ID, MEAL_NAME, MEAL_PRICE, MEAL_STATUS) VALUES (?,?,?,?);";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, mealVO.getStoreId());
			pstmt.setString(2, mealVO.getMealName());
			pstmt.setInt(3, mealVO.getMealPrice());
			pstmt.setInt(4, mealVO.getMealStatus());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public void update(Integer id, Integer status) {
		String sql = "UPDATE cga105g2.meal set MEAL_STATUS=? where MEAL_ID = ?";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, status);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public List<Meal> getAll() {
		List<Meal> list = new ArrayList<Meal>();
		String sql = "select * from cga105g2.meal;";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Meal meal = new Meal();
				meal.setMealId(rs.getInt("MEAL_ID"));
				meal.setStoreId(rs.getInt("STORE_ID"));
				meal.setMealName(rs.getString("MEAL_NAME"));
				meal.setMealPrice(rs.getInt("MEAL_PRICE"));
				meal.setMealStatus(rs.getInt("MEAL_STATUS"));
				list.add(meal);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	@Override
	public List<Meal> getByStoreIdStatus(Integer id, Integer status) {
		List<Meal> list = new ArrayList<Meal>();
		String sql = "select * from cga105g2.meal where";
		String where = null;
		if (status == 0) {
			where = " STORE_ID=? and MEAL_STATUS=0;";
		} else if (status == 1) {
			where = " STORE_ID=? and MEAL_STATUS=1;";
		}

		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql + where, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Meal meal = new Meal();
				meal.setMealId(rs.getInt("MEAL_ID"));
				meal.setStoreId(rs.getInt("STORE_ID"));
				meal.setMealName(rs.getString("MEAL_NAME"));
				meal.setMealPrice(rs.getInt("MEAL_PRICE"));
				meal.setMealStatus(rs.getInt("MEAL_STATUS"));
				list.add(meal);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;

	}

	@Override
	public Meal getByMealId(Integer id) {
		String sql = "select * from cga105g2.meal where MEAL_ID=?;";
		Meal meal = null;
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				meal = new Meal();
				meal.setMealId(rs.getInt("MEAL_ID"));
				meal.setStoreId(rs.getInt("STORE_ID"));
				meal.setMealName(rs.getString("MEAL_NAME"));
				meal.setMealPrice(rs.getInt("MEAL_PRICE"));
				meal.setMealStatus(rs.getInt("MEAL_STATUS"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return meal;
	}

	@Override
	public void MealUpdateCommit(Meal mealVO, Integer mealid) {
		// TODO Auto-generated method stub

	}


//	public static void main(String[] args) {
//		MealJDBCDAO dao = new MealJDBCDAO();
//		// 新增
//		Meal meal1 = new Meal(58, "義大利麵套餐", 250 , 1);
//		dao.insert(meal1);
//
//		// 修改 某餐點編號的狀態 把編號四狀態修改成上架
//		Meal meal2 = new Meal(4, 1);
//		dao.update(meal2);
//		Meal meal3 = new Meal(7, 0);
//		dao.update(meal3);
//
//		// 查詢全部
//		List<Meal> list = dao.getAll();
//		for (Meal e : list) {
//			System.out.println(e.getMealId());
//			System.out.println(e.getMealName());
//			System.out.println("======================================");
//		}
//
//		// 依照storeid 搭配 狀態 查詢 目的:查出該店家所有上架商品
//		List<Meal> list = dao.getByStoreIdStatus(6, 1);
//		for (Meal e : list) {
//			System.out.println(e.getMealId());
//			System.out.println(e.getMealName());
//			System.out.println("======================================");
//		}
//		
//		// 依照mealid 查詢 目的:查出該餐點資訊  如果會員訂位查詢要帶出餐點名稱 訂位id->所有餐點id->不管上架下架都要秀
//		Meal meal = dao.getByMealId(6);
//		
//		System.out.println(meal.getMealId());
//		System.out.println(meal.getMealName());
//		System.out.println("======================================");
//
//		
//		
//
//	}

}
