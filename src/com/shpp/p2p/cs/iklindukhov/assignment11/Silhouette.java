package com.shpp.p2p.cs.iklindukhov.assignment11;

/**
 * Represents a silhouette found in the image.
 * A silhouette is described by its color and the number
 * of pixels belonging to it.
 */
public class Silhouette {
    /**
     * Color of the silhouette represented as an ARGB integer.
     */
    private final int silhouetteColor;
    /**
     * Number of pixels belonging to the silhouette.
     */
    private final int silhouetteSize;

    /**
     * Creates a silhouette with the specified color and size.
     *
     * @param color color of the silhouette
     * @param size  number of pixels in the silhouette
     */
    Silhouette(int color, int size) {
        silhouetteColor = color;
        silhouetteSize = size;
    }

    public int getColor() {
        return silhouetteColor;
    }

    public int getSize() {
        return silhouetteSize;
    }
}
