package com.store.model.Store.dao;

import com.store.model.Store.pojo.Store;

import java.util.List;

public interface StoreDAO_interface {
	public void insert(Store Store);
    public void update(Store Store);
    public void delete(Integer storeId);
    public Store getById(Integer storeId);
    public List<Store> getAll();
    public void updateStatus(Integer storeId ,Integer storeStatus);
    public void updateempId(Integer storeId ,Integer empId);
    public void updatePlan(Integer storeId ,Integer storePlan);
    public void updateNplan(Integer storeId ,Integer storeNplan);
    public void updateordersetting(Integer storeId, String storeEtime, Integer storeTable, Integer storeEtable);

    Store signin(String storeAcc, String storePwd);

    List<Store> getByStoreName(String storeName);

    List<Store> getBySsta(Integer sta);
	public void update3(Store store);
	public void update4(Store store);
	public void updateplan(Integer storeId);
}
