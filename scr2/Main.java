import java.io.File;

public class Main {

    private static final String PATH = "D:\\lukas\\Documents\\GIT\\Lukas - Git\\PictureCollage\\pictures";
    public static final int HEIGHT = 1080;
    public static final int WIDTH = 1920;
    public static final int NUM_IMAGES = 12;
    public static final String FILE_NAME = "test";

    public static void main(String[] args) {
        UtilMain m = new UtilMain(new File(PATH), new Hexagon());
        m.create(HEIGHT, WIDTH, NUM_IMAGES, FILE_NAME);
        //LinesDrawingExample s = new LinesDrawingExample();

    }
}
