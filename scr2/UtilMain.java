import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UtilMain {
    private final int startX = 0;
    private final int startY = 0;
    private static final String FILE_TYPE = "PNG";
    private final File file;
    private final Shape shape;

    public UtilMain(File file, Shape shape) {
        this.file = file;
        this.shape = shape;
    }

    public void create(int height, int width, int numImages,  String name) {
        Hexagon h = new Hexagon();
        System.out.println(h.calSize(width, numImages, 4));
        int size = (int) Math.ceil(h.calSize(width, numImages, 4));
        height = 2 * 3 * size;
        width = (width / shape.getXSize(size)) *  shape.getXSize(size);
        System.out.println(size);
        ArrayList<Coordinate> listC = shape.getCoordinates(new Coordinate(startX + shape.getXSize(size),startY + (shape.getYSize(size))), new Dimension(width, height), new ArrayList<>(), size);
        try {
            this.write(listC, width, height, size, name);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void write(ArrayList<Coordinate> listC, int width, int height, int size, String name) throws IOException {
        ImageManager iM = new ImageManager(file);
        ArrayList<Shape> listS = shape.getShapes(listC.size(), size, iM.getImages());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        for (int i = 0; i < listC.size(); i++) {
            System.out.println("(" + i + ") Adding at: " + listC.get(i));
            System.out.println("    Shape: " + listS.get(i));
            g = listS.get(i).writeShape(g, listC.get(i), new Dimension(width, height));
        }
        ImageIO.write(image, FILE_TYPE, new File(file.getAbsolutePath(), name + "." + FILE_TYPE ));
        g.dispose();
        image.flush();
    }


}
