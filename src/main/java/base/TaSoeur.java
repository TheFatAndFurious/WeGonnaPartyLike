package base;

import config.Config;
import org.apache.commons.mail.EmailException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TaSoeur {
    public static void main(String[] args) throws SQLException, EmailException {
        System.out.println("Welcome my guy");
        Config config =new Config();
        String jdbcUrl = config.getDBUrl();
        String username = config.getDBUsername();
        String password = config.getDBPassword();
        InputHelper inputHelper = new ConsoleInputHelper();
        MessageHelper messageHelper = new MessageHelper();

        DataSource dataSource = new SimpleDataSource(jdbcUrl, username, password);
        Database database = new Database(dataSource, messageHelper);
        try{
            database.createTable();
        } catch (RuntimeException e) {
            messageHelper.PrintFormattedMessage(Messages.ERROR_MESSAGE, e.getMessage());
            System.exit(1);
        }
        EmailService emailService = new EmailService();
        System.out.println("test email");
        try{
            emailService.sendSimpleEmail();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
        ScheduleTaskManager scheduleTaskManager = new ScheduleTaskManager();
        TasksManager tasksManager = new TasksManager(database);
        Runnable task = tasksManager.checkBirthdays(LocalDate.now(), 5);
        if (task != null) {
            scheduleTaskManager.runService(task);
        }
        Application application = new Application(database, messageHelper, inputHelper);
        application.start();

    }
}
