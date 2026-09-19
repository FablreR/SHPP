package com.shpp.p2p.cs.iklindukhov.assignment11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Finds the image by file path
 */
public class ImageSearcher {

    /**
     * Finds the image by the file path
     *
     * @param path image path
     * @return image at selected path
     * @throws IllegalArgumentException if image wasn`t found
     */
    public static BufferedImage findImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException _) {
            throw new IllegalArgumentException("No image was found at the selected path: " + path);
        }
    }

}
