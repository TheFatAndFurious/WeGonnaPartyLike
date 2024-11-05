package base;

import java.time.LocalDate;
import java.util.ArrayList;

public class TasksManager {
    private Database database;

    public TasksManager(Database database){
        this.database = database;
    }

    /**
     * This method will be used to check if there is a birthday coming up, if positive it will trigger an action (sending an email)
     */
    public void checkBirthdays(){
        LocalDate todayDate = LocalDate.now();
        var birthdays = new ArrayList<BirthdaysManager>();
        var todayBirthdays = new ArrayList<BirthdaysManager>();


        try {
            birthdays = database.getBirthdaysByDate(todayDate);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        birthdays.forEach(BirthdaysManager -> {
            System.out.println(BirthdaysManager.getGivenName());
        });

    }
}
