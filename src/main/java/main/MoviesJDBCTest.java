package main;

import com.mysql.cj.jdbc.Driver;
import config.Config;

import java.sql.*;


public class MoviesJDBCTest {
    private static Connection connection;
    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new Driver());
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + Config.DB_HOST + ":3306/micah?allowPublicKeyRetrieval=true&useSSL=false",
                Config.DB_USER,
                Config.DB_PW
        );

        Statement stmt = connection.createStatement();
        ResultSet movieRows = stmt.executeQuery("select * from micah.movie");

        movieRows.close();
        stmt.close();
        connection.close();
    }
}
