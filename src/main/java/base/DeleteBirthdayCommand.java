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
        String idToDelete = messageHelper.PrintFormattedMessage(Messages.ID_TO_DELETE);
        String confirmIdToDelete = messageHelper.PrintFormattedMessage(Messages.CONFIRM_ID_TO_DELETE);
        String wrongConfirmation = messageHelper.PrintFormattedMessage(Messages.ID_TO_DELETE_NOT_MATCHING);

        String inputIdToDelete = inputHelper.getInputString(idToDelete);
        String inputConfirmIdToDelete = inputHelper.getInputString(confirmIdToDelete);



    }
}
