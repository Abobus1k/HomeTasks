package edu.project4;

import edu.project4.models.FractalImage;
import edu.project4.models.ImageType;
import edu.project4.models.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageType format) throws IOException {
        ImageIO.write(createImage(image), format.name().toLowerCase(), filename.toFile());
    }

    private static BufferedImage createImage(FractalImage image) {
        var bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        int[] rgbData = new int[image.width() * image.height()];

        for (int i = 0; i < image.height(); i++) {
            for (int j = 0; j < image.width(); j++) {
                Pixel pixel = image.getPixel(j, i);
                rgbData[i * image.width() + j] = new Color(pixel.getR(), pixel.getG(), pixel.getB()).getRGB();
            }
        }

        bufferedImage.setRGB(0, 0, image.width(), image.height(), rgbData, 0, image.width());
        return bufferedImage;
    }
}
