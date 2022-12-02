import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class TextFileReader {

    public static boolean readFromFile(GameDto dto) {
        int firstStringLength;
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of(dto.getREAD_FROM_FILE_NAME()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if ((lines != null) && (lines.size() > 0))
            firstStringLength = lines.get(0).length();
        else
            return false;

        if(!isFirstLineLengthCorrect(dto, firstStringLength))
            return false;

        checkLinesForMissingSymbols(dto, firstStringLength, lines);
        createArrayFromAList(dto, lines);

        if (!isMapHeightCorrect(dto, lines.size()))
            return false;

        return (isStartPositionFound(dto));
    }

    private static void checkLinesForMissingSymbols(GameDto dto, int firstStringLength, List<String> lines) {
        lines.replaceAll(x -> {
            return (x.length() < firstStringLength) ? "" + appendLineWithMissingSymbols(dto, x, x.length()) : x;
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

    private static String appendLineWithMissingSymbols(GameDto dto, String lineFromFile, int lineLength) {
        return lineFromFile + (""+dto.getEMPTY_SPACE_SYMBOL()).repeat(dto.getAxisXSize()-lineLength);
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