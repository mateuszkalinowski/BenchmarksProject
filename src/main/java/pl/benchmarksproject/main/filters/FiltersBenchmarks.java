package pl.benchmarksproject.main.filters;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FiltersBenchmarks {

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void greyscaleBenchmark() throws IOException {
        File file = new File("src/main/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.GreyScaleFilter(bufferedImage);
    }
    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void negativeFullBenchmark() throws IOException{
        File file = new File("src/main/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.NegativeFilter(bufferedImage);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void antiAliasingFullFilter() throws IOException{
        File file = new File("src/main/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.AntiAliasingFilter(bufferedImage);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void meanFilterFullBenchmark() throws IOException{
        File file = new File("src/main/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.MeanFilter(bufferedImage);
    }

}
