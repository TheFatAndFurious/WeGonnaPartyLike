package base;

public class MessageHelper {

    public String PrintMessage(String message) {
        return message;
    }

    public String PrintFormattedMessage(Messages messageEnum, Object... args){
        return String.format(messageEnum.getTemplate(), args);
    }

    public String PrintErrorMessage(Messages messageEnum, Exception e){
        if (e != null){
            e.printStackTrace(System.err);
        }
        return String.format("ERROR: " + messageEnum.getTemplate());
    }
}
