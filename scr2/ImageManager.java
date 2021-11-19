import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class ImageManager {

    private File file;

    public ImageManager(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getNumImages() {
        return readDir().length;
    }

    public ArrayList<Image> getImages() {
        ArrayList<Image> imageL = new ArrayList<>();
        for (String name : readDir()) {
            imageL.add(new Image(new File(file.getAbsolutePath(), name)));
        }
        return imageL;
    }

    private String[] readDir() {
        return file.list((dir, name) -> (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg")));
    }
}
