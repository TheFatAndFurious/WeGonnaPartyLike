package base;

import org.apache.commons.mail.EmailException;

import java.sql.SQLException;

public class TaSoeur {
    public static void main(String[] args) throws SQLException, EmailException {
        System.out.println("Welcome my guy");

        Application application = new Application();
        application.initializeApp();
        application.start();

    }
}
