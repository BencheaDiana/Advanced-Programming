package image.app;

import image.exceptions.DuplicateImageException;
import image.exceptions.ImageNotFoundException;
import image.model.Image;
import image.repositoory.ImageRepository;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;

public class ImgApp {
    public static void main(String[] args) {
        try {
            ImageRepository repository = new ImageRepository();

            Image image1 = new Image(
                    "Apus",
                    LocalDate.now(),
                    Arrays.asList("nature", "landscape"),
                    Path.of("C:\\images\\sunset.jpg")
            );

            Image image2 = new Image(
                    "Munte",
                    LocalDate.now(),
                    Arrays.asList("mountain", "scenery"),
                    Path.of("C:\\images\\mountain.png")
            );

            Image image3 = new Image(
                    "Selfie",
                    LocalDate.now(),
                    Arrays.asList("portret", "grup", "fata"),
                    Path.of("C:\\images\\Selfie.jpg")
         );
//            Image image4 = new Image(
//                    "Selfie",
//                    LocalDate.now(),
//                    Arrays.asList("portret", "fata"),
//                    Path.of("C:\images\\selfie.png")
//            );
            repository.addImage(image1);
            repository.addImage(image2);
            repository.addImage(image3);
//            repository.addImage(image4);
            repository.displayImage("Apus");
            repository.displayImage("Selfie");
//            repository.displayImage("Plaja");//pt exceptie
        } catch (DuplicateImageException e) {
            System.err.println("Dublura imaginii: " + e.getMessage());
        } catch (ImageNotFoundException e) {
            System.err.println("Imaginea nu a fost gasita: " + e.getMessage());
        }
    }
}