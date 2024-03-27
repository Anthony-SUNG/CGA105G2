package com.emp.model.Employee.dao;

import com.core.dao.CoreDao;
import com.emp.model.Employee.pojo.Employee;

import java.util.List;

public interface EmployeeDAO_interface extends CoreDao<Employee, Integer> {
    public void insert(Employee pojo);
    public void update(Employee pojo);
    public void deleteById(Integer id);
    public Employee getById(Integer id);
    Employee getByEmpAcc(String empAcc);
    public List<Employee> getAll();

}
