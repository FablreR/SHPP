package com.shpp.p2p.cs.iklindukhov.assignment10.tokens;

/**
 * Functional interface for binary operations.
 */
public interface BinaryOperation {
    /**
     * Performs an operation on two operands.
     *
     * @param a left operand of the operation
     * @param b right operand of the operation
     * @return result of the operation
     */
    public double calculate(double a, double b);

}
