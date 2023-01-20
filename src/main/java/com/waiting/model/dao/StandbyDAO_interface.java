package com.waiting.model.dao;


import com.waiting.model.pojo.Standby;

import java.util.List;

public interface StandbyDAO_interface {
	public void insert(Standby standbyVo);

	public void update(Standby standbyVo);

	public void delete(Integer staId);

	public Standby findByPrimaryKey(Integer staId);

	public List<Standby> getAll();

	public Integer standByCount();
	
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
