package com.shpp.p2p.cs.iklindukhov.assignment10;

import com.shpp.p2p.cs.iklindukhov.assignment10.tokens.Operator;
import com.shpp.p2p.cs.iklindukhov.assignment10.treeNodes.ICalc;
import com.shpp.p2p.cs.iklindukhov.assignment10.treeNodes.OperatorNode;
import com.shpp.p2p.cs.iklindukhov.assignment10.treeNodes.Parameter;
import com.shpp.p2p.cs.iklindukhov.assignment10.treeNodes.Value;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Tree consisting of nodes. Contains postfix formula
 */
public class FormulaTree {

    /**
     * The first node in the tree
     */
    private final ICalc root;

    /**
     * Constructs tree-structure from postfix formula
     *
     * @param formula postfix formula
     */
    public FormulaTree(ArrayList<String> formula, ParametersDataBase parameters) {
        LinkedList<ICalc> list = new LinkedList<>();
        for (String token : formula) {
            if (Operator.isOperator(token)) {
                Operator operator = Operator.getOperator(token);
                ICalc right = list.pop();
                if (operator.isUnary()) {
                    list.push(new OperatorNode(right, operator));
                } else {
                    ICalc left = list.pop();
                    list.push(new OperatorNode(right, left, operator));
                }
            } else if (isNumber(token)) {
                list.push(new Value(Double.parseDouble(token)));
            } else {
                list.push(new Parameter(token, parameters));
            }
        }
        this.root = list.pop();

    }

    /**
     * Gets starting point of tree calculation
     *
     * @return root of the tree
     */
    public ICalc getRoot() {
        return root;
    }

    /**
     * Checks whether the specified string represents a valid numeric value.
     *
     * @param value the string to check
     * @return true if the value can be parsed as a double;
     * false otherwise
     */
    private static boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
