
public class Dimension {

    protected final int width;
    protected final int height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "( " + this.width + "| " + this.height + ")";
    }
}
