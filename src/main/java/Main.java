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
        String imagesPath;
        String shape;
        String width;
        String height;
        if (args.length == 4) {
            imagesPath = args[0];
            shape = args[1];
            width = args[2];
            height = args[3];
        } else {
            imagesPath = System.getenv("IMAGES_PATH");
            shape = System.getenv("SHAPE");
            width = System.getenv("WIDTH");
            height = System.getenv("HEIGHT");
        }
        if (imagesPath.isEmpty() || imagesPath.isBlank()) {
            print("No 'IMAGE_PATH' was provided as argument or environment variable");
            return;
        } else if (shape.isEmpty() || shape.isBlank()) {
            print("No 'SHAPE' was provided as argument or environment variable");
            return;
        } else if (width.isEmpty() || width.isBlank()) {
            print("No 'WIDTH' was provided as argument or environment variable");
            return;
        } else if (height.isEmpty() || height.isBlank()) {
            print("No 'HEIGHT / ROWS' was provided as argument or environment variable");
            return;
        }

        File path = new File(imagesPath);
        String picturesDir = path.getAbsolutePath();
        SELECTED_SHAPE = shape.equals("Hexagon") ? new Hexagon() : new Rectangle();
        WIDTH = Integer.parseInt(width);
        HEIGHT = Integer.parseInt(height);

        FILE_NAME = new SimpleDateFormat("yyyyMMddHHmm'_collage'").format(new Date());

        print("Collage created BY dev.lr.projects@gmail.com\n");
        print("Used directory: " + picturesDir);
        print("Used shape: " + SELECTED_SHAPE.toString());
        print("Used width: " + WIDTH);
        print("Used Height: " + HEIGHT + " " + (SELECTED_SHAPE instanceof Hexagon ? "rows" : "pixel"));
        print("### Starting ###");

        ShapeManagement m;

        String[] imagePaths = Objects.requireNonNull(new File(picturesDir).list((dir, name) -> (
                !name.toLowerCase().endsWith("_collage.png") && (
                        name.toLowerCase().endsWith(".jpg")
                        || name.toLowerCase().endsWith(".jpeg")
                        || name.toLowerCase().endsWith(".png")
                )
        )));


        if (imagePaths.length <= 1) {
            print("Stopping without doing anything: Not enough images found (only 0 or 1)");
            return;
        }
        if (SELECTED_SHAPE instanceof Hexagon) {
            m = new ShapeManagement(new File(picturesDir), imagePaths, SELECTED_SHAPE, new File(picturesDir), HEIGHT, WIDTH);
        } else {
            m = new ShapeManagement(new File(picturesDir), imagePaths, SELECTED_SHAPE, new File(picturesDir), new Dimension(WIDTH, HEIGHT));
        }
        m.run(FILE_NAME);
        print("Finished: Saved into: " + FILE_NAME);

    }

    public static void print(String s) {
        System.out.println(s);
    }
}
