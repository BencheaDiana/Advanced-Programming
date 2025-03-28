package image.repositoory;

import image.exceptions.DuplicateImageException;
import image.exceptions.ImageNotFoundException;
import image.model.Image;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImageRepository {
    private final List<Image> images;

    public ImageRepository() {
        this.images = new ArrayList<>();
    }

    public void addImage(Image image) throws DuplicateImageException {
        boolean exists = images.stream()
                .anyMatch(existingImage -> existingImage.name().equals(image.name()));

        if (exists) {
            throw new DuplicateImageException("Imaginea " + image.name() + " deja exista");
        }

        images.add(image);
    }

    public Optional<Image> findImageByName(String name) {
        return images.stream()
                .filter(image -> image.name().equals(name))
                .findFirst();
    }

    public void displayImage(String imageName)
            throws ImageNotFoundException {

        Image image = findImageByName(imageName)
                .orElseThrow(() -> new ImageNotFoundException("Nu exista imaginea " + imageName));


    }

}
