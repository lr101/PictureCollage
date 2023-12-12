
public record Coordinate(int xC, int yC) {

    @Override
    public String toString() {
        return "( x:" + this.xC + "| y:" + this.yC + ")";
    }
}
