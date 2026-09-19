package com.shpp.p2p.cs.iklindukhov.assignment11;

import java.awt.image.BufferedImage;

/**
 * Processes an image and creates a matrix of its pixels.
 */
public class ImageProcessor {
    /**
     * Image being processed.
     */
    private final BufferedImage image;
    /**
     * Matrix containing all pixels of the image.
     */
    private final PixelObject[][] pixelMatrix;

    /**
     * Creates an image processor.
     *
     * @param image image to process
     */
    ImageProcessor(BufferedImage image, ColorsManager colorsManager) {
        this.image = image;
        pixelMatrix = createPixelMatrix(colorsManager);
    }

    /**
     * Creates a matrix of pixels and establishes connections
     * between neighboring pixels.
     * <p>
     * Fulfills colours database stored in {@link ColorsManager}.
     *
     * @param colorProcessor collects pixel colors to database
     * @return matrix containing the image pixels
     */
    private PixelObject[][] createPixelMatrix(ColorsManager colorProcessor) {
        int width = image.getWidth();
        int height = image.getHeight();
        PixelObject[][] matrix = new PixelObject[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                PixelObject tokenPixel = createPixel(x, y);
                matrix[y][x] = tokenPixel;
                if (x > 0) {
                    setLeftRightConnections(y, x, matrix, tokenPixel);
                }
                if (y > 0) {
                    setUpDownConnections(y, x, matrix, tokenPixel);
                }
                colorProcessor.addPixelColorToDataBase(tokenPixel.getColor());
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
    private void setLeftRightConnections(int y, int x, PixelObject[][] matrix, PixelObject token) {
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
    private void setUpDownConnections(int y, int x, PixelObject[][] matrix, PixelObject token) {
        PixelObject upperNeighbor = matrix[y - 1][x];
        token.setUpperNeighbor(upperNeighbor);
        upperNeighbor.setLowerNeighbor(token);
    }

    /**
     * Creates a pixel object from the specified image coordinates.
     *
     * @param x horizontal coordinate of the pixel
     * @param y vertical coordinate of the pixel
     * @return pixel object containing the pixel color
     */
    private PixelObject createPixel(int x, int y) {
        return new PixelObject(image.getRGB(x, y));
    }

    /**
     * Returns the matrix of pixels.
     *
     * @return pixel matrix
     */
    public PixelObject[][] getPixelMatrix() {
        return pixelMatrix;
    }
}
