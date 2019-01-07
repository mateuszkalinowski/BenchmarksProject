package pl.benchmarksproject.main.main;

import pl.benchmarksproject.main.files.FileManager;
import pl.benchmarksproject.main.filters.FilterEnum;
import pl.benchmarksproject.main.filters.Filters;

import java.awt.image.BufferedImage;

import static java.lang.System.exit;

public class Main {

    private final static String dirPath = "src/main/resources/";
    private static String fileName;
    private static String filterName;
    private static FilterEnum filterEnum;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Provide file name and filter");
            exit(1);
        }
        fileName = args[0];
        filterName = args[1].toLowerCase();
        switch (filterName) {
            case "greyscale": {
                filterEnum = FilterEnum.GREYSCALE;
                break;
            }
            case "negative": {
                filterEnum = FilterEnum.NEGATIVE;
                break;
            }
            case "antialiasing": {
                filterEnum = FilterEnum.ANTIALIASING;
                break;
            }
            case "mean": {
                filterEnum = FilterEnum.MEAN;
                break;
            }
            default: {
                System.out.println("Unknown filter");
                exit(2);
            }
        }
        FileManager fileManager = new FileManager(dirPath);
        BufferedImage image = fileManager.readImage(fileName);
        BufferedImage result = null;

        if (filterEnum.equals(FilterEnum.GREYSCALE)) {
            result = Filters.GreyScaleFilter(image);
        }
        else if (filterEnum.equals((FilterEnum.NEGATIVE))){
            result = Filters.NegativeFilter(image);
        }
        else if (filterEnum.equals(FilterEnum.ANTIALIASING)) {
            result = Filters.AntiAliasingFilter(image);
        }
        else if (filterEnum.equals(FilterEnum.MEAN)) {
            result = Filters.MeanFilter(image);
        }

        fileManager.saveImage(result);


    }
}
