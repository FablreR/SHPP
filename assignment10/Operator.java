package com.shpp.p2p.cs.iklindukhov.assignment10;

/**
 * Contains all operators and functions required for this calculator
 */
public enum Operator {
    PLUS("+", 1),
    MINUS("-", 1),
    DIVIDE("/", 2),
    MULTIPLY("*", 2),
    POWER("^", 3),
    ROOT_SQUARE("sqrt", 4),
    PARENTHESIS_OPEN("(", 0),
    PARENTHESIS_CLOSE(")", 0),
    SINUS("sin", 4),
    COSINUS("cos", 4),
    TANGENS("tan", 4),
    ATANGENS("atan", 4),
    LOG_DECIMAL("log10", 4),
    LOG_SQUARE("log2", 4);

    /**
     * String value of each operator
     */
    private final String TOKEN;
    /**
     * Operation`s performing priority
     */
    private final int PRIORITY;

    /**
     *
     * @param token    string value of each operator
     * @param priority operation`s performing priority
     */
    Operator(String token, int priority) {
        this.TOKEN = token;
        this.PRIORITY = priority;
    }

    /**
     * Gets string value of the operator
     *
     * @return string value of the operator
     */
    public String getToken() {
        return TOKEN;
    }

    /**
     * Compares token to value of each operator
     * and, if a match is found, returns that operator
     *
     * @param token the token we are checking for being the operator
     * @return operator if match is found and null otherwise
     */
    public static Operator getOperator(String token) {
        Operator[] operators = Operator.values();
        for (Operator operator : operators) {
            if (operator.getToken().equals(token)) {
                return operator;
            }
        }
        return null;
    }

    /**
     * Checks if token is an operator
     *
     * @param token the token we are checking for being the operator
     * @return true if token is operator and otherwise false
     */
    public static boolean isOperator(String token) {
        return getOperator(token) != null;
    }


    /**
     * Gets an operation priority of selected operator
     *
     * @return priority
     */
    public int getPriority() {
        return PRIORITY;
    }

}
