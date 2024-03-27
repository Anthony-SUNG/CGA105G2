package com.foodorder.model.Reserva.dao;

import com.core.dao.CoreDao;
import com.foodorder.model.Reserva.pojo.Reserva;

import java.util.List;
import java.util.Map;


public interface ReservaDAO_interface extends CoreDao<Reserva,Integer> {
    public void insert(Reserva pojo);
    public void insertToSta(Reserva pojo);
    public void insertWithReservaDetails(Reserva reservaVO, List<Map> list);
    public void update(Reserva pojo);
    public void updateRenstatusByRenid(Integer renid, Integer renstatus);
    public void deleteById(Integer id);
    public Reserva getById(Integer id);
    public Reserva getTable(Integer id);
    public List<Reserva> getByStoreIdRendate(Integer storeid, String rendate, String rentime, Integer renstatus);
    public List<Reserva> getBymemIdrenStatus(Integer memid, Integer renstatus);
	public List<Reserva> getBymemId(Integer memid);
	public List<Reserva> getBystoreId(Integer storeid);
	public List<Reserva> getBystoreIdrenStatus(Integer storeid, Integer renstatus);
    public List<Reserva> getAll();
}
