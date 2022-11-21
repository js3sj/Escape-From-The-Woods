public class GameDto {
    private final String PATH = "src/resources/";
    private final String INPUT_FILE_NAME = "map2.txt";
    private final String OUTPUT_FILE_NAME = "result.txt";

    private final String READ_FROM_FILE_NAME = PATH + INPUT_FILE_NAME;
    private final String WRITE_TO_FILE_NAME = PATH + OUTPUT_FILE_NAME;

    private final int MIN_AXIS_X_SIZE = 5;
    private final int MIN_AXIS_Y_SIZE = 5;
    private final int MAX_AXIS_X_SIZE = 11000;
    private final int MAX_AXIS_Y_SIZE = 11000;

    private final int ERROR_FOR_AXIS_X_OUT_OF_BOUNDS = 0;
    private final int ERROR_FOR_AXIS_Y_OUT_OF_BOUNDS = 0;
    private final int ERROR_FOR_NO_START_POSITION = 0;
    private final int ERROR_FOR_NO_AVAILABLE_EXITS = 0;

    private final char EMPTY_SPACE_SYMBOL = ' ';
    private final char START_POSITION_SYMBOL = 'X';

    private char[][] mapArray;
    private int axisXSize;
    private int axisYSize;
    private int xStart;
    private int yStart;

    public GameDto() {
        this.mapArray = new char[MAX_AXIS_Y_SIZE][MAX_AXIS_X_SIZE];
        this.xStart = -1;
        this.yStart = -1;
    }

    public String getPATH() {
        return PATH;
    }

    public String getINPUT_FILE_NAME() {
        return INPUT_FILE_NAME;
    }

    public String getOUTPUT_FILE_NAME() {
        return OUTPUT_FILE_NAME;
    }

    public String getREAD_FROM_FILE_NAME() {
        return READ_FROM_FILE_NAME;
    }

    public String getWRITE_TO_FILE_NAME() {
        return WRITE_TO_FILE_NAME;
    }

    public int getMIN_AXIS_X_SIZE() {
        return MIN_AXIS_X_SIZE;
    }

    public int getMIN_AXIS_Y_SIZE() {
        return MIN_AXIS_Y_SIZE;
    }

    public int getMAX_AXIS_X_SIZE() {
        return MAX_AXIS_X_SIZE;
    }

    public int getMAX_AXIS_Y_SIZE() {
        return MAX_AXIS_Y_SIZE;
    }

    public int getERROR_FOR_AXIS_X_OUT_OF_BOUNDS() {
        return ERROR_FOR_AXIS_X_OUT_OF_BOUNDS;
    }

    public int getERROR_FOR_AXIS_Y_OUT_OF_BOUNDS() {
        return ERROR_FOR_AXIS_Y_OUT_OF_BOUNDS;
    }

    public int getERROR_FOR_NO_START_POSITION() {
        return ERROR_FOR_NO_START_POSITION;
    }

    public int getERROR_FOR_NO_AVAILABLE_EXITS() {
        return ERROR_FOR_NO_AVAILABLE_EXITS;
    }

    public char getEMPTY_SPACE_SYMBOL() {
        return EMPTY_SPACE_SYMBOL;
    }

    public char getSTART_POSITION_SYMBOL() {
        return START_POSITION_SYMBOL;
    }

    public char[][] getMapArray() {
        return mapArray;
    }

    public void setMapArray(int posX, int posY, char symbol) {
        this.mapArray[posY][posX] = symbol;
    }

    public int getAxisXSize() {
        return axisXSize;
    }

    public void setAxisXSize(int axisXSize) {
        this.axisXSize = axisXSize;
    }

    public int getAxisYSize() {
        return axisYSize;
    }

    public void setAxisYSize(int axisYSize) {
        this.axisYSize = axisYSize;
    }

    public int getXStart() {
        return xStart;
    }

    public void setXStart(int xStart) {
        this.xStart = xStart;
    }

    public int getYStart() {
        return yStart;
    }

    public void setYStart(int yStart) {
        this.yStart = yStart;
    }
}
