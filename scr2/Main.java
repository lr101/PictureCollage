import java.io.File;
import java.util.Scanner;

public class Main {

    private static String PICTURES_DIR;
    private static String SAVED_TO_DIR;
    public static final int ROWS = 6;
    public static int WIDTH;
    public static int HEIGHT;
    public static final int NUM_IMAGES = 100;
    public static String FILE_NAME;

    public static void main(String[] args) {
        print("Collage Erstellen BY @LukasReim\n");
        Scanner scanner = new Scanner(System.in);
        File path = new File("C");

        while(!path.isDirectory()){
            print("Geben Sie den Dateipfad der zu verwendenen Bilder an:");
            path = new File(scanner.nextLine());
            PICTURES_DIR = path.getAbsolutePath();
            SAVED_TO_DIR = PICTURES_DIR;
        }




        boolean test = true;
        print("Geben Sie die Breite (in Pixeln) des finalen Bildes an:");
        while(test){
            if (scanner.hasNextInt()){
                WIDTH = scanner.nextInt();
                test = false;
            }else {
                scanner.next();
                print("Geben Sie die Breite (in Pixeln) des finalen Bildes an:");
            }
        }


        test = true;
        print("Geben Sie die Höhe (in Pixeln) des Finalen Bildes an:");
        while(test){
            if (scanner.hasNextInt()){
                HEIGHT = scanner.nextInt();
                test = false;
            }else {
                scanner.next();
                print("Geben Sie die Höhe (in Pixeln) des Finalen Bildes an:");
            }
        }

        test = true;
        print("Geben Sie einen Namen für die fertige Collage an:");
        while(test){
            if (scanner.hasNext()){
                FILE_NAME = scanner.next();
                test = false;
            }else {
                scanner.next();
                print("Geben Sie die Höhe (in Pixeln) des Finalen Bildes an:");
            }
        }

        ShapeManagement m = new ShapeManagement(new File(PICTURES_DIR), new Rectangle(), new File(SAVED_TO_DIR), new Dimension(WIDTH, HEIGHT), NUM_IMAGES);
        m.run(FILE_NAME);

    }

    public static void print(String s) {
        System.out.println(s);
    }
}
