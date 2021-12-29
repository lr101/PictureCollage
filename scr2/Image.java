import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Image {
    private File path;
    private final double ratio;
    private Dimension size;
    private Coordinate leftTop;

    public Image(File path) throws IOException {
        this.path = path;
        this.size = getImageDimension(path);
        this.ratio = Math.round(size.getWidth() / (double) size.getHeight() * 100.0) / 100.0;
        this.leftTop = null;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public int getWidth() {
        return size.getWidth();
    }

    public int getHeight() {
        return size.getHeight();
    }

    /**
     * Gets image dimensions for given file
     * @param imgFile image file
     * @return dimensions of image
     * @throws IOException if the file is not a known image
     */
    public static Dimension getImageDimension(File imgFile) throws IOException {
        int pos = imgFile.getName().lastIndexOf(".");
        if (pos == -1)
            throw new IOException("No extension for file: " + imgFile.getAbsolutePath());
        String suffix = imgFile.getName().substring(pos + 1);
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
        while(iter.hasNext()) {
            ImageReader reader = iter.next();
            try {
                ImageInputStream stream = new FileImageInputStream(imgFile);
                reader.setInput(stream);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                return new Dimension(width, height);
            } catch (IOException e) {
                System.out.println("Error reading: " + imgFile.getAbsolutePath() + e);
            } finally {
                reader.dispose();
            }
        }

        throw new IOException("Not a known image file: " + imgFile.getAbsolutePath());
    }


    public void getImageResized(Dimension d) throws IOException {
        this.size = d;
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


    public double getRatio() {
        return ratio;
    }

    public Coordinate getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(Coordinate leftTop) {
        this.leftTop = leftTop;
    }



    public BufferedImage getImage() throws IOException {
        //get image
        BufferedImage inputImage = ImageIO.read(path);

        //resize image
        BufferedImage outputImage = new BufferedImage(size.getWidth(), size.getHeight(), inputImage.getType());
        Graphics2D g = outputImage.createGraphics();
        g.drawImage(inputImage, 0, 0, size.getWidth(), size.getHeight(), null);

        //clean-up
        g.dispose();
        inputImage.flush();

        //return
        return outputImage;
    }
}
