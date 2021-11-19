import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UtilMain {

    private static final String FILE_TYPE = "PNG";
    private final File workingDir;
    private final File savingDir;
    private final Shape shape;

    public UtilMain(File workingDir, Shape shape, File savingDir) {
        this.workingDir = workingDir;
        this.shape = shape;
        this.savingDir = savingDir;
    }

    public void create(int rows, int width, int numImages,  String name) {
        //calculate size
        shape.setSize(shape.calSize(width, numImages, rows));
        //calculate bitmap
        shape.setMap(shape.createShapeMap());

        //calculate dimensions of picture
        Dimension pictureD = new Dimension(shape.getWidth(width), shape.getHeight(rows));
        ArrayList<Coordinate> listC = shape.getCoordinates(pictureD);
        try {
            this.write(listC, pictureD, name);
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void write(ArrayList<Coordinate> listC, Dimension pictureD, String name) throws IOException {
        ImageManager iM = new ImageManager(workingDir);

        //get a list of shapes
        ArrayList<Shape> listS = shape.getShapes(listC.size(), iM.getImages());
        //create image
        BufferedImage image = new BufferedImage(pictureD.getWidth(), pictureD.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        //logging
        System.out.println("Size: " + pictureD);
        System.out.println("Number of Pictures: " + listC.size());

        //Creating JFrame
        JFrameManager jFM = new JFrameManager(image);

        //adding pictures to Coordinates
        for (int i = 0; i < listC.size(); i++) {
            //logging
            System.out.println("(" + (i + 1) + "/" + listC.size() + ") Adding at: " + listC.get(i));
            System.out.println("\tShape: " + listS.get(i));
            //write picture shape
            g = listS.get(i).writeShape(g, listC.get(i), pictureD);
            //update JFrame
            jFM.update();
        }

        //save image
        ImageIO.write(image, FILE_TYPE, new File(savingDir.getAbsolutePath(), name + "." + FILE_TYPE ));
        //clean-up
        g.dispose();
        image.flush();
        jFM.destroy();
    }

}
