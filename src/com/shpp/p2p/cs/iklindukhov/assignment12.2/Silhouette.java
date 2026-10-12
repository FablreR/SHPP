package com.shpp.p2p.cs.iklindukhov.assignment12;

import java.util.ArrayList;

/**
 * Represents a silhouette as a collection of connected pixels.
 *
 * @param pixels list of pixels belonging to the silhouette
 */
public record Silhouette(ArrayList<PixelObject> pixels) {
    /**
     * Returns the number of pixels in the silhouette.
     *
     * @return number of pixels
     */
    public int getSize() {
        return pixels.size();
    }
}
