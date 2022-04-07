package main;

import com.mysql.cj.jdbc.Driver;
import config.Config;

import java.sql.*;

public class MoviesJDBCTest {
    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + Config.DB_HOST + ":3306/micah?allowPublicKeyRetrieval=true&useSSL=false",
                Config.DB_USER,
                Config.DB_PW
        );
        String selectQuery = "Select * From movie";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(selectQuery);


    }
}
