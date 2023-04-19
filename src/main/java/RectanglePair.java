package main.java;

public class RectanglePair {
    private Image rec1;
    private Image rec2;

    public RectanglePair (Image rec1, Image rec2) {
        if (rec1.getRatio() == rec2.getRatio() && rec1 != rec2) {
            this.rec1 = rec1;
            this.rec2 = rec2;
        }
    }

    public Image getRec1() {
        return rec1;
    }

    public Image getRec2() {
        return rec2;
    }

    public double getRatio() {
        return rec1.getRatio();
    }

}
