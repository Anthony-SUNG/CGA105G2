package com.emp.model.Employee.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.emp.model.Employee.dao.EmployeeDAO_interface;
import com.emp.model.Employee.pojo.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBCDAO extends Common implements EmployeeDAO_interface {
    @Override
    public void insert(Employee EmployeeVO) {
        String sql = "INSERT INTO cga105g2.employee (EMP_ACC,EMP_PWD) VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, EmployeeVO.getEmpAcc());
            pstmt.setString(2, EmployeeVO.getEmpPwd());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
        }
    }

    @Override
    public Employee update(Employee EmployeeVO) {
        String sql = "UPDATE cga105g2.employee set EMP__STATUS=?,  EMP_ACC=?, EMP_PWD=?, EMP_PER=?, EMP_TIME=?, EMP_RTIME=? where EMP_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, EmployeeVO.getEmpStatus());
            pstmt.setString(2, EmployeeVO.getEmpAcc());
            pstmt.setString(3, EmployeeVO.getEmpPwd());
            pstmt.setInt(4, EmployeeVO.getEmpPer());
            pstmt.setDate(5, EmployeeVO.getEmpTime());
            pstmt.setDate(6, EmployeeVO.getEmpRtime());
            pstmt.setInt(7, EmployeeVO.getEmpId());
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(sql), se);
        }
        return EmployeeVO;
    }

    @Override
    public Employee findByEMP_ID(Integer EMP_ID) {
        String sql = "SELECT EMP__STATUS,EMP_ID,EMP_ACC,EMP_PWD,EMP_PER,EMP_TIME,EMP_RTIME FROM cga105g2.employee where EMP_ID = ?";
        Employee employeeVO = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, EMP_ID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employeeVO = new Employee();
                employeeVO.setEmpStatus(rs.getInt("EMP__STATUS"));
                employeeVO.setEmpId(rs.getInt("EMP_ID"));
                employeeVO.setEmpAcc(rs.getString("EMP_ACC"));
                employeeVO.setEmpPwd(rs.getString("EMP_PWD"));
                employeeVO.setEmpPer(rs.getInt("EMP_PER"));
                employeeVO.setEmpTime(rs.getDate("EMP_TIME"));
                employeeVO.setEmpRtime(rs.getDate("EMP_RTIME"));
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return employeeVO;
    }

    @Override
    public Employee findByEmpAcc(String empAcc) {
        String sql = "SELECT * FROM cga105g2.employee where EMP_ACC = ?";
        Employee employee = null;

        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, empAcc);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employee = new Employee();
                employee.setEmpStatus(rs.getInt("EMP__STATUS"));
                employee.setEmpId(rs.getInt("EMP_ID"));
                employee.setEmpAcc(rs.getString("EMP_ACC"));
                employee.setEmpPwd(rs.getString("EMP_PWD"));
                employee.setEmpPer(rs.getInt("EMP_PER"));
                employee.setEmpTime(rs.getDate("EMP_TIME"));
                employee.setEmpRtime(rs.getDate("EMP_RTIME"));
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        Employee employeeVO;
        String sql = "SELECT * FROM cga105g2.employee order by EMP_ID";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employeeVO = new Employee();
                employeeVO.setEmpStatus(rs.getInt("EMP__STATUS"));
                employeeVO.setEmpId(rs.getInt("EMP_ID"));
                employeeVO.setEmpAcc(rs.getString("EMP_ACC"));
                employeeVO.setEmpPwd(rs.getString("EMP_PWD"));
                employeeVO.setEmpPer(rs.getInt("EMP_PER"));
                employeeVO.setEmpTime(rs.getDate("EMP_TIME"));
                employeeVO.setEmpRtime(rs.getDate("EMP_RTIME"));
                list.add(employeeVO);
            }
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
        return list;
    }
}
