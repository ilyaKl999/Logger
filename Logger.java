import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Logger {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String ANSI_RESET = "\u001B[0m";  //Сбрасывает цвет текста и фона к значениям по умолчанию.
    public static final String ANSI_RED = "\u001B[31m";   //Красный цвет текста (danger).
    public static final String ANSI_GREEN = "\u001B[32m"; //Зеленый цвет текста (successfull).
    public static final String ANSI_YELLOW = "\u001B[33m";//Желтый цвет текста (level).
    public static final String ANSI_PURPLE = "\u001B[35m";//Фиолетовый цвет текста (methodName/className).
    public static final String ANSI_CYAN = "\u001B[36m";  //Голубой цвет текста (timestamp).
    public static final String ANSI_BRIGHT_MAGENTA = "\u001B[95m"; // Ярко-пурпурный цвет (elapsedTime).

    public static void successfully(String message, long elapsedTime) {
        log(message, elapsedTime, ANSI_GREEN, "SUCCESS");
    }

    public static void danger(String message, long elapsedTime) {
        log(message, elapsedTime, ANSI_RED, "DANGER");
    }

    private static void log(String message, long elapsedTime, String color, String level) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[3]; // Индекс 3 соответствует вызывающему методу
        String className = caller.getClassName();
        String methodName = caller.getMethodName();
        String lineNumber = String.valueOf(caller.getLineNumber());
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        System.out.print(ANSI_CYAN + "[" + timestamp + "] " + ANSI_RESET);
        System.out.print(ANSI_PURPLE + "[" + className + "." + methodName + ":" + lineNumber + "] " + ANSI_RESET);
        System.out.print(ANSI_YELLOW + "[" + level + "] " + ANSI_RESET);
        System.out.println(color + "[" + message + "] " + ANSI_BRIGHT_MAGENTA + "[Time: " + elapsedTime + " ms]" + ANSI_RESET);
    }
}