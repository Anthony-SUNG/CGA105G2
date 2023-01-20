package com.point.model.PointGoods.dao;

import java.util.List;

import com.point.model.PointGoods.pojo.PointGoods;

public interface PointGoodsDAO_interface {

	void insert(PointGoods pointgoods);

	void update(PointGoods pointgoods);

	void delete(Integer pd_id);

	PointGoods getByPK(Integer pd_id);

	List<PointGoods> getAll();

}