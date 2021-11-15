import java.util.ArrayList;

public abstract class Shape {

    protected final Coordinate coordinate;
    protected final Image image;

    public Shape (int xC, int xY, Image image) {
        coordinate = new Coordinate(xC, xY);
        this.image = image;
    }

    public int getCor_x() {
        return coordinate.getxC();
    }

    public int getCor_y() {
        return coordinate.getyC();
    }

    public Image getImage() {
        return image;
    }

    abstract ArrayList<Coordinate> getCoordinates(Coordinate lastC, Coordinate borderC, ArrayList<Coordinate> listC, int size);

    abstract Coordinate calC (Coordinate center, int size, int corner);

    abstract double calD (Dimension map, int numImages);

}
