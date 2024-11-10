package base;

import base.util.ConsoleInputHelper;
import base.util.InputHelper;
import base.util.MessageHelper;
import config.Config;
import org.apache.commons.mail.EmailException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;

public class TaSoeur {
    public static void main(String[] args) throws SQLException, EmailException {
        System.out.println("Welcome my guy");

        Application application = new Application();
        application.initializeApp();
        application.start();

    }
}
