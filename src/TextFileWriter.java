import java.io.IOException;
import java.nio.file.*;

public class TextFileWriter {

    public static void writeToFile(String fileName, String message) {
        try {
            Files.writeString(Path.of(fileName), message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}