package com.shpp.p2p.cs.iklindukhov.assignment11;

import java.util.HashMap;

/**
 * Image formats supported by the application.
 */
public enum Formats {

    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png");
    /**
     * Formats name.
     */
    private final String formatName;
    /**
     * Stores supported image formats and their corresponding enum values.
     */
    private static final HashMap<String, Formats> formatsDatabase = new HashMap<>();

    static {
        for (Formats format : Formats.values()) {
            formatsDatabase.put(format.getFormatName(), format);
        }
    }

    /**
     * Creates a format with the specified name.
     *
     * @param formatName name of the image format
     */
    Formats(String formatName) {
        this.formatName = formatName;
    }

    /**
     * Returns the name of the image format.
     *
     * @return image format name
     */
    public String getFormatName() {
        return formatName;
    }

    /**
     * Checks whether the specified image format is supported.
     *
     * @param format format name to check
     * @return {@code true} if the format is supported, otherwise {@code false}
     */
    public static boolean isFormateValid(String format) {
        return formatsDatabase.containsKey(format);
    }

}
