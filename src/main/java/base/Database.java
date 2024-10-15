package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String JDBC_url = "jdbc:h2:./data/database";
    private static final String USER = "User";
    private static final String PASSWORD = "secretStuff";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_url, USER, PASSWORD);
    }
}
