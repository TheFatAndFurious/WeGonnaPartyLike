package base;

public class MenuManager {
    InputHelper inputHelper;
    MessageHelper messageHelper;

    public MenuManager(InputHelper inputHelper, MessageHelper messageHelper){
        this.inputHelper = inputHelper;
        this.messageHelper = messageHelper;

    }
        public void run(){
            System.out.println("Welcome my guy");
        }
}
