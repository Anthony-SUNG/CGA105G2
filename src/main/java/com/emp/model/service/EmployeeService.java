package com.emp.model.service;

import java.util.List;

import com.emp.model.Employee.dao.EmployeeDAO_interface;
import com.emp.model.Employee.dao.impl.EmployeeJDBCDAO;
import com.emp.model.Employee.pojo.Employee;
import com.emp.model.EmployeeRoot.dao.EmployeeRootDAO_interface;
import com.emp.model.EmployeeRoot.dao.impl.EmployeeRootJDBCDAO;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;
import com.emp.model.Root.dao.RootDAO_interface;
import com.emp.model.Root.dao.impl.RootJDBCDAO;
import com.emp.model.Root.pojo.Root;

public class EmployeeService {
	private EmployeeDAO_interface dao;
	private RootDAO_interface daoRoot;
	private EmployeeRootDAO_interface daoEmpRoot;


	public EmployeeService() {
		dao = new EmployeeJDBCDAO();
		daoRoot = new RootJDBCDAO();
		daoEmpRoot =new EmployeeRootJDBCDAO();
	}
	

	public Employee getAcc(String acc) {
		return dao.findByEmpAcc(acc);
	}
	public Employee getOneEmp(Integer empID) {
		return dao.findByEMP_ID(empID);
	}
	public Employee addEmp(String acc, String pwd) {
		Employee employee = new Employee();
		employee.setEmpAcc(acc);
		employee.setEmpPwd(pwd);
		dao.insert(employee);
		return employee;
	}
	public Employee updateEmp(Employee employee) {
		return dao.update(employee);
		
	}
	public List<Employee> all() {
		return dao.getAll();

	}
	public List<Root>  getEmpRootAll() {
		return  daoRoot.getAll();
	}

	public List<EmployeeRoot>  getRootEmp(Integer ROOT_ID) {
		System.out.println(ROOT_ID);
		return daoEmpRoot.findByROOT_ID(ROOT_ID);
	}
	public List<EmployeeRoot> getRoot(Integer EMP_ID) {
		return daoEmpRoot.findByEMP_ID(EMP_ID);
	}

}
