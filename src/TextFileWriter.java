import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {

    public static void writeToFile(String fileName, String message) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("" + message);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
