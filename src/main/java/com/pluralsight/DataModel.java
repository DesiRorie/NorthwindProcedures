package com.pluralsight;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataModel {
    private Connection connection;

    public DataModel(Connection connection){
        this.connection = connection;
    }

    public void getOrderHistory(Scanner  scanner){
        String query = "{CALL CustOrderHist(?)}";
        try {
            System.out.println("Enter ID to search");
            String customerId = scanner.next();
            scanner.nextLine();
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1, customerId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s - %d\n",
                        resultSet.getString("ProductName"),
                        resultSet.getInt("TOTAL"));
            }

        } catch (SQLException e) {

        }
    }
    public void searchByYear(Scanner  scanner){
        String query = "{CALL SalesByYear(?,?)}";

        try {System.out.println("Enter the beginning date to search ");
            String beginningDate = scanner.nextLine();
            System.out.println("Enter the end date to search ");
            String endDate = scanner.nextLine();
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setTimestamp(1, Timestamp.valueOf(beginningDate));
            stmt.setTimestamp(2, Timestamp.valueOf(endDate));
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s - %s\n",
                        "OrderID: " + resultSet.getInt("OrderID"),
                        "Timestamp: " + resultSet.getTimestamp("ShippedDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSalesByCategory(Scanner  scanner){
        String query = "{CALL SalesByCategory(?,?)}";
        try {
            System.out.println("Enter the category to search ");
            String categorySearch = scanner.next();
            scanner.nextLine();
            System.out.println("Enter the year to search ");
            String yearSearch = scanner.next();
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1,categorySearch);
            stmt.setString(2,yearSearch);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s - %s\n",
                        resultSet.getString("ProductName"),
                        resultSet.getDouble("TotalPurchase"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
