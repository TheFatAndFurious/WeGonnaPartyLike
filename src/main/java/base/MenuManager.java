package base;

import java.util.Map;
import java.util.Scanner;

public class MenuManager {
    InputHelper inputHelper;
    MessageHelper messageHelper;
    Map<Integer, MenuOption> menuOptionMap;
    public MenuManager(InputHelper inputHelper, MessageHelper messageHelper){
        this.inputHelper = inputHelper;
        this.messageHelper = messageHelper;

    }
        public void run(){
            int userChoice = inputHelper.getInputInteger("Choose an action");


        }
}
