package net.javatutorial.tutorials;

import java.sql.*;
import java.sql.DriverManager;

public final class database {
    public Connection conn;
    private Statement statement;

    public static database db;

    private database() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "api-java";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "admin";
        String password = "Admin_4321";

        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName+"?autoReconnect=true&useSSL=false",userName,password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized database getDbCon() {
        if ( db == null ) {
            db = new database();
        }
        return db;

    }

    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;

    }

}
