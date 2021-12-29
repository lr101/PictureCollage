/*import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public abstract class StaticShape{


    protected Image image;
    protected Boolean[][] map;
    protected int size;
    protected Dimension d;

    public StaticShape(Image image, int size, Boolean[][] map) {
        this.image = image;
        this.size = size;
        this.map = map;
    }

    public StaticShape() {
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

    abstract Coordinate calCorner (Coordinate center, int corner);

    abstract public int calSize(int width, int numI, int numR);

    abstract ArrayList<StaticShape> getShapes(int numImages, ArrayList<Image> imageL) throws IOException;

    abstract void setDimension(int rows, Dimension size);
    abstract int getXSize();

    abstract int getYSize();

    abstract Graphics2D writeShape(Graphics2D g, Coordinate center, Dimension d) throws IOException;

    abstract Boolean[][] createShapeMap();

    @Override
    public String toString() {
        return "Size: " + this.size + " | Image: " + image.getPath().getAbsolutePath();
    }

}
*/