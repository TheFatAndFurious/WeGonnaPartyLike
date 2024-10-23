package base;

public enum Messages {
    BIRTHDAY_ADDED_SUCCESSFULLY("%s %s birthday has been added successfully"),
    BIRTHDAY_UPDATED_SUCCESSFULLY("%s %s birthday has been updated successfully"),
    BIRTHDAY_UPDATE_FAILURE("%s %s birthday failed to update"),
    BIRTHDAY_DELETED_SUCCESSFULLY("Birthday has been deleted successfully"),
    BIRTHDAY_DELETED_FAILURE("Failed to delete birthday"),
    ENTER_GIVEN_NAME("Please enter a given name"),
    ENTER_FAMILY_NAME("Please enter family name"),
    ENTER_BIRTHDATE("Please enter a birthdate: YYYY-MM-DD");

    private final String template;

    Messages(String template) {
        this.template = template;
    }

    public String getTemplate(){
        return template;
    }
}
