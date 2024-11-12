package base;

import base.util.InputHelper;
import base.util.MessageHelper;

import java.time.LocalDate;
import java.util.Objects;


/**
 * This Class will implement the Command interface so we can use the .execute method to run each option from the Menu (Add, Edit, Delete, List).
 * We will first use a initMessages method to generate all the messages that will be used to ask the user for inputs.
 */
public class AddBirthdayCommand implements Command{
    private Database database;
    private MessageHelper messageHelper;
    private InputHelper inputHelper;
    private String enterGivenName;
    private String enterFamilyName;
    private String enterBirthdate;
    private String addAnotherBirthday;

    public AddBirthdayCommand(Database database, MessageHelper messageHelper, InputHelper inputHelper){
        this.database = database;
        this.messageHelper = messageHelper;
        this.inputHelper = inputHelper;
        initMessages();
    }

    private void initMessages(){
        this.enterGivenName = messageHelper.PrintFormattedMessage(Messages.ENTER_GIVEN_NAME);
        this.enterFamilyName = messageHelper.PrintFormattedMessage(Messages.ENTER_FAMILY_NAME);
        this.enterBirthdate = messageHelper.PrintFormattedMessage(Messages.ENTER_BIRTHDATE);
        this.addAnotherBirthday = messageHelper.PrintFormattedMessage(Messages.ADD_ANOTHER_BIRTHDAY);

    }

    @Override
    public void execute() {
        boolean operationIsFinished = false;

        while(!operationIsFinished){
            String inputGivenName = inputHelper.getInputString(enterGivenName);
            String inputFamilyName = inputHelper.getInputString(enterFamilyName);
            LocalDate inputBirthdate = inputHelper.getInputLocalDate(enterBirthdate);

            BirthdaysManager newEntry = new BirthdaysManager();
            newEntry.setGivenName(inputGivenName);
            newEntry.setFamilyName(inputFamilyName);
            newEntry.setBirthdate(inputBirthdate);
            try{
                var createdBirthday = database.addBirthday(newEntry);
                if (createdBirthday != null){
                    messageHelper.PrintMessage(Messages.BIRTHDAY_ADDED_SUCCESSFULLY.getTemplate());
                } else {
                    messageHelper.PrintMessage(Messages.BIRTHDAY_ADD_FAILURE.getTemplate());
                }
            } catch (RuntimeException e) {
                messageHelper.PrintFormattedMessage(Messages.ERROR_MESSAGE, e);
            }
            String inputAddAnotherBirthday = inputHelper.getInputString(addAnotherBirthday);
            if(Objects.equals(inputAddAnotherBirthday, "n")){
                operationIsFinished = true;
            }

        }
    }

}
