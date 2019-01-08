package pl.benchmarksproject.main.filters;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScalerBenchmarks {
    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void getScaledByNewBenchmark() throws IOException {
        File file = new File("src/main/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        Scaler scaler = new Scaler(bufferedImage);
        BufferedImage result = scaler.getScaledByNew(2);
    }
//
//    @Benchmark
//    @Fork(value = 1, warmups = 1)
//    @BenchmarkMode(Mode.AverageTime)
//    public void getScaledByOldBenchmark() throws IOException {
//        File file = new File("src/main/resources/obrazek.png");
//        BufferedImage bufferedImage = ImageIO.read(file);
//        Scaler scaler = new Scaler(bufferedImage);
//        BufferedImage result = scaler.getScaledByNew(2);
//    }
}
