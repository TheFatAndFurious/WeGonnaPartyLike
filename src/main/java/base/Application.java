package base;

import java.util.LinkedHashMap;
import java.util.Map;

public class Application {
    private Database database;
    private MessageHelper messageHelper;
    private InputHelper inputHelper;
    private Map<Integer, MenuOption> menuOptionsMap;
    private MenuManager menuManager;

    public Application(Database database, MessageHelper messageHelper, InputHelper inputHelper){
        this.database = database;
        this.messageHelper = messageHelper;
        this.inputHelper = inputHelper;

        menuOptionsMap = new LinkedHashMap<>();
        menuManager = new MenuManager(inputHelper, messageHelper, menuOptionsMap);
        initializeCommands();
    }



    private void initializeCommands(){
        Command addBirthday = new AddBirthdayCommand(database, messageHelper, inputHelper);
        Command deleteBirthday = new DeleteBirthdayCommand(messageHelper, inputHelper, database);
        Command listBirthdays = new ListBirthdaysCommand(database, messageHelper);

        menuOptionsMap.put(1, new MenuOption("Add new birthday", addBirthday));
        menuOptionsMap.put(2, new MenuOption("List all birthdays", listBirthdays));
        menuOptionsMap.put(3, new MenuOption("Delete a birthday", deleteBirthday));
        menuOptionsMap.put(4, new MenuOption("Update a birthday", deleteBirthday));
    }

    public void start(){
        menuManager.start();
    }
}
