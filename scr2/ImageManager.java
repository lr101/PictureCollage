import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class ImageManager {

    private static final String FILE_TYPE = "PNG";

    private File file;

    public ImageManager(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getNumImages() {
        return readDir().length;
    }

    public ArrayList<Image> getImages() throws IOException {
        ArrayList<Image> imageL = new ArrayList<>();
        for (String name : readDir()) {
            imageL.add(new Image(new File(file.getAbsolutePath(), name)));
        }
        return imageL;
    }

    private String[] readDir() {
        return file.list((dir, name) -> (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg")));
    }

    public void writeImage(ArrayList<Image> images, Dimension d, File savingDir, String name, DefaultShape shape) throws IOException {
        BufferedImage image = new BufferedImage(d.getWidth(), d.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        for (int i = 0; i < images.size(); i++) {
            //logging
            System.out.println("(" + (i + 1) + "/" + images.size() + " -> " + (Math.round((i+1) * 100.0 / images.size())) +"%) Adding at: " + images.get(i).getLeftTop());
            //write picture shape
            DefaultShape.writeShape(g, images.get(i), shape.createShapeMap(images.get(i), shape.getSize()));
        }
        //save image
        ImageIO.write(image, FILE_TYPE, new File(savingDir.getAbsolutePath(), name + "." + FILE_TYPE ));
        //clean-up
        g.dispose();
        image.flush();
    }

}
