package com.goods.model.Goods.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.goods.model.Goods.dao.GoodsDAO_interface;
import com.goods.model.Goods.pojo.Goods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodsJDBCDAO extends Common implements GoodsDAO_interface {


    @Override
    public void insert(Goods Goods) {
        final String sql = "INSERT INTO cga105g2.goods (store_id,goods_img,goods_name,goods_status,goods_price,goods_text) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, Goods.getStoreId());
            pstmt.setBytes(2, Goods.getGoodsImg());
            pstmt.setString(3, Goods.getGoodsName());
            pstmt.setInt(4, Goods.getGoodsStatus());
            pstmt.setInt(5, Goods.getGoodsPrice());
            pstmt.setString(6, Goods.getGoodsText());
            pstmt.executeUpdate();
            close();
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
    public void update(Goods Goods) {
        final String sql = "UPDATE cga105g2.goods set store_id=?, goods_img=?, goods_name=?, goods_status=?, goods_price=?, goods_text=? where goods_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, Goods.getStoreId());
            pstmt.setBytes(2, Goods.getGoodsImg());
            pstmt.setString(3, Goods.getGoodsName());
            pstmt.setInt(4, Goods.getGoodsStatus());
            pstmt.setInt(5, Goods.getGoodsPrice());
            pstmt.setString(6, Goods.getGoodsText());
            pstmt.setInt(7, Goods.getGoodsId());
            pstmt.executeUpdate();
            close();
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
    public void deleteById(Integer goodsno) {
        final String sql = "DELETE FROM cga105g2.goods where goods_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, goodsno);
            pstmt.executeUpdate();
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.DELETE_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public Goods getById(Integer goodsno) {
        final String sql = "SELECT * FROM cga105g2.goods where goods_id = ?";
        Goods goods = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, goodsno);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                goods = new Goods();
                goods.setGoodsId(rs.getInt("goods_id"));
                goods.setGoodsImg(rs.getBytes("goods_img"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsStatus(rs.getInt("goods_status"));
                goods.setGoodsPrice(rs.getInt("goods_price"));
                goods.setGoodsText(rs.getString("goods_text"));
                goods.setGoodsTime(rs.getTimestamp("goods_time"));
                goods.setGoodsRtime(rs.getTimestamp("goods_rtime"));
                goods.setStoreId(rs.getInt("store_id"));
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return goods;
    }

    @Override
    public List<Goods> getByStoreId(Integer id) {
        final String sql = "SELECT goods_id, store_id, goods_img, goods_name, goods_status, goods_price, goods_text, goods_time, goods_rtime FROM cga105g2.goods WHERE store_id = ?;  ";
        List<Goods> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setGoodsId(rs.getInt("goods_id"));
                goods.setStoreId(rs.getInt("store_id"));
                goods.setGoodsImg(rs.getBytes("goods_img"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsStatus(rs.getInt("goods_status"));
                goods.setGoodsPrice(rs.getInt("goods_price"));
                goods.setGoodsText(rs.getString("goods_text"));
                goods.setGoodsTime(rs.getTimestamp("goods_time"));
                goods.setGoodsRtime(rs.getTimestamp("goods_rtime"));
                list.add(goods);
            }
            close();
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
    public List<Goods> getAll() {
        final String sql = "SELECT * FROM cga105g2.goods ";
        List<Goods> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setGoodsId(rs.getInt("goods_id"));
                goods.setStoreId(rs.getInt("store_id"));
                goods.setGoodsImg(rs.getBytes("goods_img"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsStatus(rs.getInt("goods_status"));
                goods.setGoodsPrice(rs.getInt("goods_price"));
                goods.setGoodsText(rs.getString("goods_text"));
                goods.setGoodsTime(rs.getTimestamp("goods_time"));
                goods.setGoodsRtime(rs.getTimestamp("goods_rtime"));
                list.add(goods);
            }
            close();
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
    public List<Goods> getAllGoods(Map<String, String[]> map) {
        List<Goods> list = new ArrayList<>();
        String sql = "select * from goods " + getWhereCondition(map) + "order by goods_id";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setGoodsId(rs.getInt("goods_id"));
                goods.setStoreId(rs.getInt("store_id"));
                goods.setGoodsImg(rs.getBytes("goods_img"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsStatus(rs.getInt("goods_status"));
                goods.setGoodsPrice(rs.getInt("goods_price"));
                goods.setGoodsText(rs.getString("goods_text"));
                goods.setGoodsTime(rs.getTimestamp("goods_time"));
                goods.setGoodsRtime(rs.getTimestamp("goods_rtime"));
                list.add(goods);
            }
            close();
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


    public static String getConditionerMdb(String columnName, String value) {
        String aCondition = null;
        if ("goods_name".equals(columnName))
            aCondition = columnName + " like '%" + value + "%' ";
        return aCondition;
    }

    public static String getWhereCondition(Map<String, String[]> map) {
        Set<String> keys = map.keySet();
        StringBuilder whereCondition = new StringBuilder();
        int count = 0;
        for (String key : keys) {
            String value = map.get(key)[0];
            if (value != null && !value.trim().isEmpty() && !"action".equals(key)) {
                count++;
                String aCondition = getConditionerMdb(key, value.trim());
                if (count == 1)
                    whereCondition.append(String.format(" where %s ", aCondition));
                logger.info("有送出查詢資料的欄位數count = " + count);
            }
        }
        return whereCondition.toString();
    }


}
