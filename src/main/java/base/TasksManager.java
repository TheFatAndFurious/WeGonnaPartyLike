package base;

import org.apache.commons.mail.EmailException;

import java.time.LocalDate;
import java.util.ArrayList;

public class TasksManager {

    private Database database;
    private EmailService emailService;

    public TasksManager(Database database, EmailService emailService){
        this.database = database; this.emailService = emailService;
    }

    /**
     * This method will be used to check if there is a birthday coming up, if positive it will trigger an action (sending an email)
     * We will generate a start date (today) and a end date (in n number of days depending on of much ahead we want to search)
     *
     * @return a Runnable so we can use it as a param
     */
    public Runnable checkBirthdays(LocalDate startDate, int range){
        var birthdays = new ArrayList<BirthdaysManager>();

        try {
            birthdays = database.getBirthdaysByDate(startDate, range);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        if(!birthdays.isEmpty()){
            try{
                emailService.sendSimpleEmail("mrguerrilla@gmail.com", "birthday coming up", "gros test");
            } catch (EmailException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
