package base;

import java.time.LocalDate;

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

        String inputGivenName = inputHelper.getInputString(givenName);
        String inputFamilyName = inputHelper.getInputString(familyName);
        LocalDate inputBirthdate = inputHelper.getInputLocalDate(birthdate);
        
    }
}
