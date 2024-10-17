package base;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

    public static void deleteData(int id){
        String sqlQuery = "DELETE FROM birthdays WHERE id = ?";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("user has been deleted");
        } catch (Exception e) {
            throw new RuntimeException("Something unexpected happened", e);
        }
    }

    public static ArrayList<BirthdaysManager> queryDataByDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date parameter cannot be null");
        }

        String sqlQuery = "SELECT id, givenName, familyName, birthdate FROM birthdays WHERE EXTRACT(MONTH FROM birthdate) = ? AND EXTRACT(DAY FROM birthdate) = ?";
        ArrayList<BirthdaysManager> listOfBirthdays = new ArrayList<>();

        int day = date.getDayOfMonth();
        int month = date.getMonthValue();

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, month);
            pstmt.setInt(2, day);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BirthdaysManager newBirthday = new BirthdaysManager();
                    newBirthday.setGivenName(rs.getString("givenName"));
                    newBirthday.setFamilyName(rs.getString("familyName"));
                    Date sqlDate = rs.getDate("birthdate");
                    LocalDate birthdate = sqlDate.toLocalDate();
                    newBirthday.setBirthdate(birthdate);
                    listOfBirthdays.add(newBirthday);
                }
            }

            return listOfBirthdays;

        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

}
