package com.shpp.p2p.cs.iklindukhov.assignment11;

/**
 * Represents a pixel of an image and its neighboring pixels.
 * Each pixel stores its color, visited state and references
 * to its four neighboring pixels.
 */
public class PixelObject {
    /**
     * Color of the pixel represented as an ARGB integer.
     */
    private final int color;
    /**
     * Indicates whether the pixel has already been visited during the search.
     */
    private boolean isVisited = false;
    /**
     * Reference to the right neighboring pixel.
     */
    private PixelObject right;
    /**
     * Reference to the lower neighboring pixel.
     */
    private PixelObject down;
    /**
     * Reference to the left neighboring pixel.
     */
    private PixelObject left;
    /**
     * Reference to the upper neighboring pixel.
     */
    private PixelObject up;

    /**
     * Creates a pixel object with the specified color.
     *
     * @param color ARGB color of the pixel
     */
    PixelObject(int color) {
        this.color = color;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setLeftNeighbor(PixelObject left) {
        this.left = left;
    }

    public void setRightNeighbor(PixelObject right) {
        this.right = right;
    }

    public void setLowerNeighbor(PixelObject down) {
        this.down = down;
    }

    public void setUpperNeighbor(PixelObject up) {
        this.up = up;
    }

    public PixelObject getUpperNeighbor() {
        return up;
    }

    public PixelObject getLowerNeighbor() {
        return down;
    }

    public PixelObject getLeftNeighbor() {
        return left;
    }

    public PixelObject getRightNeighbor() {
        return right;
    }

    public int getColor() {
        return color;
    }

}
