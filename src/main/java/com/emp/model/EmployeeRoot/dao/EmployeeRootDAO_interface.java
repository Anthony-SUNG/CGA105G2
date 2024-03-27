package com.emp.model.EmployeeRoot.dao;

import com.core.dao.CoreDao;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;

import java.util.List;



public interface EmployeeRootDAO_interface extends CoreDao<EmployeeRoot,Integer> {
	public void insert(EmployeeRoot pojo);
	public void update(EmployeeRoot pojo);
	public void deleteById(Integer empId);
	public void delete(Integer empId,Integer rootId);
	public EmployeeRoot getById(Integer id);
	public List<EmployeeRoot> getByEmpId(Integer empId);
	public List<EmployeeRoot> getByRootId(Integer empId);
	public List<EmployeeRoot> getAll();

}
