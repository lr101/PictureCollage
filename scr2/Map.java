import java.util.ArrayList;

public class Map {
    private static final int startX = 0;
    private static final int startY = 0;
    private final Shape shape;

    public Map(Shape shape) {
        this.shape = shape;
    }

    public void create(int height, int width) {
        double size = shape.calD(new Dimension(width, height), 9);
        System.out.println(size);
        System.out.println(area(size));
        //ArrayList<Coordinate> cor_list = shape.getCoordinates(new Coordinate(startX,startY), new Coordinate(width, height), new ArrayList<>());
    }

    public double area (double sideLength) {
        return (3 * Math.sqrt(3) * Math.pow(sideLength,2)) / 2.0;
    }
}
