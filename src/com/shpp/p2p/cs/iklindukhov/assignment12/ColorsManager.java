package com.shpp.p2p.cs.iklindukhov.assignment12;

import java.util.*;

/**
 * Manages image colors and stores the number of pixels having each color
 * <p>
 * Alpha channel in ARGB is not supported and so is ignored
 */
public class ColorsManager {

    private ColorsManager() {
    }

    /**
     * Finds the background color of the image by counting the occurrences
     * of each color in the pixel matrix.
     *
     * @param matrix two-dimensional array containing the image pixels
     * @return the color that occurs most frequently in the image, and so is considered as background
     */
    public static int getBackgroundColor(PixelObject[][] matrix) {
        return findBackgroundColorInBase(fulfillColorsDataBase(matrix));
    }

    /**
     * Adds a new color to the database with an initial pixel count of one.
     *
     * @param color          RGB color represented as an integer
     * @param colorsDatabase database containing colors and their pixel counts
     */
    private static void putNewColorToBase(int color, HashMap<Integer, Integer> colorsDatabase) {
        colorsDatabase.put(color, 1);
    }

    /**
     * Checks whether the specified color is present in the database.
     *
     * @param color          RGB color represented as an integer
     * @param colorsDatabase database containing colors and their pixel counts
     * @return {@code true} if the color is present in the database;
     * {@code false} otherwise
     */
    private static boolean isColorInDatabase(int color, HashMap<Integer, Integer> colorsDatabase) {
        return colorsDatabase.containsKey(color);
    }

    /**
     * Increases the number of pixels having the specified color by one.
     *
     * @param color          RGB color represented as an integer
     * @param colorsDatabase database containing colors and their pixel counts
     */
    private static void increaseNumberOfPixelsHavingThisColor(int color, HashMap<Integer, Integer> colorsDatabase) {
        colorsDatabase.merge(color, 1, Integer::sum);

    }

    /**
     * Finds the most frequently occurring color in the database.
     *
     * @param colorsDatabase database containing colors and their pixel counts
     * @return the color with the highest number of pixels
     */
    public static int findBackgroundColorInBase(HashMap<Integer, Integer> colorsDatabase) {
        return colorsDatabase.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    /**
     * Creates a database containing each color found in the pixel matrix
     * and the number of pixels having that color.
     *
     * @param matrix array with image pixels
     * @return a database where each color is mapped to the number of pixels
     * having that color
     */
    private static HashMap<Integer, Integer> fulfillColorsDataBase(PixelObject[][] matrix) {
        HashMap<Integer, Integer> colorsDatabase = new HashMap<>();
        for (PixelObject[] pixelObjects : matrix) {
            for (PixelObject pixelObject : pixelObjects) {
                int color = pixelObject.getColor();
                if (isColorInDatabase(color, colorsDatabase)) {
                    increaseNumberOfPixelsHavingThisColor(color, colorsDatabase);
                } else {
                    putNewColorToBase(color, colorsDatabase);
                }
            }
        }
        return colorsDatabase;
    }
}
