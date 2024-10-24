package base;

public enum Messages {
    BIRTHDAY_ADDED_SUCCESSFULLY("%s %s birthday has been added successfully"),
    BIRTHDAY_UPDATED_SUCCESSFULLY("%s %s birthday has been updated successfully"),
    BIRTHDAY_UPDATE_FAILURE("%s %s birthday failed to update"),
    BIRTHDAY_DELETED_SUCCESSFULLY("Birthday has been deleted successfully"),
    BIRTHDAY_DELETED_FAILURE("Failed to delete birthday"),
    ENTER_GIVEN_NAME("Please enter a given name"),
    ENTER_FAMILY_NAME("Please enter family name"),
    ENTER_BIRTHDATE("Please enter a birthdate: YYYY-MM-DD"),
    ID_TO_DELETE("Please enter id to delete"),
    CONFIRM_ID_TO_DELETE("Please confirm ID to delete"),
    ID_TO_DELETE_NOT_MATCHING("Ids are not matching, please re-enter an ID to delete"),
    DATABASE_EMPTY("Database is empty, no birthdays to display"),
    BIRTHDAYS_LIST("===== BIRTHDAYS =====");

    private final String template;

    Messages(String template) {
        this.template = template;
    }

    public String getTemplate(){
        return template;
    }
}
