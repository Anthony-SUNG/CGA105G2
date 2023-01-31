package com.point.model.service;

import java.util.List;

import com.point.model.PointOrder.dao.impl.PointOrderDAO_interface;
import com.point.model.PointOrder.dao.PointOrderDAO;
import com.point.model.PointOrder.pojo.PointOrder;

public class PointOrderService {
	private PointOrderDAO_interface dao;

	public PointOrderService() {
		dao = new PointOrderDAO();
	}

	public PointOrder addPointOrder(Integer memId, Integer pdId, Integer poPrice, String poText, Integer poStatus, java.sql.Timestamp poTime, Integer empId) {

		PointOrder pointorder = new PointOrder();

		pointorder.setMemId(memId);
		pointorder.setPdId(pdId);;
		pointorder.setPoPrice(poPrice);
		pointorder.setPoText(poText);
		pointorder.setPoStatus(poStatus);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
		pointorder.setPoTime(poTime);
		pointorder.setEmpId(empId);
		dao.insert(pointorder);

		return pointorder;
	}

	public PointOrder updatePointOrder(Integer memId, Integer pdId, Integer poPrice, String poText, Integer poStatus, java.sql.Timestamp poUtime, Integer poId) {

		PointOrder pointorder = new PointOrder();
		pointorder.setMemId(memId);
		pointorder.setPdId(pdId);;
		pointorder.setPoPrice(poPrice);
		pointorder.setPoText(poText);
		pointorder.setPoStatus(poStatus);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
		pointorder.setPoUtime(poUtime);
		pointorder.setPoId(poId);
		dao.update(pointorder);

		return pointorder;
	}

	public void deletePointOrder(Integer po_id) {
		dao.delete(po_id);
	}

	public PointOrder getPointOrder(Integer po_id) {
		return dao.getByPK(po_id);
	}

	public List<PointOrder> getAll() {
		return dao.getAll();
	}
	
	public List<PointOrder> getBackOrder() {
		return dao.getBackOrder();
	}
	
	public PointOrder updateOrderStatus(Integer poStatus, java.sql.Timestamp poUtime, Integer poId) {

		PointOrder pointorder = new PointOrder();
		pointorder.setPoStatus(poStatus);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
		pointorder.setPoUtime(poUtime);
		pointorder.setPoId(poId);
		dao.updateStatus(pointorder);

		return pointorder;
	}
	
	
}
