package other.utils.logger;

public class LoggerUtil {
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void logger(String message) {
        System.out.println(RED + "LOGGER:" + RESET + " " + message);
    }
}
