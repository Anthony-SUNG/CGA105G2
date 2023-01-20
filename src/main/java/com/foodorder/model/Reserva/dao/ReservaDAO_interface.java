package com.foodorder.model.Reserva.dao;

import com.foodorder.model.Reserva.pojo.Reserva;

import java.util.List;
import java.util.Map;


public interface ReservaDAO_interface {
    public void insert(Reserva reservaVO);
    public void update(Reserva reservaVO);
//    public void delete(Integer REN_ID);
    public Reserva getById(Integer REN_ID);
    public List<Reserva> getAll();

    List<Reserva> getByStoreIdRendate(Integer storeid, String rendate, String rentime, Integer renstatus);

    void insertWithReservaDetails(Reserva reservaVO, List<Map> list);
}
