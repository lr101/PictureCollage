import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class Shape {

    protected Image image;
    protected Boolean[][] map;
    protected int size;

    public Shape ( Image image, int size) {
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

    abstract ArrayList<Coordinate> getCoordinates(Dimension borderD, int size);

    abstract Coordinate calC (Coordinate center, int size, int corner);

    abstract int getHeight(int size, int rows);

    abstract int getWidth(int size, int width);

    abstract public double calSize(int width, int numI, int numR);

    abstract ArrayList<Shape> getShapes(int numImages, int size, ArrayList<Image> imageL) throws IOException;

    abstract int getXSize(int size);

    abstract int getYSize(int size);

    abstract Graphics2D writeShape(Graphics2D g, Coordinate center, Dimension d) throws IOException;

    abstract Boolean[][] getShapeMap (int size);

    @Override
    public String toString() {
        return "Size: " + size + " | Image: " + image.getPath().getAbsolutePath();
    }

}
