package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private Connection connection;
    private Statement statement;

    private Config config = new Config();

    private static Database instance = null;

    private Database() {
        config = new Config();
        String MYSQL_HOST = config.get("MYSQL_HOST");
        String MYSQL_PORT = config.get("MYSQL_PORT");
        String MYSQL_DATABASE = config.get("MYSQL_DATABASE");
        String MYSQL_USER = config.get("MYSQL_USER");
        String MYSQL_PASSWORD = config.get("MYSQL_PASSWORD");

        // Connect to the database
        try {
            connection = java.sql.DriverManager.getConnection(
                "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE,
                MYSQL_USER,
                MYSQL_PASSWORD
            );
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
