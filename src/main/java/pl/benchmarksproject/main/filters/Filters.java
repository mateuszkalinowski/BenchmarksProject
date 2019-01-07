package pl.benchmarksproject.main.filters;

import pl.benchmarksproject.main.main.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Filters {

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void greyscaleBenchmark(){
        String[] args = {"obrazek.png","greyscale"};
        Main.main(args);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void negativeBenchmark(){
        String[] args = {"obrazek.png","negative"};
        Main.main(args);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void antiAliasingFilter(){
        String[] args = {"obrazek.png","antialiasing"};
        Main.main(args);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.AverageTime)
    public void meanFilterBenchmark(){
        String[] args = {"obrazek.png","mean"};
        Main.main(args);
    }


    /**
     * Filtr przekształca kolorowy obraz na obraz w skali szarości, poprzez policzenie średniej wartości każdego piksela.
     * @param oldImage obrazek do zmiany
     * @return obraz na którym został zastosowany filtr
     */
    public static BufferedImage GreyScaleFilter(BufferedImage oldImage) {
        BufferedImage newBufferedImage = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), oldImage.getType());
        for (int i = 0; i < newBufferedImage.getWidth(); i++) {
            for (int j = 0; j < newBufferedImage.getHeight(); j++) {
                Color oldColor = new Color(oldImage.getRGB(i, j));
                int newColorInt = (oldColor.getGreen() + oldColor.getBlue() + oldColor.getRed()) / 3;
                Color newColor = new Color(newColorInt, newColorInt, newColorInt);
                newBufferedImage.setRGB(i, j, newColor.getRGB());
            }
        }

        return newBufferedImage;
    }

    /**
     * Filtr neguje poszczególne kolory wszystkich pikseli obrazu, to znaczy zmienia, każdą składową piksela (czerwoną, zieloną i niebieską) odejmując ją od 255.
     * @param oldImage obrazek do zmiany
     * @return obraz na którym został zastosowany filtr
     */
    public static BufferedImage NegativeFilter(BufferedImage oldImage) {
        BufferedImage newBufferedImage = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), oldImage.getType());
        for (int i = 0; i < newBufferedImage.getWidth(); i++) {
            for (int j = 0; j < newBufferedImage.getHeight(); j++) {
                Color oldColor = new Color(oldImage.getRGB(i, j));
                int r = oldColor.getRed();
                int g = oldColor.getGreen();
                int b = oldColor.getBlue();
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                Color newColor = new Color(r, g, b);
                newBufferedImage.setRGB(i, j, newColor.getRGB());
            }
        }
        return newBufferedImage;
    }

    /**
     * Metoda zastosowuje anty-aliasing. Celem jego stosowania jest wygładzanie krawędzi. Dla każdego piksela obliczana jest średnia wartość kolorów piskeli sąsiadujących i zmieniana jest jego barwa na wyliczony kolor.
     * @param oldImage obrazek do zmiany
     * @return obraz na którym został zastosowany filtr
     */
    public static BufferedImage AntiAliasingFilter(BufferedImage oldImage) {
        BufferedImage result = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), oldImage.getType());
        for (int i = 0; i < result.getWidth(); i ++) {
            for (int j = 0; j < result.getHeight(); j++) {
                if (i == 0 || j == 0 || i == result.getWidth() - 1 || j == result.getHeight() - 1) {
                    result.setRGB(i, j, oldImage.getRGB(i, j));
                } else {
                    result.setRGB(
                            i,
                            j,
                            getAverageColor(
                                    new Color(oldImage.getRGB(i - 1, j)),
                                    new Color(oldImage.getRGB(i, j - 1)),
                                    new Color(oldImage.getRGB(i + 1, j)),
                                    new Color(oldImage.getRGB(i, j + 1))
                            ).getRGB());
                }
            }
        }
        return result;
    }

    private static Color getAverageColor(Color a, Color b, Color c, Color d) {
        return new Color(
                (a.getRed() + b.getRed() + c.getRed() + d.getRed()) / 4,
                (a.getGreen() + b.getGreen() + c.getGreen() + d.getGreen()) / 4,
                (a.getBlue() + b.getBlue() + c.getBlue() + d.getBlue()) / 4
        );
    }

    /**
     * Filtr uśrednia wartość piseli w masce 9x9, przy czym waga każdego piksela to '1'.
     * @param oldImage obrazek do zmiany
     * @return obraz na którym został zastosowany filtr
     */
    public static BufferedImage MeanFilter(BufferedImage oldImage) {
        BufferedImage result = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), oldImage.getType());
        for(int i = 0; i < result.getWidth()-9;i+=9) {
            for(int j = 0; j < result.getHeight()-9;j+=9) {
                int avgRed = 0;
                int avgBlue = 0;
                int avgGreen = 0;
                Color color;
                for(int k = i; k < i+9; k++) {
                    for(int l = j; l < j+9;l++) {
                        color = new Color(oldImage.getRGB(k,l));
                        avgBlue+=color.getBlue();
                        avgGreen+=color.getGreen();
                        avgRed+=color.getRed();
                    }
                }
                avgRed = avgRed/81;
                avgBlue = avgBlue/81;
                avgGreen = avgGreen/81;
                for(int k = i; k < i+9; k++) {
                    for(int l = j; l < j+9;l++) {
                        Color newColor = new Color(avgRed,avgGreen,avgBlue);
                        result.setRGB(k,l,newColor.getRGB());
                    }
                }
            }
        }
        return result;
    }
}