package base;

import javax.xml.crypto.Data;

public class DeleteBirthdayCommand implements Command{
    MessageHelper messageHelper;
    InputHelper inputHelper;
    Database database;

    DeleteBirthdayCommand(MessageHelper messageHelper, InputHelper inputHelper, Database database){
        this.messageHelper = messageHelper;
        this.inputHelper = inputHelper;
        this.database = database;
    }


    @Override
    public void execute() {
        String idToDelete = inputHelper.getInputInteger(messageHelper.)

    }
}
