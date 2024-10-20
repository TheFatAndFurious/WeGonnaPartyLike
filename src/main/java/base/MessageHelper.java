package base;

public class MessageHelper {

    public void PrintMessage(String message) {
        System.out.println(message);
    }

    public void PrintFormattedMessage(Messages messageEnum, Object... args){
        String formattedMessage = String.format(messageEnum.getTemplate(), args);
        System.out.println(formattedMessage);
    }

    public void PrintErrorMessage(Messages messageEnum, Exception e){
        String formattedMessage = "ERROR: " + messageEnum.getTemplate();
        System.out.println(formattedMessage);
        if (e != null){
            e.printStackTrace(System.err);
        }
    }
}
