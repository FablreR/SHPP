package com.shpp.p2p.cs.iklindukhov.assignment12;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Searches for silhouettes in a pixel matrix.
 * <p>
 * A silhouette is a connected group of pixels whose colors
 * are similar to each other and different from the background color.
 */
public class SilhouettesSearcher {
    /**
     * Coefficient used to determine the proportion of the original silhouette
     * size to retain after removing its border pixels.
     * <p>
     * A value of 0.7 means that border removal continues until 70%
     * of the original silhouette pixels remain.
     */
    private static final double RETAINED_SILHOUETTE_SIZE = 0.7;
    /**
     * Minimum silhouette size relative to the largest detected silhouette.
     */
    private static final double MINIMUM_SILHOUETTE_SIZE_RATIO = 0.05;
    /**
     * Color representing the image background.
     */
    private final int backgroundColor;

    /**
     * Creates a silhouette searcher using the specified background color.
     *
     * @param bg color representing the image background
     */
    SilhouettesSearcher(int bg) {
        backgroundColor = bg;
    }

    /**
     * Detects silhouettes in the image, removes their border pixels,
     * and filters out silhouettes that are too small.
     *
     * @param matrix two-dimensional array containing the image pixels
     * @return number of detected silhouettes remaining after filtering
     */
    public int countSilhouettes(PixelObject[][] matrix) {
        ArrayList<Silhouette> silhouettes = findSilhouettes(matrix);
        removeSilhouetteBorders(silhouettes);
        silhouettes = findSilhouettes(matrix);
        removeSmallSilhouettes(silhouettes);
        return silhouettes.size();
    }

    /**
     * Finds all connected groups of non-background pixels in the image.
     * Each group is represented as a separate silhouette.
     *
     * @param matrix two-dimensional array containing the image pixels
     * @return list of detected silhouettes
     */
    private ArrayList<Silhouette> findSilhouettes(PixelObject[][] matrix) {
        ArrayList<Silhouette> silhouettes = new ArrayList<>();
        for (PixelObject[] pixelObjects : matrix) {
            for (PixelObject currentPixel : pixelObjects) {
                if (!currentPixel.isVisited() && currentPixel.getColor() != backgroundColor) {
                    Silhouette sil = new Silhouette(findSilhouettePixels(currentPixel));
                    silhouettes.add(sil);
                }
            }
        }
        makeSilhouettePixelsNotVisited(silhouettes);
        return silhouettes;
    }

    /**
     * Removes border pixels from each silhouette until the remaining pixel
     * count reaches the specified proportion of its original size.
     *
     * @param silhouettes list of silhouettes to process
     */
    private void removeSilhouetteBorders(ArrayList<Silhouette> silhouettes) {
        for (Silhouette sil : silhouettes) {
            ArrayList<PixelObject> silhouettePixels = sil.pixels();
            int initialSize = sil.getSize();
            while (silhouettePixels.size() > initialSize * RETAINED_SILHOUETTE_SIZE) {
                Set<PixelObject> borderPixels = findBorderPixels(silhouettePixels);
                removeBorderPixels(borderPixels, silhouettePixels);
            }
        }
    }

    /**
     * Finds silhouette pixels that are adjacent to at least one background pixel.
     *
     * @param silhouettePixels pixels belonging to the silhouette
     * @return set of pixels identified as border pixels
     */
    private Set<PixelObject> findBorderPixels(ArrayList<PixelObject> silhouettePixels) {
        Set<PixelObject> borderPixels = new HashSet<>();
        for (PixelObject pixel : silhouettePixels) {
            for (PixelObject neighbour : pixel.getNeighborsList()) {
                if (neighbour != null && neighbour.getColor() == backgroundColor) {
                    borderPixels.add(pixel);
                    break;
                }
            }
        }
        return borderPixels;
    }


    /**
     * Replaces border pixels with the background color and removes them
     * from the list of silhouette pixels.
     *
     * @param borderPixels     set of border pixels to remove
     * @param silhouettePixels list of pixels belonging to the silhouette
     */
    private void removeBorderPixels(Set<PixelObject> borderPixels, ArrayList<PixelObject> silhouettePixels) {
        for (PixelObject pixel : borderPixels) {
            pixel.setColor(backgroundColor);
            silhouettePixels.remove(pixel);
        }
    }

    /**
     * Removes silhouettes whose size is below the specified proportion
     * of the largest detected silhouette.
     *
     * @param silhouettes list of silhouettes to filter
     */
    private void removeSmallSilhouettes(ArrayList<Silhouette> silhouettes) {
        int biggestSilhouetteSize = getMaxSize(silhouettes);
        silhouettes.removeIf(sil -> sil.getSize() < biggestSilhouetteSize * MINIMUM_SILHOUETTE_SIZE_RATIO);
    }

    /**
     * Finds the size of the largest silhouette in the list.
     *
     * @param sils list of silhouettes to examine
     * @return size of the largest silhouette, or 0 if the list is empty
     */
    private int getMaxSize(ArrayList<Silhouette> sils) {
        int maxSize = 0;
        for (Silhouette s : sils) {
            if (s.getSize() > maxSize) {
                maxSize = s.getSize();
            }
        }
        return maxSize;
    }


    /**
     * Finds the size of a silhouette using
     * breadth-first search.
     *
     * @param pixel starting pixel
     * @return ArrayList containing all pixels of the silhouette
     */
    private ArrayList<PixelObject> findSilhouettePixels(PixelObject pixel) {
        ArrayList<PixelObject> pixels = new ArrayList<>();
        ArrayDeque<PixelObject> deque = new ArrayDeque<>();
        pixel.setVisited(true);
        deque.add(pixel);
        while (!deque.isEmpty()) {
            PixelObject currentPixel = deque.pollFirst();
            pixels.add(currentPixel);
            for (PixelObject neighbour : currentPixel.getNeighborsList()) {
                if (isValidNeighbour(neighbour, backgroundColor)) {
                    neighbour.setVisited(true);
                    deque.add(neighbour);
                }
            }
        }
        return pixels;
    }

    /**
     * Checks whether a neighboring pixel can be added to the current
     * connected component.
     *
     * @param neighbour       neighboring pixel to check
     * @param backgroundColor color representing the image background
     * @return {@code true} if the neighbor exists, has not been visited,
     * and does not have the background color; {@code false} otherwise
     */
    private boolean isValidNeighbour(PixelObject neighbour, int backgroundColor) {
        return neighbour != null
                && !neighbour.isVisited()
                && neighbour.getColor() != backgroundColor;
    }

    /**
     * Resets the visited state of all pixels belonging to the specified silhouettes.
     *
     * @param silhouettes list of silhouettes whose pixels should be reset
     */
    private void makeSilhouettePixelsNotVisited(ArrayList<Silhouette> silhouettes) {
        for (Silhouette sil : silhouettes) {
            for (PixelObject pixel : sil.pixels()) {
                pixel.setVisited(false);
            }
        }
    }
}

