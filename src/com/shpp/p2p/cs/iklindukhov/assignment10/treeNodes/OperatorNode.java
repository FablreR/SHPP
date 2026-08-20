package com.shpp.p2p.cs.iklindukhov.assignment10.treeNodes;

import com.shpp.p2p.cs.iklindukhov.assignment10.tokens.Operator;

/**
 * Represents a node in the formula tree that performs an operation.
 * The node can represent either unary or binary operation.
 */
public class OperatorNode implements ICalc {
    /**
     * Left operand
     */
    private final ICalc left;
    /**
     * Right operand. Is null for unary operations
     */
    private final ICalc right;
    /**
     * Operator, which determines mathematical operation to be performed
     */
    private final Operator operator;

    /**
     * Creates a node for a binary operation.
     *
     * @param left left operand
     * @param right right operand
     * @param operator operator to perform
     */
    public OperatorNode(ICalc right, ICalc left, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    /**
     * Creates a node for a unary operation.
     *
     * @param left operand of the operation
     * @param operator operator to perform
     */
    public OperatorNode(ICalc left, Operator operator) {
        this.left = left;
        this.right = null;
        this.operator = operator;
    }

    /**
     * Calculates the result of the operation.
     * Uses only the left operand for unary operations
     * and both operands for binary operations.
     *
     * @return result of the operation
     */
    @Override
    public double calculate() {
        return right == null ? operator.calculate(left.calculate()) :
                operator.calculate(left.calculate(), right.calculate());
    }
}
