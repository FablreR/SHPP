package com.shpp.p2p.cs.iklindukhov.assignment11;

import java.awt.image.BufferedImage;

/**
 * Program searches for silhouettes in the image.
 * Image path should be given in command line,
 * otherwise the basic path from {@link PathValidator} will be used.
 * <p>
 *
 * Only paths with two parts are supported
 * <blockquote>
 *     For example
 * <pre>
 *     {@code something.png}
 * </pre>
 * </blockquote>
 *<p>
 *
 * Image mustn't have different pixel amount in rows or columns,
 * otherwise program will crash.
 */
public class Assignment11Part1 {
    /**
     * Program entry point
     *
     * @param args array with command line arguments.
     *             Only {@code args [0]} is used
     */
    /* IDEA said public is redundant since java25 */
    static void main(String[] args) {
        try {
            String filepath = PathValidator.validatePath(args);
            BufferedImage image = ImageSearcher.findImage(filepath);
            ColorsManager colorsManager = new ColorsManager();
            ImageProcessor imageProcessor = new ImageProcessor(image, colorsManager);
            SilhouettesSearcher searcher = new SilhouettesSearcher(
                    imageProcessor.getPixelMatrix(),
                    colorsManager.getBackgroundColor()
            );
            InfoPrinter printer = new InfoPrinter(searcher.getSilhouettesArray());
            printer.printSilhouettesInfo();
        } catch (IllegalArgumentException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
