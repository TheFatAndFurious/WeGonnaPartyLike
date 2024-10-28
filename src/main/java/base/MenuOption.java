package base;

public class MenuOption {

    String description;
    Command command;

    public MenuOption(String description, Command command){
        this.description = description;
        this.command = command;
    }


    public String getDescription() {
        return description;
    }

    public Command getCommand() {
        return command;
    }
}
