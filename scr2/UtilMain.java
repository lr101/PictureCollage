import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UtilMain {

    private static final String FILE_TYPE = "PNG";
    private final File workingDir;
    private final Shape shape;

    public UtilMain(File workingDir, Shape shape) {
        this.workingDir = workingDir;
        this.shape = shape;
    }

    public void create(int rows, int width, int numImages,  String name, File file) {

        int size = (int) Math.ceil(shape.calSize(width, numImages, rows));
        int height = shape.getHeight(size, rows);
        width = shape.getWidth(size, width);
        ArrayList<Coordinate> listC = shape.getCoordinates(new Dimension(width, height), size);
        try {
            this.write(listC, width, height, size, name, file);
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void write(ArrayList<Coordinate> listC, int width, int height, int size, String name, File file) throws IOException {
        ImageManager iM = new ImageManager(workingDir);

        //get a list of shapes
        ArrayList<Shape> listS = shape.getShapes(listC.size(), size, iM.getImages());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        //logging
        System.out.println("Size: (" + width + "|" + height + ")");
        System.out.println("Number of Pictures: " + listC.size());

        //Creating JFrame
        JFrameManager jFM = new JFrameManager(image);

        //adding pictures to Coordinates
        for (int i = 0; i < listC.size(); i++) {
            //logging
            System.out.println("(" + (i + 1) + "/" + listC.size() + ") Adding at: " + listC.get(i));
            System.out.println("\tShape: " + listS.get(i));
            //write picture shape
            g = listS.get(i).writeShape(g, listC.get(i), new Dimension(width, height));
            jFM.update();
        }

        //save image
        ImageIO.write(image, FILE_TYPE, new File(file.getAbsolutePath(), name + "." + FILE_TYPE ));
        g.dispose();
        image.flush();
        jFM.destroy();
    }

}
