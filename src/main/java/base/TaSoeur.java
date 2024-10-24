package base;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class TaSoeur {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome my guy");
        String jdbcUrl = "jdbc:h2:~/test";
        String username = "user";
        String password = "secretStuff";
        InputHelper inputHelper = new ConsoleInputHelper();
        MessageHelper messageHelper = new MessageHelper();
        DataSource dataSource = new SimpleDataSource(jdbcUrl, username, password);
        Database database = new Database(dataSource, messageHelper);
        database.createTable();
        AddBirthdayCommand addBirthdayCommand = new AddBirthdayCommand(database, messageHelper, inputHelper);
        addBirthdayCommand.execute();
        ListBirthdaysCommand listBirthdaysCommand = new ListBirthdaysCommand(database, messageHelper);
        listBirthdaysCommand.execute();

    }
}
