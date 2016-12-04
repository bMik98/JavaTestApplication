package ru.magnit.test.JavaTestApplication.dao.util;

import ru.magnit.test.JavaTestApplication.property.DbConnectionProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {
    public static Connection build(final DbConnectionProperties properties) {
        initJdbcDriver(properties.getDriverName());
        Connection connection;
        try {
            connection = DriverManager.getConnection(properties.buildUrl(), properties.getProperties());
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        if (connection == null) {
            System.err.println("Failed to make connection!");
        }
        return connection;
    }

    private static boolean initJdbcDriver(final String driverName) {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.err.println("Where is your JDBC Driver?");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
