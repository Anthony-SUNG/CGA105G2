package com.store.model.service;

import com.code.model.Code.pojo.Code;
import com.code.model.service.CodeService;
import com.member.model.Member.pojo.Member;
import com.store.model.Store.dao.StoreDAO_interface;
import com.store.model.Store.dao.impl.StoreDAO;
import com.store.model.Store.pojo.Store;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class StoreService {
	private StoreDAO_interface dao;

	public StoreService() {
		dao = new StoreDAO();
	}

	public Store signin(String storeAcc, String storePwd) {
		return dao.signin(storeAcc, storePwd);
	}

	public Store getById(Integer storeId) {
		return dao.getById(storeId);
	}

	public List<Store> getStoreName(String storeName) { // 要叫louie新增
		return dao.getByStoreName(storeName);
	}

	public JSONArray empstore(Integer empId) {
		Integer rootId = new CodeService().Coupon_root(empId);
		List<Store> list = dao.getBySsta(1);
		JSONArray json = new JSONArray();
		JSONObject map = null;
		switch (rootId) {
		case 1:
			for (Store e : list) {
				if (e.getEmpId() == 0) {
					map = new JSONObject();
					map.put("STORE_ID", e.getStoreId());
					map.put("STORE_STATUS", e.getStoreStatus());
					map.put("STORE_COM_ID", e.getStoreComId());
					map.put("STORE_NAME", e.getStoreName());
					map.put("STORE_CITY", e.getStoreCity());
					map.put("STORE_DISTRICT", e.getStoreDistrict());
					map.put("STORE_ACC", e.getStoreAcc());
					map.put("STORE_TW_ID", e.getStoreTwId());
					map.put("STORE_TO_NAME", e.getStoreComAddress());
					map.put("STORE_PHONE", e.getStorePhone2());
					map.put("STORE_MAIL", e.getStoreMail());
					json.add(map);
				}
			}
			break;
		case 4:
			for (Store e : list) {
				if (e.getEmpId() == empId) {
					map = new JSONObject();
					map.put("STORE_ID", e.getStoreId());
					map.put("STORE_STATUS", e.getStoreStatus());
					map.put("STORE_COM_ID", e.getStoreComId());
					map.put("STORE_NAME", e.getStoreName());
					map.put("STORE_CITY", e.getStoreCity());
					map.put("STORE_DISTRICT", e.getStoreDistrict());
					map.put("STORE_ACC", e.getStoreAcc());
					map.put("STORE_TW_ID", e.getStoreTwId());
					map.put("STORE_TO_NAME", e.getStoreComAddress());
					map.put("STORE_PHONE", e.getStorePhone2());
					map.put("STORE_MAIL", e.getStoreMail());
					json.add(map);
				}
			}
			break;
		}
		return json;
	}

	public boolean toempId(Integer storeId, Integer toempId) {
		dao.updateempId(storeId, toempId);
		return true;
	}

	public boolean tostat(Integer storeId, Integer storeStatus) {
		dao.updateStatus(storeId, storeStatus);
		return true;
	}

	public boolean update(Store pojo) {
		dao.update(pojo);
		return true;
	}

	public JSONArray statStoreAll() {
		List<Store> list = dao.getBySsta(2);
		JSONArray json = new JSONArray();
		JSONObject map = null;
		for (Store e : list) {
			map = new JSONObject();
			map.put("STORE_ID", e.getStoreId());
			map.put("STORE_STATUS", "通過");
			map.put("EMP_ID", e.getEmpId());
			map.put("STORE_COM_ID", e.getStoreComId());
			map.put("STORE_NAME", e.getStoreName());
			map.put("STORE_CITY", e.getStoreCity());
			map.put("STORE_DISTRICT", e.getStoreDistrict());
			map.put("STORE_ACC", e.getStoreAcc());
			map.put("STORE_TW_ID", e.getStoreTwId());
			map.put("STORE_TO_NAME", e.getStoreComAddress());
			map.put("STORE_PHONE", e.getStorePhone2());
			map.put("STORE_MAIL", e.getStoreMail());
			json.add(map);
		}
		List<Store> list2 = dao.getBySsta(0);
		for (Store e : list2) {
			if (e.getEmpId() > 0) {
				map = new JSONObject();
				map.put("STORE_ID", e.getStoreId());
				map.put("STORE_STATUS", "未通過");
				map.put("EMP_ID", e.getEmpId());
				map.put("STORE_COM_ID", e.getStoreComId());
				map.put("STORE_NAME", e.getStoreName());
				map.put("STORE_CITY", e.getStoreCity());
				map.put("STORE_DISTRICT", e.getStoreDistrict());
				map.put("STORE_ACC", e.getStoreAcc());
				map.put("STORE_TW_ID", e.getStoreTwId());
				map.put("STORE_TO_NAME", e.getStoreComAddress());
				map.put("STORE_PHONE", e.getStorePhone2());
				map.put("STORE_MAIL", e.getStoreMail());
				json.add(map);
			}
		}
		return json;
	}

	public List<Store> getStoreList() {
		return dao.getAll();
	}

	public Store forget1(String storeAcc, String storePwd) {
		Store store = new Store();

		store.setStoreAcc(storeAcc);
		store.setStorePwd(storePwd);
		dao.update3(store);

		return store;
	}

}