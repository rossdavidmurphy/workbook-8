package com.pluralsight;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // load the MySQL Driver, next line no longer needed in newer versions of Java
//         Class.forName("com.mysql.cj.jdbc.Driver");
        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <username> <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];


        // 1. open a connection to the database
        // use the database URL to point to the correct database
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);

        // create statement
        // the statement is tied to the open connection
        Statement statement = connection.createStatement();

        // define your query
        String query = "SELECT * FROM products";
        System.out.println(query);
        // 2. Execute your query
        ResultSet results = statement.executeQuery(query);

        // process the results
        while (results.next()) {
            String productName = results.getString("ProductName");
//            String productId = results.getNString("ProductID");
//            System.out.println(productInfo);
            System.out.println(productName);
        }

        // 3. Close resources
        results.close();
        statement.close();
        connection.close();
    }
}
