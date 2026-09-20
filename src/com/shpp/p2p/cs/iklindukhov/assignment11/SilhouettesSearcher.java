package com.shpp.p2p.cs.iklindukhov.assignment11;

import java.util.ArrayList;

/**
 * Searches for silhouettes in a pixel matrix.
 * <p>
 * A silhouette is a connected group of pixels whose colors
 * are similar to each other and different from the background color.
 */
public class SilhouettesSearcher {

    /**
     * List of silhouettes found in the image.
     */
    private ArrayList<Silhouette> silhouettes = new ArrayList<>();
    private static final int MINIMUM_SILHOUETTE_SIZE = 40;

    /**
     * Creates a silhouette searcher and finds all silhouettes in the matrix.
     *
     * @param matrix pixel matrix of the image
     * @param backgroundColor background color of the image
     */
    SilhouettesSearcher(PixelObject[][] matrix, int backgroundColor) {
        silhouettes = findSilhouettes(matrix, backgroundColor);
    }

    /**
     * Searches the entire pixel matrix for silhouettes.
     *
     * @param matrix pixel matrix of the image
     * @param backgroundColor background color used to distinguish silhouettes
     * @return list of found silhouettes
     */
    private ArrayList <Silhouette> findSilhouettes(PixelObject[][] matrix, int backgroundColor) {
        ArrayList <Silhouette> silhouettes = new ArrayList<>();
        for (PixelObject[] pixelObjects : matrix) {
            for (PixelObject tokenPixel : pixelObjects) {
                searching (tokenPixel, silhouettes, backgroundColor);
            }
        }
        return silhouettes;
    }

    /**
     * Checks whether the specified pixel belongs to the background
     * or starts a search for a new silhouette.
     *
     * @param pixel pixel being checked
     * @param silhouettes list to which a found silhouette is added
     * @param backgroundColor background color of the image
     */
    private void searching(PixelObject pixel, ArrayList <Silhouette> silhouettes, int backgroundColor) {
        if (!pixel.isVisited()) {
            if (ColorsManager.areColorsSimilar(pixel.getColor(), backgroundColor)) {
                pixel.setVisited(true);
            } else {
                int silhouetteColor = pixel.getColor();
                int silhouetteSize = silhouetteSearch(pixel, silhouetteColor, 0);
                if (silhouetteSize >= MINIMUM_SILHOUETTE_SIZE) {
                silhouettes.add(new Silhouette(silhouetteColor, silhouetteSize));
                }
            }
        }
    }


    /**
     * Recursively searches for all connected pixels belonging to the same silhouette.
     * The search checks the right, lower, left and upper neighbors of each pixel.
     *
     * @param pixel current pixel
     * @param silhouetteColor color used to identify the silhouette
     * @param size current number of pixels in the silhouette
     * @return total number of pixels found in the silhouette
     */
    private int silhouetteSearch(PixelObject pixel, int silhouetteColor, int size) {
        if (pixel != null && ColorsManager.areColorsSimilar(pixel.getColor(), silhouetteColor) && !pixel.isVisited()) {
            pixel.setVisited(true);
            size += 1;
            PixelObject neighbor = pixel.getRightNeighbor();
            size += silhouetteSearch(neighbor, silhouetteColor, 0);
            neighbor = pixel.getLowerNeighbor();
            size += silhouetteSearch(neighbor, silhouetteColor, 0);
            neighbor = pixel.getLeftNeighbor();
            size += silhouetteSearch(neighbor, silhouetteColor, 0);
            neighbor = pixel.getUpperNeighbor();
            size += silhouetteSearch(neighbor, silhouetteColor, 0);
        }
        return size;
    }

    /**
     * Returns the list of silhouettes found in the image.
     *
     * @return list of silhouettes
     */
    public ArrayList<Silhouette> getSilhouettesArray() {
        return silhouettes;
    }
}

