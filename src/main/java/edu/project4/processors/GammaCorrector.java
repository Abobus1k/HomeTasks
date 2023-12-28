package edu.project4.processors;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;

public final class GammaCorrector implements ImageProcessor {
    private final double gamma;
    private double max = 0.0;

    public GammaCorrector(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        for (int i = 0; i < image.height(); i++) {
            for (int j = 0; j < image.width(); j++) {
                Pixel current = image.getPixel(j, i);

                if (current.getHitCount() != 0) {
                    current.setNormal(
                        Math.log10(current.getHitCount())
                    );

                    if (current.getNormal() > max) {
                        max = current.getNormal();
                    }
                }
            }
        }

        for (int i = 0; i < image.height(); i++) {
            for (int j = 0; j < image.width(); j++) {
                Pixel current = image.getPixel(j, i);

                current.setNormal(
                    current.getNormal() / max
                );

                current.setR((int) (current.getR() * Math.pow(current.getNormal(), 1.0 / gamma)));
                current.setG((int) (current.getG() * Math.pow(current.getNormal(), 1.0 / gamma)));
                current.setB((int) (current.getB() * Math.pow(current.getNormal(), 1.0 / gamma)));
            }
        }
    }
}
