package com.shpp.p2p.cs.iklindukhov.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Finds the image by file path
 */
public class ImageSearcher {

    private ImageSearcher() {
    }

    /**
     * Finds the image by the file path
     *
     * @param path image path
     * @return image at selected path
     * @throws IllegalArgumentException if the image wasn`t found
     */
    public static BufferedImage findImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            if (image == null) {
                throw new IllegalArgumentException("File is null. Probably file format is not valid");
            }
            return image;
        } catch (IOException _) {
            throw new IllegalArgumentException("No image was found at the selected path");
        }
    }

}
