package com.db451B07.HW4.company;

import com.db451B07.HW4.config.Password;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class CompanyDao {

    private static String url = "jdbc:mysql://localhost:3306/COMPANY";
    private static String user = "root";
    private static String password = new Password().getPassword();

    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
