package com.mybank.co.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provide access to the local database using SQLITE driver
 */
public  class DatabaseConnection {

    public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
        Properties properties = new Properties();
        Class.forName("org.sqlite.JDBC");

        InputStream stream = DatabaseConnection.class.getClassLoader().getResourceAsStream("mybank.properties");
        properties.load(stream);
        String database = properties.getProperty("database.database");
        String url = properties.getProperty("database.url")
                .concat(System.getProperty("user.dir")).concat(database);
        return DriverManager.getConnection(url);

    }
}
