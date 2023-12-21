
public class Coordinate {
    
    private int xC;
    
    private int yC;
    
    public Coordinate(int xC, int yC) {
        this.xC = xC;
        this.yC = yC;
    }

    @Override
    public String toString() {
        return "( x:" + this.xC + "| y:" + this.yC + ")";
    }

    public int getxC() {
        return xC;
    }

    public void setxC(int xC) {
        this.xC = xC;
    }

    public int getyC() {
        return yC;
    }

    public void setyC(int yC) {
        this.yC = yC;
    }
}
