package pl.benchmarksproject.main.filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Scaler {

    private BufferedImage img;

    public Scaler(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage getScaledByNew(int scale) {
        if (scale < 2) {
            return this.img;
        }
        int newWidth = img.getWidth() * scale;
        int newHeight = img.getHeight() * scale;
        BufferedImage newImg = new BufferedImage(
                newWidth,
                newHeight,
                img.getType()
        );
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                Color color = new Color(
                        img.getRGB(
                                i / scale,
                                j / scale
                        )
                );
                newImg.setRGB(i, j, color.getRGB());
            }
        }
        return newImg;
    }

    public BufferedImage getScaledByOld(int scale) {
        if (scale < 2) {
            return this.img;
        }
        int newWidth = img.getWidth() * scale;
        int newHeight = img.getHeight() * scale;
        BufferedImage newImg = new BufferedImage(
                newWidth,
                newHeight,
                img.getType()
        );
        int r, c;
        for (int i = 0; i < img.getWidth(); i++) {
            c = i * scale;
            for (int j = 0; j < img.getHeight(); j++) {
                r = j * scale;
                Color color = new Color(img.getRGB(i, j));
                for (int k = 0; k < scale; k++) {
                    for (int l = 0; l < scale; l++) {
                        newImg.setRGB(
                                c + k,
                                r + l,
                                color.getRGB()
                        );
                    }
                }
            }
        }
        return newImg;
    }
}
