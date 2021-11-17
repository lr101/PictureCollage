import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class Shape {

    protected Image image;
    protected Boolean[][] map;
    protected int size;

    public Shape ( Image image, int size) throws IOException {
        this.image = image;
        this.map = this.getShapeMap(size);
        this.size = size;
    }

    public Shape () {
        this.image = null;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    abstract ArrayList<Coordinate> getCoordinates(Coordinate newC, Dimension borderD, ArrayList<Coordinate> listC, int size, int info);

    abstract Coordinate calC (Coordinate center, int size, int corner);

    abstract double calD (Dimension map, int numImages);

    abstract ArrayList<Shape> getShapes(int numImages, int size, ArrayList<Image> imageL) throws IOException;

    abstract int getXSize(int size);

    abstract int getYSize(int size);

    abstract Graphics2D writeShape(Graphics2D g, Coordinate center) throws IOException;

    abstract Boolean[][] getShapeMap (int size) throws IOException;

    @Override
    public String toString() {
        return "Size: " + size + " | Image: " + image.getPath().getAbsolutePath();
    }

}
