import java.util.ArrayList;

public class pictureClass {
    private ArrayList<Image> list = new ArrayList<>();
    private double ratio;
    private int numUsed = 0;

    public  pictureClass( Image image){
        ratio = (double)image.getHeight() / (double) image.getWidth();
        list.add(image);
    }


    public ArrayList<Image> getImageList(){
        return  list;
    }
    public int size(){return list.size();}
    public int getNumUsed(){return numUsed;}
    public void plusUsed(){numUsed++;}
    public void clearNumUsed(){
        numUsed = 0;
    }

    public boolean addImage(Image image){
        if (((double)image.getHeight() / (double) image.getWidth()) == ratio){
            list.add(image);
            return true;
        }
        return false;
    }

    public double ratio (){
        return ratio;
    }
}
