import java.io.File;

public class Main {

    private static final String PICTURES_DIR = "./";
    private static final String SAVED_TO_DIR = "./";
    public static final int ROWS = 6;
    public static final int WIDTH = 1920;
    public static final int NUM_IMAGES = 100;
    public static final String FILE_NAME = "c-200";

    public static void main(String[] args) {
        UtilMain m = new UtilMain(new File(PICTURES_DIR), new Hexagon(), new File(SAVED_TO_DIR));
        m.create(ROWS, WIDTH, NUM_IMAGES, FILE_NAME);
    }
}
