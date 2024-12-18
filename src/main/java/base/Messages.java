package base;

public enum Messages {
    BIRTHDAY_ADDED_SUCCESSFULLY("%s %s birthday has been added successfully"),
    BIRTHDAY_UPDATED_SUCCESSFULLY("%s %s birthday has been updated successfully"),
    BIRTHDAY_ADD_FAILURE("Birthday was not added successfully, please re-try"),
    BIRTHDAY_UPDATE_FAILURE("%s %s birthday failed to update"),
    BIRTHDAY_DELETED_SUCCESSFULLY("Birthday has been deleted successfully"),
    BIRTHDAY_DELETED_FAILURE("Failed to delete birthday: no matching ID"),
    ENTER_GIVEN_NAME("Please enter a given name"),
    ENTER_FAMILY_NAME("Please enter family name"),
    ENTER_BIRTHDATE("Please enter a birthdate: YYYY-MM-DD"),
    ID_TO_DELETE("Please enter id to delete"),
    CONFIRM_ID_TO_DELETE("Please confirm ID to delete"),
    ID_TO_DELETE_NOT_MATCHING("Ids are not matching, please re-enter an ID to delete"),
    DATABASE_EMPTY("Database is empty, no birthdays to display"),
    WELCOME("===== BIRTHDAYS ====="),
    GOODBYE("Goodbye!! See you soon!"),
    WRONG_COMMAND("Wrong command, please try again"),
    ADD_ANOTHER_BIRTHDAY("Do you wish to add another birthday ? y - N"),
    ERROR_MESSAGE("There was an error: %s");

    private final String template;

    Messages(String template) {
        this.template = template;
    }

    public String getTemplate(){
        return template;
    }
}
