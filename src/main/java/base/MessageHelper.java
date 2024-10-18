package base;

public class MessageHelper {

    public static void PrintMessage(String message) {
        System.out.println(message);
    }

    public static void PrintFormattedMessage(String message, Object... args){
        System.out.println(String.format(message, args));
    }

    public static void PrintErrorMessage(String message, Exception e){
        System.out.println("ERROR: " + message);
        if (e != null){
            e.printStackTrace(System.err);
        }
    }
}
