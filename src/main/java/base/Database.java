package base;

import base.util.MessageHelper;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Provides data access methods for birthdays entries including inserting, retrieving, updating and deleting
 */
public class Database {
    private DataSource dataSource;
    private MessageHelper messageHelper;

    /**
     * Constructs a new {@code Database} with the specified data source and message helper
     * @param dataSource The data source for database connections
     * @param messageHelper The message helper for displaying messages to users
     */
    public Database(DataSource dataSource, MessageHelper messageHelper){
        this.dataSource = dataSource;
        this.messageHelper = messageHelper;
    }

    /**
     * Creates a new database if none exists
     * @throws RuntimeException in case of failure to create a new database, it will be bubble up until it is caught and the program is exited as it is an unrecoverable error
     */
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS birthdays (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "givenName VARCHAR (255) NOT NULL, " +
                    "familyName VARCHAR (255) NOT NULL, " +
                    "birthdate DATE NOT NULL " +
                    ");";

        try {
            Connection conn = dataSource.getConnection();
            Statement smt = conn.createStatement();
            smt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("An error happened trying to create a database", e);
        }
    }

    /**
     * Add a new birthday entry to the database
     *
     * @param newEntry The {@code BirthdayManager} object containing the details for the new entry
     * @return The {@code BirthdayManager} object with the generated ID if the insert was made successfully
     *          or {@code null} if the insert failed and no Id was generated
     * @throws SQLException if any SQL exception is thrown
     */
    public BirthdaysManager addBirthday(BirthdaysManager newEntry) throws RuntimeException{
        String sql = "INSERT INTO birthdays (givenName, familyName, birthdate) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, newEntry.givenName);
                preparedStatement.setString(2, newEntry.familyName);
                preparedStatement.setObject(3, newEntry.birthdate);
                preparedStatement.executeUpdate();

                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if (generatedKeys.next()) {
                        newEntry.setId(generatedKeys.getInt(1));
                    } else {
                        return null;
                    }
                }
            return newEntry;
        } catch (SQLException e) {
            throw new RuntimeException("Error adding birthday to the database", e);
        }
    }

    public ArrayList<BirthdaysManager> getAllBirthdays(){

        ArrayList<BirthdaysManager> listOfBirthdays = new ArrayList<>();
        String sql = "SELECT id, givenName, familyName, birthdate from birthdays";

        try (Connection conn = dataSource.getConnection();
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

    public boolean deleteBirthday(int id){
        String sqlQuery = "DELETE FROM birthdays WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setInt(1, id);
                int affectedRows = preparedStatement.executeUpdate();

                if(affectedRows > 0){
                    System.out.println(messageHelper.PrintFormattedMessage(Messages.BIRTHDAY_DELETED_SUCCESSFULLY));
                    return true;
                } else {
                    System.out.println(messageHelper.PrintFormattedMessage(Messages.BIRTHDAY_DELETED_FAILURE));
                    return false;
                }
        } catch (Exception e) {
            throw new RuntimeException("Something unexpected happened", e);
        }
    }

    public ArrayList<BirthdaysManager> getBirthdaysByDate(LocalDate date, int range) {
        if (date == null) {
            throw new IllegalArgumentException("Date parameter cannot be null");
        }

        // Make it a method ?
        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(date);
        for(int i = 0; i <= range; i++){
            dates.add(date.plusDays(i + 1));
        }

        String sqlQuery = "SELECT id, givenName, familyName, birthdate FROM birthdays WHERE EXTRACT(MONTH FROM birthdate) = ? AND EXTRACT(DAY FROM birthdate) = ?";
        ArrayList<BirthdaysManager> listOfBirthdays = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {

            dates.forEach(LocalDate -> {
            int day = LocalDate.getDayOfMonth();
            int month = LocalDate.getMonthValue();

            try{
                pstmt.setInt(1, month);
                pstmt.setInt(2, day);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            });

        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
        return listOfBirthdays;
    }

    public boolean updateBirthday(BirthdaysManager updatedUser, int id){
        String sqlQuery = "UPDATE birthdays SET givenName = ?, familyName = ?, birthdate = ? where ID = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setString(1, updatedUser.getGivenName());
            preparedStatement.setString(2, updatedUser.getFamilyName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(updatedUser.getBirthdate()));
            preparedStatement.setInt(4, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                messageHelper.PrintFormattedMessage(Messages.BIRTHDAY_UPDATED_SUCCESSFULLY, updatedUser.getGivenName(), updatedUser.getFamilyName());
                return true;
            } else {
                messageHelper.PrintFormattedMessage(Messages.BIRTHDAY_UPDATE_FAILURE, updatedUser.getGivenName(), updatedUser.getFamilyName());
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
