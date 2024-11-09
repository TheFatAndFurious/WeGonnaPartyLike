package base;

import base.util.ConsoleInputHelper;
import base.util.InputHelper;
import base.util.MessageHelper;
import config.Config;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This class will be used to instantiate everything we need to start and run the app
 */

public class Application {
    private Database database;
    private InputHelper inputHelper;
    private Map<Integer, MenuOption> menuOptionsMap;
    private MenuManager menuManager;
    private MessageHelper messageHelper;
    private Config config;
    public Application(Database database){
        this.database = database;
    }

    /**
     * Initiating a Config object to be able the reach the config file
     */
    //TODO: Should we pass a param to allow to use different config files ?
    public void initConfig(){
        this.config = new Config();
    }

    public void initHelpers() {
        this.messageHelper = new MessageHelper();
        this.inputHelper = new ConsoleInputHelper();
    }

    public void initializeDatabase() throws SQLException {
        String jdbcUrl = config.getDBUrl();
        String username = config.getDBUsername();
        String password = config.getDBPassword();
        DataSource dataSource = new SimpleDataSource(jdbcUrl, username, password);
        Database database = new Database(dataSource, messageHelper);
        try{
            database.createTable();
        } catch (RuntimeException e) {
            messageHelper.PrintFormattedMessage(Messages.ERROR_MESSAGE, e.getMessage());
            System.exit(1);
        }
    }
    public void initializeApp(){
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
