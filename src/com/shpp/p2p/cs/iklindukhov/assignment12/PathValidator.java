package com.shpp.p2p.cs.iklindukhov.assignment12;

import java.util.Arrays;

/**
 * Validates a file path provided as a command-line argument.
 * The validator checks the path, the file name and the format and uses
 * {@value #BASE_FILE_NAME} if no other name is provided,
 * and {@value #BASE_FILE_PATH} as a basic file path.
 */
public class PathValidator {
    /**
     * Delimiter is used to separate file name from file format.
     */
    private static final String DELIMITER = ".";

    /**
     * This name is used if no other name was selected
     */
    private static final String BASE_FILE_NAME = "test.jpg";

    /**
     * This path is used if no other path was selected
     */
    private static final String BASE_FILE_PATH = "extra/images/";

    private PathValidator() {
    }

    /**
     * Validates the command-line arguments and returns a valid file path.
     * </p>
     * If no argument or an empty argument is provided, {@value #BASE_FILE_PATH} + {@value #BASE_FILE_NAME} is returned.
     *
     * @param args array with expected file path in {@code args[0]}
     * @return validated file path
     * @throws IllegalArgumentException if the file name or format is invalid
     */
    public static String validatePath(String[] args) {
        if (isPathEmpty(args)) {
            return BASE_FILE_PATH + BASE_FILE_NAME;
        } else {
            String fileName = args[0];
            String trimmedName = fileName.replaceAll("\\s+", "");
            return trimmedName.isEmpty() ? BASE_FILE_PATH + BASE_FILE_NAME : BASE_FILE_PATH + validateFileFormat(trimmedName);
        }
    }

    private static boolean isPathEmpty(String[] args) {
        return args.length == 0;
    }

    /**
     * Validates the file format
     *
     * @param fileName fileName to validate
     * @return validated file name with a normalized file format
     * @throws IllegalArgumentException if the filename or file format is invalid
     */
    private static String validateFileFormat(String fileName) {
        String fileFormat = fileName.substring(fileName.lastIndexOf(DELIMITER) + 1).toLowerCase();
        if (!Formats.isFormateValid(fileFormat)) {
            throw new IllegalArgumentException(
                    fileFormat
                            + " is not supported \n" +
                            "Supported file formats: "
                            + Arrays.toString(Formats.values()));
        }
        return fileName;
    }
}
