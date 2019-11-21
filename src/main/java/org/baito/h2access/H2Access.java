package org.baito.h2access;

import org.h2.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class H2Access {

    public static H2Access create(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        return new H2Access(url, user, pass);
    }

    private java.sql.Connection conn;

    public H2Access(){};

    public H2Access(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        connect(url, user, pass);
    }

    public void connect(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        url = verifyDirectory(url);
        conn = DriverManager.getConnection(url,user,pass);
    }

    public ResultSet query(String query) throws SQLException {
        return conn.createStatement().executeQuery(query);
    }

    public void update(String update) throws SQLException {
        conn.createStatement().executeUpdate(update);
    }

    public void lupdate(String update) throws SQLException {
        conn.createStatement().executeLargeUpdate(update);
    }

    private String verifyDirectory(String path) {
        return path.contains("jdbc:h2:") ? path : "jdbc:h2:" + path;
    }
}
