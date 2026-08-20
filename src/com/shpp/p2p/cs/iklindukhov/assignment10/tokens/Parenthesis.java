package com.shpp.p2p.cs.iklindukhov.assignment10.tokens;

import java.util.HashMap;

/**
 * Represents parenthesis in a formula
 */
public enum Parenthesis {
    PARENTHESIS_OPEN("("),
    PARENTHESIS_CLOSE(")");

    /**
     * Small database with parenthesis
     */
    private final static HashMap<String, Parenthesis> parenthesis = new HashMap <String, Parenthesis>();

    static {
        for (Parenthesis parenthesisOperator : Parenthesis.values()) {
            parenthesis.put(parenthesisOperator.getToken(), parenthesisOperator);
        }
    }

    /**
     * String value of the parenthesis
     */
    private final String token;

    /**
     *
     * @param token string value of each parenthesis
     */
    Parenthesis(String token) {
        this.token = token;
    }

    /**
     * Gets string value of the parenthesis
     *
     * @return string value of the parenthesis
     */
    public String getToken() {
        return token;
    }

    /**
     * Compares token to value of each parenthesis
     * and, if a match is found, returns that parenthesis
     *
     * @param token the token we are checking for being the parenthesis
     * @return parenthesis if match is found and null otherwise
     */
    public static Parenthesis getParenthesis(String token) {
        return parenthesis.get(token);
    }

    /**
     * Checks if token is a parenthesis
     *
     * @param token the token we are checking for being the parenthesis
     * @return true if token is parenthesis and otherwise false
     */
    public static boolean isParenthesis(String token) {
        return parenthesis.containsKey(token);
    }


}