package com.shpp.p2p.cs.iklindukhov.assignment12;

import java.util.ArrayDeque;

/**
 * Searches for silhouettes in a pixel matrix.
 * <p>
 * A silhouette is a connected group of pixels whose colors
 * are similar to each other and different from the background color.
 */
public class SilhouettesSearcher {
    /**
     * Coefficient used to determine whether a bunch of not background pixels can be considered as a silhouette.
     * <p>
     * Basic value 500 means, that minimum silhouette size is 0.5% of the image size.
     */
    private final static int MINIMUM_SILHOUETTE_SIZE_COEFFICIENT = 500;

    private SilhouettesSearcher() {
    }

    /**
     * Finds silhouettes in the pixel matrix using breadth-first search.
     *
     * @param matrix          matrix containing the image pixels
     * @param imageSize       total number of pixels in the image
     * @param backgroundColor color representing the image background
     * @return number of silhouettes found in the image
     */
    public static int findSilhouettes(PixelObject[][] matrix, int imageSize, int backgroundColor) {
        int silhouettes = 0;
        int minimumSilhouetteSize = imageSize / MINIMUM_SILHOUETTE_SIZE_COEFFICIENT;
        for (PixelObject[] pixelObjects : matrix) {
            for (PixelObject tokenPixel : pixelObjects) {
                silhouettes = silhouettesSearch(tokenPixel, silhouettes, minimumSilhouetteSize, backgroundColor);
            }
        }
        return silhouettes;
    }

    /**
     * Checks whether the pixel is unvisited and is non-background,
     * and if true, gets a silhouette from this pixel using breadth-first search.
     *
     * @param pixel               current pixel
     * @param silhouettes         current number of detected silhouettes
     * @param minimumSize         minimum size required for a component
     *                            to be considered a silhouette
     * @param backgroundColor     color representing the image background
     * @return updated number of detected silhouettes
     */
    private static int silhouettesSearch(PixelObject pixel, int silhouettes, int minimumSize, int backgroundColor) {
        if (!pixel.isVisited() && pixel.getColor() != backgroundColor) {
            int silhouetteSize = getSilhouetteSize(pixel, backgroundColor);
            if (silhouetteSize >= minimumSize) {
                silhouettes++;
            }
        }
        pixel.setVisited(true);
        return silhouettes;
    }

    /**
     * Finds the size of a silhouette using
     * breadth-first search.
     *
     * @param pixel           starting pixel
     * @param backgroundColor int value of the image background color
     * @return number of pixels in the silhouette
     */
    private static int getSilhouetteSize(PixelObject pixel, int backgroundColor) {
        int silhouetteSize = 1;
        ArrayDeque<PixelObject> deque = new ArrayDeque<>();
        pixel.setVisited(true);
        deque.add(pixel);
        while (!deque.isEmpty()) {
            PixelObject tokenPixel = deque.pollFirst();
            for (PixelObject neighbour : tokenPixel.getNeighborsList()) {
                if (checkNeighbour(neighbour, backgroundColor)) {
                    neighbour.setVisited(true);
                    deque.add(neighbour);
                    silhouetteSize++;
                }
            }
        }
        return silhouetteSize;
    }

    /**
     * Checks whether a neighboring pixel can be added to the current
     * connected component.
     *
     * @param neighbour       neighboring pixel to check
     * @param backgroundColor color representing the image background
     * @return {@code true} if the neighbor exists, has not been visited,
     *         and does not have the background color; {@code false} otherwise
     */
    private static boolean checkNeighbour(PixelObject neighbour, int backgroundColor) {
        return neighbour != null
                && !neighbour.isVisited()
                && neighbour.getColor() != backgroundColor;
    }
}

