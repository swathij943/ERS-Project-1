package project.one.db;

import project.one.dao.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;

public class Repository {
    static private Connection connection = null;
    static private PreparedStatement preparedStatement = null;
    static private Repository instance = null;

    static {
        makeJDBCConnection();
    }

    public static Repository getInstance(){
        if(instance == null) instance = new Repository();
        return instance;
    }

    private Repository(){}

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            String getQueryStatement = "SELECT * FROM employee";
            preparedStatement = connection.prepareStatement(getQueryStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                employees.add(new Employee(id, first_name, last_name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static void log(String string) {
        System.out.println(string);
    }

    private static void makeJDBCConnection() {
//        try {
//            Class.forName("com.postgresql.jdbc.Driver");
//            log("Congrats - Seems your postgreSQL JDBC Driver Registered!");
//        } catch (ClassNotFoundException e) {
//            log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
//            e.printStackTrace();
//            return;
//        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "dbuser", "password");
            if (connection != null) {
                log("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                log("Failed to make connection!");
            }
        } catch (SQLException e) {
            log("postgreSQL Connection Failed!");
            e.printStackTrace();
            return;
        }
    }
}
