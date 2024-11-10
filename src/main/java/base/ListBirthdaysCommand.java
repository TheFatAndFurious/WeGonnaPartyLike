package base;

import base.util.MessageHelper;

import java.util.ArrayList;

/**
 * This class will be used to print a list of all the birthdays in the database
 */

public class ListBirthdaysCommand implements Command{
        Database database;
        MessageHelper messageHelper;

        public ListBirthdaysCommand(Database database, MessageHelper messageHelper){
            this.messageHelper = messageHelper;
            this.database = database;
        }

    @Override
    public void execute() {

            ArrayList<BirthdaysManager> birthdays = database.getAllBirthdays();

            if (birthdays.isEmpty()){
                System.out.println(messageHelper.PrintFormattedMessage(Messages.DATABASE_EMPTY));
            } else {
                for (BirthdaysManager users : birthdays){
                    System.out.println(users.getId() + " -- " + users.getGivenName() + " " + users.getFamilyName() + " -- " + users.getBirthdate());
                }
            }
    }
}
