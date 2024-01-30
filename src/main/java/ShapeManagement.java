import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ShapeManagement {

    private final File workingDir;
    private final File savingDir;
    private final DefaultShape defaultShape;
    private final Dimension finalPictureDimension;

    private final String[] imagePaths;

    public ShapeManagement(File workingDir, String[] imagePaths, DefaultShape defaultShape, File savingDir, Dimension finalPictureDimension) {
        this.workingDir = workingDir;
        this.imagePaths = imagePaths;
        this.defaultShape = defaultShape;
        this.savingDir = savingDir;
        this.finalPictureDimension = finalPictureDimension;
    }

    public ShapeManagement(File workingDir, String[] imagePaths, DefaultShape defaultShape, File savingDir, int rows, int width) {
        this.workingDir = workingDir;
        this.imagePaths = imagePaths;
        this.defaultShape = defaultShape;
        this.savingDir = savingDir;
        this.finalPictureDimension = defaultShape.getFinalPictureSize(imagePaths.length, rows, width);
    }

    public void run(String name) {
        try {
            ImageManager imageManager = new ImageManager(workingDir);
            ArrayList<Image> images = imageManager.getImages(imagePaths);
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

}
