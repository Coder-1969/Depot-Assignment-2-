import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static Log singleInstance; // Singleton instance of the Log class
    private final StringBuffer logBuffer; // StringBuffer to store log events
    private final DateTimeFormatter timestampFormatter; // Formatter for timestamps

    // Private constructor for Singleton pattern
    private Log() {
        logBuffer = new StringBuffer();
        timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    // Public method to get the single instance of the Log class
    public static synchronized Log getInstance() {
        if (singleInstance == null) {
            singleInstance = new Log();
        }
        return singleInstance;
    }

    // Method to add an event to the log
    public void addEvent(String event) {
        String timestamp = LocalDateTime.now().format(timestampFormatter);
        logBuffer.append("[").append(timestamp).append("] ").append(event).append("\n");
    }

    // Method to retrieve all log events as a String
    public String getLog() {
        return logBuffer.toString();
    }

    // Method to clear the log
    public void clearLog() {
        logBuffer.setLength(0);
    }

    // Method to write the log to a text file
    public void writeLogToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(getLog());
            System.out.println("Log has been written to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing log to file: " + e.getMessage());
        }
    }

    // Method to display the log contents (for demonstration purposes)
    public void printLog() {
        System.out.println(getLog());
    }

    public void saveToFile(String logFileName) {
    }
}
