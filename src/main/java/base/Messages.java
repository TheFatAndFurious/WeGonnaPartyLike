package base;

public enum Messages {
    BIRTHDAY_ADDED_SUCCESSFULLY("%s %s birthday has been added successfully"),
    BIRTHDAY_UPDATED_SUCCESSFULLY("%s %s birthday has been updated successfully"),
    BIRTHDAY_UPDATE_FAILURE("%s %s birthday failed to update"),
    BIRTHDAY_DELETED_SUCCESSFULLY("Birthday has been deleted successfully"),
    BIRTHDAY_DELETED_FAILURE("Failed to delete birthday");

    private final String template;

    Messages(String template) {
        this.template = template;
    }

    public String getTemplate(){
        return template;
    }
}
