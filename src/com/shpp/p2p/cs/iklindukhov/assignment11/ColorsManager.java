package com.shpp.p2p.cs.iklindukhov.assignment11;

import java.util.*;

/**
 * Manages image colors and stores the number of pixels having each color
 * <p>
 * Alpha channel in ARGB is not supported and so is ignored
 */
public class ColorsManager {
    /**
     * Maximum Euclidean distance between two colors for them to be considered similar.
     */
    private final static int MAX_EUCLIDEAN_DISTANCE = 250;
    /**
     * Stores colors as keys and the number of pixels having each color as values.
     */
    private final HashMap<Integer, Integer> colorsDatabase = new HashMap<>();

    ColorsManager() {
    }

    /**
     * Adds a pixel color to the database.
     * If the color is already present, its pixel count is increased.
     * Otherwise, the color is added with an initial count of one.
     *
     * @param color RGB color represented as an integer
     */
    public void addPixelColorToDataBase(int color) {
        if (isColorInDatabase(color)) {
            increaseNumberOfPixelsHavingThisColor(color);
        } else {
            putNewColorToBase(color);
        }
    }

    /**
     * Adds a new color to the database with an initial pixel count of one.
     *
     * @param color RGB color represented as an integer
     */
    private void putNewColorToBase(int color) {
        colorsDatabase.put(color, 1);
    }

    /**
     * Checks whether the specified color is present in the database.
     *
     * @param color RGB color represented as an integer
     * @return {@code true} if the color is present in the database,
     * otherwise {@code false}
     */
    private boolean isColorInDatabase(int color) {
        return colorsDatabase.containsKey(color);
    }

    /**
     * Increases the number of pixels having the specified color.
     *
     * @param color RGB color represented as an integer
     */
    private void increaseNumberOfPixelsHavingThisColor(int color) {
        colorsDatabase.merge(color, 1, Integer::sum);

    }

    /**
     * Finds the most frequently occurring color in the image.
     *
     * @return the color with the highest number of pixels
     */
    public int getBackgroundColor() {
        return colorsDatabase.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    /**
     * Checks whether two colors are similar based on their Euclidean distance in RGB space.
     *
     * @param color1 first RGB color represented as an integer
     * @param color2 second RGB color represented as an integer
     * @return {@code true} if the colors are equal or their Euclidean distance
     * is less than or equal to the maximum allowed distance
     */
    public static boolean areColorsSimilar(int color1, int color2) {
        if (color1 == color2) {
            return true;
        } else {
            int r1 = color1 >> 16 & 0xFF;
            int g1 = color1 >> 8 & 0xFF;
            int b1 = color1 & 0xFF;

            int r2 = color2 >> 16 & 0xFF;
            int g2 = color2 >> 8 & 0xFF;
            int b2 = color2 & 0xFF;

            double euclideanDistance = Math.sqrt(
                    Math.pow(r1 - r2, 2) +
                            Math.pow(g1 - g2, 2) +
                            Math.pow(b1 - b2, 2)
            );
            return euclideanDistance <= MAX_EUCLIDEAN_DISTANCE;
        }
    }
}
