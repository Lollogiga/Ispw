package com.example.greenpear.dao;

import com.example.greenpear.exception.DatabaseConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;




public class SingletonConnection {
    private static Connection connection;

    private SingletonConnection() throws DatabaseConnectionException {
        databaseConnection();
    }

    private static void databaseConnection() throws DatabaseConnectionException {
        try (InputStream input = new FileInputStream("src/main/resources/com/example/greenpear/properties/connectionDB.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("CONNECTION_USER");
            String pass = properties.getProperty("CONNECTION_PASS");

            connection = DriverManager.getConnection(connectionUrl, user, pass);
        } catch (IOException | SQLException e) {
            throw new DatabaseConnectionException(e.getMessage());
        }
    }

    public static Connection getInstance() throws SQLException {
        if(connection == null){
            try{
                new SingletonConnection();
            }catch (DatabaseConnectionException e){
                throw new SQLException("Impossibile connettersi al DB");
            }
        }
        return connection;
    }
}