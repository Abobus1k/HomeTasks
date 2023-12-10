package edu.project4;

import edu.project4.models.ImageType;
import edu.project4.models.Rect;
import edu.project4.transformations.Disc;
import edu.project4.transformations.Linear;
import edu.project4.transformations.Sinusoidal;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Swirl;
import edu.project4.transformations.Transformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestFractals {

    private final static int WIDTH = 1920;
    private final static int HEIGHT = 1080;
    private final static Rect WORLD = new Rect(-2, -2, 4.0, 4.0);
    private final static int SAMPLES = 300000;

    private final static int ITERS = 150;
    private final static int SYMMETRY = 1;
    private final static double GAMMA = 0.8;
    public static final Path IMAGES = Path.of("src/main/java/edu/project4/images/");

    @Test
    void testFractalsSpeed() throws IOException {
        List<Transformation> variations = new ArrayList<>();
        variations.add(new Linear());
        variations.add(new Sinusoidal());
        variations.add(new Spherical());
        variations.add(new Swirl());
        variations.add(new Disc());

        ImageType imageType = ImageType.PNG;
        String fileName = String.format(
            "samples%d_iter%d_symmetry%d_gamma%.2f.%s",
            SAMPLES, ITERS, SYMMETRY, GAMMA, imageType.name().toLowerCase()
        );
        Path path = IMAGES.resolve(fileName);
        long startTime = System.currentTimeMillis();
        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITERS,
            SYMMETRY,
            GAMMA,
            1,
            path,
            imageType
        );
        long firstTime = System.currentTimeMillis() - startTime;

        long speedStartTime = System.currentTimeMillis();
        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITERS,
            SYMMETRY,
            GAMMA,
            1,
            path,
            imageType
        );
        long secondTime = System.currentTimeMillis() - speedStartTime;
        Assertions.assertTrue(secondTime < firstTime);
    }

}
