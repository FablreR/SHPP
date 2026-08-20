package com.shpp.p2p.cs.iklindukhov.assignment10.tokens;

import java.util.HashMap;

/**
 * Contains all operators and functions required for this calculator
 */
public enum Operator {
    PLUS("+", 1, Double::sum),
    MINUS("-", 1, (a, b) -> a - b),
    DIVIDE("/", 2, (a, b) -> a/b),
    MULTIPLY("*", 2, (a, b) -> a*b),
    POWER("^", 3, Math::pow),
    ROOT_SQUARE("sqrt", 4, (a) -> {
        if (a < 0) {
            throw new IllegalArgumentException("Square root of the negative value: sqrt " + a);
        } else {
            return Math.sqrt(a);
        }}),
    SINUS("sin", 4, Math::sin),
    COSINUS("cos", 4, Math::cos),
    TANGENS("tan", 4, Math::tan),
    ATANGENS("atan", 4, Math::atan),
    LOG_DECIMAL("log10", 4, Math::log10),
    LOG_SQUARE("log2", 4, (a) -> Math.log(a) / Math.log(2));

    /**
     * Stores all operators indexed by their string tokens.
     */
    private final static HashMap<String, Operator> operators = new HashMap <String, Operator>();

    /**
     * String value of each operator
     */
    private final String token;
    /**
     * Operation`s performing priority
     */
    private final int priority;
    /**
     * Binary operation performed by this operator.
     */
    private final BinaryOperation binaryOperation;
    /**
     * Unary operation performed by this operator.
     */
    private final UnaryOperation unaryOperation;

    /**
     * Indicates whether this operator is unary.
     */
    private final boolean isUnary;

    static {
        for (Operator operator : Operator.values()) {
            operators.put(operator.getToken(), operator);
        }
    }

    /**
     * Binary operator constructor
     *
     * @param token           String value of the operator
     * @param priority        Operation`s performing priority
     * @param binaryOperation what type of operation must be performed to this operator
     */
    Operator(String token, int priority, BinaryOperation binaryOperation) {
        this.token = token;
        this.priority = priority;
        this.binaryOperation = binaryOperation;
        this.unaryOperation = null;
        this.isUnary = false;
    }

    /**
     * Unary operator constructor
     *
     * @param token          String value of the operator
     * @param priority       Operation`s performing priority
     * @param unaryOperation what type of operation must be performed to this operator
     */
    Operator(String token, int priority, UnaryOperation unaryOperation) {
        this.token = token;
        this.priority = priority;
        this.unaryOperation = unaryOperation;
        this.binaryOperation = null;
        this.isUnary = true;
    }

    /**
     * Gets string value of the operator
     *
     * @return string value of the operator
     */
    public String getToken() {
        return token;
    }

    /**
     * Gets an operation priority of selected operator
     *
     * @return priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Performs the binary operation using two operands.
     *
     * @param a left operand
     * @param b right operand
     * @return result of the binary operation
     */
    public double calculate(double a, double b) {
        return binaryOperation.calculate(a, b);
    }

    /**
     * Performs the unary operation using single operand.
     *
     * @param a left operand
     * @return result of the unary operation
     */
    public double calculate(double a) {
        return unaryOperation.calculate(a);
    }

    /**
     * Gets token from database if such is present
     *
     * @param token the token we are checking for being the operator
     * @return operator if match is found and null otherwise
     */
    public static Operator getOperator(String token) {
        return operators.get(token);
    }

    /**
     * Checks if token is an operator
     *
     * @param token the token we are checking for being the operator
     * @return true if token is operator and otherwise false
     */
    public static boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    /**
     * Indicates whether this operator is unary.
     *
     * @return true if the operator is unary,
     *         false if it is binary
     */
    public boolean isUnary() {
        return this.isUnary;
    }
}
