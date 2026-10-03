package com.shpp.p2p.cs.iklindukhov.assignment12;

import java.awt.image.BufferedImage;

/**
 * Processes an image and creates a matrix of its pixels.
 */
public class ImageProcessor {

    private ImageProcessor() {
    }

    /**
     * Returns the matrix of pixels.
     *
     * @return pixel matrix
     */
    public static PixelObject[][] getPixelMatrix(BufferedImage image) {
        return createPixelMatrix(image);
    }

    /**
     * Returns the matrix of pixels.
     *
     * @return pixel matrix
     */
    public static int getImageSize(BufferedImage image) {
        return image.getWidth() * image.getHeight();
    }

    /**
     * Creates a matrix of pixels and establishes connections
     * between neighboring pixels.
     * <p>
     * Fulfills colours database stored in {@link ColorsManager}.
     *
     * @param image from which the pixel matrix is created
     * @return matrix containing the image pixels
     */
    private static PixelObject[][] createPixelMatrix(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        PixelObject[][] matrix = new PixelObject[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                PixelObject tokenPixel = new PixelObject(image.getRGB(x, y));
                matrix[y][x] = tokenPixel;
                if (x != 0) {
                    setLeftRightConnections(y, x, matrix, tokenPixel);
                }
                if (y != 0) {
                    setUpDownConnections(y, x, matrix, tokenPixel);
                }
                if (x != 0 && y != 0) {
                    setLeftRightDiagonalConnections(y, x, matrix, tokenPixel);
                }
                if (y != 0 && x < width - 1 && y < height - 1) {
                    setRightLeftDiagonalConnections(y, x, matrix, tokenPixel);
                }
            }
        }
        return matrix;
    }

    /**
     * Establishes connections between the current pixel and its left and right neighbors.
     *
     * @param y      row index of the current pixel
     * @param x      column index of the current pixel
     * @param matrix matrix containing all pixels
     * @param token  current pixel
     */
    private static void setLeftRightConnections(int y, int x, PixelObject[][] matrix, PixelObject token) {
        PixelObject leftNeighbor = matrix[y][x - 1];
        token.setLeftNeighbor(leftNeighbor);
        leftNeighbor.setRightNeighbor(token);
    }

    /**
     * Establishes connections between the current pixel and its upper and lower neighbors.
     *
     * @param y      row index of the current pixel
     * @param x      column index of the current pixel
     * @param matrix matrix containing all pixels
     * @param token  current pixel
     */
    private static void setUpDownConnections(int y, int x, PixelObject[][] matrix, PixelObject token) {
        PixelObject upperNeighbor = matrix[y - 1][x];
        token.setUpperNeighbor(upperNeighbor);
        upperNeighbor.setLowerNeighbor(token);
    }

    /**
     * Establishes connections between the current pixel and its
     * upper-left and lower-right diagonal neighbors.
     *
     * @param y          row index of the current pixel
     * @param x          column index of the current pixel
     * @param matrix     matrix containing all pixels
     * @param tokenPixel current pixel
     */
    private static void setLeftRightDiagonalConnections(int y, int x, PixelObject[][] matrix, PixelObject tokenPixel) {
        PixelObject neighbour = matrix[y - 1][x - 1];
        tokenPixel.setLeftUpper(neighbour);
        neighbour.setRightLower(tokenPixel);
    }

    /**
     * Establishes connections between the current pixel and its
     * upper-right and lower-left diagonal neighbors.
     *
     * @param y          row index of the current pixel
     * @param x          column index of the current pixel
     * @param matrix     matrix containing all pixels
     * @param tokenPixel current pixel
     */
    private static void setRightLeftDiagonalConnections(int y, int x, PixelObject[][] matrix, PixelObject tokenPixel) {
        PixelObject neighbour = matrix[y - 1][x + 1];
        tokenPixel.setRightUpper(neighbour);
        neighbour.setLeftLower(tokenPixel);
    }





}