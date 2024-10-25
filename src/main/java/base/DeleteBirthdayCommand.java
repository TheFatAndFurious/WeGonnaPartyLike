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

        boolean inputsMatching = false;
        int inputIdToDelete = 0;
        while (!inputsMatching) {
            inputIdToDelete = inputHelper.getInputInteger(idToDelete);
            int inputConfirmIdToDelete = inputHelper.getInputInteger(confirmIdToDelete);
            if(inputIdToDelete == inputConfirmIdToDelete){
                inputsMatching = true;
            } else {
                System.out.println(messageHelper.PrintFormattedMessage(Messages.ID_TO_DELETE_NOT_MATCHING));
            }
        }
        database.deleteBirthday(inputIdToDelete);

    }
}
