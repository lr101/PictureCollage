import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ShapeManagement {

    private final File workingDir;
    private final File savingDir;
    private final DefaultShape defaultShape;
    private final Dimension finalPictureDimension;
    private final int numImages;

    public ShapeManagement(File workingDir, DefaultShape defaultShape, File savingDir, Dimension finalPictureDimension, int numImages) {
        this.workingDir = workingDir;
        this.defaultShape = defaultShape;
        this.savingDir = savingDir;
        this.finalPictureDimension = finalPictureDimension;
        this.numImages = numImages;
    }

    public ShapeManagement(File workingDir, DefaultShape defaultShape, File savingDir, int rows, int width, int numImages) {
        this.workingDir = workingDir;
        this.defaultShape = defaultShape;
        this.savingDir = savingDir;
        this.finalPictureDimension = defaultShape.getFinalPictureSize(numImages, rows, width);
        this.numImages = numImages;
    }

    public void run(String name) {
        try {
            ImageManager imageManager = new ImageManager(workingDir);
            ArrayList<Image> images = imageManager.getImages();
            //change size of images
            //change bitmap of images
            //set coordinates in images
            Collections.shuffle(images);
            images = defaultShape.getCoordinates(finalPictureDimension, images);
            imageManager.writeImage(images, finalPictureDimension, savingDir, name, defaultShape);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /*private void write(StaticShape s, int numImages, int width, String name, int rows){
        //calculate size
        s.setSize(s.calSize(width, numImages, rows));
        //calculate bitmap
        s.setMap(s.createShapeMap());

        //calculate dimensions of picture
        Dimension pictureD = new Dimension(s.getWidth(width), s.getHeight(rows));
        ArrayList<Coordinate> listC = defaultShape.getCoordinates(pictureD);

        ImageManager iM = new ImageManager(workingDir);

        //get a list of shapes
        try {
            ArrayList<StaticShape> listS = s.getShapes(listC.size(), iM.getImages());

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
        } catch (IOException e) {
            System.out.println(e);
        }
    }*/

}
