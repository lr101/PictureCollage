import java.io.File;

public class Main {

    private static final String PICTURES_DIR = "D:\\lukas\\Bilder\\D3500 Edited pictures\\LigthroomClassic";
    private static final String SAVED_TO_DIR = "D:\\lukas\\Documents\\GIT\\Lukas - Git\\PictureCollage\\pictures";
    public static final int ROWS = 2;
    public static final int WIDTH = 1000;
    public static final int NUM_IMAGES = 10;
    public static final String FILE_NAME = "test";

    public static void main(String[] args) {
        UtilMain m = new UtilMain(new File(SAVED_TO_DIR), new Hexagon());
        m.create(ROWS, WIDTH, NUM_IMAGES, FILE_NAME, new File(SAVED_TO_DIR));
    }
}
