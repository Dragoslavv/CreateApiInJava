package net.javatutorial.tutorials;

import java.sql.*;
import java.sql.DriverManager;

public class database {
    private static Connection connect = null;
    private static database db;
    private  database() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "java_api";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "admin";
        String password = "Admin_4321";

        try {

            Class.forName(driver);
            this.connect = DriverManager.getConnection(url+dbName+"?autoReconnect=true&useSSL=false",userName,password);

            if(this.connect != null ){
                System.out.println("Connected to the database");
            }

        } catch (ClassNotFoundException ex) {

            System.out.println("Could not find database driver class");
            ex.printStackTrace();

        } catch (SQLException ex) {

            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();

        }
    }

    public static database getDbCon() {
        if ( db == null ) {
            db = new database();
        }
        return db;

    }

    public void insert(String fname, String lname, String address, int zipcode) {
        String sql = "INSERT INTO `data_in_customer`(`fname`, `lname`, `address`, `zipcode`) VALUES (?,?,?,?)";

        try ( Connection conn = connect; PreparedStatement stmt = conn.prepareStatement(sql) ) {

            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setString(3, address);
            stmt.setInt(4, zipcode);
            stmt.executeUpdate();

        } catch ( SQLException e ) {

            System.out.println(e.getMessage());

        }
    }

    public void selectAll(){
        String sql = "SELECT * FROM `data_in_customer`";

        try(Connection conn = connect; Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                System.out.println( rs.getInt("id") + "\t" +
                                    rs.getString("fname") + "\t" +
                                    rs.getString("lname") + "\t" +
                                    rs.getString("address") + "\t" +
                                    rs.getInt("zipcode"));
            }
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage());
        }


    }

    public void select(int id){
        String sql = "SELECT id,fname,lname FROM data_in_customer WHERE id ="+ id;

        try(Connection conn = connect; Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("fname") + "\t" +
                        rs.getString("lname")
                );
            }
        } catch ( SQLException ex ) {
            System.out.println(ex.getMessage());
        }
    }

}

