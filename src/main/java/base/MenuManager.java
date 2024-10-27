package base;

import java.util.Scanner;

public class MenuManager {
    InputHelper inputHelper;
    MessageHelper messageHelper;
    Scanner scanner = new Scanner(System.in);
    Command command;

    public MenuManager(InputHelper inputHelper, MessageHelper messageHelper){
        this.inputHelper = inputHelper;
        this.messageHelper = messageHelper;

    }
        public void run(){
            int userChoice = inputHelper.getInputInteger("Choose an action");


        }
}
