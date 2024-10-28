package base;

import javax.sql.DataSource;
import java.sql.SQLException;

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
        Application application = new Application(database, messageHelper, inputHelper);
        application.start();
    }
}
