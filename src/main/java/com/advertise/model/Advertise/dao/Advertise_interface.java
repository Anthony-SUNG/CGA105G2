package com.advertise.model.Advertise.dao;

import com.advertise.model.Advertise.pojo.Advertise;
import com.core.dao.CoreDao;

import java.util.List;



public interface Advertise_interface extends CoreDao<Advertise,Integer> {
	public void insert(Advertise pojo);
    public void update(Advertise pojo);
    public void deleteById(Integer id);
    public Advertise getById(Integer id);
    public Advertise getByStoreId(Integer storeId);
    public List<Advertise> getByStatus(Integer advStatus);
    public List<Advertise> getAll();
}
