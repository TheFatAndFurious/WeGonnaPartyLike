package base;

public enum Messages {
    BIRTHDAY_ADDED_SUCCESSFULLY("%s %s birthday has been added successfully"),
    BIRTHDAY_UPDATED_SUCCESSFULLY("%s %s birthday has been updated successfully"),
    BIRTHDAY_DELETED_SUCCESSFULLY("%s %s birthday has been deleted successfully");

    private final String template;

    Messages(String template) {
        this.template = template;
    }

    public String getTemplate(){
        return template;
    }
}
