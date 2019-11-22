package org.baito.h2access;

import java.sql.*;

public class H2Access {

    public static H2Access create(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        return new H2Access(url, user, pass);
    }

    private Connection conn;
    private String user;
    private String pass;
    private String url;

    public H2Access(){};

    public H2Access(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        this.user = user;
        this.pass = pass;
        this.url = url;
        connect();
    }

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        url = verifyDirectory(url);
        conn = DriverManager.getConnection(url,user,pass);
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

    public void open() throws SQLException, ClassNotFoundException {
        connect();
    }

    private String verifyDirectory(String path) {
        return path.contains("jdbc:h2:") ? path : "jdbc:h2:" + path;
    }
}
