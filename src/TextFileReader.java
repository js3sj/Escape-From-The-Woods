import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileReader {

    public static boolean readFromFile(GameDto dto) {
        try {
            File fileToReadObj = new File(dto.getREAD_FROM_FILE_NAME());
            Scanner myReader = new Scanner(fileToReadObj);
            int y = 0;

            while (myReader.hasNextLine()) {
                String lineFromFile = myReader.nextLine();
                int lineLength = lineFromFile.length();

                if (!isFirstLineLengthCorrect(dto, y, lineLength))
                    return false;
                if (lineLength < dto.getAxisXSize()){
                    lineFromFile = appendLineWithMissingSymbols(dto, lineFromFile, lineLength);
                }
                processLineFromFile(dto, lineFromFile, y);
                y++;
            }

            if (!isMapHeightCorrect(dto, y))
                return false;

            return (isStartPositionFound(dto));

        } catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isFirstLineLengthCorrect(GameDto dto, int y, int lineLength) {
        if (y == 0) {
            if ((lineLength < dto.getMIN_AXIS_X_SIZE()) || (lineLength > dto.getMAX_AXIS_X_SIZE())) {
                TextFileWriter.writeToFile(dto.getWRITE_TO_FILE_NAME(),
                        Integer.toString(dto.getERROR_FOR_AXIS_X_OUT_OF_BOUNDS()));
                return false;
            } else {
                dto.setAxisXSize(lineLength);
            }
        }
        return true;
    }

    private static String appendLineWithMissingSymbols(GameDto dto, String lineFromFile, int lineLength) {
        StringBuilder sb = new StringBuilder(lineFromFile);
        for (int i = lineLength; i < dto.getAxisXSize(); i++)
            sb.append(dto.getEMPTY_SPACE_SYMBOL());
        return sb.toString();
    }

    private static void processLineFromFile(GameDto dto, String lineFromFile, int y) {
        for (int x = 0; x < lineFromFile.length(); x++) {
            dto.setMapArray(x, y, lineFromFile.charAt(x));
            if (lineFromFile.charAt(x) == dto.getSTART_POSITION_SYMBOL()) {
                dto.setXStart(x);
                dto.setYStart(y);
            }
        }
    }

    private static boolean isMapHeightCorrect(GameDto dto, int y) {
        if ((y < dto.getMIN_AXIS_Y_SIZE() ) || (y > dto.getMAX_AXIS_Y_SIZE())) {
            TextFileWriter.writeToFile(dto.getWRITE_TO_FILE_NAME(),
                    Integer.toString(dto.getERROR_FOR_AXIS_Y_OUT_OF_BOUNDS()));
            return false;
        } else {
            dto.setAxisYSize(y);
        }
        return true;
    }

    private static boolean isStartPositionFound(GameDto dto) {
        if ((dto.getXStart() >= 0) && (dto.getYStart() >= 0))
            return true;
        else {
            TextFileWriter.writeToFile(dto.getWRITE_TO_FILE_NAME(),
                    Integer.toString(dto.getERROR_FOR_NO_START_POSITION()));
            return false;
        }
    }
}
