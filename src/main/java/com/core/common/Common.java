package com.core.common;

import com.core.entity.ErrorTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Common {
    protected Connection con = null;
    public static final Logger logger = LogManager.getLogger(Common.class);

    static {
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            logger.error(ErrorTitle.CLASS_NOT_FOUND.getTitle(driverName + " error "), e);
        }
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cga105g2";
        String user = "2023032003";
        String password = "2023032003";
        try {
            this.con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(ErrorTitle.CONNECTION_TITLE.getTitle(con.toString()), e);
        }
        return con;
    }

    public void close() throws SQLException {
        if (this.con != null) {
            this.con.commit();
            this.con.close();
        }
    }

    public Connection getCon() {
        return this.con;
    }

}
