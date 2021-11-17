public class Coordinate {
    protected final int xC;
    protected final int yC;

    public Coordinate(int xC, int yC) {
        this.xC = xC;
        this.yC = yC;
    }

    public int getxC() {
        return xC;
    }

    public int getyC() {
        return yC;
    }

    @Override
    public String toString() {
        return "( " + this.xC + "| " + this.yC + ")";
    }
}
