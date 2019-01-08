package pl.benchmarksproject;

import pl.benchmarksproject.main.filters.Filters;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class FiltersTest {

    @Test
    public void greyScaleSizeTest() throws Exception {
        File file = new File("src/test/java/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.GreyScaleFilter(bufferedImage);
        assertEquals(bufferedImage.getHeight(), result.getHeight());
        assertEquals(bufferedImage.getWidth(), result.getWidth());
    }

    @Test
    public void negativeFilterSizeTest() throws Exception {
        File file = new File("src/test/java/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.NegativeFilter(bufferedImage);
        assertEquals(bufferedImage.getHeight(), result.getHeight());
        assertEquals(bufferedImage.getWidth(), result.getWidth());
    }

    @Test
    public void antiAliasingFilterSizeTest() throws Exception {
        File file = new File("src/test/java/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.AntiAliasingFilter(bufferedImage);
        assertEquals(bufferedImage.getHeight(), result.getHeight());
        assertEquals(bufferedImage.getWidth(), result.getWidth());
    }

    @Test
    public void meanFilterSizeTest() throws Exception {
        File file = new File("src/test/java/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.MeanFilter(bufferedImage);
        assertEquals(bufferedImage.getHeight(), result.getHeight());
        assertEquals(bufferedImage.getWidth(), result.getWidth());
    }


    @Test
    public void greyScaleFilter() throws Exception{
        File file = new File("src/test/java/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.GreyScaleFilter(bufferedImage);
        for(int i = 0; i < result.getWidth();i++) {
            for(int j = 0; j < result.getHeight();j++) {
                Color color = new Color(result.getRGB(i,j));
                assertEquals(color.getRed(), color.getBlue());
                assertEquals(color.getBlue(), color.getGreen());
            }
        }
    }

    @Test
    public void NegativeFilter() throws Exception{
        File file = new File("src/test/java/resources/obrazek.png");
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage result = Filters.NegativeFilter(bufferedImage);
        for(int i = 0; i < result.getWidth();i++) {
            for(int j = 0; j < result.getHeight();j++) {
                Color color = new Color(result.getRGB(i,j));
                int Rresult = color.getRed();
                int Gresult = color.getGreen();
                int Bresult = color.getBlue();

                Rresult = 255 - Rresult;
                Gresult = 255 - Gresult;
                Bresult = 255 - Bresult;

                Color colorTest = new Color(bufferedImage.getRGB(i,j));

                assertEquals(Rresult,colorTest.getRed() );
                assertEquals(Gresult,colorTest.getGreen());
                assertEquals(Bresult,colorTest.getBlue());


            }
        }
    }

    @Test
    public void AntiAliasingFilterTest() throws Exception{
        File file = new File("src/test/java/resources/test_image_2.png");
        BufferedImage original = ImageIO.read(file);
        BufferedImage result = Filters.AntiAliasingFilter(original);
        for(int i = 1; i < result.getWidth() - 1;i++) {
            for(int j = 1; j < result.getHeight() - 1;j++) {
                if (
                        original.getRGB(i, j) != original.getRGB(i - 1, j) ||
                                original.getRGB(i, j) != original.getRGB(i + 1, j) ||
                                original.getRGB(i, j) != original.getRGB(i, j - 1) ||
                                original.getRGB(i, j) != original.getRGB(i, j + 1)
                ) {
                    assertNotEquals(original.getRGB(i, j), result.getRGB(i, j));
                } else {
                    assertEquals(original.getRGB(i, j), result.getRGB(i, j));
                }
            }
        }
    }


    @Test
    public void meanFilter() throws Exception{
        File file = new File("src/test/java/resources/obrazek.png");
        BufferedImage original = ImageIO.read(file);
        BufferedImage result = Filters.MeanFilter(original);
        for(int i = 0; i < result.getWidth()-9;i+=9) {
            for(int j = 0; j < result.getHeight()-9;j+=9) {
                int rgb = result.getRGB(i,j);
                for(int k = i; k < i+9; k++) {
                    for(int l = j; l < j+9;l++) {
                        if(k == i && l == j)
                            continue;
                        assertEquals(result.getRGB(k,l),rgb);

                    }
                }
            }
        }
    }
}