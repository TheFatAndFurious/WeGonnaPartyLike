package base;

import java.sql.*;
import java.time.LocalDate;

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
                    "birthdate DATE NOT NULL " +
                    ");";

        try {
            Connection conn = getConnection();
            Statement smt = conn.createStatement();
            smt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertData(BirthdaysManager newEntry){
        String sql = "INSERT INTO birthdays (givenName, familyName, birthdate) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                preparedStatement.setString(1, newEntry.givenName);
                preparedStatement.setString(2, newEntry.familyName);
                preparedStatement.setObject(3, newEntry.birthdate);
                preparedStatement.executeUpdate();
                System.out.println("Birthday inserted successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void queryData(){
        String sql = "SELECT * from birthdays";

        try (Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("givenName");
                String familyName = rs.getString("familyName");
                var birthdate = rs.getObject("birthdate");
                System.out.println(name + " " + familyName + " birthday is " + birthdate) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Here we want the function to return an array of Birthdaysmanager if there's any
    // It is the function that will be run automatically each day to check if there's any birthdays
    public static BirthdaysManager[] queryDataByDates(){
        String sqlQuery = "SELECT givenName, familyName, birthdate FROM birthdays WHERE EXTRACT(MONTH FROM birthdate) = ? AND EXTRACT (DAY FROM birthdate) = ?";


    }


}