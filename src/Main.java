import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static boolean sortArray(ArrayList<pictureClass> list, pictureClass newClass){  //adds pictureClass to Array sorting from low to heigth picture-ratio
        for (int i = 0; i< list.size(); i++){
            if (i+1!=list.size() && list.get(i).ratio() > newClass.ratio() && list.get(i+1).ratio() < newClass.ratio()){
                list.add(i,newClass);
                return true;
            } else if (i+1 == list.size() && list.get(i).ratio() > newClass.ratio()){
                list.add(i,newClass);
                return true;
            }
        }
        list.add(newClass);
        return false;
    }

    private static void clearArray(ArrayList<pictureClass> list){  //Resets array to not used
        for (pictureClass pClass :list){
            pClass.clearNumUsed();
        }
    }

    private static  int getHighRatioImgIndex(ArrayList<pictureClass> list){ // searches for the image index in the given array with the heigest ratio
        for (int i = list.size()-1; i != -1; i--){
            if (list.get(i).size()-list.get(i).getNumUsed() != 0){
                return i;
            }
        }
        return -1;
    }

    private static  int getLowRatioImgIndex(ArrayList<pictureClass> list){ // searcges for image index in the given array with lowest ratio
        for (int i = 0; i <list.size(); i++){
            if (list.get(i).size()-list.get(i).getNumUsed() != 0){
                return i;
            }
        }
        return -1;
    }

    private static boolean testIfPic(String name){ // test String if picture with extension .jpg .JPG .jpeg
        if (name.contains(".JPG") || name.contains(".jpg") || name.contains(".jpeg")) {
            String[] pic = name.split("\\.");
            if (name.split("\\.")[pic.length - 1].equals("JPG") || name.split("\\.")[pic.length - 1].equals("jpg") || name.split("\\.")[pic.length - 1].equals("jpeg")) {
                return true;
            }
        }
        return false;
    }

    private static boolean addBuffToArray(File path, String name, ArrayList<pictureClass> picturesSorted){ // loads BufferedImage from path and extracts with, heigt, name
        try {                                                                                              // creates Image and adds to ArrayList via .addImage or sortArray
            BufferedImage bimage = ImageIO.read(new File(path, name));
            Image image = new Image(new File(path, name), bimage.getWidth(), bimage.getHeight());
            bimage.flush();

            for (pictureClass pClass : picturesSorted) {
                if (pClass.addImage(image)){
                    return true;
                }
            }
            pictureClass newClass = new pictureClass(image);
            sortArray(picturesSorted, newClass);
            return true;

        }catch (Exception e){
            print(e.toString());
        }
        return false;
    }



    private static void setPixAb(String[] pictures, double maxH, double maxW, File path) {  //used to extract the most amount of images (heigt) in regards to amount of images and width
        try {



            ArrayList<pictureClass> picturesSorted = new ArrayList<>();

            int anzH = 0;
            print("Loading Images");
            for (int i = 0; i < pictures.length; i++) {
                print((int)((double)i/(double) pictures.length*100) + "%");
               if (testIfPic(pictures[i])){
                    addBuffToArray(path,pictures[i],picturesSorted);
                }
            }

            boolean done = false;
            double totalWidth;
            int anzGr = 0;

            while (!done) {
                anzH++;
                anzGr = 0;
                for (int i = 1; i <= anzH;i++){
                    totalWidth = 0;
                    if ((i-1)%2 == 0 && i < anzH){
                        int count = 0;

                        for (int y = 0; totalWidth < maxW && y < picturesSorted.size() ;count++) {
                            pictureClass pClass = picturesSorted.get(y);

                            if( pClass.size()-pClass.getNumUsed() >= 2){

                                if (count%4 == 0 && count != 0){
                                    int index;
                                    if ((count/2)%2 == 0){
                                        index = getHighRatioImgIndex(picturesSorted);
                                    }else {
                                        index = getLowRatioImgIndex(picturesSorted);
                                    }

                                    if (index != -1){
                                        totalWidth = totalWidth + ((maxH/(double) anzH)/picturesSorted.get(index).ratio())*2;
                                        picturesSorted.get(index).plusUsed();
                                        anzGr++;
                                    }
                                } else {

                                    totalWidth = totalWidth + (maxH/(double) anzH)/pClass.ratio();
                                    pClass.plusUsed();
                                    pClass.plusUsed();
                                }
                            }
                            if (pClass.size()-pClass.getNumUsed() < 2){
                                y++;
                            }
                        }
                        if (totalWidth <= maxW){
                            done = true;
                            anzH--;
                        }
                        i++;
                    }else {
                        for (pictureClass pClass : picturesSorted) {
                            while( pClass.size()-pClass.getNumUsed() >= 1 && totalWidth < maxW){
                                totalWidth = totalWidth + (maxH/(double) anzH)/pClass.ratio();
                                pClass.plusUsed();
                            }
                            if (totalWidth >= maxW){
                                break;
                            }
                        }
                    }
                }
                int count = 0;
                for (pictureClass pClass : picturesSorted) {
                    if (pClass.size()-pClass.getNumUsed()!=0){
                        count++;
                    }
                }
                if (count==0 && anzH != 1){
                    done = true;
                    anzH--;
                }

                clearArray(picturesSorted);
            }
            if (anzH%2 != 0 && anzH>1){
                anzGr = (int)((double)anzGr/((double) (anzH-1)/2));
            }else{
                anzGr = (int)((double)anzGr/((double)anzH/2));
            }
            writeImage(picturesSorted,maxW,maxH,anzH, path, anzGr);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static boolean contains(final int[] arr, final int key) {  // checks if Array contains int
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    private static  int[] randomNum (int gesamt){ // returns random list of indexes for position of big Images
        int[] xCor = new int [gesamt];
        Random rn = new Random();
        if (gesamt>0) {
            xCor[0] = rn.nextInt((3 - 1) + 1) + 1;
        }
        for(int i = 1; i < gesamt; i++){
            xCor[i] = xCor[i-1]+4;

        }


        return xCor;
    }

    private static void print(String text){  // System.out.println in short
        System.out.println(text);
    }

    private static void writeImage(ArrayList<pictureClass> picturesSorted, double maxW, double maxH, int anzH, File path, int anzGr){ // creates the final image
        try{
            double x;
            double y = 0;
            print("Bilder werden zusammengefügt");
            BufferedImage combined = new BufferedImage((int)maxW , (int)maxH, BufferedImage.TYPE_INT_ARGB);
            Graphics g = combined.createGraphics();
            boolean low = false;
            for (int i = 1; i <= anzH;i++){
                x = 0;
                if ((i-1)%2 == 0 && i < anzH){
                    int[] grIndex = randomNum(anzGr);
                    if (grIndex.length%2 == 0){
                        if (low){low= false;}else {low=true;}
                    }
                    int count = 0;
                    for (int z = 0; x < maxW && z < picturesSorted.size() ;count++) {
                        pictureClass pClass = picturesSorted.get(z);
                        if( pClass.size()-pClass.getNumUsed() >= 2){
                            if (contains(grIndex, count)){
                                int index;

                                if (!low){

                                    low = true;
                                    index = getHighRatioImgIndex(picturesSorted);
                                }else {
                                    low = false;
                                    index = getLowRatioImgIndex(picturesSorted);
                                }
                                if (index != -1){
                                    BufferedImage image =ImageIO.read(picturesSorted.get(index).getImageList().get(picturesSorted.get(index).getNumUsed()).getPath());
                                    g.drawImage(image, (int)x, (int)y,(int)(((maxH/(double) anzH)/picturesSorted.get(index).ratio())*2) ,(int)(maxH/(double)anzH*2) , null);
                                    x = x + ((maxH/(double) anzH)/picturesSorted.get(index).ratio())*2;
                                    picturesSorted.get(index).plusUsed();
                                    image.flush();
                                }
                            } else {
                                BufferedImage image = ImageIO.read(pClass.getImageList().get(pClass.getNumUsed()).getPath());
                                g.drawImage(image, (int)x, (int)y,(int)((maxH/(double) anzH)/pClass.ratio()) ,(int)(maxH/(double)anzH) , null);
                                pClass.plusUsed();
                                image.flush();
                                image = ImageIO.read(pClass.getImageList().get(pClass.getNumUsed()).getPath());
                                g.drawImage(image, (int)x, (int)(maxH/(double)anzH+y),(int)((maxH/(double) anzH)/pClass.ratio()) ,(int)(maxH/(double)anzH) , null);
                                x = x + (maxH/(double) anzH)/pClass.ratio();
                                pClass.plusUsed();
                                image.flush();
                            }
                        }
                        if (pClass.size()-pClass.getNumUsed() < 2){
                            z++;
                        }
                    }
                    y = y + (maxH/ (double) anzH*2);

                    i++;
                }else {
                    for (pictureClass pClass : picturesSorted) {
                        while( pClass.size()-pClass.getNumUsed() >= 1 && x < maxW){
                            BufferedImage image = ImageIO.read(pClass.getImageList().get(pClass.getNumUsed()).getPath());
                            g.drawImage(image, (int)x, (int)y,(int)((maxH/(double) anzH)/pClass.ratio()) ,(int)(maxH/(double)anzH) , null);
                            x = x + (maxH/(double) anzH)/pClass.ratio();
                            pClass.plusUsed();
                            image.flush();
                        }
                        if (x >= maxW){
                            break;
                        }
                    }
                }
                print((int)((double)i/(double)anzH)*100 + "%");
            }

            print("Bild wird gespeichert");
            ImageIO.write(combined, "PNG", new File(path, "combined.png"));
            g.dispose();
            System.out.println("Bild ist fertig");
        } catch(Exception e) {
            System.out.println(e);
        }
    }



    private static String getFileExtension(File file) { // return String with File extension
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    private static ArrayList<File> addToList(String[] pictures, File path){ // adds objects from pictures to new ArrayList, checking if it is a valid picture
        ArrayList<File> random = new ArrayList<>();
        if (pictures != null){
            for (String name : pictures) {
                if (testIfPic(name)){
                    random.add(new File(path, name));
                }
            }
        }
        return random;

    }



    private static void randomiser(File path, String[] pictures){ // randomises names and therefore positions of all valid images
        ArrayList<File> random = addToList(pictures,path);

        for (int i = 0; random.size() > 0; i++) {
            Random rd = new Random();
            int num = rd.nextInt(random.size());
            File newFile = new File(path, "D" + i + getFileExtension(random.get(num)));
            if (!random.get(num).renameTo(newFile)) {
                random.remove(num);
            }
        }
    }

    public static void main(String[] args) { // Main method, user adds File path and the size of the final image
        print("Collage Erstellen BY @LukasReim");
        print("");
        Scanner scanner = new Scanner(System.in);

        File path = new File("C");

        while(!path.isDirectory()){
            print("Geben Sie den Dateipfad der zu verwendenen Bilder an:");
            path = new File(scanner.nextLine());
        }
        String[] pictures = path.list();


        boolean test = true;
        double maxW = 0;
        print("Geben Sie die Breite (in Pixeln) des finalen Bildes an:");
        while(test){
            if (scanner.hasNextDouble()){
                maxW = scanner.nextDouble();
                test = false;
            }else {
                scanner.next();
                print("Geben Sie die Breite (in Pixeln) des finalen Bildes an:");
            }
        }


        test = true;
        double maxH = 0;
        print("Geben Sie die Höhe (in Pixeln) des Finalen Bildes an:");
        while(test){
            if (scanner.hasNextDouble()){
                maxH = scanner.nextDouble();
                test = false;
            }else {
                scanner.next();
                print("Geben Sie die Höhe (in Pixeln) des Finalen Bildes an:");
            }
        }

        boolean anordenen = false;
        test = true;
        while (test){

            String s = scanner.nextLine();
            if (s.equals("Y")){
                test = false;
                anordenen = true;
            }else  if (s.equals("N")){
                test = false;
            }
            print("Sollen Bilder zufällig Angeordnet werden (ACHTUNG: die Namen der Bilder werden UMBENNANT!!!) (Y/N)");
        }

        if (pictures != null){
            if (anordenen) {
                randomiser(path, path.list());
            }
            setPixAb(path.list(), maxH,maxW, path);
        }
    }
}
