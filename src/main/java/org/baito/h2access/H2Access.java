package org.baito.h2access;

import org.h2.Driver;

import java.sql.*;

public class H2Access {

    public static H2Access create(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        return new H2Access(url, user, pass);
    }

    private Connection conn;

    public H2Access(){};

    public H2Access(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        connect(url, user, pass);
    }

    public void connect(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        url = verifyDirectory(url);
        conn = DriverManager.getConnection(url,user,pass);
        conn.close();
    }

    public ResultSet query(String query) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet set = stm.executeQuery(query);
        stm.closeOnCompletion();
        return set;
    }

    public void update(String update) throws SQLException {
        Statement stm = conn.createStatement();
        stm.executeUpdate(update);
        stm.closeOnCompletion();
    }

    public void lupdate(String update) throws SQLException {
        Statement stm = conn.createStatement();
        stm.executeLargeUpdate(update);
        stm.closeOnCompletion();
    }

    public void close() throws SQLException {
        if (!conn.isClosed()) conn.close();
    }

    private String verifyDirectory(String path) {
        return path.contains("jdbc:h2:") ? path : "jdbc:h2:" + path;
    }
}
