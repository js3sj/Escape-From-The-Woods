import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class TextFileReader {

    public static boolean readFromFile(GameDto dto) {
        int firstLineLength;
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of(dto.getREAD_FROM_FILE_NAME()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if ((lines != null) && (lines.size() > 0))
            firstLineLength = lines.get(0).length();
        else
            return false;

        if(!isFirstLineLengthCorrect(dto, firstLineLength))
            return false;

        checkLinesForMissingSymbols(dto, firstLineLength, lines);
        createArrayFromAList(dto, lines);

        if (!isMapHeightCorrect(dto, lines.size()))
            return false;

        return (isStartPositionFound(dto));
    }

    private static void checkLinesForMissingSymbols(GameDto dto, int lineLength, List<String> lines) {
        lines.replaceAll(x -> {
            return (x.length() < lineLength) ? "" + appendLineWithMissingSymbols(dto, x) : x;
        });
    }

    private static boolean isFirstLineLengthCorrect(GameDto dto, int lineLength) {
        if ((lineLength < dto.getMIN_AXIS_X_SIZE()) || (lineLength > dto.getMAX_AXIS_X_SIZE())) {
            TextFileWriter.writeToFile(dto.getWRITE_TO_FILE_NAME(),
                    Integer.toString(dto.getERROR_FOR_AXIS_X_OUT_OF_BOUNDS()));
            return false;
        } else {
            dto.setAxisXSize(lineLength);
        }

        return true;
    }

    private static String appendLineWithMissingSymbols(GameDto dto, String line) {
        return line + (""+dto.getEMPTY_SPACE_SYMBOL()).repeat(dto.getAxisXSize()-line.length());
    }

    private static void createArrayFromAList(GameDto dto, List<String> listOfLines) {
        int y = 0;
        for (String line : listOfLines){
            for (int x = 0; x < line.length(); x++){
                dto.setMapArray(x, y, line.charAt(x));
                if (line.charAt(x) == dto.getSTART_POSITION_SYMBOL()) {
                    dto.setXStart(x);
                    dto.setYStart(y);
                }
            };
            y++;
        };
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