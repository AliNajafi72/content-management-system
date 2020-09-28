package ir.maktabsharif.singletons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection databaseConnection;
    private final static String DATABASE_SERVER_URL = "jdbc:mysql://localhost/cms";
    private static Properties databaseConnectionProperties;
    private DatabaseConnection() {
    }

    public static Connection getInstance() throws SQLException {
        if (databaseConnection == null || databaseConnection.isClosed()) {
            databaseConnectionProperties = new Properties();
            databaseConnectionProperties.setProperty("user","root");
            databaseConnectionProperties.setProperty("password","Ali721100");
            databaseConnection = DriverManager.getConnection(DATABASE_SERVER_URL,
                    databaseConnectionProperties.getProperty("user"),
                    databaseConnectionProperties.getProperty("password"));
            databaseConnection.setAutoCommit(false);
        }
        return databaseConnection;
    }
}
