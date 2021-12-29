import java.io.File;

public class Main {

    private static final String PICTURES_DIR = "D:\\lukas\\Bilder\\Camera Roll\\28.01.18 Handy";
    private static final String SAVED_TO_DIR = "D:\\lukas\\Documents\\GIT\\Lukas - Git\\PictureCollage\\pictures";
    public static final int ROWS = 6;
    public static final int WIDTH = 10000;
    public static final int HEIGHT = 4000;
    public static final int NUM_IMAGES = 100;
    public static final String FILE_NAME = "test";

    public static void main(String[] args) {
        ShapeManagement m = new ShapeManagement(new File(PICTURES_DIR), new Rectangle(), new File(SAVED_TO_DIR), new Dimension(WIDTH, HEIGHT), NUM_IMAGES);
        m.run(FILE_NAME);
    }
}
