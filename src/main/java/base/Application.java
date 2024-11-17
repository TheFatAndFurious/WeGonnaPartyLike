package base;

import base.util.ConsoleInputHelper;
import base.util.InputHelper;
import base.util.MessageHelper;
import config.Config;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


/**
 * This class will be used to instantiate everything we need to start and run the app
 */

public class Application {
    private Database database;
    private InputHelper inputHelper;
    private EmailService emailService;
    private ScheduleTaskManager scheduleTaskManager;
    private Map<Integer, MenuOption> menuOptionsMap;
    private MenuManager menuManager;
    private MessageHelper messageHelper;
    private Config config;
    private TasksManager tasksManager;

    public Application(){}

    /**
     * Initiating a Config object to be able the reach the config file
     */
    //TODO: Should we pass a param to allow to use different config files ?
    private void initConfig(){
        this.config = new Config();
    }

    /**
     * Initializing the classes we will use to print messages to the user and take user inputs
     */
    private void initHelpers() {
        this.messageHelper = new MessageHelper();
        this.inputHelper = new ConsoleInputHelper();
    }

    /**
     * Initializing the database
     * @throws SQLException that will bubble up and be handled by the caller of this method
     */
    private void initDatabase() throws SQLException {
        String jdbcUrl = config.getDBUrl();
        String username = config.getDBUsername();
        String password = config.getDBPassword();
        DataSource dataSource = new SimpleDataSource(jdbcUrl, username, password);
        database = new Database(dataSource, messageHelper);
        try{
            database.createTable();
        } catch (RuntimeException e) {
            messageHelper.PrintFormattedMessage(Messages.ERROR_MESSAGE, e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Initializing the services used to send emails and the scheduler
     */
    private void initServices(){
        this.scheduleTaskManager = new ScheduleTaskManager();
        this.emailService = new EmailService();
    }

    /**
     * Initializing the class responsible for tasks logic
     */
    private void initTasks(){
        this.tasksManager = new TasksManager(database, emailService);
    }

    /**
     * Passing a task to the scheduler
     */
    private void scheduleTasks(){
        Runnable task = () -> {
            var upcomingBirthdays = tasksManager.checkBirthdays(LocalDate.now(), 5);
            tasksManager.sendBirthdayNotifications(upcomingBirthdays);
            };
            scheduleTaskManager.runService(task);
    }

    public void initializeApp(){
        try {
            initConfig();
            initHelpers();
            initDatabase();
            initServices();
            initTasks();
            initializeCommands();
        } catch (Exception e){
            System.out.println("Error starting the system" + e);
            e.getStackTrace();
            System.exit(1);
        }
    }

    /**
     * Building the menu
     */
    private void initializeCommands(){
        menuOptionsMap = new LinkedHashMap<>();
        menuManager = new MenuManager(inputHelper, messageHelper, menuOptionsMap);
        Command addBirthday = new AddBirthdayCommand(database, messageHelper, inputHelper);
        Command deleteBirthday = new DeleteBirthdayCommand(messageHelper, inputHelper, database);
        Command listBirthdays = new ListBirthdaysCommand(database, messageHelper);
        NotesManager notesManager = new NotesManager();

        menuOptionsMap.put(1, new MenuOption("Add new birthday", addBirthday));
        menuOptionsMap.put(2, new MenuOption("List all birthdays", listBirthdays));
        menuOptionsMap.put(3, new MenuOption("Delete a birthday", deleteBirthday));
        menuOptionsMap.put(4, new MenuOption("Update a birthday", deleteBirthday));
        menuOptionsMap.put(5, new MenuOption("Take notes", notesManager));
    }

    public void start(){
        menuManager.start();
    }
}
