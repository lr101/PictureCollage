import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Hexagon implements DefaultShape{

    private int size;

    public Hexagon () {}

    /**
     * Calculates Coordinates for a Hexagon pattern
     *
     * @param borderD dimensions of final image
     */
    public ArrayList<Image> getCoordinates(Dimension borderD, ArrayList<Image> images) throws IOException {
        //init
        Coordinate newC = new Coordinate(0,0);
        boolean even = true;
        int xSize = this.getXSize() * 2;
        int ySize = this.getYSize() * 2;
        //loop
        for (int i = 0;i < images.size(); i++) {
            if (newC.getyC() <= borderD.getHeight()) {
                images.get(i).setLeftTop(newC);
                images.get(i).getImageResized(new Dimension(xSize, ySize));
                if (newC.getxC() + getXSize() < borderD.getWidth()) {
                    //create a new cor bordering at the right (X -> X)
                    newC = new Coordinate(newC.getxC() + xSize, newC.getyC());
                } else {
                    //create a new row below
                    newC = new Coordinate((even ? xSize : this.getXSize()), newC.getyC() + (int) (size * 1.5));
                    even = !even;
                }
            } else {
                images.remove(i);
            }
        }
        return images;
    }

    public Dimension getFinalPictureSize(int numImages, int rows, int width) {
        this.setSize(width, numImages, rows);
        return new Dimension(this.getWidth(width), this.getHeight(rows));
    }

    @Override
    public Boolean[][] createShapeMap(Image image, double size) {
        int inRadius = this.getXSize();

        //create graphics
        BufferedImage bufferedImage = new BufferedImage(inRadius * 2, (int)(size * 2), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        Polygon polygon = new Polygon();
        g.setColor(new Color(HEX_COLOR));

        //create polygon
        Coordinate center = new Coordinate(inRadius, (int)size);
        for (int i = 0; i < 6; i++) {
            Coordinate c = this.calCorner(center, i);
            polygon.addPoint(c.getxC(), c.getyC());
        }
        g.draw(polygon);
        g.fill(polygon);

        // clean-up code
        g.dispose();
        bufferedImage.flush();

        //return
        return mapFromImage(bufferedImage);
    }

    @Override
    public double getSize() {
        return 0;
    }

    private Coordinate calCorner(Coordinate center, int corner) {
        Coordinate p;
        switch (corner % 6) {
            case 0:
                p = new Coordinate(center.getxC(), center.getyC() + size);
                break;
            case 1:
                p = new Coordinate(center.getxC() + this.getXSize(), center.getyC() + size/2);
                break;
            case 2:
                p = new Coordinate(center.getxC() + this.getXSize(), center.getyC() - size/2);
                break;
            case 3:
                p = new Coordinate(center.getxC(), center.getyC() - size);
                break;
            case 4:
                p = new Coordinate(center.getxC() - this.getXSize(), center.getyC() - size/2);
                break;
            case 5:
                p = new Coordinate(center.getxC() - this.getXSize(), center.getyC() + size/2);
                break;
            default: p = new Coordinate(0,0);
        }
        return p;
    }

    int getHeight(int rows) {
        return  rows/2 * 3 * size;
    }

    int getWidth(int width) {
        return (width / (2 * this.getXSize())) *  2 * this.getXSize();
    }

    public void setSize(int width, int numImages, int rows) {
        double c = 1 /  Math.sqrt(3.0);
        int size = (int) Math.ceil((c * ((rows * width) / (double) numImages)));
        System.out.println(size);
        //round up to the next round number. This prevents rounding error later on
        this.size = (size % 2 == 0 ? size : size + 1);
    }



    int getXSize() {
        return (int) Math.ceil(Math.sqrt(3) / 2 * this.size);
    }

    int getYSize() {
        return this.size;
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
