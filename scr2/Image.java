import java.io.File;

public class Image {
    private File path;
    private int width;
    private int height;

    public Image(File path, int width, int height){
        this.path = path;
        this.width = width;
        this.height = height;
    }

    public File getPath() {
        return path;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
