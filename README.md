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

1. Create a mvn wrapper in your directory:
```shell
mvn -N wrapper:wrapper
```

2. Build your docker file (Note: **Don't** change the -e *IMAGES_PATH* but do so instead in the first part of -v):
```shell
docker build --tag "collage" .
docker run -e SHAPE='Rectangle' -e WIDTH=1000 -e HEIGHT=400 -e IMAGES_PATH="/images" -v "C:\:/images" collage
```
