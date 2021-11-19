import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private File path;

    public Image(File path){
        this.path = path;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public BufferedImage getImage() throws IOException {
        return ImageIO.read(path);
    }

    public BufferedImage getImageResized(Dimension d) throws IOException {
        //get image
        BufferedImage inputImage = ImageIO.read(path);

        //calculate image size
        d = calD(d.getHeight(), d.getWidth(),inputImage.getHeight(), inputImage.getWidth());

        //resize image
        BufferedImage outputImage = new BufferedImage(d.getWidth(), d.getHeight(), inputImage.getType());
        Graphics2D g = outputImage.createGraphics();
        g.drawImage(inputImage, 0, 0, d.getWidth(), d.getHeight(), null);

        //clean-up
        g.dispose();
        inputImage.flush();

        //return
        return outputImage;
    }

    private Dimension calD (int height, int width, int iHeight, int iWidth) {
        double wRatio =  iWidth / (double) width;
        double hRatio = iHeight / (double) height;


        if (wRatio < hRatio) {
            if (wRatio >= 1) {
                height = (int)(iHeight / wRatio);
            } else {
                width = iWidth;
                height = iHeight;
            }
        } else {
            if (hRatio >= 1) {
                width = (int) (iWidth / hRatio);
            } else {
                width = iWidth;
                height = iHeight;
            }
        }
        return new Dimension(width, height);
    }


}
