package com.foodorder.model.ReservaDetail.dao;

import com.advertise.model.Advertise.pojo.Advertise;
import com.core.dao.CoreDao;
import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;

import java.util.List;


public interface ReservaDetailDAO_interface extends CoreDao<ReservaDetail,Integer> {
    public void insert(ReservaDetail pojo);
    public void update(ReservaDetail pojo);
    public void deleteById(Integer id);
    public ReservaDetail getById(Integer id);
    public List<ReservaDetail> getByPK(Integer id, String pk);
    public List<ReservaDetail> getAll();
}
