import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static DefaultShape SELECTED_SHAPE;
    public static int WIDTH;
    public static int HEIGHT;
    public static String FILE_NAME;

    public static void main(String[] args) {
        File path = new File(System.getenv("IMAGES_PATH"));
        String picturesDir = path.getAbsolutePath();
        SELECTED_SHAPE = System.getenv("SHAPE").equals("Hexagon") ? new Hexagon() : new Rectangle();
        WIDTH = Integer.parseInt(System.getenv("WIDTH"));
        HEIGHT = Integer.parseInt(System.getenv("HEIGHT"));

        FILE_NAME = new SimpleDateFormat("yyyyMMddHHmm'_collage'").format(new Date());

        print("Collage created BY dev.lr.projects@gmail.com\n");
        print("Used directory: " + picturesDir);
        print("Used shape:" + SELECTED_SHAPE.toString());
        print("Used width:" + WIDTH);
        print("Used Height: " + HEIGHT + " " + (SELECTED_SHAPE instanceof Hexagon ? "rows" : "pixel"));
        print("Saving into: " + FILE_NAME);

        ShapeManagement m;
        int numImages = Objects.requireNonNull(new File(picturesDir).list((dir, name) -> (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png")))).length;
        if (SELECTED_SHAPE instanceof Hexagon) {
            m = new ShapeManagement(new File(picturesDir), SELECTED_SHAPE, new File(picturesDir), HEIGHT, WIDTH, numImages);
        } else {
            m = new ShapeManagement(new File(picturesDir), SELECTED_SHAPE, new File(picturesDir), new Dimension(WIDTH, HEIGHT));
        }
        m.run(FILE_NAME);

    }

    public static void print(String s) {
        System.out.println(s);
    }
}
