package com.shpp.p2p.cs.iklindukhov.assignment11;

import java.util.Arrays;

/**
 * Validates a file path provided as a command-line argument.
 * The validator checks the file name and format and uses
 * {@value #BASE_FILE_PATH} if no path is provided.
 */
public class PathValidator {
    /**
     * {@code Do not change this value}
     * <p>
     * The code is designed to work only with {@code "."}.
     * <p>
     * Delimiter is used to separate file name from file format.
     */
    private static final String DELIMITER = "\\.";

    /**
     * {@code Do not change this value}
     * <p>
     * Current code doesnt support paths with more than 1 delimiter,
     * and thus with more than 2 parts
     * <p>
     * Number of parts in file path
     */
    private static final int PARTS_NUMBER = 2;

    /**
     * {@code Do not change this value}
     * <p>
     * Current code doesnt support paths with more than 1 delimiter,
     * and thus with more than 2 parts
     * <p>
     * Number of delimiters in file path
     */
    private static final int DELIMITERS_NUMBER = PARTS_NUMBER - 1;

    /**
     * This path is used if no other path was selected
     */
    private static final String BASE_FILE_PATH = "test.jpg";

    /**
     * Validates the command-line arguments and returns a valid file path.
     * </p>
     * If no argument or an empty argument is provided, {@value #BASE_FILE_PATH} is returned.
     *
     * @param args array with expected file path in {@code args[0]}
     * @return validated file path
     * @throws IllegalArgumentException if the file name or format is invalid
     */
    public static String validatePath(String[] args) {
        if (isPathEmpty(args)) {
            return BASE_FILE_PATH;
        } else {
            String path = args[0];
            String trimmedPath = path.replaceAll("\\s+", "");
            return trimmedPath.isEmpty() ? BASE_FILE_PATH : validate(trimmedPath);
        }
    }

    private static boolean isPathEmpty(String[] args) {
        return args.length == 0;
    }

    /**
     * Validates the file name and file format
     *
     * @param path file path to validate
     * @return validated file name with a normalized file format
     * @throws IllegalArgumentException if the file name or format is invalid
     */
    private static String validate(String path) {
        String[] array = path.split(DELIMITER);

        if (array.length != PARTS_NUMBER) {
            throw new IllegalArgumentException("More or less than "
                    + DELIMITERS_NUMBER
                    + " delimiter (\"" + "." + "\")" + " present");
        }

        String fileName = array[0];
        String fileFormat = array[1].toLowerCase();

        if (!Formats.isFormateValid(fileFormat)) {
            throw new IllegalArgumentException(
                    fileFormat
                            + " is not supported \n" +
                            "Supported file formats: "
                            + Arrays.toString(Formats.values()));
        }

        if (!fileName.isEmpty()) {
            return fileName + "." + fileFormat;
        } else {
            throw new IllegalArgumentException("File name is empty");
        }
    }
}
