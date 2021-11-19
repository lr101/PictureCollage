import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class Shape {

    protected Image image;
    protected Boolean[][] map;
    protected int size;

    public Shape ( Image image, int size, Boolean[][] map) {
        this.image = image;
        this.size = size;
        this.map = map;
    }

    public Shape () {
        this.image = null;
    }

    public Image getImage() {
        return image;
    }

    public void setMap(Boolean[][] map) { this.map = map; }

    public Boolean[][] getMap() { return this.map; }

    public int getSize() { return size; }

    public void setSize(int size) {this.size = size; }

    public void setImage(Image image) {
        this.image = image;
    }

    abstract ArrayList<Coordinate> getCoordinates(Dimension borderD);

    abstract Coordinate calCorner (Coordinate center, int corner);

    abstract int getHeight(int rows);

    abstract int getWidth(int width);

    abstract public int calSize(int width, int numI, int numR);

    abstract ArrayList<Shape> getShapes(int numImages, ArrayList<Image> imageL) throws IOException;

    abstract int getXSize();

    abstract int getYSize();

    abstract Graphics2D writeShape(Graphics2D g, Coordinate center, Dimension d) throws IOException;

    abstract Boolean[][] createShapeMap();

    @Override
    public String toString() {
        return "Size: " + this.size + " | Image: " + image.getPath().getAbsolutePath();
    }

}
