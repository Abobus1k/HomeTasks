package edu.project4.renderers;

import edu.project4.models.FractalImage;
import edu.project4.models.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

public class MultiThreadedRenderer implements Renderer {
    private final int threadNumber;

    public MultiThreadedRenderer(int threadNumber) {
        this.threadNumber = Math.min(
            threadNumber,
            Runtime.getRuntime().availableProcessors()
        );
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world, List<Transformation> variations,
        int samples,
        int iters,
        int symmetry
    ) {
        var threads = createAndStartThreads(threadNumber, canvas, world, variations, samples, iters, symmetry);
        waitForThreads(threads);
        return canvas;
    }

    private Thread[] createAndStartThreads(
        int threadNumber,
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iters,
        int symmetry
    ) {
        var threads = new Thread[threadNumber];
        int samplesPerThread = samples / threadNumber;

        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new Thread(() -> {
                var renderer = new SingleThreadedRenderer();
                renderer.render(
                    canvas,
                    world,
                    variations,
                    samplesPerThread,
                    iters,
                    symmetry
                );
            });

            threads[i].start();
        }

        return threads;
    }

    private void waitForThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        }
    }
}
