package com.shpp.p2p.cs.iklindukhov.assignment12;

import java.util.ArrayList;
import java.util.List;

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
     * Reference to the upperRight neighboring pixel.
     */
    private PixelObject rightUpper;
    /**
     * Reference to the lowerRight neighboring pixel.
     */
    private PixelObject rightLower;
    /**
     * Reference to the lowerLeft neighboring pixel.
     */
    private PixelObject leftLower;
    /**
     * Reference to the upperLeft neighboring pixel.
     */
    private PixelObject leftUpper;


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

    public void setRightUpper(PixelObject rightUpper) {
        this.rightUpper = rightUpper;
    }

    public void setRightLower(PixelObject rightDown) {
        this.rightLower = rightDown;
    }

    public void setLeftLower(PixelObject leftDown) {
        this.leftLower = leftDown;
    }

    public void setLeftUpper(PixelObject leftUpper) {
        this.leftUpper = leftUpper;
    }

    public List<PixelObject> getNeighborsList() {
        List<PixelObject> list = new ArrayList<>();
        list.add(right);
        list.add(down);
        list.add(up);
        list.add(left);
        list.add(rightUpper);
        list.add(rightLower);
        list.add(leftLower);
        list.add(leftUpper);
        return list;
    }

    public int getColor() {
        return color;
    }
}
