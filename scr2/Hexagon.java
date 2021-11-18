import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Hexagon extends Shape {

    private static final int HEX_COLOR = 0x000000;
    private static final int EVEN = 0;
    private static final int ODD = 1;

    public Hexagon(Image image, int size) {
        super(image, size);
    }

    public Hexagon () {
        super();
    }

    /**
     * Calculates Coordinates for a Hexagon pattern
     *
     * @param borderD dimensions of final image
     * @param size radius of the bigger Hexagon circle / length of one side
     * @return a list of Coordinates that creates a perfect pattern
     */
    @Override
    ArrayList<Coordinate> getCoordinates(Dimension borderD, int size) {
        //init
        ArrayList<Coordinate> listC = new ArrayList<>();
        Coordinate newC = new Coordinate(this.getXSize(size),this.getYSize(size));
        boolean even = true;

        //add first
        listC.add(newC);

        //loop
        while (newC.getyC() - size < borderD.getyC()) {
            if (newC.getxC() + 2 * this.getXSize(size) < borderD.getxC()) {
                //create a new cor bordering at the right (X -> X)
                newC = new Coordinate(newC.getxC() + this.getXSize(size) * 2, newC.getyC());
                listC.add(newC);
            } else {
                //create a new row below
                newC = new Coordinate( (even ? 0 : this.getXSize(size) ), newC.getyC() + (int) (size * 1.5));
                listC.add(newC);
                even = !even;
            }
        }
        return listC;
    }

    @Override
    Coordinate calC(Coordinate center, int size, int corner) {
        Coordinate p;
        switch (corner % 6) {
            case 0:
                p = new Coordinate(center.getxC(), center.getyC() + size);
                break;
            case 1:
                p = new Coordinate(center.getxC() + this.getXSize(size), center.getyC() + size/2);
                break;
            case 2:
                p = new Coordinate(center.getxC() + this.getXSize(size), center.getyC() - size/2);
                break;
            case 3:
                p = new Coordinate(center.getxC(), center.getyC() - size);
                break;
            case 4:
                p = new Coordinate(center.getxC() - this.getXSize(size), center.getyC() - size/2);
                break;
            case 5:
                p = new Coordinate(center.getxC() - this.getXSize(size), center.getyC() + size/2);
                break;
            default: p = new Coordinate(0,0);
        }
        return p;
    }

    @Override
    int getHeight(int size, int rows) {
        return  rows/2 * 3 * size;
    }

    @Override
    int getWidth(int size, int width) {
        return (width / this.getXSize(size)) *  this.getXSize(size);
    }

    @Override
    public double calSize(int width, int numI, int numR) {
        double c = 1 /  Math.sqrt(3.0);
        return c * ((numR * width) / (double) numI);
    }

    @Override
    ArrayList<Shape> getShapes(int numImages, int size, ArrayList<Image> imageL) throws IOException {
        ArrayList<Shape> listS = new ArrayList<>();
        if (imageL.size() < numImages) throw new IOException("Not enough images");
        for (int i = 0; i < numImages; i++) {
            listS.add(new Hexagon(imageL.get(i), size));
        }
        return listS;
    }

    @Override
    int getXSize(int size) {
        return (int) (Math.sqrt(3) / 2 * size);
    }

    @Override
    int getYSize(int size) {
        return size;
    }

    @Override
    Graphics2D writeShape(Graphics2D g, Coordinate center, Dimension d) throws IOException {
        if (image != null && map != null) {
            int width = this.map.length;
            int height = this.map[0].length;


            BufferedImage i = image.getImageResized(new Dimension (width, height));

            int xC = center.getxC() - this.getXSize(this.size);
            int yC = center.getyC() - this.size;

            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++)  {
                    if (map[x][y]) {
                        int xCor = xC + x;
                        int yCor = yC + y;
                        g.drawImage(i, xCor, yCor, xCor + 1, yCor + 1,x, y, x +1, y + 1, null);
                    }
                }
            }
            i.flush();
        }
        return g;
    }

    @Override
    Boolean[][] getShapeMap(int size) {
        int inRadius = this.getXSize(size);

        //create graphics
        BufferedImage image = new BufferedImage(inRadius * 2, size * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        Polygon polygon = new Polygon();
        g.setColor(new Color(HEX_COLOR));

        //create polygon
        Coordinate center = new Coordinate(inRadius, size);
        for (int i = 0; i < 6; i++) {
            Coordinate c = this.calC(center, size, i);
            polygon.addPoint(c.getxC(), c.getyC());
        }
        g.draw(polygon);
        g.fill(polygon);

        //ImageIO.write(image, "PNG", new File("D:\\lukas\\Documents\\GIT\\Lukas - Git\\PictureCollage", "combined.png"));

        // clean-up code
        g.dispose();
        image.flush();

        //return
        return mapFromImage(image);
    }

    private Boolean[][] mapFromImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Boolean[][] map = new Boolean[width][height];

        //iterate through image.
        //save true and false for black and other pixels
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = image.getRGB(x, y) != HEX_COLOR;
            }
        }
        return map;
    }
}
