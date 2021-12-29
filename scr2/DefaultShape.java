import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public interface DefaultShape {
    int HEX_COLOR = 0x000000;
    ArrayList<Image> getCoordinates(Dimension borderD, ArrayList<Image> images) throws IOException;
    Dimension getFinalPictureSize(int numImages, int rows, int width);
    Boolean[][] createShapeMap(Image image, double size);
    double getSize();

    static void writeShape(Graphics2D g, Image image, Boolean[][] map) throws IOException {
        if (image != null) {
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println(width);
            System.out.println(height);

            int xC = image.getLeftTop().getxC();
            int yC = image.getLeftTop().getyC();

            BufferedImage bufI = image.getImage();

            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++)  {
                    if (map[x][y]) {
                        int xCor = xC + x;
                        int yCor = yC + y;
                        g.drawImage(bufI, xCor, yCor, xCor + 1, yCor + 1,x, y, x +1, y + 1, null);
                    }
                }
            }
            bufI.flush();
        }
    }

}
