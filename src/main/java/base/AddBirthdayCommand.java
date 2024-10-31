package base;

import java.time.LocalDate;
import java.util.Objects;

public class AddBirthdayCommand implements Command{
    private Database database;
    private MessageHelper messageHelper;
    private InputHelper inputHelper;

    public AddBirthdayCommand(Database database, MessageHelper messageHelper, InputHelper inputHelper){
        this.database = database;
        this.messageHelper = messageHelper;
        this.inputHelper = inputHelper;
    }

    @Override
    public void execute() {
        String givenName = messageHelper.PrintFormattedMessage(Messages.ENTER_GIVEN_NAME);
        String familyName = messageHelper.PrintFormattedMessage(Messages.ENTER_FAMILY_NAME);
        String birthdate = messageHelper.PrintFormattedMessage(Messages.ENTER_BIRTHDATE);
        String addAnotherBirthday = messageHelper.PrintFormattedMessage(Messages.ADD_ANOTHER_BIRTHDAY);
        boolean operationIsFinished = false;

        while(!operationIsFinished){
            String inputGivenName = inputHelper.getInputString(givenName);
            String inputFamilyName = inputHelper.getInputString(familyName);
            LocalDate inputBirthdate = inputHelper.getInputLocalDate(birthdate);

            BirthdaysManager newEntry = new BirthdaysManager();
            newEntry.setGivenName(inputGivenName);
            newEntry.setFamilyName(inputFamilyName);
            newEntry.setBirthdate(inputBirthdate);
            database.addBirthday(newEntry);
            String inputAddAnotherBirthday = inputHelper.getInputString(addAnotherBirthday);
            if(Objects.equals(inputAddAnotherBirthday, "n")){
                operationIsFinished = true;
            }

        }
    }
}
