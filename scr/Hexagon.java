import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Hexagon extends DefaultShape{

    private int size;

    public Hexagon () {}

    /**
     * Calculates Coordinates for a Hexagon pattern
     *
     * @param borderD dimensions of final image
     */
    public ArrayList<Image> getCoordinates(Dimension borderD, ArrayList<Image> images) {
        //init
        Coordinate newC = new Coordinate(0,0);
        boolean even = true;
        int xSize = this.getXSize() * 2;
        int ySize = this.getYSize() * 2;
        ArrayList<Image> toBeRemoved = new ArrayList<>();
        //loop
        for (Image image : images) {
            if (newC.yC() <= borderD.getHeight()) {
                image.setLeftTop(newC);
                image.getImageResized(new Dimension(xSize, ySize));
                if (newC.xC() + getXSize() < borderD.getWidth()) {
                    //create a new cor bordering at the right (X -> X)
                    newC = new Coordinate(newC.xC() + xSize, newC.yC());
                } else {
                    //create a new row below
                    newC = new Coordinate((even ? (int) (xSize  * 0.5) : 0), newC.yC() + (int) (size * 1.5));
                    even = !even;
                }
            } else {
                toBeRemoved.add(image);
            }
        }
        images.removeAll(toBeRemoved);
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
            polygon.addPoint(c.xC(), c.yC());
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
        return size;
    }

    private Coordinate calCorner(Coordinate center, int corner) {
        Coordinate p;
        switch (corner % 6) {
            case 0:
                p = new Coordinate(center.xC(), center.yC() + size);
                break;
            case 1:
                p = new Coordinate(center.xC() + this.getXSize(), center.yC() + size/2);
                break;
            case 2:
                p = new Coordinate(center.xC() + this.getXSize(), center.yC() - size/2);
                break;
            case 3:
                p = new Coordinate(center.xC(), center.yC() - size);
                break;
            case 4:
                p = new Coordinate(center.xC() - this.getXSize(), center.yC() - size/2);
                break;
            case 5:
                p = new Coordinate(center.xC() - this.getXSize(), center.yC() + size/2);
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

    @Override
    public void writeShape(Dimension d, Graphics2D g, Image image, Boolean[][] map) throws IOException {
        if (image != null) {
            int width = image.getWidth();
            int height = image.getHeight();

            int xC = image.getLeftTop().xC();
            int yC = image.getLeftTop().yC();

            BufferedImage bufI = image.getImage();
            int w = d.getWidth();
            int h = d.getHeight();
            System.out.println(h);

            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++)  {
                    if (map[x][y]) {
                        int xCor = (xC + x) % w;
                        int yCor = (yC + y) % h;
                        g.drawImage(bufI, xCor, yCor, xCor + 1, yCor + 1,x, y, x +1, y + 1, null);
                    }
                }
            }
            bufI.flush();
        }
    }
}
