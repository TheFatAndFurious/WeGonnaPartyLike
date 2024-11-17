package base;

import base.util.InputHelper;
import base.util.MessageHelper;

/**
 * Class responsible for user deletions
 */
public class DeleteBirthdayCommand implements Command{
    MessageHelper messageHelper;
    InputHelper inputHelper;
    Database database;

    DeleteBirthdayCommand(MessageHelper messageHelper, InputHelper inputHelper, Database database){
        this.messageHelper = messageHelper;
        this.inputHelper = inputHelper;
        this.database = database;
    }

    /**
     * user will be asked for the ID of the user he wants to delete, after he first inputs it he will then be asked to confirm it before any deletion is made
     */
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
                messageHelper.PrintFormattedMessage(Messages.BIRTHDAY_DELETED_SUCCESSFULLY);
                inputsMatching = true;
            } else {
                System.out.println(messageHelper.PrintFormattedMessage(Messages.ID_TO_DELETE_NOT_MATCHING));
            }
        }
        database.deleteBirthday(inputIdToDelete);

    }
}
