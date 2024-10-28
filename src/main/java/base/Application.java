package base;

public class Application {
    private Database database;
    private MessageHelper messageHelper;
    private InputHelper inputHelper;
    private MenuManager menuManager;


    private void initializeCommands(){
        Command addBirthday = new AddBirthdayCommand(database, messageHelper, inputHelper);
        Command deleteBirthday = new DeleteBirthdayCommand(messageHelper, inputHelper, database);
        Command listBirthdays = new ListBirthdaysCommand(database, messageHelper);
    }
}
