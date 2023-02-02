package com.point.model.PointOrder.dao;

import java.util.List;

import com.point.model.PointOrder.pojo.PointOrder;

public interface PointOrderDAO_interface {

	void insert(PointOrder pointorder);

	void update(PointOrder pointorder);

	void delete(Integer po_id);

	PointOrder getByPK(Integer po_id);

	List<PointOrder> getAll();
	
	List<PointOrder> getBackOrder();

	void updateStatus(PointOrder pointorder);

}