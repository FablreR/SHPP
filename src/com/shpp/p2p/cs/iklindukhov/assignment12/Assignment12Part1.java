package com.shpp.p2p.cs.iklindukhov.assignment12;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Program searches for silhouettes in the image.
 * Image {@code name} should be given in command line,
 * otherwise the basic name from {@link PathValidator} will be used.
 * <p>
 * Don`t give the filepath in commandline.
 * Filepath must only be changed in {@link PathValidator} via {@code BASE_FILE_PATH} constant.
 * <p>
 * Image mustn't have different pixel amount in rows or columns,
 * otherwise program will crash.
 */
public class Assignment12Part1 {
    /**
     * Program entry point
     *
     * @param args array with command line arguments.
     *             Only {@code args [0]} is used
     */
    static void main(String[] args) {
        try {
            String filepath = PathValidator.validatePath(args);
            BufferedImage image = ImageSearcher.findImage(filepath);
            PixelObject[][] matrix = ImageProcessor.getPixelMatrix(image);
            int backgroundColor = ColorsManager.getBackgroundColor(matrix);
            int silhouettes = SilhouettesSearcher.findSilhouettes
                    (matrix, ImageProcessor.getImageSize(image), backgroundColor);
            System.out.println("Silhouettes found: " + silhouettes);
        } catch (IllegalArgumentException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

}
