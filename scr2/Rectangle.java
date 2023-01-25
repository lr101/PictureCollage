import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Rectangle implements DefaultShape {

    private final static double RANDOM_FACTOR = 10.0;
    private final Random random = new Random();
    private final long RANDOM_SEED = random.nextLong();

    public Rectangle() {}

    private void setImageList(ArrayList<Image> images, ArrayList<Image> bigImages, ArrayList<RectanglePair> recPair) {
        recPair.clear();
        bigImages.clear();
        if (images.size() > 1) {
            ArrayList<Image> other = new ArrayList<>();
            for (Image image : images) {
                boolean found = false;
                for (Image i : other) {
                    if (i.getRatio() == image.getRatio()) {
                        recPair.add(new RectanglePair(i, image));
                        other.remove(i);
                        found = true;
                        break;
                    }
                }
                if (!found) other.add(image);
            }
            bigImages.addAll(other);
        }
    }

    private void setImageCoordinates(Image image, Coordinate cor, Dimension d) throws IOException {
        image.setLeftTop(cor);
        image.getImageResized(d);
    }

    @Override
    public ArrayList<Image> getCoordinates(Dimension borderD, ArrayList<Image> images) throws IOException {
        if (images.size() > 0) {
            ArrayList<Image> bigImages = new ArrayList<>();
            ArrayList<RectanglePair> recPair = new ArrayList<>();

            int loopHeight = this.getLoopHeight(borderD, images, recPair, bigImages);
            this.runCoordinates(borderD, loopHeight, bigImages, recPair, images);
            for (Image i : bigImages) {
                System.out.println(i);
            }
            images.removeAll(bigImages);
            for(RectanglePair r : recPair) {
                System.out.println(r.getRec1());
                System.out.println(r.getRec2());
                images.remove(r.getRec1());
                images.remove(r.getRec2());
            }
        }

        return images;
    }

    private boolean runCoordinates(Dimension borderD, int loopHeight, ArrayList<Image> bigImages, ArrayList<RectanglePair> recPair, ArrayList<Image> images) throws IOException {
        this.setImageList(images, bigImages, recPair);
        int countLastBig = 0;
        random.setSeed(this.RANDOM_SEED);
        Coordinate newC = new Coordinate(0,0);
        while (newC.getyC() < borderD.getHeight() && recPair.size() != 0) {
            Dimension imageSize;
            if (this.random(countLastBig)) {
                if (bigImages.size() == 0) {
                    bigImages.add(recPair.get(0).getRec1());
                    bigImages.add(recPair.get(0).getRec2());
                    recPair.remove(0);
                }
                imageSize = new Dimension((int) (bigImages.get(0).getRatio() * loopHeight * 2), loopHeight * 2);
                this.setImageCoordinates(bigImages.get(0), newC, imageSize);
                bigImages.remove(0);
                countLastBig = 0;
            } else {
                imageSize = new Dimension((int) (recPair.get(0).getRatio() * loopHeight), loopHeight);
                this.setImageCoordinates(recPair.get(0).getRec1(), newC, imageSize);
                this.setImageCoordinates(recPair.get(0).getRec2(), new Coordinate(newC.getxC(), newC.getyC() + imageSize.getHeight()), imageSize);
                recPair.remove(0);
                countLastBig++;
            }

            if (newC.getxC() + imageSize.getWidth() < borderD.getWidth()) {
                //create a new cor bordering at the right (X -> X)
                newC = new Coordinate(newC.getxC() + imageSize.getWidth(), newC.getyC());
            } else {
                //create a new row below
                newC = new Coordinate(0, newC.getyC() + loopHeight * 2);
            }
        }
        return recPair.size() != 0;
    }

    private boolean random(int lastRandom) {
        //return random.nextInt(101) / 100.0 < (lastRandom / RANDOM_FACTOR);
        return lastRandom == 5;
    }

    private int getLoopHeight(Dimension borderD, ArrayList<Image> images, ArrayList<RectanglePair> recPair, ArrayList<Image> bigImages ) throws IOException {
        int numRows = 0;
        while(this.runCoordinates(borderD, (int) Math.ceil(borderD.getHeight() * 1.0 / (numRows + 2 )), bigImages, recPair, images)) {
            numRows += 2;
        }
        return (int) Math.ceil(borderD.getHeight() / (numRows * 1.0));
    }

    @Override
    public Dimension getFinalPictureSize(int numImages, int rows, int width) {
        //TODO
        return null;
    }

    @Override
    public Boolean[][] createShapeMap(Image image, double size) {
        int width = image.getWidth();
        int height = image.getHeight();
        Boolean[][] map = new Boolean[width][height];
        for (Boolean[] mapRow : map) {
            Arrays.fill(mapRow, Boolean.TRUE);
        }
        return map;
    }

    @Override
    public double getSize() {
        return -1;
    }
}
