package com.advertise.model.service;

import java.util.List;

import com.advertise.model.Advertise.dao.Advertise_interface;
import com.advertise.model.Advertise.dao.impl.AdvertiseJDBCDAO;
import com.advertise.model.Advertise.pojo.Advertise;

public class AdvertiseService {
	private Advertise_interface dao;
	
	public AdvertiseService() {
		dao = new AdvertiseJDBCDAO();
	}
	
	public List<Advertise> getStatus(){
		return dao.getByStatus(1);
	}
	public List<Advertise> getStatusPass(){
		return dao.getByStatus(2);
	}
	public List<Advertise> getAll(){
		return dao.getAll();
	}
	public Advertise getByAdvId(Integer advId) {
		return dao.getByAdvId(advId);
		
	}
	
	
	public void update(Integer advId,Integer empId){
		Advertise advertise = dao.getByAdvId(advId);
		advertise.setAdvStatus(3);
		advertise.setEmpId(empId);
		dao.update(advertise);
		
	}
	public void updatePass(Integer advId,Integer empId){
		Advertise advertise = dao.getByAdvId(advId);
		advertise.setAdvStatus(2);
		advertise.setEmpId(empId);
		dao.update(advertise);
		
	}
}
