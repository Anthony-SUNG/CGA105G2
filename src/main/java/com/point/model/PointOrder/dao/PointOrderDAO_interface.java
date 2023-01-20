package com.point.model.PointOrder.dao;

import com.point.model.PointOrder.pojo.PointOrder;

import java.util.List;

public interface PointOrderDAO_interface {

	void insert(PointOrder pointorder);

	void update(PointOrder pointorder);

	void delete(Integer po_id);

	PointOrder getByPK(Integer po_id);

	List<PointOrder> getAll();

}