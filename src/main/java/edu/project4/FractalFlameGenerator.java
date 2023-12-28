package edu.project4;

import edu.project4.models.FractalImage;
import edu.project4.models.ImageType;
import edu.project4.models.Rect;
import edu.project4.processors.GammaCorrector;
import edu.project4.processors.ImageProcessor;
import edu.project4.renderers.MultiThreadedRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleThreadedRenderer;
import edu.project4.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings({"ParameterNumber"})
public class FractalFlameGenerator {
    private FractalFlameGenerator() {
    }

    public static FractalImage generate(
        int width,
        int height,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iters,
        int symmetry,
        double gamma,
        int threadNumber,
        Path path,
        ImageType format
    ) throws IOException {

        FractalImage canvas = FractalImage.create(width, height);

        Renderer renderer;

        if (threadNumber == 1) {
            renderer = new SingleThreadedRenderer();
        } else {
            renderer = new MultiThreadedRenderer(threadNumber);
        }

        FractalImage renderedImage = renderer.render(
            canvas,
            world,
            variations,
            samples,
            iters,
            symmetry
        );

        ImageProcessor processor = new GammaCorrector(gamma);
        processor.process(renderedImage);

        ImageUtils.save(renderedImage, path, format);

        return renderedImage;
    }
}
