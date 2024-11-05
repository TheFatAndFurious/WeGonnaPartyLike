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
     * We will generate a start date (today) and a end date (in n number of days depending on of much ahead we want to search)
     *
     * @return
     */
    public Runnable checkBirthdays(LocalDate startDate, int range){
        var birthdays = new ArrayList<BirthdaysManager>();

        try {
            birthdays = database.getBirthdaysByDate(startDate, range);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        birthdays.forEach(BirthdaysManager -> {
            System.out.println(BirthdaysManager.getFamilyName());
        });
        if(!birthdays.isEmpty()){
            // trigger some action
            System.out.println("Pas d'annifs bruh");
        }
        return null;
    }
}
