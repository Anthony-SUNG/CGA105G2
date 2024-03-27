package com.emp.model.EmployeeRoot.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.emp.model.EmployeeRoot.dao.EmployeeRootDAO_interface;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeRootJDBCDAO extends Common implements EmployeeRootDAO_interface {

    @Override
    public void insert(EmployeeRoot employeeRoot) {
        String sql = "INSERT INTO cga105g2.employee_root (EMP_ID,ROOT_ID) VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, employeeRoot.getEmpId());
            pstmt.setInt(2, employeeRoot.getRootId());
            pstmt.executeUpdate();
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public List<EmployeeRoot> getAll() {
        String sql = "SELECT * FROM cga105g2.employee_root order by EMP_ID";
        List<EmployeeRoot> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                EmployeeRoot employeeRootVO = new EmployeeRoot();
                employeeRootVO.setRootId(rs.getInt("ROOT_ID"));
                employeeRootVO.setEmpId(rs.getInt("EMP_ID"));
                list.add(employeeRootVO);
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return list;
    }

    @Override
    public List<EmployeeRoot> findByEMP_ID(Integer EMP_ID) {
        String sql = "SELECT * FROM cga105g2.employee_root where EMP_ID =  ? ";
        List<EmployeeRoot> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, EMP_ID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                EmployeeRoot employeeRootVO = new EmployeeRoot();
                employeeRootVO.setRootId(rs.getInt("ROOT_ID"));
                employeeRootVO.setEmpId(rs.getInt("EMP_ID"));
                list.add(employeeRootVO);
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return list;
    }

    @Override
    public void delete(Integer EMP_ID, Integer ROOT_ID) {
        String sql = "DELETE FROM  cga105g2.employee_root where EMP_ID = ? and ROOT_ID = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, EMP_ID);
            pstmt.setInt(2, ROOT_ID);
            pstmt.executeUpdate();
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.DELETE_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }

    @Override
    public List<EmployeeRoot> findByROOT_ID(Integer ROOT_ID) {
        String sql = "SELECT * FROM cga105g2.employee_root where ROOT_ID =  ? ";
        List<EmployeeRoot> list = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, ROOT_ID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                EmployeeRoot employeeRoot = new EmployeeRoot();
                employeeRoot.setRootId(rs.getInt("ROOT_ID"));
                employeeRoot.setEmpId(rs.getInt("EMP_ID"));
                list.add(employeeRoot);
            }
            close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
            try {
                getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
        return list;
    }

}
