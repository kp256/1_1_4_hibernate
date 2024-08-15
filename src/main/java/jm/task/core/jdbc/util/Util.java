package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection getConnection() throws SQLException {
        String dbUrl;
        String dbUsername;
        String dbPassword;

        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            props.load(fis);
            dbUrl = props.getProperty("db.url");
            dbUsername = props.getProperty("db.username");
            dbPassword = props.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        if (!connection.isClosed()) {
            return connection;
        } else {
            throw new SQLException("Failed to establish a database connection.");
        }

    }
}
