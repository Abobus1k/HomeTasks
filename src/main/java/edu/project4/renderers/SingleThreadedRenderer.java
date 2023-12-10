package edu.project4.renderers;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.models.Rect;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"ParameterAssignment"})
public class SingleThreadedRenderer implements Renderer {
    private static final Color COLOR = new Color(100, 200, 200);
    public static final int START = -20;

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iters,
        int symmetry
    ) {
        renderSamples(canvas, world, variations, samples, iters, symmetry);
        return canvas;
    }

    private void renderSamples(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iters,
        int symmetry
    ) {
        for (int i = 0; i < samples; i++) {
            Point point = world.getRandomPoint();
            renderIteration(canvas, world, variations, point, iters, symmetry);
        }
    }

    private void renderIteration(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        Point point,
        int iters,
        int symmetry
    ) {
        for (int i = START; i < iters; i++) {
            Transformation variation = getRandomVariation(variations);
            point = applyVariation(variation, point);

            if (i >= 0) {
                double theta2 = 0.0;
                for (int s = 0; s < symmetry; ++s) {
                    theta2 += Math.PI * 2.0 / symmetry;

                    Point rPoint = rotate(point, theta2);
                    if (!world.contains(rPoint)) {
                        continue;
                    }

                    Pixel pixel = mapRange(world, rPoint, canvas);
                    if (pixel == null) {
                        continue;
                    }

                    synchronized (pixel) {
                        updatePixelWithColor(pixel);
                    }
                }
            }
        }
    }

    private Point applyVariation(Transformation variation, Point point) {
        return variation.apply(point);
    }

    private void updatePixelWithColor(Pixel pixel) {
        if (pixel.getHitCount() == 0) {
            pixel.setR(COLOR.getRed());
            pixel.setG(COLOR.getGreen());
            pixel.setB(COLOR.getBlue());
        } else {
            pixel.setR((pixel.getR() + COLOR.getRed()) / 2);
            pixel.setG((pixel.getG() + COLOR.getGreen()) / 2);
            pixel.setB((pixel.getB() + COLOR.getBlue()) / 2);
        }

        pixel.addHit();
    }

    private Transformation getRandomVariation(List<Transformation> variations) {
        return variations.get(ThreadLocalRandom.current().nextInt(0, variations.size()));
    }

    private Point rotate(Point point, double angle) {
        double x = point.x() * Math.cos(angle) - point.y() * Math.sin(angle);
        double y = point.x() * Math.sin(angle) + point.y() * Math.cos(angle);

        return new Point(x, y);
    }

    private Pixel mapRange(Rect world, Point point, FractalImage canvas) {
        int x = (int) ((point.x() - world.x()) * canvas.width() / world.width());
        int y = (int) ((point.y() - world.y()) * canvas.height() / world.height());

        if (!canvas.contains(x, y)) {
            return null;
        }

        return canvas.getPixel(x, y);
    }
}
