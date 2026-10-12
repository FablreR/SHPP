package com.shpp.p2p.cs.iklindukhov.assignment12;
import java.awt.image.BufferedImage;

/**
 * Program searches for silhouettes in the image.
 * Image {@code name} should be given in command line,
 * otherwise the basic name {@link #BASE_FILE_NAME} will be used.
 * <p>
 * Don`t give the filepath in commandline.
 * Filepath must only be changed via {@link #BASE_FILE_PATH} constant.
 * <p>
 * Image mustn't have different pixel amount in rows or columns,
 * otherwise program will crash.
 */
public class Assignment12Part1 {

    /**
     * This name is used if no other filename was selected
     */
    private static final String BASE_FILE_NAME = "test.jpg";

    /**
     * This path is used if no other filepath was selected
     */
    private static final String BASE_FILE_PATH = "extra/images/";

    /**
     * Program entry point
     *
     * @param args array with command line arguments.
     *             Only {@code args [0]} is used
     */
    static void main(String[] args) {
        try {
            String filepath = PathBuilder.buildFilePath(args, BASE_FILE_PATH, BASE_FILE_NAME);
            BufferedImage image = ImageSearcher.findImage(filepath);
            PixelObject[][] matrix = ImageProcessor.getPixelMatrix(image);

            ColorsManager colorsManager = new ColorsManager(matrix);
            matrix = colorsManager.replaceColorsSimilarToBackground(matrix);
            int backgroundColor = colorsManager.getBackgroundColor();

            SilhouettesSearcher silhouettesSearcher = new SilhouettesSearcher(backgroundColor);
            int silhouettes = silhouettesSearcher.countSilhouettes(matrix);
            System.out.println("Silhouettes found: " + silhouettes);
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
