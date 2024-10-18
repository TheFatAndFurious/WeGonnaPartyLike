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

    public static void addBirthday(BirthdaysManager newEntry){
        String sql = "INSERT INTO birthdays (givenName, familyName, birthdate) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                preparedStatement.setString(1, newEntry.givenName);
                preparedStatement.setString(2, newEntry.familyName);
                preparedStatement.setObject(3, newEntry.birthdate);
                preparedStatement.executeUpdate();
                MessageHelper.PrintFormattedMessage(Messages.BIRTHDAY_ADDED_SUCCESSFULLY, newEntry.getGivenName(), newEntry.familyName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<BirthdaysManager> getAllBirthdays(){

        ArrayList<BirthdaysManager> listOfBirthdays = new ArrayList<>();
        String sql = "SELECT id, givenName, familyName, birthdate from birthdays";

        try (Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                BirthdaysManager user = new BirthdaysManager();
                user.setId(rs.getInt("id"));
                user.setGivenName(rs.getString("givenName")) ;
                user.setFamilyName(rs.getString("familyName"));
                user.setBirthdate(rs.getDate("birthdate").toLocalDate());
                listOfBirthdays.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfBirthdays;
    }

    public static void deleteBirthday(int id){
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

    public static ArrayList<BirthdaysManager> getBirthdaysByDate(LocalDate date) {
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

    public static void updateBirthday(BirthdaysManager updatedUser, int id){
        String sqlQuery = "UPDATE birthdays SET givenName = ?, familyName = ?, birthdate = ? where ID = ?";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setString(1, updatedUser.getGivenName());
            preparedStatement.setString(2, updatedUser.getFamilyName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(updatedUser.getBirthdate()));
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated successfully user " + updatedUser.getGivenName() + " " + updatedUser.getFamilyName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
