package image.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public record Image(
        String name,
        LocalDate date,
        List<String> tags,
        Path fileLocation
) {
    public Image {
        Objects.requireNonNull(name, "Image name cannot be null");
        Objects.requireNonNull(date, "Image date cannot be null");
        Objects.requireNonNull(tags, "Image tags list cannot be null");
        Objects.requireNonNull(fileLocation, "Image file location cannot be null");
    }

    @Override
    public String toString() {
        return name+date+tags+fileLocation;
    }
}
