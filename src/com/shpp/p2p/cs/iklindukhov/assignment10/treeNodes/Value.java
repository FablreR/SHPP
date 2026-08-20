package com.shpp.p2p.cs.iklindukhov.assignment10.treeNodes;

/**
 * Represents a numeric value in a formula.
 */
public class Value implements ICalc {
    /**
     * Numeric value
     */
    private final double value;

    /**
     * Creates a value with the specified number.
     *
     * @param value numeric value
     */
    public Value(double value) {
        this.value = value;
    }

    /**
     * Returns the stored numeric value.
     *
     * @return numeric value
     */
    @Override
    public double calculate() {
        return value;
    }
}
