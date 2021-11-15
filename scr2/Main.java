import java.io.File;

public class Main {
    public static void main(String[] args) {
        Map m = new Map(new Hexagon(0,0 , new Image(new File("/"), 100, 100)));
        m.create(100, 100);
    }
}
