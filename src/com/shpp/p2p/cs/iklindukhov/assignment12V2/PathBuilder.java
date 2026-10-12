package com.shpp.p2p.cs.iklindukhov.assignment12V2;

/**
 * Builds a file path using the name and the path.
 * <p>
 * Name is provided as a command-line argument {@code args[0]},
 * otherwise is equal to {@code BASE_FILE_NAME} constant from {@link Assignment12Part1}
 * <p>
 * Path is equal to {@code BASE_FILE_PATH} constant from {@link Assignment12Part1}
 */
public class PathBuilder {
    private PathBuilder() {
    }

    /**
     * Returns the full path to the file. If no path is provided or the
     * provided path contains only whitespace, the the {@code defaultPath} + {@code defaultName} is returned.
     *
     * @param args        array with expected file path in {@code args[0]}
     * @param defaultPath default directory of the file
     * @param defaultName default file name
     * @return full path to the file
     */
    public static String buildFilePath(String[] args, String defaultPath, String defaultName) {
        if (isPathEmpty(args)) {
            return defaultPath + defaultName;
        } else {
            String fileName = args[0];
            String trimmedName = fileName.replaceAll("\\s+", "");
            return trimmedName.isEmpty() ? defaultPath + defaultName : defaultPath + fileName;
        }
    }

    /**
     * Checks whether the command-line arguments array is empty.
     *
     * @param args command-line arguments
     * @return {@code true} if the array contains no arguments, otherwise {@code false}
     */
    private static boolean isPathEmpty(String[] args) {
        return args.length == 0;
    }
}
