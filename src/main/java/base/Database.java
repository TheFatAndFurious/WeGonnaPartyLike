package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String JDBC_url = "jdbc:h2:./data/database";
    private static final String USER = "User";
    private static final String PASSWORD = "secretStuff";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_url, USER, PASSWORD);
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS birthdays (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "givenName VARCHAR (255) NOT NULL, " +
                    "familyName VARCHAR (255) NOT NULL, " +
                    "birthdate DATE NOT NULL, " +
                    ");";

        try {
            Connection conn = getConnection();
            Statement smt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
