package main.java;

import java.awt.*;
import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static String PICTURES_DIR;
    private static String SAVED_TO_DIR;
    public static DefaultShape SELECTED_SHAPE;
    public static int WIDTH;
    public static int HEIGHT;
    public static String FILE_NAME;

    public static void main(String[] args) {
        print("Collage created BY dev.lr.projects@gmail.com\n");
        Scanner scanner = new Scanner(System.in);
        File path = new File("C");

        while(!path.isDirectory()){
            print("Type the path to directory of the images you choose to use: ");
            path = new File(scanner.nextLine());
            PICTURES_DIR = path.getAbsolutePath();
            SAVED_TO_DIR = PICTURES_DIR;
        }




        boolean test = true;
        print("Choose your shape (0: Rectangle | 1: Hexagon): ");
        while(test){
            if (scanner.hasNextInt()){
                int i = scanner.nextInt();
                if (i == 0) {
                    SELECTED_SHAPE = new Rectangle();
                    test = false;
                } else if (i == 1) {
                    SELECTED_SHAPE = new Hexagon();
                    test = false;
                } else {
                    scanner.next();
                    print("Choose your shape (0: Rectangle | 1: Hexagon): ");
                }
            }else {
                scanner.next();
                print("Choose your shape (0: Rectangle | 1: Hexagon: ");
            }
        }

        test = true;
        print("Width (in pixel): ");
        while(test){
            if (scanner.hasNextInt()){
                WIDTH = scanner.nextInt();
                test = false;
            }else {
                scanner.next();
                print("Width (in pixel): ");
            }
        }


        test = true;
        print("Height (" + (SELECTED_SHAPE instanceof Hexagon ? "in rows" : "in pixel") + "): ");
        while(test){
            if (scanner.hasNextInt()){
                HEIGHT = scanner.nextInt();
                test = false;
            }else {
                scanner.next();
                print("Height (in pixel): ");
            }
        }

        test = true;
        print("Name of final image: ");
        while(test){
            if (scanner.hasNext()){
                FILE_NAME = scanner.next();
                test = false;
            }else {
                scanner.next();
                print("Name of final image: ");
            }
        }

        scanner.close();
        ShapeManagement m;
        int numImages = Objects.requireNonNull(new File(PICTURES_DIR).list((dir, name) -> (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png")))).length;
        if (SELECTED_SHAPE instanceof Hexagon) {
            m = new ShapeManagement(new File(PICTURES_DIR), SELECTED_SHAPE, new File(SAVED_TO_DIR), HEIGHT, WIDTH, numImages);
        } else {
            m = new ShapeManagement(new File(PICTURES_DIR), SELECTED_SHAPE, new File(SAVED_TO_DIR), new Dimension(WIDTH, HEIGHT));
        }
        m.run(FILE_NAME);

    }

    public static void print(String s) {
        System.out.println(s);
    }
}
