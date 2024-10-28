package base;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuManager {
    InputHelper inputHelper;
    MessageHelper messageHelper;
    Map<Integer, MenuOption> menuOptionMap = new HashMap<>();
    public MenuManager(InputHelper inputHelper, MessageHelper messageHelper, Map<Integer, MenuOption> menuOptionMap){
        this.inputHelper = inputHelper;
        this.messageHelper = messageHelper;
        this.menuOptionMap = menuOptionMap;

    }
        public void start(){
            boolean exit = false;
                while(!exit){
                    int userChoice = inputHelper.getInputInteger("Choose an action");
                    if(userChoice == 0){
                        exit = true;
                        // TODO: implement a stop method to shutdown the app
                        messageHelper.PrintMessage(Messages.GOODBYE.getTemplate());
                    } else {
                        MenuOption selectedOption = menuOptionMap.get(userChoice);
                        if(selectedOption != null){
                            selectedOption.getCommand().execute();
                        } else {
                            messageHelper.PrintMessage(Messages.WRONG_COMMAND.getTemplate());
                        }
                    }
                    }
        }

        private void displayMenu(){
            messageHelper.PrintMessage(Messages.WELCOME.getTemplate());
            for(Map.Entry<Integer, MenuOption> entry : menuOptionMap.entrySet()){
                System.out.println(entry.getKey() + " - " + entry.getValue().getDescription());
            }
        }
}
