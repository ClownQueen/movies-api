package main;

import com.mysql.cj.jdbc.Driver;
import config.Config;

import java.sql.*;

public class MoviesJDBCTest {
    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + Config.getUrl() + ":3306/micah?allowPublicKeyRetrieval=true&useSSL=false",
                Config.getUser(),
                Config.getPassword()
        );
        connection.close();


    }
}
