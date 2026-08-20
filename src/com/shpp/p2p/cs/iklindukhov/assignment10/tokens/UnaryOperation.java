package com.shpp.p2p.cs.iklindukhov.assignment10.tokens;

/**
 * Functional interface for unary operations.
 */
public interface UnaryOperation {
    /**
     * Performs an operation on a single operand.
     *
     * @param a operand of the operation
     * @return result of the operation
     */
    public double calculate(double a);
}
