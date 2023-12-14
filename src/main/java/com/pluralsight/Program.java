package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";
        String myPassword = System.getenv("MY_DB_PASSWORD");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(user);
        dataSource.setPassword(myPassword);
        Connection conn = dataSource.getConnection();

DataModel dataModel = new DataModel(conn);
dataModel.getOrderHistory(scanner);

        dataModel.searchByYear(scanner);

        dataModel.getSalesByCategory(scanner);
    }

}
