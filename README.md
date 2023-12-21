## What it does:

This JAVA Program takes multiple images from a selects folder and creates with a selectes width and height a random collage. It works with different sized images and different ratio images, so no pre-processing is necessary. Additionally, there are two available shapes to choose from: *Rectangle* and *Hexagon* 

Perfect for creating posters wit A LOT of images and a high quality 

Example results (2000x1500 rectangles):
![image1](examples/final1.jpg)
(2000x300 rectangles)
![image2](examples/final2.jpg)
(2000x2rows hexagon)
![image3](examples/final3.jpg)
(2000x4rows hexagon)
![image4](examples/final4.jpg)
## How it works:

### Run with maven and java 17:

1. Clone repo 
```
git clone git@github.com:lr101/PictureCollage.git
```
2. Compile:
```shell
mvn install
```
3. Set environment variables like this or any other way:
```
export IMAGES_PATH="C:\"
export SHAPE=Rectangle
export WIDTH=1000
export HEIGHT400
```
4. Run the jar
```shell
java -jar target/picturecollage-1.jar
```

### Run with Docker

1. Run image from docker hub:
```shell
docker run \
  -e SHAPE='Rectangle' \
  -e WIDTH=1000 \
  -e HEIGHT=400 \
  -v "<path to images>:/images" \
  lrprojects/picture-collage:latest
```
 - Edit ``SHAPE`` use ``Rectangle`` or  ``Hexagon``
 - Edit ``WIDTH`` in pixel
 - Edit ``HEIGTH`` in pixel for Rectangle and number of rows for Hexagons
 - Insert your directory path to your images under ``<path to images>``
