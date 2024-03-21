package com.core.common;

import com.core.entity.ErrorTitle;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Common {
    protected static Connection con = null;
    public static final Logger logger = Logger.getLogger("IMPORT");

    static {
        DOMConfigurator.configure("D:\\SYI\\CGA105G2\\log4j.xml");
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            logger.error(ErrorTitle.CLASS_NOT_FOUND.getTitle(driverName + " error "), e);
        }
    }


    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cga105g2";
        String user = "2023032003";
        String password = "2023032003";
        try {
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(ErrorTitle.CONNECTION_TITLE.getTitle(con.toString()), e);
        }
        return con;
    }
}
