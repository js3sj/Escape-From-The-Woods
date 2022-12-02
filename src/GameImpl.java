import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameImpl {
    char EMPTY_SPACE_SYMBOL;
    char[][] mapArray;
    int axisXSize;
    int axisYSize;
    int xStart;
    int yStart;
    ArrayList<Integer> foundPathsInMap;

    public void run() {
        GameDto dto = new GameDto();

        if (TextFileReader.readFromFile(dto)) {
            setGameData(dto);
            printMap(mapArray, axisXSize, axisYSize, xStart, yStart);
            searchAllPaths();
            if (foundPathsInMap.size() > 0) {
                int result = Collections.min(foundPathsInMap);
                System.out.println("Shortest path length = %d".formatted(result));
                TextFileWriter.writeToFile(dto.getWRITE_TO_FILE_NAME(),
                        Integer.toString(result));
            } else
                TextFileWriter.writeToFile(dto.getWRITE_TO_FILE_NAME(),
                        Integer.toString(dto.getERROR_FOR_NO_AVAILABLE_EXITS()));
        }
    }

    private void setGameData(GameDto dto) {
        mapArray = dto.getMapArray();
        EMPTY_SPACE_SYMBOL = dto.getEMPTY_SPACE_SYMBOL();
        axisXSize = dto.getAxisXSize();
        axisYSize = dto.getAxisYSize();
        xStart = dto.getXStart();
        yStart = dto.getYStart();
        foundPathsInMap = new ArrayList<>();
    }

    private void searchAllPaths() {
        for (Point pnt : getPointsAround(new Point(xStart, yStart))) {
            HashMap<Integer, String> newPath = new HashMap<>();
            newPath.put(0, pnt.toString());
            moveToNextPoint(pnt, newPath);
        }
    }

    private ArrayList<Point> getPointsAround(Point point) {
        int x = point.x();
        int y = point.y();

        Point pointUp = new Point(x, y-1);
        Point pointRight = new Point(x+1, y);
        Point pointBelow = new Point(x, y+1);
        Point pointLeft = new Point(x-1, y);

        ArrayList<Point> pointsAround = new ArrayList<>();
        pointsAround.add(pointUp);
        pointsAround.add(pointRight);
        pointsAround.add(pointBelow);
        pointsAround.add(pointLeft);

        return pointsAround;
    }

    private void moveToNextPoint(Point point, HashMap<Integer, String> path) {

        if (isPointOnTheMapEmpty(point))
            if (isPointOnThePerimeter(point)) {
                foundPathsInMap.add(path.size());
            } else {
                for (Point pnt : getPointsAround(point)) {
                    if ((isPointOnTheMapEmpty(pnt))
                            && !isPointOnThePath(pnt, path)) {
                        HashMap<Integer, String> newPath = new HashMap<>(path);
                        newPath.put(path.size(), pnt.toString());
                        moveToNextPoint(pnt, newPath);
                    }
                }
            }
    }

    private boolean isPointOnThePath(Point point, HashMap<Integer, String> path) {
        return path.containsValue(point.toString());
    }

    private boolean isPointOnTheMapEmpty(Point point){
        return mapArray[point.y()][point.x()] == EMPTY_SPACE_SYMBOL;
    }

    private boolean isPointOnThePerimeter(Point point) {
        int x = point.x();
        int y = point.y();

        return ((((x > 0) && (x < axisXSize - 1)) && ((y == 0) || (y == axisYSize - 1)))
                || (((y > 0) && (y < axisYSize - 1)) && (((x == 0)) || (((x == axisXSize - 1))))));
    }

    private void printMap(char[][] map, int xLength, int yLength, int xMain, int yMain) {
        for(int y = 0; y < yLength; y++) {
            for(int x = 0; x < xLength; x++) {
                System.out.print("" + map[y][x]);
            }
            System.out.println();
        }
        System.out.printf("Map length(x) x height(y) = %d x %d%n", xLength, yLength);
        System.out.printf("Start position (x,y) = (%d,%d)%n", xMain, yMain);
    }
}