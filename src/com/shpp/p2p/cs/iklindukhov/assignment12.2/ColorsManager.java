package com.shpp.p2p.cs.iklindukhov.assignment12;

import java.awt.*;
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
    private final static int MAX_EUCLIDEAN_DISTANCE = 75;
    /**
     * Maps each color to the number of pixels having that color.
     */
    private final HashMap<Integer, Integer> colorsDatabase;
    /**
     * The color identified as the image background.
     */
    private final int backgroundColor;

    /**
     * Initializes the color database and identifies the background color.
     *
     * @param matrix two-dimensional array containing the image pixels
     */
    ColorsManager(PixelObject[][] matrix) {
        colorsDatabase = fulfillColorsDataBase(matrix);
        backgroundColor = findBackgroundColor();
    }

    /**
     * Creates a database containing each color found in the pixel matrix,
     * and the number of pixels having that color.
     * <p>
     * Increments the key`s {@code color} value by 1 if color is present in base, otherwise
     * adds new key {@code color} to database with an initial value of 1.
     *
     * @param matrix array with image pixels
     * @return a database where each color is mapped to the number of pixels
     * having that color
     */
    private HashMap<Integer, Integer> fulfillColorsDataBase(PixelObject[][] matrix) {
        HashMap<Integer, Integer> colorsDatabase = new HashMap<>();
        for (PixelObject[] pixelObjects : matrix) {
            for (PixelObject pixelObject : pixelObjects) {
                int color = pixelObject.getColor();
                colorsDatabase.merge(color, 1, Integer::sum);
            }
        }
        return colorsDatabase;
    }

    /**
     * Finds the most frequently occurring color in the database.
     *
     * @return the color that occurs most frequently in the image, and so is considered as background
     */
    private int findBackgroundColor() {
        return colorsDatabase.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    /**
     * Replaces colors similar to the background color with the background color.
     * <p>
     * Modifies the existing pixel objects in the provided matrix.
     *
     * @param matrix two-dimensional array containing the image pixels
     * @return the modified pixel matrix
     */
    public PixelObject[][] replaceColorsSimilarToBackground(PixelObject[][] matrix) {
        PixelObject[][] newMatrix = matrix;
        for (PixelObject[] pixelObjects : matrix) {
            for (PixelObject pixelObject : pixelObjects) {
                int pixelColor = pixelObject.getColor();
                if (isColorSimilarToBackground(pixelColor)) {
                    pixelObject.setColor(backgroundColor);
                }
            }
        }
        return newMatrix;
    }

    /**
     * Checks whether a pixel color can be considered as similar to the background
     * color using the Euclidean distance between their RGB components.
     *
     * @param pixelColor RGB color value of the pixel
     * @return {@code true} if the Euclidean distance does not exceed
     * {@value #MAX_EUCLIDEAN_DISTANCE}, otherwise {@code false}
     */
    private boolean isColorSimilarToBackground(int pixelColor) {
        Color pixel = new Color(pixelColor);
        Color background = new Color(backgroundColor);
        double euclideanDistance = Math.sqrt(
                Math.pow(pixel.getRed() - background.getRed(), 2) +
                        Math.pow(pixel.getGreen() - background.getGreen(), 2) +
                        Math.pow(pixel.getBlue() - background.getBlue(), 2)
        );
        return euclideanDistance <= MAX_EUCLIDEAN_DISTANCE;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
