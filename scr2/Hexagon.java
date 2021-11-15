import java.util.ArrayList;

public class Hexagon extends Shape {


    public Hexagon(int cor_x, int cor_y, Image image) {
        super(cor_x, cor_y, image);
    }

    /**
     * Calculates
     *
     * @param lastC
     * @param borderC
     * @param listC
     * @return
     */
    ArrayList<Coordinate> getCoordinates(Coordinate lastC, Coordinate borderC, ArrayList<Coordinate> listC, int size) {
        //TODO
        return null;
    }

    @Override
    Coordinate calC(Coordinate center, int size, int corner) {
        int angle = 60 * corner - 30;
        double angleRad = Math.PI / 180 * angle;
        return new Coordinate((int)(center.getxC() + size * Math.cos(angleRad)), (int)(center.getyC() + size * Math.sin(angleRad)));
    }

    @Override
    double calD(Dimension map, int numImages) {
        return Math.sqrt((2 * map.getxC() * map.getyC()) / (numImages * 3 * Math.sqrt(3)));
    }

    private double area (int sideLength) {
        return (3 * Math.sqrt(3) * Math.pow(sideLength,2)) / 2.0;
    }

    private double inradius(int sideLength) {
        return Math.sqrt((double) 3 / 2) * sideLength;
    }
}
