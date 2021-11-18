import java.io.File;

public class Main {

    private static final String PATH = "D:\\lukas\\Bilder\\D3500 Edited pictures\\LigthroomClassic";
    public static final int ROWS = 4;
    public static final int WIDTH = 1920;
    public static final int NUM_IMAGES = 50;
    public static final String FILE_NAME = "test";

    public static void main(String[] args) {
        UtilMain m = new UtilMain(new File(PATH), new Hexagon());
        m.create(ROWS, WIDTH, NUM_IMAGES, FILE_NAME);
    }
}
