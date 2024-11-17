package base;

import org.apache.commons.mail.EmailException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TasksManager {

    private Database database;
    private EmailService emailService;

    public TasksManager(Database database, EmailService emailService){
        this.database = database; this.emailService = emailService;
    }

    /**
     * This method will be used to check if there is a birthday coming up.
     * We will generate a start date (today) and a end date (in n number of days depending on of much ahead we want to search)
     *
     * @return a list of BirthdaysManager
     */
    public List<BirthdaysManager> checkBirthdays(LocalDate startDate, int range){

        try {
           return database.getBirthdaysByDate(startDate, range);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error retriveing upcoming birthdays", e);
        }
    }

    public Runnable sendBirthdayNotifications(List<BirthdaysManager> upcomingBirthdays){
        if(!upcomingBirthdays.isEmpty()){
            try{
                emailService.sendSimpleEmail("mathieu.baro@gmail.com", "upcoming birthdays", "winter is coming");
            } catch (EmailException e) {
                throw new RuntimeException("Error trying to send email", e);
            }
        }
    }
}
