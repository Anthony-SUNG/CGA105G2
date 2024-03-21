package com.foodorder.model.Meal.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.foodorder.model.Meal.dao.MealDAO_interface;
import com.foodorder.model.Meal.pojo.Meal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MealJDBCDAO extends Common implements MealDAO_interface {


    @Override
    public void insert(Meal mealVO) {
        String sql = "INSERT INTO cga105g2.meal (STORE_ID, MEAL_NAME, MEAL_PRICE, MEAL_STATUS) VALUES (?,?,?,?);";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, mealVO.getStoreId());
            pstmt.setString(2, mealVO.getMealName());
            pstmt.setInt(3, mealVO.getMealPrice());
            pstmt.setInt(4, mealVO.getMealStatus());
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
    public void update(Integer id, Integer status) {
        String sql = "UPDATE cga105g2.meal set MEAL_STATUS=? where MEAL_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, status);
            pstmt.setInt(2, id);
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
    public List<Meal> getAll() {
        List<Meal> list = new ArrayList<>();
        String sql = "select * from cga105g2.meal;";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
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
    public List<Meal> getByStoreIdStatus(Integer id, Integer status) {
        List<Meal> list = new ArrayList<>();
        String sql = "select * from cga105g2.meal where";
        String where = null;
        if (status == 0) {
            where = " STORE_ID=? and MEAL_STATUS=0;";
        } else if (status == 1) {
            where = " STORE_ID=? and MEAL_STATUS=1;";
        }
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql + where)) {
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
    public Meal getByMealId(Integer id) {
        String sql = "select * from cga105g2.meal where MEAL_ID=?;";
        Meal meal = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
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
        return meal;
    }

    @Override
    public void MealUpdateCommit(Meal mealVO, Integer mealid) {
        // TODO Auto-generated method stub

    }
}
