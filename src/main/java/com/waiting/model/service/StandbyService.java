package com.waiting.model.service;


import com.waiting.model.dao.impl.StandbyDAO;
import com.waiting.model.dao.StandbyDAO_interface;
import com.waiting.model.pojo.Standby;

import java.util.List;

public class StandbyService {

	private StandbyDAO_interface dao;

	public StandbyService() {
		dao = new StandbyDAO();
	}

	public Standby addStandBy(Integer storeId, String staName, String staPhone, Integer staNumber) {

		Standby standbyVo = new Standby();
		standbyVo.setStoreId(storeId);
		standbyVo.setStaName(staName);
		standbyVo.setStaPhone(staPhone);
		standbyVo.setStaNumber(staNumber);
//		waitingVo.setSta_time(sta_time);
//		waitingVo.setSta_status(sta_status);
//		Timestamp sta_time,Integer sta_status
		dao.insert(standbyVo);
		return standbyVo;
	}

	public Standby updateStandBy(Integer staId, Integer staStatus) {
		Standby standbyVo = new Standby();
		standbyVo.setStaId(staId);
		standbyVo.setStaStatus(staStatus);

		dao.update(standbyVo);
		return standbyVo;
	}

	public void deleteStandBy(Integer staId) {
		dao.delete(staId);
	}

	public Standby getOneStandBy(Integer staId) {
		return dao.findByPrimaryKey(staId);
	}

	public List<Standby> getAll() {
		return dao.getAll();
	}

	public Integer standByCount() {
		return dao.standByCount();
	}
}
