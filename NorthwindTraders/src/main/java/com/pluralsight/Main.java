package com.pluralsight;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <username> <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        String sql = """
                SELECT ProductId,
                       ProductName,
                       UnitPrice,
                       UnitsInStock
                FROM Products
                """;

        try (
                Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",username,password) ;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet results = preparedStatement.executeQuery();
                ) {


            System.out.printf("%-5s %-40s %15s %10s%n", "ID", "Product Name", "Price", "Stock");
            System.out.println("-------------------------------------------------------------------------");

            while (results.next()) {
                int productId = results.getInt("ProductID");
                String productName = results.getString("ProductName");
                double unitPrice = results.getDouble("UnitPrice");
                int unitsInStock = results.getInt("UnitsInStock");
                System.out.printf("%-5d %-40s %15.2f %10d%n", productId, productName, unitPrice, unitsInStock);
            }
        } catch(SQLException e){
            System.out.println("There was an error retrieving your information. Please try again or contact support");
            throw new RuntimeException(e);
        }
    }
}

